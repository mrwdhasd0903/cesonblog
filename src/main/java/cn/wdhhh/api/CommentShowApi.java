package cn.wdhhh.api;


import cn.wdhhh.po.Comment;
import cn.wdhhh.service.CommentService;
import cn.wdhhh.util.GetIpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CommentShowApi {
    @Autowired
    private CommentService commentService;


    /**
     * 根据文章id查询 评论
     *
     * @param id
     * @return
     */
    @GetMapping("/comments")
    public List<Comment> comments(Long id) {
        return commentService.listCommentByBlogId(id);
    }

    /**
     * 评论
     * parentComment.id=14&blog.id=4&nickname=1234&email=14124@qq.com&content=!@%23$%^%26*&avatar=/images/avatar.png
     *
     * @param comment
     * @param session
     * @return
     */
    @PostMapping("/comment")
    public int post(Comment comment, HttpSession session, HttpServletRequest request) {
        //存储时间 , 防止刷评论
        try {
            long time = (long) session.getAttribute("time");
            if (System.currentTimeMillis() - time < 5000) {
                return 2;
            }
        } catch (Exception e) {
        } finally {
            session.setAttribute("time", System.currentTimeMillis());
        }
        //判断参数是完整
        if (comment.getParentComment() == null ||
                comment.getBlog() == null ||
                comment.getNickname() == null ||
                comment.getContent() == null ||
                comment.getAvatar() == null) {
            //失败
            return 1;
        }
        //获取ip
        comment.setIp(new GetIpUtils().getIpAddress(request));
        comment.setAdminComment(false);
        Comment a = commentService.saveComment(comment);
        if (a != null) {
            //成功
            return 0;
        } else {
            //失败
            return 1;
        }
    }
}
