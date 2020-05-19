package cn.wdhhh.service;

import cn.wdhhh.po.User;

/**
 * Created by wdh on 2020/3/31.
 */
public interface UserService {
    User checkUser(String username, String password);

    User updateUser(Long id, User user);

    User getUser(Long id);
}
