package com.backend.oculus.repository;

import org.springframework.stereotype.Repository;

import com.backend.oculus.models.Oculus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OculusRepository extends JpaRepository<Oculus, Long>{
	
	List<Oculus> findByIdIn(List<Long> userIds);
	
	List<Oculus> findAll();
}
