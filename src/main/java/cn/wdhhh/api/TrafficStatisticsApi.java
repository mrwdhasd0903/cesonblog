package cn.wdhhh.api;

import cn.wdhhh.po.Traffic;
import cn.wdhhh.service.TrafficService;
import cn.wdhhh.util.GetIpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
/**
 * @author 王达浩
 * @version 1.0
 * @date 2020/5/17 1:04
 */
@RestController
public class TrafficStatisticsApi {
    @Autowired
    private TrafficService trafficService;

    @PostMapping("/trafficUp")
    public void trafficUp(HttpSession session, HttpServletRequest request) {
        //存储时间,防止多次请求
        try{
            long time =(long) session.getAttribute("trafficUptime");
            if (System.currentTimeMillis() - time < 5000) {
                return;
            }
        }catch (Exception e){
        }finally {
            session.setAttribute("trafficUptime", System.currentTimeMillis());
        }
        Traffic tr = new Traffic();
        tr.setIp(new GetIpUtils().getIpAddress(request));
        trafficService.saveTraffic(tr);
    }

    @GetMapping("/trafficByCreateTime")
    public Page<Traffic> trafficByCreateTime(@PageableDefault(size = 8, sort = {"ip"}, direction = Sort.Direction.DESC) Pageable pageable,
                                             String query) {
        if (query == null) {
            return null;
        }
        Page<Traffic> pt = trafficService.listTraffic("%" + query + "%", pageable);
        return pt;
    }

    /**
     * 24小时vp
     *
     * @return
     */
    @GetMapping("/trafficList24hours")
    public List<Map<String, Object>> trafficList24hours() {
        return trafficService.list24hours();
    }

    @GetMapping("/trafficTotal")
    public Integer trafficTotal() {
        return trafficService.findTotal();
    }
}
