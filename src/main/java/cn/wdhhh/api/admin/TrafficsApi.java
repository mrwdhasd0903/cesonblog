package cn.wdhhh.api.admin;

import cn.wdhhh.po.Traffic;
import cn.wdhhh.service.TrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王达浩
 * @version 1.0
 * @date 2020/5/17 1:04
 */

@RestController
@RequestMapping("/admin")
public class TrafficsApi {
    @Autowired
    private TrafficService trafficService;

    @GetMapping("/trafficByCreateTime")
    public Page<Traffic> trafficByCreateTime(@PageableDefault(size = 8, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                             String query) {
        if (query == null) {
            return null;
        }
        Page<Traffic> pt = trafficService.listTraffic("%" + query + "%", pageable);
        return pt;
    }

    @PostMapping("/dtraffic")
    public int delete(Long id) {
        try {
            trafficService.deleteTraffic(id);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }
}
