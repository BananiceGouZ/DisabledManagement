package cn.bananice.user.domain;

import cn.bananice.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-02-10
 */
@Data
public class User extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String username;
    /**
     * 0女1男
     */
    private Boolean gender;
    private Integer age;
    private Date birthday;
    private String phone;
    private String email;
    private String identity;
    private String disabilityCode;
    private Long disabilityTypeId;
    private Integer disabilityLevel;
    private Long addressId;
    private String salt;
    private String password;
    /**
     * 0禁用，1启用
     */
    private Boolean state;
    private Long logininfoId;

}
