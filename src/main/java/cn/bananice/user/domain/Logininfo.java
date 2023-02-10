package cn.bananice.user.domain;

import cn.bananice.basic.domain.BaseDomain;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-02-08
 */
public class Logininfo extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String username;
    private String phone;
    private String email;
    private String salt;
    private String password;
    /**
     * 0禁用，1启用
     */
    private Boolean state;
    /**
     * 0工作人员，1用户
     */
    private Boolean type;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Logininfo{" +
        ", username=" + username +
        ", phone=" + phone +
        ", email=" + email +
        ", salt=" + salt +
        ", password=" + password +
        ", state=" + state +
        ", type=" + type +
        "}";
    }
}
