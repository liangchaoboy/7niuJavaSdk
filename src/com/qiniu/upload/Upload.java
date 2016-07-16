package com.qiniu.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.File;

/**
 * Created by smile on 5/30/16.
 */

public class Upload {
    @org.junit.Test
    public void upTest2(){

        String ak = "kq0nTUwXHRkfz5ipg_krWgGFYhMAZCTSf21IdjEu";
        String sk = "tu23TRIMJlk3XQUoF6qAYNdlFoGBqMnT5O5fJBjK";
        Auth auth = Auth.create(ak, sk);
        UploadManager uploadManager = new UploadManager();

        String token = auth.uploadToken("logs", null, 51840000, new StringMap()
                .put("returnBody","$(fname)")
        );

        try {
            Response res = uploadManager.put("/Users/smile/Documents/2.jpg", "3", token);

            System.out.println(res.toString());
            System.out.print(res.bodyString());
        } catch (QiniuException e) {
            Response re = e.response;

            System.out.println(re.toString());
        }

    }
    // 普通上传
    @org.junit.Test
    public  void upTest(){

        File file = new File("/Users/smile/Downloads/课件.rar");

        System.out.print(file.getName());

        String ak = "kq0nTUwXHRkfz5ipg_krWgGFYhMAZCTSf21IdjEu";
        String sk = "sZHaqTsn72cYaywvwQC9i3KrJpbRQYvt3_GV4L-0";
        Auth auth = Auth.create(ak, sk);
        UploadManager uploadManager = new UploadManager();
        String token = auth.uploadToken("logs", null, 51840000, new StringMap()
                .put("returnBody","$(fname)")
        );

        try {
            Response res = uploadManager.put("/Users/smile/Downloads/课件.rar", "3", token);

            System.out.println(res.toString());
            System.out.print(res.bodyString());
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
