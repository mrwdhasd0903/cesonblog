package cn.wdhhh.po;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wdh on 2020/4/17.
 */
@Entity
@Table(name = "t_traffic")
public class Traffic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String ip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Traffic{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", ip='" + ip + '\'' +
                '}';
    }
}
