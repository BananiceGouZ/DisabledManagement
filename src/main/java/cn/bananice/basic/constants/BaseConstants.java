package cn.bananice.basic.constants;

public class BaseConstants {

    /*
    *
    * 通过内部类的方式进行系统常量分类 ctrl + shift + u:大小写转换
    * */
    public class VerifyConstants{
        public static final String USER_PHONE_REGISTER_CODE_PREFIX = "register:";
        public static final String USER_PHONE_BINDER_CODE_PREFIX = "binder:";
    }

    //微信登录相关常量
    public class WxConstants {
        public static final String APPID = "wxd853562a0548a7d0";
        public static final String SECRET = "4a5d5615f93f24bdba2ba8534642dbb6";
        public static final String TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        public static final String USERURL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    }


}
