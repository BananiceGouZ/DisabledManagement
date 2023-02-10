package cn.bananice.basic.util;

import cn.bananice.basic.jwt.*;
import cn.bananice.user.domain.Logininfo;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

public class LoginContext {

    public static Logininfo getLogininfo(HttpServletRequest request){
        String token = request.getHeader("U-TOKEN");
        PublicKey publicKey = RsaUtils.getPublicKey(JwtRsaHolder.INSTANCE.getJwtRsaPubData());
        Payload<PayloadData> payload = JwtUtils.getInfoFromToken(token, publicKey, PayloadData.class);

        JSONObject logininfoJSON = (JSONObject) payload.getUserInfo().getLogininfo();
        String jsonStr = JSONObject.toJSONString(logininfoJSON);//将对象转成json字符串
        return JSONObject.parseObject(jsonStr, Logininfo.class);
    }
}
