package com.qiniu.fusion;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Hex;

/**
 * Created by smile on 7/5/16.
 */
public class TimeStamp {

    public static String getTimestamp(String url, String encryptKey, int durationInSeconds)
            throws MalformedURLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        URL urlObj = new URL(url);
        String path = urlObj.getPath();

        long timestampNow = System.currentTimeMillis() / 1000 + durationInSeconds;
        String expireHex = Long.toHexString(timestampNow);

        String toSignStr = String.format("%s%s%s", encryptKey, path, expireHex);
        String signedStr = md5ToLower(toSignStr);

        String signedUrl = null;
        if (urlObj.getQuery() != null) {
            signedUrl = String.format("%s&sign=%s&t=%s", url, signedStr, expireHex);
        } else {
            signedUrl = String.format("%s?sign=%s&t=%s", url, signedStr, expireHex);
        }

        return signedUrl;
    }

    private static String md5ToLower(String src) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(src.getBytes("utf-8"));
        byte[] md5Bytes = digest.digest();
        return Hex.encodeHexString(md5Bytes);
    }

    @org.junit.Test
    public void test(){

        // cdn 配置的基于时间戳防盗链的加密字符串，cdn 配置完成后会得到
        String encryptKey = "**";
        // 待加密链接
        String urlToSign = "**";
        // 有效期
        int duration = 3600;

        try {
            String signedUrl = getTimestamp(urlToSign, encryptKey, duration);
            System.out.println(signedUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void ts(){
        //URL url = new URL();

    }


}
