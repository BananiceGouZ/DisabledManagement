package cn.bananice.addr.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.bananice.basic.domain.BaseDomain;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-02-08
 */
public class AddressStreet extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AddressStreet{" +
        ", name=" + name +
        "}";
    }
}
