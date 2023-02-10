package cn.bananice.basic.interceptor;


import cn.bananice.basic.anno.PreAuthorize;
import cn.bananice.basic.jwt.*;
import cn.bananice.system.mapper.PermissionMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.PublicKey;
import java.util.List;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PermissionMapper permissionMapper;

   /* @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String message = null;

        String token = request.getHeader("U-TOKEN");
        Object logininfoObj;
        if (token != null && (logininfoObj = redisTemplate.opsForValue().get(token)) != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);
            if (preAuthorize != null) {
                Logininfo logininfo = (Logininfo) logininfoObj;
                List<String> permissionSnList = permissionMapper.loadByLogininfoId(logininfo.getId());
                if (permissionSnList.indexOf(preAuthorize.value()) > -1) {
                    redisTemplate.opsForValue().set(token, logininfoObj, 30, TimeUnit.MINUTES);
                } else {
                    message = "noPermission";
                }
            }
        } else {
            message = "noLogin";
        }

        if (message == null) {
            return true;
        }

        response.setCharacterEncoding("utf-8");//设置字符编码集 告诉前端
        response.setContentType("application/json;charset=utf-8");//设置文本内容格式和字符编码

        PrintWriter writer = response.getWriter();
        writer.write("{\"success\":false,\"message\":\"" + message + "\"}");
        writer.flush();
        writer.close();

        return false;
    }*/

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String message = "noLogin";

        String token = request.getHeader("U-TOKEN");

        if (!StringUtils.isBlank(token)) {
            Payload<PayloadData> payload = null;

            try {
                PublicKey publicKey = RsaUtils.getPublicKey(JwtRsaHolder.INSTANCE.getJwtRsaPubData());
                payload = JwtUtils.getInfoFromToken(token, publicKey, PayloadData.class);
            } catch (Exception e) {

            }

            if (payload != null) {
                //拥有的权限
                List<String> permissions = payload.getUserInfo().getPermissions();

                //需要的权限
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                PreAuthorize preAuthorize = handlerMethod.getMethodAnnotation(PreAuthorize.class);

                if (preAuthorize != null && permissions.indexOf(preAuthorize.value()) == -1) {
                    message = "noPermission";
                } else {
                    return true;
                }
            }
        }

        response.setCharacterEncoding("utf-8");//设置字符编码集 告诉前端
        response.setContentType("application/json;charset=utf-8");//设置文本内容格式和字符编码

        PrintWriter writer = response.getWriter();
        writer.write("{\"success\":false,\"message\":\"" + message + "\"}");
        writer.flush();
        writer.close();

        return false;
    }
}
