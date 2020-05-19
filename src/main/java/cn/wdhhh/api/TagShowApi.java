package cn.wdhhh.api;

import cn.wdhhh.po.Blog;
import cn.wdhhh.po.Tag;
import cn.wdhhh.service.BlogService;
import cn.wdhhh.service.TagService;
import cn.wdhhh.util.DeredundantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagShowApi {
    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/blogbytag")
    public Page<Blog> tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                           Long id) {
        if (id == -1) {
            List<Tag> tags = tagService.listTagTop(1);
            id = tags.get(0).getId();
        }
        return DeredundantUtils.deredundant(blogService.listBlog(id, pageable));
    }
}
