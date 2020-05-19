package cn.wdhhh.util;

import cn.wdhhh.po.Blog;
import cn.wdhhh.po.Tag;
import cn.wdhhh.po.Type;
import org.springframework.data.domain.Page;

import java.util.List;

public class DeredundantUtils {
    /**
     * 去冗余
     *
     * @param pb
     * @return
     */
    public static Page<Blog> deredundant(Page<Blog> pb) {
        int size = pb.getContent().size();
        for (int i = 0; i < size; i++) {
            pb.getContent().get(i).setContent(null);
            pb.getContent().get(i).setCommentssize();
            pb.getContent().get(i).setComments(null);
            pb.getContent().get(i).getUser().setUsername(null);
        }
        return pb;
    }

    public static void deredundantByType(List<Type> lt) {
        for (int i = 0; i < lt.size(); i++) {
            lt.get(i).setBlogssize();
            lt.get(i).setBlogs(null);
        }
    }

    public static void deredundantByTag(List<Tag> lt) {
        for (int i = 0; i < lt.size(); i++) {
            lt.get(i).setBlogssize();
            lt.get(i).setBlogs(null);
        }
    }
}
