package com.qiniu.ops;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import java.util.Date;

/**
 * Created by smile on 5/30/16.
 */
public class Avthumb{

    @org.junit.Test
    public void test2(){

        System.out.println(new Date(System.currentTimeMillis()));
    }

    @org.junit.Test
    public void test(){
        //设置账号的AK,SK
        String ACCESS_KEY = "**";
        String SECRET_KEY = "**";
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        //新建一个OperationManager对象
        OperationManager operater = new OperationManager(auth);
        //设置要转码的空间和key，并且这个key在你空间中存在
        String bucket = "atest";
        String key = "oFx_Ol4RpO3d_WMo-2I9bV6MdCEpQBH59FZnsFd5H1Sb_ChOvzTJ_LMEh4Gr85C7.amr";
        //设置转码操作参数
        String fops = "avthumb/mp3";
        //设置转码的队列
        String pipeline = "da";
        //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
        String urlbase64 = UrlSafeBase64.encodeToString("atest:test1.mp3");
        String pfops = fops + "|saveas/"+urlbase64;
        //设置pipeline参数
        StringMap params = new StringMap().putWhen("force", 1, true).putNotEmpty("pipeline", pipeline);
        try {

            String persistid = operater.pfop(bucket, key, pfops, params);
            System.out.println(new Date(System.currentTimeMillis()));
            //打印返回的persistid
            System.out.println(persistid);
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            try {
                // 响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }
}