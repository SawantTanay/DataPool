package com.intelliswift.beans;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class ProductURL
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long productURLID;
    @Lob
    private String direct;
    @Lob
    private String feed;
    @Lob
    private String sdc;
    @OneToOne
    @JoinColumn(name="ProductID")
    private Products products;
    
    public String getDirect()
    {
        return direct;
    }
    public void setDirect(String direct)
    {
        this.direct = direct;
    }
    public String getFeed()
    {
        return feed;
    }
    public void setFeed(String feed)
    {
        this.feed = feed;
    }
    public String getSdc()
    {
        return sdc;
    }
    public void setSdc(String sdc)
    {
        this.sdc = sdc;
    }
    public long getProductURLID()
    {
        return productURLID;
    }
    public void setProductURLID(long productURLID)
    {
        this.productURLID = productURLID;
    }
    public Products getProducts()
    {
        return products;
    }
    public void setProducts(Products products)
    {
        this.products = products;
    }
    
}
