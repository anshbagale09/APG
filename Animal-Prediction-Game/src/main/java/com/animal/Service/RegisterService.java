package com.animal.Service;

import com.animal.Entity.Register;

public interface RegisterService {
	
	 Register saveUser(Register register);
	 
	 Register getUserData(String email,String password);
	 
	 Register getUserById(Long userId);
	 
	
	
	

}
