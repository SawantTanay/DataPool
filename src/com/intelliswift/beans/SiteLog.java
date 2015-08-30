package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class SiteLog {
	
	@Id
	@Column(name="EntryPoint")
	private String logId;
	@Column(name="Total_Products")
	private int numProds;
	@OneToOne
	@JoinColumn(name="Site_Id")
	private RetailerConfigs retailerConfigs;
	
	

	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public int getNumProds() {
		return numProds;
	}
	public void setNumProds(int numProds) {
		this.numProds = numProds;
	}
	public RetailerConfigs getRetailerConfigs() {
		return retailerConfigs;
	}
	public void setRetailerConfigs(RetailerConfigs retailerConfigs) {
		this.retailerConfigs = retailerConfigs;
	}
	
	
	
}
