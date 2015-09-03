package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries ({
    @NamedQuery(name="SiteLog.bylogId",query="from SiteLog where Site_Id = ?")
})
public class SiteLog {
	
	@Id
	@Column(name="EntryPoint")
	private String logId;
	@Column(name="Total_Products")
	private int numProds;
	@ManyToOne
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
