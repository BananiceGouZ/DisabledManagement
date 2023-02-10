package cn.bananice.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.bananice.basic.domain.BaseDomain;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-01-13
 */
public class Permission extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String name;
    private String url;
    private String descs;
    private String sn;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "Permission{" +
        ", name=" + name +
        ", url=" + url +
        ", descs=" + descs +
        ", sn=" + sn +
        "}";
    }
}
