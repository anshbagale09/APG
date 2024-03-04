package com.animal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.animal.Entity.Plan;
import com.animal.Service.PlanService;


@RestController
public class PlanController {
	
	@Autowired
	private PlanService planservice;
	
	
	public PlanController(PlanService planservice) {
		super();
		this.planservice = planservice;
	}


	@PostMapping("/saveplans")
	public ResponseEntity<Plan> savePlans(@RequestBody Plan plan)
	{
	
		Plan plans=planservice.savePlans(plan);
		return new ResponseEntity<>(plans,HttpStatus.CREATED);
	}
	
	@GetMapping("/getallplans")
	public ResponseEntity<List<Plan>> getAllPlans()
	{
		List<Plan> p=planservice.getAllPlans();
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	 @DeleteMapping("/deleteplan/{id}")
		public ResponseEntity<String> deleteImages(@PathVariable("id") Long id) {
			planservice.deletePlan(id);

			return new ResponseEntity<>("Deleted......!!!!", HttpStatus.OK);
		}
	 
	 @GetMapping("getplanbyid/{id}")
	 public ResponseEntity<Plan> getplansbyid(@PathVariable ("id") Long id)
	 {
		 Plan p=planservice.getByid(id);
		 return new ResponseEntity<>(p,HttpStatus.OK);
	 }

}
