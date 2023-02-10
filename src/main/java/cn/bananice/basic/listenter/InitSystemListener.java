package cn.bananice.basic.listenter;

import cn.bananice.basic.jwt.JwtRsaHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class InitSystemListener implements ServletContextListener {

    @Value("${jwt.rsa.pri}")
    private String jwtRasPri;

    @Value("${jwt.rsa.pub}")
    private String jwtRasPub;

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {
        log.info("jwt.rsa.pri--------------->"+jwtRasPri);
        log.info("jwt.rsa.pub--------------->"+jwtRasPub);

        JwtRsaHolder.INSTANCE.setJwtRsaPriData(FileCopyUtils.copyToByteArray(this.getClass().getClassLoader().getResourceAsStream(jwtRasPri)));
        JwtRsaHolder.INSTANCE.setJwtRsaPubData(FileCopyUtils.copyToByteArray(this.getClass().getClassLoader().getResourceAsStream(jwtRasPub)));

        log.info("读取系统配置文件完成");
    }
}
