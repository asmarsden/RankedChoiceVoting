package com.CS495.RankChoiceVoting.Controllers; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.CS495.RankChoiceVoting.DataTransferObjects.UserDTO;
import com.CS495.RankChoiceVoting.Services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping ("/api/users")
@CrossOrigin
public class UserController {
	
	public final UserService userService;
	
	@Autowired
	public UserController ( UserService userService ) 
	{
		this.userService = userService;
	}
	
	@RequestMapping ("/user")
	public Principal user ( HttpServletRequest request )
	{
		string Token = request.getHeader( " Authorization" ).substring( "Basic".length()).trim();
		return() -> new String( Base64.getDecoder().decode(Token)).split(":")[0];
	}
	
	@PutMapping ("/{id}")
	@PreAuthorize ("isAuthenticated()")
	public UserDTO update (@PathVariable ("id") Long id, @RequestBody UserDTO userDTO)
	{
		return null;
	}
	
	@DeleteMapping ("/{id}")
	@Secured ("ROLE_ADMIN")
	public void delete (@PathVariable ("id") Long id)
	{
		//Return 
	}
}