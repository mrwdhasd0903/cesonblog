package cn.wdhhh.api.admin;

import cn.wdhhh.po.User;
import cn.wdhhh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class LoginApi {
    @Autowired
    private UserService userService;


    /**
     * 验证用户名和密码
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public int login(String username, String password, HttpSession session) {
        if (username == null || password == null) {
            return 1;
        }
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user", user);
            //登陆成功
            return 0;
        } else {
            //密码错误
            return 1;
        }
    }

    @PostMapping("/postuser")
    public int postuser(User user, HttpSession session) {
        User u = userService.updateUser(user.getId(), user);
        if (u == null) {
            return 1;
        } else {
            //刷新session
            session.setAttribute("user", userService.getUser(user.getId()));
            return 0;
        }
    }

    @GetMapping("/getuser")
    public User getuser(HttpSession session) {
        User user = new User();
        User u = (User) session.getAttribute("user");
        user.setId(u.getId());
//        user.setCreateTime(u.getCreateTime());
//        user.setUpdateTime(u.getUpdateTime());
        user.setNickname(u.getNickname());
        user.setUsername(u.getUsername());
//        user.setPassword(u.getPassword());
        user.setType(u.getType());
        user.setAvatar(u.getAvatar());
        user.setEmail(u.getEmail());
        return user;
    }

    @GetMapping("/islogin")
    public int islogin(HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u != null) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public int logout(HttpSession session) {
        session.removeAttribute("user");
        return 1;
    }
}
