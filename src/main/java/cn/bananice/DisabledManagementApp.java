package cn.bananice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("cn.bananice.*.mapper")
public class DisabledManagementApp {
    public static void main(String[] args) {
        SpringApplication.run(DisabledManagementApp.class);
    }
}
