package com.animal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.animal.Entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {

}
