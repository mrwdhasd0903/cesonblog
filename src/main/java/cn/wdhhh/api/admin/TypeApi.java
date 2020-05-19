package cn.wdhhh.api.admin;

import cn.wdhhh.po.Type;
import cn.wdhhh.service.TypeService;
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
public class TypeApi {
    @Autowired
    private TypeService typeService;

    /**
     * 分页排序查询 并去冗余数据
     *
     * @param pageable
     * @return
     */
    @GetMapping("/types")
    public Page<Type> types(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.DESC)
                                    Pageable pageable) {
        Page<Type> pt = typeService.listType(pageable);
        DeredundantUtils.deredundantByType(pt.getContent());
        return pt;
    }

    /**
     * 添加
     *
     * @param type
     * @return
     */
    @PostMapping("/types")
    public int post(Type type) {
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            //重复
            return 1;
        }
        Type t = typeService.saveType(type);
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
    @GetMapping("/type")
    public Type editInput(Long id) {
        Type t = typeService.getType(id);
        t.setBlogssize();
        for (int i = 0; i < t.getBlogs().size(); i++) {
            t.getBlogs().get(i).setContent(null);
        }
        return t;
    }

    /**
     * 根据id修改
     *
     * @param type
     * @param id
     * @return
     */
    @PostMapping("/type")
    public int editPost(Type type, Long id) {
        if (id == null || type.getName() == null) {
            //失败
            return 2;
        }
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null) {
            //重复
            return 1;
        }
        Type t = typeService.updateType(id, type);
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
    @PostMapping("/dtype")
    public int delete(Long id) {
        if (typeService.getType(id) != null) {
            typeService.deleteType(id);
            //成功
            return 0;
        } else {
            //不存在
            return 1;
        }
    }
}

