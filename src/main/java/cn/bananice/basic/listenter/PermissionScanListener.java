package cn.bananice.basic.listenter;

import cn.bananice.system.service.IPermissionScanService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PermissionScanListener implements ServletContextListener {

    @Autowired
    private IPermissionScanService permissionScanService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                permissionScanService.permissionScan();
            }
        }).start();
    }
}
