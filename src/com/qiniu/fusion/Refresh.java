package com.qiniu.fusion;

import com.qiniu.util.Auth;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by smile on 7/5/16.
 */
public class Refresh {

    String ak = "";
    String sk = "";

    public Auth auth = Auth.create(ak, sk);
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void fresh(String str) throws IOException {

        String signingStr =  "/refresh\n";
        String url = "http://fusion.qiniuapi.com/refresh";
        String access_token = auth.sign(signingStr);
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
}
