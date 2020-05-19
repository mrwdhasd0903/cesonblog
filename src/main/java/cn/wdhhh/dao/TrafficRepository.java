package cn.wdhhh.dao;


import cn.wdhhh.po.Traffic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wdh on 2020/4/17.
 */
public interface TrafficRepository extends JpaRepository<Traffic, Long> {

    @Query("select t from Traffic t where convert(t.createTime,DATETIME) like ?1 ")
    Page<Traffic> findByQuery(String query, Pageable pageable);

    @Query("select count(id) from Traffic t where convert(t.createTime,DATETIME) like ?1")
    Integer findByQuery(String query);

    @Query("select count(id) from Traffic")
    Integer findTotal();
}
