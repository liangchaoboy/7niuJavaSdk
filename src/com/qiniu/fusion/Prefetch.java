package com.qiniu.fusion;

import com.qiniu.util.Auth;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by smile on 7/5/16.
 */
public class Prefetch {


    String ak = "";
    String sk = "";

    public Auth auth = Auth.create(ak, sk);

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public void pre(String str) throws IOException {

        String signingStr =  "/prefetch\n";
        String url = "http://fusion.qiniuapi.com/prefetch";
        // 生成管理凭证,依赖java-sdk中的auth类
        String access_token = auth.sign(signingStr);
        System.out.println(access_token);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON,str);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "QBox " +access_token)
                .post(body)
                .build();

        Response re = client.newCall(request).execute();

        System.out.println(re.body().string());
    }


    @org.junit.Test
    public void getStatus(String requestId){
        String signingStr = "/prefetch/query?requestId="+requestId+"\n";
        String url = "http://fusion.qiniuapi.com/prefetch/query";
        String access_token = auth.sign(signingStr);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url+"?requestId="+requestId)//+ requestId
                .addHeader("Authorization", "QBox " +access_token)
                .build();
        Response re;
        try {
            re = client.newCall(request).execute();
            System.out.println(re.body().string());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
