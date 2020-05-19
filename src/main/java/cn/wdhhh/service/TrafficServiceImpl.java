package cn.wdhhh.service;

import cn.wdhhh.dao.TrafficRepository;
import cn.wdhhh.po.Traffic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wdh on 2020/4/17.
 */
@Service
public class TrafficServiceImpl implements TrafficService {

    @Autowired
    private TrafficRepository trafficRepository;

    @Override
    public Traffic saveTraffic(Traffic t) {
        t.setCreateTime(new Date());
        return trafficRepository.save(t);
    }

    @Override
    public Page<Traffic> listTraffic(String query, Pageable pageable) {
        return trafficRepository.findByQuery(query, pageable);
    }

    /**
     * 查询24小时数据
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> list24hours() {
        List<Map<String, Object>> lm = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            String hoursStr = i < 10 ? " " + "0" + i + ":" : " " + i + ":";
            Map<String, Object> map = new HashMap<>();
            map.put("x", (hoursStr + "00").substring(1));
            map.put("y", trafficRepository.findByQuery("%" + hoursStr + "%"));
            lm.add(map);
        }
        return lm;
    }

    @Override
    public Integer findTotal() {
        return trafficRepository.findTotal();
    }

    @Override
    public void deleteTraffic(Long id) {
        trafficRepository.deleteById(id);
    }
}
