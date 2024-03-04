package com.animal.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.Entity.Plan;
import com.animal.Repository.PlanRepository;
import com.animal.Service.PlanService;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepository planrepo;
	

	public PlanServiceImpl(PlanRepository planrepo) {
		super();
		this.planrepo = planrepo;
	}


	@Override
	public Plan savePlans(Plan plan) {
		// TODO Auto-generated method stub
		return planrepo.save(plan);
	}


	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return planrepo.findAll();
	}


	@Override
	public String deletePlan(Long id) {
		// TODO Auto-generated method stub
		planrepo.deleteById(id);
		return "Deleted";
	}


	@Override
	public Plan getByid(Long id) {
		// TODO Auto-generated method stub
		Plan p=planrepo.findById(id).get();
		return p;
	}

}
