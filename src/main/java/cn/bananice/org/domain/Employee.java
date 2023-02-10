package cn.bananice.org.domain;

import java.math.BigDecimal;
import java.util.Date;
import cn.bananice.basic.domain.BaseDomain;

/**
 * <p>
 * 
 * </p>
 *
 * @author bananice
 * @since 2023-02-10
 */
public class Employee extends BaseDomain{

    private static final long serialVersionUID = 1L;

    private String username;
    private Integer age;
    private String phone;
    private String email;
    private String salt;
    private String password;
    private Boolean state;
    private Long logininfoId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public Long getLogininfoId() {
        return logininfoId;
    }

    public void setLogininfoId(Long logininfoId) {
        this.logininfoId = logininfoId;
    }

    @Override
    public String toString() {
        return "Employee{" +
        ", username=" + username +
        ", age=" + age +
        ", phone=" + phone +
        ", email=" + email +
        ", salt=" + salt +
        ", password=" + password +
        ", state=" + state +
        ", logininfoId=" + logininfoId +
        "}";
    }
}
