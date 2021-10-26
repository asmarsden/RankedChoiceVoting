package com.CS495.RankChoiceVoting.Controllers; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Model.User;
import com.CS495.RankChoiceVoting.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping ("/api/users")
@CrossOrigin
public class UserController {
	@Autowired
	public UserService userService;
	

	
	//@RequestMapping ("/user")
	//public Principal user ( HttpServletRequest request )
	//{
		//String Token = request.getHeader( " Authorization" ).substring( "Basic".length()).trim();
		//() -> new String( Base64.getDecoder().decode(Token)).split(":")[0];
	//}
	@PostMapping("/register")
	public void createUser (@RequestBody UserDTO userDTO)
	{
		userService.create(userDTO);
	}
	
	@PutMapping ("/{id}")
	//@PreAuthorize ("isAuthenticated()")
	public UserDTO update (@PathVariable ("id") Long id, @RequestBody UserDTO userDTO)
	{
		return null;
	}
	
	@DeleteMapping ("/{id}")
	//@Secured ("ROLE_ADMIN")
	public void delete (@PathVariable ("id") Long id)
	{
		
	}
}