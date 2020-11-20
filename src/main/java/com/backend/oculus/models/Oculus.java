package com.backend.oculus.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "Oculus")
public class Oculus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ville;
	private String incident;
	private String description;
	private String phone;
	private double latitude;
	private double longitude;
	private String image1;
	private String image2;
	private String created_at;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "oculus")
	private Set<Imei> multiImei = new HashSet<>();
	
	public Oculus() {
		// TODO Auto-generated constructor stub
	}
	
	/*public Oculus(String ville, String incident, String description, String phone, double latitude, double longitude, String created_at, String multiImei, String image1, String image2) {
		// TODO Auto-generated constructor stub
		this.ville = ville;
		this.incident = incident;
		this.description = description;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image1 = image1;
		this.image2 = image2;
		this.created_at = created_at;
	}*/
	
		
	
	public String getVille() {
		return ville;
	}
	
	public Oculus(String ville, String incident, String description, String phone, double latitude,
			double longitude, String created_at, String image1, String image2, Set<Imei> multiImei) {
		super();
		this.ville = ville;
		this.incident = incident;
		this.description = description;
		this.phone = phone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image1 = image1;
		this.image2 = image2;
		this.created_at = created_at;
		this.multiImei = multiImei;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getIncident() {
		return incident;
	}
	
	public void setIncident(String incident) {
		this.incident = incident;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getImage1() {
		return image1;
	}
	
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	
	public String getImage2() {
		return image2;
	}
	
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	
	public String getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public Set<Imei> getMultiImei() {
		return multiImei;
	}
	
	public void setMultiImei(Set<Imei> multiImei) {
		this.multiImei = multiImei;
	}

	@Override
	public String toString() {
		return "Oculus [id=" + id + ", ville=" + ville + ", incident=" + incident + ", description=" + description
				+ ", phone=" + phone + ", latitude=" + latitude + ", longitude=" + longitude + ", image1=" + image1
				+ ", image2=" + image2 + ", created_at=" + created_at + ", multiImei=" + multiImei + ", getVille()="
				+ getVille() + ", getIncident()=" + getIncident() + ", getDescription()=" + getDescription()
				+ ", getPhone()=" + getPhone() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + ", getImage1()=" + getImage1() + ", getImage2()=" + getImage2()
				+ ", getCreated_at()=" + getCreated_at() + ", getImeis()=" + getMultiImei() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
