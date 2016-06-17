package com.volley.req;

import org.json.JSONException;

/**
 * @author czq
 * @desc 请用一句话描述它
 * @date 16/3/30
 */
public interface RequsetListener {


    public void handleRspSuccess(int reqCode, Object obj) throws JSONException;//处理成功后的请求参数


}
