package com.hyl.hylbase.utils.imgUpload;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.hyl.hylbase.utils.L;

public class Download {


    public static InputStream getImageStream(String path) throws Exception{
    	L.e("tag", "path--->"+path);
        URL url = new URL(path);  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setConnectTimeout(60 * 1000);  
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            return conn.getInputStream();  
        }  
        return null;  
    } 
    
   
	public static void saveFile(Bitmap bm, String fileName) throws IOException {
//	    File dirFile = new File(NewsConfig.imagePath);
	    File dirFile = new File("");
	    if(!dirFile.exists()){
	        dirFile.mkdir();  
	    }
//	    File myCaptureFile = new File(NewsConfig.imagePath + fileName);
	    File myCaptureFile = new File("" + fileName);
	    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
	    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);  
	    bos.flush();  
	    bos.close();  
	} 
    
//    public void saveImgFile(Bitmap bm, String fileName) throws IOException {
//    	String imgPath = FileUtils.pathDir(mcontext, R.string.newsimage_dir);
//	    File dirFile = new File(imgPath);
//	    if(!dirFile.exists()){
//	        dirFile.mkdir();
//	    }
//	    File myCaptureFile = new File(imgPath+fileName);
//	    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
//	    bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
//	    bos.flush();
//	    bos.close();
//	}



    /**
     * 保存图片
     *
     * @param bm
     * @param fileName
     * @param imagePath
     * @throws java.io.IOException
     */
    public static void saveFile(Bitmap bm, String fileName, String imagePath)
            throws IOException {
        File dirFile = new File(imagePath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(imagePath +File.separator+ fileName);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    public static void saveHeadFile(Bitmap bm, String fileName) throws IOException {
//        String imagePath = URLConfig.headImagePath;
        String imagePath = SDCardOperation.getSDPath()+ ImgConfig.BASE_DIR;
        File dirFile = new File(imagePath);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(imagePath +File.separator + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        bos.flush();
        bos.close();
    }

}



