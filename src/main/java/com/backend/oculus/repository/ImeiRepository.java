package com.backend.oculus.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.oculus.models.Imei;
import com.backend.oculus.models.Oculus;

@Repository
public interface ImeiRepository extends JpaRepository<Imei, Long>{
	List<Imei> findAll();
	
	List<Imei> findByOculus(Oculus oculus);
	
	@Query("SELECT i FROM Imei i WHERE i.oculus.id = :id")
	List<Imei> findImeiByOculus(Long id);
	
	Optional<Imei> findById(Long id);
}
