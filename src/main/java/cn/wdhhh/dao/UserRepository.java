package cn.wdhhh.dao;

import cn.wdhhh.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wdh on 2020/3/31.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);
}
