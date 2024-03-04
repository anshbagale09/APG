package com.animal.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long planno;
	
	private String planname;
	
	private Long amount;
	
	private Long points;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPlanno() {
		return planno;
	}

	public void setPlanno(Long planno) {
		this.planno = planno;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plan(Long id, Long planno, String planname, Long amount, Long points) {
		super();
		this.id = id;
		this.planno = planno;
		this.planname = planname;
		this.amount = amount;
		this.points = points;
	}
	
	

}
