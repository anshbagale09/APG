package com.animal.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.Entity.Register;
import com.animal.Repository.RegisterRepository;
import com.animal.Service.RegisterService;

@Service
public class RegisterImpl implements RegisterService {
	@Autowired
	private RegisterRepository repo;

	public RegisterImpl(RegisterRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Register saveUser(Register register) {
		// TODO Auto-generated method stub
		
		return repo.save(register);
	}

	@Override
	public Register getUserData(String email, String password) {
		// TODO Auto-generated method stub
		 List<Register> users = repo.findByEmailAndPassword(email, password);

		    if (!users.isEmpty()) {
		        // Assuming you want to return the first user if there are multiple matches
		        return users.get(0);
		    } else {
		        return null;
		    }
	}

	@Override
	public Register getUserById(Long userId) {
		// TODO Auto-generated method stub
		return repo.findById(userId).orElse(null);
	}


}
