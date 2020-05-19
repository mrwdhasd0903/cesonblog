package cn.wdhhh.api.admin;

import cn.wdhhh.po.Blog;
import cn.wdhhh.po.User;
import cn.wdhhh.service.BlogService;
import cn.wdhhh.service.TagService;
import cn.wdhhh.service.TypeService;
import cn.wdhhh.util.DeredundantUtils;
import cn.wdhhh.vo.BlogQuery;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class BlogApi {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 分页条件查询
     *
     * @param pageable
     * @param blog
     * @return
     */
    @GetMapping("/blogs")
    public Page<Blog> blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            BlogQuery blog) {
        //return typeService.listType();
        return DeredundantUtils.deredundant(blogService.listBlog(pageable, blog));
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/blog")
    public Blog editInput(Long id) {
        Blog blog = blogService.getBlog(id);
        blog.init();
        return blog;
    }

    /**
     * 增加和修改集成
     *
     * @param blog
     * @param session
     * @return
     */
    @PostMapping("/blogs")
    public int post(Blog blog, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));

        blog.setContent(StringEscapeUtils.unescapeXml(blog.getContent()));
        Blog b;
        //id是否存在
        if (blog.getId() == null) {
            //不存在=>保存
            b = blogService.saveBlog(blog);
        } else {
            //存在=>更新
            b = blogService.updateBlog(blog.getId(), blog);
        }

        if (b == null) {
            //失败
            return 1;
        } else {
            //成功
            return 0;
        }
    }

    /**
     * 根据id的删除
     *
     * @param id
     * @return
     */
    @GetMapping("/dblog")
    public int delete(Long id) {
        try {
            blogService.deleteBlog(id);
            //成功
            return 0;
        } catch (Exception e) {
            //失败
            return 1;
        }
    }

}
