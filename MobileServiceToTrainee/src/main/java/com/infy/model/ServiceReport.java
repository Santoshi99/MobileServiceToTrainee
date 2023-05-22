package com.infy.model;

import java.util.*;
public class ServiceReport {
	//your code goes here
	private Integer serviceId;
	private String Brand;
	private List<String> issues;
	private Float serviceFee;
	
	public ServiceReport(Integer serviceId, String Brand, List<String> issues, Float serviceFee) {
		this.serviceId = serviceId;
		this.Brand = Brand;
		this.issues = issues;
		this.serviceFee=serviceFee;
	}
	public Integer getServiceId() {
		return serviceId;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public List<String> getIssues() {
		return issues;
	}
	public void setIssues(List<String> issues) {
		this.issues = issues;
	}
	public Float getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Float serviceFee) {
		this.serviceFee = serviceFee;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	
	
}

