package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InStock
{
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int instockId;
    @Column(name="SKU")
    private String sku;
    @Column(name="Size")
    private String size;
    @Column (name="Color")
    private String color;
    @Column (name="Price")
    private double price;
    @Column (name="SalePrice")
    private double salePrice;
    @ManyToOne
    @JoinColumn (name="ProductID")
    private Products products;
    public String getSku()
    {
        return sku;
    }
    public void setSku(String sku)
    {
        this.sku = sku;
    }
    public String getSize()
    {
        return size;
    }
    public void setSize(String size)
    {
        this.size = size;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public int getInstockId()
    {
        return instockId;
    }
    public void setInstockId(int instockId)
    {
        this.instockId = instockId;
    }
    public Products getProducts()
    {
        return products;
    }
    public void setProducts(Products products)
    {
        this.products = products;
    }
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}
    
    
}
