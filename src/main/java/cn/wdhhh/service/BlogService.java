package cn.wdhhh.service;

import cn.wdhhh.po.Blog;
import cn.wdhhh.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by wdh on 2020/3/31.
 */
public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id,Boolean isAdd);

    Page<Blog> listBlog(Pageable pageable,BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlogByJoin(Long tagId,Pageable pageable,String join);

    Page<Blog> listBlog(String query,Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
