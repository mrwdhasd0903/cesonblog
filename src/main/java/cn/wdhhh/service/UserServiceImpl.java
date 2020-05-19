package cn.wdhhh.service;

import cn.wdhhh.NotFoundException;
import cn.wdhhh.dao.UserRepository;
import cn.wdhhh.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by wdh on 2020/3/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 管理员登陆查询
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User updateUser(Long id, User user) {
        User u = userRepository.findById(id).get();
        if (u == null) {
            throw new NotFoundException("用户不存在");
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            user.setPassword(u.getPassword());
        }
        user.setUpdateTime(new Date());
        user.setCreateTime(u.getCreateTime());
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }
}
