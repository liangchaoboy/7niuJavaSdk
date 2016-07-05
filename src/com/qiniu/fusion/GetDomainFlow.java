package com.qiniu.fusion;

import com.qiniu.util.Auth;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by smile on 7/5/16.
 */
public class GetDomainFlow {

    String ak = "";
    String sk = "";
    // 生成鉴权类,依赖java-sdk
    public Auth auth = Auth.create("**","**");

    /**
     * @param domain
     *            需要获取带宽的域名
     * @param startTime
     *            起始时间
     * @param endTime
     *            结束时间
     */
    public void getFlow(String domain, String startTime, String endTime) throws IOException {

        String reqUrl = String.format("http://fusion.qiniuapi.com/domain/traffic?domain=%s&startTime=%s&endTime=%s",
                URLEncoder.encode(domain, "utf-8"), URLEncoder.encode(startTime, "utf-8"),
                URLEncoder.encode(endTime, "utf-8"));

        // 生成管理凭证,依赖java-sdk中的auth类
        String access_token =  auth.signRequest(reqUrl, null, null);

        System.out.println(access_token);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(reqUrl)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Authorization", "QBox " +access_token)
                .build();

        Response re = client.newCall(request).execute();

        System.out.println(re.body().string());

        System.out.println(re.code());
    }


    @org.junit.Test
    public void getTest(){

        try {
            getFlow("qiniu.com", "2016-07-05 00:00:00", "2016-07-05 01:50:00");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
