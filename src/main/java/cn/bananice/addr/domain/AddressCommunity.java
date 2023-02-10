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
public class AddressCommunity extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String name;
    private Long streetId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    @Override
    public String toString() {
        return "AddressCommunity{" +
        ", name=" + name +
        ", streetId=" + streetId +
        "}";
    }
}
