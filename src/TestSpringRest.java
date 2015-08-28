

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.ai.dop.demo.domain.TestUser;
import com.ai.dop.demo.service.TestUserService;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public class TestSpringRest {

	FileSystemXmlApplicationContext ac = new   FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
	TestUserService testUserService = (TestUserService)ac.getBean("testUserService");

	
	@Test(expected=RuntimeException.class)
	public void testTransaction() {
		TestUserService testUserService = (TestUserService)ac.getBean("testUserService");
		testUserService.saveTestTransaction();	
	}
	
	//@Test
	public void testPage() {
		
		PageBounds bds = new PageBounds(2, 2);
		
		List<TestUser> list = testUserService.listUsers(new TestUser(), bds);
		System.out.println(list);
		PageList pageList = (PageList)list;
		System.out.println(pageList.getPaginator().getTotalCount());
		
	}
	
	
	
	@Test
	public void testRestSave() {
		String url = TestLogLevel.WEB_PREFIX +  "/userrest";
		
		
		TestUser bean = new TestUser();
		bean.setUserName("restUser");
		bean.setPasswd("rest密码");
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, bean);
	}
	
	
	@Test
	public void testRestSaveResCode() {
		String url =   TestLogLevel.WEB_PREFIX + "/userrest/reponseCode";
		
		
		TestUser bean = new TestUser();
		bean.setUserName("restUser");
		bean.setPasswd("rest密码");
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, bean);
		
		
		ResponseEntity responseEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity(bean),  (Class)null);
		
		System.out.println(responseEntity.getStatusCode());
	}
	
	@Test
	public void testRestList() {
		String url =  "http://localhost:8880/aiosp/userrest?userName={userName}";
		
		RestTemplate restTemplate = new RestTemplate();
		List<TestUser> lists = restTemplate.getForObject(url, List.class, "restUser");
	    System.out.println(lists);
		
	}
	
	 public static void main(String[] args) {
		
	}
	
}
