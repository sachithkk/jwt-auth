package com.login.pool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.login.pool.domain.User;
import com.login.pool.request.JwtAuthenticationResponse;
import com.login.pool.request.UserLoginRequest;
import com.login.pool.request.UserRequest;
import com.login.pool.response.Response;
import com.login.pool.security.JwtTokenProvider;
import com.login.pool.service.UserServiceImpl;

@RestController
@RequestMapping("/pool/user")
public class UserController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtTokenProvider tokenProvider;
	
	@RequestMapping(method=RequestMethod.POST , value="/save")
	public Response addStudent(@RequestBody UserRequest userRequest) {
	
		User result = userServiceImpl.addNewUser(userRequest);
		if(result != null) {
			Response response = new Response();
			response.setStatusCode("201");
			response.setObject(result);
			
			return response;
		}
		
		Response response = new Response();
		response.setStatusCode("204");
		response.setObject("User not create");
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/login")
	public JwtAuthenticationResponse userLogin(@RequestBody UserLoginRequest userLoginRequest) {
		
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		userLoginRequest.getEmail(),
                		userLoginRequest.getPassword()
                )
        );
		
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setAccessToken(jwt);
        
        return jwtAuthenticationResponse;
	}
}
