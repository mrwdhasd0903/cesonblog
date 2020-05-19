package cn.wdhhh.api;

import cn.wdhhh.po.Blog;
import cn.wdhhh.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ArchiveShowApi {
    @Autowired
    private BlogService blogService;

    /**
     * 归档列表
     *
     * @return
     */
    @GetMapping("/archivemap")
    public Map<String, List<Blog>> archives() {
        return blogService.archiveBlog();
    }


    /**
     * 总博客数量
     *
     * @return
     */
    @GetMapping("/blogcount")
    public Long blogCount() {
        return blogService.countBlog();
    }
}
