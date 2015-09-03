package com.intelliswift.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
@NamedQueries ({
    @NamedQuery(name="RetailerConfigs.all",query="from RetailerConfigs"),
    @NamedQuery(name="RetailerConfigs.locale",query ="from RetailerConfigs where Locale=?"),
    @NamedQuery(name="RetailerConfigs.siteName",query="from RetailerConfigs where Site=?")
})

public class RetailerConfigs
{
    @Id
    @Column (name="Site_Id")
    private String site_id;
    @Column (name="Site")
    private String siteName;
    @Column (name="Retailer")
    private String retailer;
    @Column (name="Locale")
    private String locale;
    @Column (name="Domain")
    private String domain;
//    @Transient
    @OneToMany (mappedBy="retailerConfigs", cascade=CascadeType.ALL)
    private List<Products> productList = new ArrayList<Products>();
    @OneToMany (mappedBy="retailerConfigs",cascade=CascadeType.ALL)
    private List<SiteLog> siteLogList = new ArrayList<SiteLog>();


    public List<SiteLog> getSiteLogList() {
		return siteLogList;
	}
	public void setSiteLogList(List<SiteLog> siteLogList) {
		this.siteLogList = siteLogList;
	}
	public String getSite_id() {
        return site_id;
    }
    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    public String getRetailer() {
        return retailer;
    }
    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }
    public String getLocale() {
        return locale;
    }
    public void setLocale(String locale) {
        this.locale = locale;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public List<Products> getProductList() {
        return productList;
    }
    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }


}
