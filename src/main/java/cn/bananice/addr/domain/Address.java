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
public class Address extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private Long streetId;
    private Long communityId;
    private String addresDetailed;


    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public Long getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Long communityId) {
        this.communityId = communityId;
    }

    public String getAddresDetailed() {
        return addresDetailed;
    }

    public void setAddresDetailed(String addresDetailed) {
        this.addresDetailed = addresDetailed;
    }

    @Override
    public String toString() {
        return "Address{" +
        ", streetId=" + streetId +
        ", communityId=" + communityId +
        ", addresDetailed=" + addresDetailed +
        "}";
    }
}
