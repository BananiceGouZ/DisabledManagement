package cn.bananice.basic.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

public class HttpClientUtils {
    /**
     * 发送get请求
     * @param url 请求地址
     * @return 返回内容 json
     */
    public static String httpGet(String url){

        // 1 创建发起请求客户端
        try {
            HttpClient client = new HttpClient();
            // 2 创建要发起请求-tet
            GetMethod getMethod = new GetMethod(url);
//            getMethod.addRequestHeader("Content-Type",
//                    "application/x-www-form-urlencoded;charset=UTF-8");
            getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf8");
            // 3 通过客户端传入请求就可以发起请求,获取响应对象
            client.executeMethod(getMethod);
            // 4 提取响应json字符串返回
            String result = new String(getMethod.getResponseBodyAsString().getBytes("utf8"));
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}