package cn.wdhhh.service;

import cn.wdhhh.po.Traffic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface TrafficService {
    Traffic saveTraffic(Traffic t);

    Page<Traffic> listTraffic(String query, Pageable pageable);

    List<Map<String,Object>> list24hours();

    Integer findTotal();

    void deleteTraffic(Long id);
}
