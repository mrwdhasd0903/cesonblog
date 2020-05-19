package cn.wdhhh.api;

import cn.wdhhh.po.Blog;
import cn.wdhhh.po.Tag;
import cn.wdhhh.po.Type;
import cn.wdhhh.service.BlogService;
import cn.wdhhh.service.TagService;
import cn.wdhhh.service.TypeService;
import cn.wdhhh.util.DeredundantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class IndexApi {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    /**
     * 分页查询博客过滤冗余属性
     *
     * @param pageable
     * @return
     */
    @GetMapping("/blogs")
    public Page<Blog> blogs(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        //return typeService.listType();
        Page<Blog> pb = blogService.listBlog(pageable);
        return DeredundantUtils.deredundant(pb);
    }

    /**
     * 排序查询前size个分类过滤冗余属性
     *
     * @param size
     * @return
     */
    @GetMapping("/typessort")
    public List<Type> typessort(int size) {
        List<Type> lt = typeService.listTypeTop(size);
        DeredundantUtils.deredundantByType(lt);
        return lt;
    }

    /**
     * 排序查询前size个标签过滤冗余属性
     *
     * @param size
     * @return
     */
    @GetMapping("/tagssort")
    public List<Tag> tagssort(int size) {
        List<Tag> lt = tagService.listTagTop(size);
        DeredundantUtils.deredundantByTag(lt);
        return lt;
    }

    /**
     * 排序查询前size个推荐过滤冗余属性
     *
     * @param size
     * @return
     */
    @GetMapping("/recommendsort")
    public List<Blog> recommendsort(int size) {
        List<Blog> lb = blogService.listRecommendBlogTop(size);
        for (int i = 0; i < lb.size(); i++) {
            lb.get(i).setContent(null);
            lb.get(i).setUser(null);
            lb.get(i).setComments(null);
        }
        return lb;
    }

    /**
     * 搜索查询过滤冗余属性
     *
     * @param pageable
     * @param query
     * @return
     */
    @GetMapping("/searchblog")
    public Page<Blog> search(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                             String query) {
        if(query==null){
            return null;
        }
        Page<Blog> pb = blogService.listBlog("%" + query + "%", pageable);
        return DeredundantUtils.deredundant(pb);
    }

    /**
     * 根据id查询单个blog ,去掉评论 , 并将内容转为html
     *
     * @param id
     * @return
     */
    @GetMapping("blog")
    public Blog blog(Long id, HttpSession session) {
        //存储时间,防止刷阅读数
        Boolean isAdd = true;
        try {
            long time = (long) session.getAttribute("addTime");
            if (System.currentTimeMillis() - time < 5000) {
                isAdd = false;
            }
        } catch (Exception e) {
        } finally {
            session.setAttribute("addTime", System.currentTimeMillis());
        }
        System.out.println(isAdd);
        Blog b = blogService.getAndConvert(id,isAdd);
        b.setCommentssize();
        b.setComments(null);
        return b;
    }

}
