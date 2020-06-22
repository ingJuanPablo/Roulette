package com.masivian.ruleta.domain;

import java.io.Serializable;

public class Roulette implements Serializable {

	private static final long serialVersionUID = 0L;
	
	private String id;
	private String state;
	private String maxNumber;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(String maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
