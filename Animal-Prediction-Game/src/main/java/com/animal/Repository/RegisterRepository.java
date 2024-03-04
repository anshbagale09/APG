package com.animal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animal.Entity.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
	
	List<Register> findByEmailAndPassword(String email, String password);

}
