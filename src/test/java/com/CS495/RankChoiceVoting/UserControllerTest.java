//package com.CS495.RankChoiceVoting;
//
//
//import java.net.URI;
//import java.net.URISyntaxException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.RestTemplate;
//
//import com.CS495.RankChoiceVoting.Controllers.UserController;
//import com.CS495.RankChoiceVoting.Model.User;
//import com.CS495.RankChoiceVoting.Repository.UserRepository;
//import com.CS495.RankChoiceVoting.Services.UserService;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerTest extends RankChoiceVotingApplicationTests {
//	
//	@LocalServerPort
//	private int port;
//	
//	private String url;
//	
//	@MockBean 
//	private UserRepository userRepository;
//	
//	@Autowired
//	TestRestTemplate restTemplate;
//	
//	@BeforeEach
//    public void setUp() {
//        url = String.format("http://localhost:%d/", port);
//    }
//	
//	
//	@Test
//	void testUserCreation() throws URISyntaxException
//	{
//		var userToTest = new User();
//		
//		userToTest.setUserName("userTest");
//		userToTest.setUserID("123");
//		
//		
//		
//		
//		
//	}
	
	
//}