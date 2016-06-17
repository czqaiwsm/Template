package com.hyl.hylbase.bean;

/**
 * @desc banner或广告的信息
 * @creator caozhiqing
 * @data 2015/11/24
 */
public class BannerImgInfo {
    private String id;//广告唯一标识ID(护士id)
    private String title;//广告标题（护士名）
    private String thumb;//图片地址
    private String redirect;//客户端页面跳转url
    private String url = "http://s15.sinaimg.cn/bmiddle/4edd201da9b9ece8d448e";

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
