package eu.ubis.fiimdb.controller;

import eu.ubis.fiimdb.model.User;
import eu.ubis.fiimdb.service.ServiceFactory;
import eu.ubis.fiimdb.service.UserService;

public class UserBean {
	
	private UserService userService=ServiceFactory.getUserService();
	private User userDetails;
	
	public void registerNewUser(User user){
		userService.registerNewUser(user);
	}
	
	public User getUserDetails(){
		return this.userDetails;
	}
	
	public void setUserDetails(String username){
		this.userDetails=userService.getUserByUsername(username);
	}
	
	public void updateUser(String username, String firstName, String lastName, String email){
		userService.updateUser(username, firstName, lastName, email);
	}
	
}
