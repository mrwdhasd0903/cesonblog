package cn.wdhhh.dao;

import cn.wdhhh.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wdh on 2020/3/31.
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where (b.title like ?1 or b.content like ?1) and b.published = true")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Query("select b from Blog b where b.published = true")
    Page<Blog> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format',b.createTime,'%Y') as year from Blog b WHERE b.published = true group by function('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    @Query("select b from Blog b where b.published = true and function('date_format',b.createTime,'%Y') = ?1")
    List<Blog> findByYear(String year);

    @Query("SELECT COUNT(b) FROM Blog b WHERE b.published = true")
    long count();
}
