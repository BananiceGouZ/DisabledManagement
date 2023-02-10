package cn.bananice.user.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.bananice.basic.domain.BaseDomain;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-01-11
 */
public class Wxuser extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String openid;
    private String nickname;
    private Integer sex;
    private String address;
    private String headimgurl;
    private String unionid;
    private Long userId;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wxuser{" +
        ", openid=" + openid +
        ", nickname=" + nickname +
        ", sex=" + sex +
        ", address=" + address +
        ", headimgurl=" + headimgurl +
        ", unionid=" + unionid +
        ", userId=" + userId +
        "}";
    }
}
