package cn.wdhhh.api;

import cn.wdhhh.util.DeredundantUtils;
import cn.wdhhh.po.Blog;
import cn.wdhhh.po.Type;
import cn.wdhhh.service.BlogService;
import cn.wdhhh.service.TypeService;
import cn.wdhhh.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeShowApi {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
     * 根据分类id 分页查询博客过滤冗余属性
     *
     * @param pageable
     * @param id
     * @return
     */
    @GetMapping("/blogbytype")
    public Page<Blog> types(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                            Long id) {
        if (id == -1) {
            List<Type> types = typeService.listTypeTop(1);
            id = types.get(0).getId();
        }
        return DeredundantUtils.deredundant(blogService.listBlogByJoin(id,pageable, "type"));
    }
}
