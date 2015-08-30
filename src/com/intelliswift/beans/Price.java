package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Price
{
    @Id
    @Column(name="Price_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int priceId;
    @Column (name="minPrice")
    private double minPrice;
    @Column (name="MaxPrice")
    private double maxPrice;
    @Column (name="MinSalePrice")
    private double minSalePrice;
    @Column (name="MaxSalePrice")
    private double maxSalePrice;
    @OneToOne
    @JoinColumn(name="ProductID")
    private Products products;
    
    
    /**
     * @return the id
     */

    public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public double getMinSalePrice() {
		return minSalePrice;
	}
	public void setMinSalePrice(double minSalePrice) {
		this.minSalePrice = minSalePrice;
	}
	public double getMaxSalePrice() {
		return maxSalePrice;
	}
	public void setMaxSalePrice(double maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}
	/**
     * @return the products
     */
    public Products getProducts()
    {
        return products;
    }
    /**
     * @param products the products to set
     */
    public void setProducts(Products products)
    {
        this.products = products;
    }
    public int getPriceId()
    {
        return priceId;
    }
    public void setPriceId(int priceId)
    {
        this.priceId = priceId;
    }


}
