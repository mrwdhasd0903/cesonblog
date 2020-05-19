package cn.wdhhh.service;

import cn.wdhhh.po.Comment;

import java.util.List;

/**
 * Created by wdh on 2020/3/31.
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);

    void deleteComment(Long id);
}
