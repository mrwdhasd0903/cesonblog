package cn.wdhhh.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wdh on 2020/4/6
 */
@Entity
@Table(name = "t_contact")
public class Contact {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String qq;
    private String wechat;
    private String wechatCode;
    private String payByWechat;
    private String payByZhi;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getPayByWechat() {
        return payByWechat;
    }

    public void setPayByWechat(String payByWechat) {
        this.payByWechat = payByWechat;
    }

    public String getPayByZhi() {
        return payByZhi;
    }

    public void setPayByZhi(String payByZhi) {
        this.payByZhi = payByZhi;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", wechatCode='" + wechatCode + '\'' +
                ", payByWechat='" + payByWechat + '\'' +
                ", payByZhi='" + payByZhi + '\'' +
                '}';
    }
}
