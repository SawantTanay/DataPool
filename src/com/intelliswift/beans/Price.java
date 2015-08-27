package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Price
{
    @Id
    @Column(name="Price_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int priceId;
    @Column (name="minPrice")
    private String minPrice;
    @Column (name="MaxPrice")
    private String maxPrice;
    @Column (name="MinSalePrice")
    private String minSalePrice;
    @Column (name="MaxSalePrice")
    private String maxSalePrice;
    @OneToOne
    @JoinColumn(name="ProductID")
    private Products products;
    
    public String getMinPrice()
    {
        return minPrice;
    }
    public void setMinPrice(String minPrice)
    {
        this.minPrice = minPrice;
    }
    public String getMaxPrice()
    {
        return maxPrice;
    }
    public void setMaxPrice(String maxPrice)
    {
        this.maxPrice = maxPrice;
    }
    public String getMinSalePrice()
    {
        return minSalePrice;
    }
    public void setMinSalePrice(String minSalePrice)
    {
        this.minSalePrice = minSalePrice;
    }
    public String getMaxSalePrice()
    {
        return maxSalePrice;
    }
    public void setMaxSalePrice(String maxSalePrice)
    {
        this.maxSalePrice = maxSalePrice;
    }
    /**
     * @return the id
     */

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
