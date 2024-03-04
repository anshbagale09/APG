package com.animal.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.animal.Entity.Plan;

@Service
public interface PlanService {
	
	 Plan savePlans(Plan plan);
	 
	 List<Plan>getAllPlans();
	 
	 String deletePlan(Long id);
	 
	 Plan getByid(Long id);

}
