package com.xawe.micro.requests;

import java.io.Serializable;

public class EcRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ecName;
	private Integer id;
	private String ecNumber;
	private Boolean isPF;
	private Boolean isPJ;
	public Boolean getIsPF() {
		return isPF;
	}
	public void setIsPF(Boolean isPF) {
		this.isPF = isPF;
	}
	public Boolean getIsPJ() {
		return isPJ;
	}
	public void setIsPJ(Boolean isPJ) {
		this.isPJ = isPJ;
	}
	public String getEcName() {
		return ecName;
	}
	public void setEcName(String ecName) {
		this.ecName = ecName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEcNumber() {
		return ecNumber;
	}
	public void setEcNumber(String ecNumber) {
		this.ecNumber = ecNumber;
	}
}
