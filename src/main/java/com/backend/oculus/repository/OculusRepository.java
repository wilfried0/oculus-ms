package com.backend.oculus.repository;

import org.springframework.stereotype.Repository;

import com.backend.oculus.models.Oculus;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface OculusRepository extends JpaRepository<Oculus, Long>{
	
	List<Oculus> findByIdIn(List<Long> userIds);
	
	List<Oculus> findAll();
	
	Optional<Oculus> findById(Long id);
	
	List<Oculus> findByVille(String ville);
	
	List<Oculus> findByIncident(String incident);
	
	List<Oculus> findByVilleAndIncident(String ville, String incident);
}