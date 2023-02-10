package cn.bananice.basic.jwt;

import java.util.Date;

public class Payload<T> {

    private String id;  // jwt的id(token)
    private T userInfo;  // 用户信息：用户数据，不确定，可以是任意类型
    private Date expiration;  // 过期时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(T userInfo) {
        this.userInfo = userInfo;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id='" + id + '\'' +
                ", userInfo=" + userInfo +
                ", expiration=" + expiration +
                '}';
    }
}