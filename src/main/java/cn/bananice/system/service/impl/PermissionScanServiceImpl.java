package cn.bananice.system.service.impl;

import cn.bananice.basic.anno.PreAuthorize;
import cn.bananice.basic.util.Classutil;
import cn.bananice.system.domain.Permission;
import cn.bananice.system.mapper.PermissionMapper;
import cn.bananice.system.service.IPermissionScanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileFilter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PermissionScanServiceImpl implements IPermissionScanService {
    private static final String  PKG_PREFIX   = "cn.bananice.";
    private static final String  PKG_SUFFIX   = ".controller";

    @Autowired
    private PermissionMapper permissionMapper;


    @Override
    public void permissionScan() {
        //获取org.yaosang下面所有的模块目录
        String path = this.getClass().getResource("/").getPath() + "/cn/bananice/";//     /cn/bananice/
        File file = new File(path);
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        //获取org.yaosang.*.web.controller里面所有的类
        Set<Class> clazzes = new HashSet<>();
        for (File fileTmp : files) {
            System.out.println(fileTmp.getName());
            clazzes.addAll(Classutil.getClasses(PKG_PREFIX+fileTmp.getName()+PKG_SUFFIX));//传入包名 获取这个包下面的所有字节码对象 cn.bananice. basic .controller 、cn.bananice. org .controller、cn.bananice. user .controller
        }

        for (Class clazz : clazzes) {
            Method[] methods = clazz.getMethods();//获取所有方法
            if (methods==null || methods.length<1)
                return;
            for (Method method : methods) {
                String uri = getUri(clazz, method);//获取请求地址
                try {
                    PreAuthorize preAuthorizeAnno = method.getAnnotation(PreAuthorize.class);//获取方法上的PreAuthorize注解
                    if (preAuthorizeAnno==null) {
                        continue;
                    }
                    String name = preAuthorizeAnno.name();
                    String permissionSn = preAuthorizeAnno.value();
                    Permission permissionTmp = permissionMapper.loadBySn(permissionSn);
                    //如果不存在就添加
                    if (permissionTmp == null) {
                        Permission permission = new Permission();
                        permission.setName(name);
                        permission.setSn(permissionSn);
                        permission.setUrl(uri);
                        permissionMapper.save(permission);
                    }else{
                        //如果存在就修改
                        permissionTmp.setName(name);
                        permissionTmp.setSn(permissionSn);
                        permissionTmp.setUrl(uri);
                        permissionMapper.update(permissionTmp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getUri(Class clazz, Method method) {
        String classPath = "";
        Annotation annotation = clazz.getAnnotation(RequestMapping.class);
        if (annotation!=null){
            RequestMapping requestMapping = (RequestMapping) annotation;
            String[] values = requestMapping.value();
            if(values!=null&&values.length>0){
                classPath = values[0];
                if (!"".equals(classPath)&&!classPath.startsWith("/"))
                    classPath="/"+classPath;
            }
        }
        GetMapping getMapping =  method.getAnnotation(GetMapping.class);
        String methodPath = "";
        if (getMapping!=null){
            String[] values = getMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }
        }
        PostMapping postMapping =  method.getAnnotation(PostMapping.class);
        if (postMapping!=null){
            String[] values = postMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }
        }
        DeleteMapping deleteMapping =  method.getAnnotation(DeleteMapping.class);
        if (deleteMapping!=null){
            String[] values = deleteMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }
        }
        PutMapping putMapping =  method.getAnnotation(PutMapping.class);
        if (putMapping!=null){
            String[] values = putMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }

        }
        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
        if (patchMapping!=null){
            String[] values = patchMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }
        }
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping!=null){
            String[] values = requestMapping.value();
            if(values!=null&&values.length>0){
                methodPath = values[0];
                if (!"".equals(methodPath)&&!methodPath.startsWith("/"))
                    methodPath="/"+methodPath;
            }
        }
        return classPath+methodPath;
    }

    private String getPermissionSn(String value) {
        String regex = "\\[(.*?)]";
        Pattern p = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher m = p.matcher(value);
        String permissionSn =null;
        if (m.find()){
            permissionSn= m.group(0).substring(1, m.group().length()-1);
        }
        return permissionSn;
    }
}
