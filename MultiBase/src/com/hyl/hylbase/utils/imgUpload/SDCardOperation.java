package com.hyl.hylbase.utils.imgUpload;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class SDCardOperation {

	/**
	 * 当前设备是否sdcard
	 * @return
	 */
	public static boolean hasSdcard() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	//创建app文件夹
	  public SDCardOperation(){
		     if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
		            // 创建一个文件夹对象，赋值为外部存储器的目录
		             File sdcardDir =Environment.getExternalStorageDirectory();
		           //得到一个路径，内容是sdcard的文件夹路径和名字
		             String path=sdcardDir.getPath();
		             File path1 = new File(path);
		            if (!path1.exists()) {
		             //若不存在，创建目录，可以在应用启动的时候创建
		             path1.mkdirs();
		          
		           }
		     }
		     else{	     
		      return;
		    }
		 }
	  
	  public static void CreateFile(String url){
		  if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
	            // 创建一个文件夹对象，赋值为外部存储器的目录
	             File sdcardDir =Environment.getExternalStorageDirectory();
	           //得到一个路径，内容是sdcard的文件夹路径和名字
	             String path=sdcardDir.getPath()+url;
	             File path1 = new File(path);
	            if (!path1.exists()) {
	             //若不存在，创建目录，可以在应用启动的时候创建
	             path1.mkdirs();
	          
	           }
	     }
	     else{	     
	      return;
	    }
	  }
	  
	  public static boolean fileIsExists(String file){
          try{
                  File f=new File(file);
                  if(!f.exists()){
                          return false;
                  }
                  
          }catch (Exception e) {
                  // TODO: handle exception
                  return false;
          }
          return true;
  }


    public static String getSDPath(Context context){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)){
            File sdcardDir =Environment.getExternalStorageDirectory();
            String path=sdcardDir.getPath();
            return path;
        }else {
            return "";
        }
    }
    public static String getSDPath(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            File sdcardDir =Environment.getExternalStorageDirectory();
            String path=sdcardDir.getPath();
            return path;
        }else {
            return "";
        }
    }
	private static boolean hasExternalStoragePermission(Context context) {
		int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
		return perm == 0;
	}
}



