package com.hyl.hylbase.utils.imgUpload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import com.android.volley.VolleyError;
import com.hyl.hylbase.R;
import com.hyl.hylbase.utils.L;

/**
 * 头像相关操作
 *
 *
 */
public class CustomHeadPortrait {

	private Context context;
	private FragmentManager fragmentManager;
	private String headImageName;
	public static final int PHOTO_REQUEST_CAMERA = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	public static final int PHOTO_REQUEST_CUT = 3;// 结果
	 Fragment fragment;
//	private ImageView mFace;

	public int outputX=200,outputY=200;//裁剪后的图片大小
	private static final String HEAD_PHOTO_FILE_NAME = "head_portrait.jpg"; // 未上传成功时头像保存在本地
	public static final String PHOTO_FILE_NAME = "head_photo.jpg"; // 剪切后的头像
	private File tempFile;


	public CustomHeadPortrait(Fragment fragment) {
		this.context = fragment.getContext();
		this.fragmentManager = fragment.getFragmentManager();
//		this.mFace = mFace;
		this.fragment=fragment;
	}


	/**
	 * 获取本地头像
	 * @param imgPath
	 * @return
	 */
	private Bitmap getDiskBitmap(String imgPath) {
		Bitmap bitmap = null;
		try {
			File file = new File(imgPath);
			if (file.exists()) {
				bitmap = BitmapFactory.decodeFile(imgPath);
			}
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	public void show() {
		context.setTheme(R.style.ActionSheetStyleIOS7);
		ActionSheet.createBuilder(context, fragmentManager)
				.setCancelButtonTitle("取消").setOtherButtonTitles("拍照", "从手机相册选择")
				.setCancelableOnTouchOutside(true)
				.setListener(new ActionSheet.ActionSheetListener() {

					@Override
					public void onDismiss(ActionSheet actionSheet,
										  boolean isCancel) {
					}

					@Override
					public void onOtherButtonClick(ActionSheet actionSheet,
												   int index) {
						switch (index) {
							case 0:
								camera();
								break;

							case 1:
								gallery();
								break;

							default:
								break;
						}
					}

				}).show();
	}

	/*
	 * 拍照
	 */
	public void camera() {
		Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		// 判断存储卡是否可以用，可用进行存储
		if (hasSdcard()) {
			tempFile = new File(SDCardOperation.getSDPath(context), PHOTO_FILE_NAME);
			L.e(">>>>>>>","filePath:"+SDCardOperation.getSDPath(context));
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
		}
		fragment.startActivityForResult(intent,
				PHOTO_REQUEST_CAMERA);
	}

	/*
	 * 从相册获取
	 */
	public void gallery() {
		// 激活系统图库，选择一张图片
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType("image/*");
		fragment.startActivityForResult(intent,
				PHOTO_REQUEST_GALLERY);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == PHOTO_REQUEST_GALLERY) {

			if (data != null) {
				// 得到图片的全路径
				Uri uri = data.getData();
//				try {
//					Bitmap bitmap = MediaStore.Images.Media.getBitmap(fragment.getActivity().getContentResolver(), uri);
//				}catch (Exception e){
//					e.printStackTrace();
//				}
				crop(uri);
			}
		} else if (requestCode == PHOTO_REQUEST_CAMERA) {
			if (hasSdcard()) {
				tempFile = new File(SDCardOperation.getSDPath(context), PHOTO_FILE_NAME);
				crop(Uri.fromFile(tempFile));
			} else {
				Toast.makeText(context, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
			}

		} 
		else if (requestCode == PHOTO_REQUEST_CUT) {
			try {
				Bitmap bitmap = data.getParcelableExtra("data");
				Download.saveHeadFile(bitmap, "before.jpg");
//				compressImage(bitmap);
				Download.saveHeadFile(bitmap, "after.jpg");
				data.putExtra("data",bitmap);
//				this.mFace.setImageBitmap(bitmap);
//				Download.saveHeadFile(bitmap,HEAD_PHOTO_FILE_NAME);
				//  在回调里处理 1、保存图片到本地 2、上传头像
//				uploadImg(bitmap);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	 public void uploadImg(String url,Bitmap bitmap,ResponseListener listener){
		 	UploadApi api=new UploadApi(context);
		 	api.uploadImg(url,bitmap,listener) ;
	    }

	/**
	 * 剪切图片
	 * @param uri
	 */
	public void crop(Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
//		intent.putExtra("aspectX", 1);
//		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("scale", true);
		intent.putExtra("noFaceDetection", true);// 取消人脸识别
		intent.putExtra("return-data", true);// true:不返回uri，false：返回uri
		fragment.startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}

	public boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	private String getHeadImageForSP() {
		SharedPreferences sp = context.getSharedPreferences("headPortraitSP",
				Activity.MODE_PRIVATE);
		return sp.getString("headImage", headImageName);
	}

	private void setHeadImageToSP(String headImage) {
		SharedPreferences sp = context.getSharedPreferences("headPortraitSP",
				Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("headImage", headImage);
		editor.commit();
	}

	public static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();// 重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;// 每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
		return bitmap;
	}

}


