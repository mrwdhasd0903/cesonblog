package cn.wdhhh.api.admin;


import cn.wdhhh.po.Tag;
import cn.wdhhh.service.TagService;
import cn.wdhhh.util.DeredundantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class TagApi {
    @Autowired
    private TagService tagService;

    /**
     * 分页排序查询 并消除冗余数据
     *
     * @param pageable
     * @return
     */
    @GetMapping("/tags")
    public Page<Tag> tags(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                  Pageable pageable) {
        Page<Tag> pt = tagService.listTag(pageable);
        DeredundantUtils.deredundantByTag(pt.getContent());
        return pt;
    }

    /**
     * 添加
     *
     * @param tag
     * @return
     */
    @PostMapping("/tags")
    public int post(Tag tag) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null) {
            //重复
            return 1;
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            //失败
            return 2;
        } else {
            //成功
            return 0;
        }
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/tag")
    public Tag editInput(Long id) {
        Tag t = tagService.getTag(id);
        t.setBlogssize();
        for (int i = 0; i < t.getBlogs().size(); i++) {
            t.getBlogs().get(i).setContent(null);
        }
        return t;
    }

    /**
     * 根据id修改
     *
     * @param tag
     * @param id
     * @return
     */
    @PostMapping("/tag")
    public int editPost(Tag tag, Long id) {
        if (id == null || tag.getName() == null) {
            //失败
            return 2;
        }
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null && tag1.getColor().equals(tag.getColor())) {
            //重复
            return 1;
        }
        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            //失败
            return 2;
        } else {
            //成功
            return 0;
        }
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @PostMapping("/dtag")
    public int delete(Long id) {
        if (tagService.getTag(id) != null) {
            tagService.deleteTag(id);
            //成功
            return 0;
        } else {
            //不存在
            return 1;
        }
    }
}
