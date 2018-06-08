package com.bgy.httpclient.test;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;


@SuppressWarnings("deprecation")
public class Test {

    public static void main(String[] args) throws Exception {
        //1  post method
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://b2b.test.pagoda.com.cn/trade/vendor/queryJoinStatus");
        JSONObject params = new JSONObject();
        params.put("vendorId", 1000000655);
        StringEntity s = new StringEntity(params.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        post.setEntity(s);
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            System.out.println("request success !");
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            System.out.println("response: \t" + result);
        } else {
            System.out.println("request error !");
        }
        
        //2 get method
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        System.out.println("executing request:\t" + httpGet.getURI());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse resp = httpclient.execute(httpGet);
        HttpEntity entity = resp.getEntity();
        System.out.println(resp.getStatusLine());
        if (entity != null) {
            System.out.println("Response content length:\t" + entity.getContentLength());
            System.out.println("Response conetnt:\t" + EntityUtils.toString(entity));
            
        }
        System.out.println("------------------------");
        resp.close();
        httpclient.close();
    }

}
