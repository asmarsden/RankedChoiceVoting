package com.CS495.RankChoiceVoting;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Repository.UserRepository;
import com.CS495.RankChoiceVoting.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest extends RankChoiceVotingApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired 
	private ObjectMapper objectMapper;
	@MockBean 
	private UserService userService;
	@MockBean 
	private UserRepository userRepository;
	
	private JacksonTester<UserDTO> jsonTester;
	
	private UserDTO userDTO;
	
	@Before
	public void setup() {
		JacksonTester.initFields(this, objectMapper);
		userDTO = new UserDTO();
	}
	@Test
	public void testUserCreation() throws Exception {
		final String userDTOJson = jsonTester.write(userDTO).getJson();
		//given(userService.create(UserDTO.class));
		mockMvc.perform(post("/register").content(userDTOJson).contentType(APPLICATION_JSON))
		.andExpect(status().isCreated());
		//verify(userRepository).findUserByUserName(UserDTO.class);
		
	}
}