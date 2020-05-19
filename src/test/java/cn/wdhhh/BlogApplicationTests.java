package cn.wdhhh;

import cn.wdhhh.dao.UserRepository;
import cn.wdhhh.service.TrafficService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Autowired
	private TrafficService trafficService;

	@Test
	public void contextLoads() {
		System.out.println(trafficService.findTotal());
	}

}
