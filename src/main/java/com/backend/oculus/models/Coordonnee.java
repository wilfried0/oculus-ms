package com.backend.oculus.models;

public class Coordonnee {
	private double latitude;
	private double longitude;
	
	public Coordonnee() {
		// TODO Auto-generated constructor stub
	}
	
	public Coordonnee(double latitude, double longitude) {
		// TODO Auto-generated constructor stub
		this.latitude = latitude;
		this.longitude = longitude;
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
}
