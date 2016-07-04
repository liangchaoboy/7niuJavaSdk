package com.qiniu.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * Created by smile on 5/30/16.
 */

public class Upload {
    // 普通上传
    @org.junit.Test
    public  void upTest(){

        String ak = "**";
        String sk = "**";
        Auth auth = Auth.create(ak, sk);
        UploadManager uploadManager = new UploadManager();
        String token = auth.uploadToken("atest", null, 51840000, new StringMap().put("saveKey","$(imageInfo.width)"));
        System.out.print(token);

        try {
            Response res = uploadManager.put("/Users/smile/Documents/2.jpg", "2", token);
            System.out.println(res.toString());
        } catch (QiniuException e) {
            Response re = e.response;
            System.out.println(re.toString());
        }
    }

    //上传转码
    public void upTest02(){
        String ak = "**";
        String sk = "**";
        Auth auth = Auth.create(ak, sk);
        UploadManager uploadManager = new UploadManager();

        StringMap policy = new StringMap().put("","");

        String token = auth.uploadToken("atest","litest", 3600, null, true);

    }

}
