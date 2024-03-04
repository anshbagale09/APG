package com.animal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.animal.Entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{


	 @Query("SELECT COUNT(i) > 0 FROM Image i WHERE i.imageno = :imageno")
	    boolean existsByImageno(@Param("imageno") Long imageno);
	 
	 @Query("SELECT COUNT(i) > 0 FROM Image i WHERE i.imagename = :imagename")
	    boolean existsByImagename(@Param("imagename") String imagename);
	

}




