package com.hyl.hylbase.utils.imgUpload;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

/**
 * Created by moon.zhong on 2015/3/2.
 */
public class FormImage {

    private String mName ;

    private String mFileName ;

    private String mValue ;

    private String mMime ;

    private Bitmap mBitmap ;

    public FormImage(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public String getName() {
//        return mName;
        return "img" ;
    }

    public String getFileName() {
        return "test.jpg";
    }

    public byte[] getValue() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
        mBitmap.compress(Bitmap.CompressFormat.JPEG,80,bos) ;
        return bos.toByteArray();
    }

    public String getMime() {
        return "image/jpg";
    }
}
