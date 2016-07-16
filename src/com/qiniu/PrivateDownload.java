package com.qiniu;

import com.qiniu.util.Auth;

/**
 * Created by smile on 7/11/16.
 */
public class PrivateDownload {

    @org.junit.Test
    public void getPrivate(){
        String ak = "**";
        String sk = "**";
        Auth auth = Auth.create(ak,sk);
        String url = auth.privateDownloadUrl("rtmp://pili-publish.pili-test.qiniuts.com/pili-test/stream01");

        System.out.print(url);

    }
}
