package com.backend.oculus.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "imeis")
public class Imei {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String imei;
	
	@ManyToOne
	@JsonIgnore
	private Oculus oculus;
	
	public Imei() {
		// TODO Auto-generated constructor stub
	}
	
	public Imei(String imei) {
		this.imei = imei;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImei() {
		return imei;
	}
	
	public void setImei(String imei) {
		this.imei = imei;
	}
	
	public Oculus getOculus() {
		return oculus;
	}
	
	public void setOculus(Oculus oculus) {
		this.oculus = oculus;
	}
	
	
}
