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
public class Sizes
{
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int sizeId;
    
    @Column (name="Size")
    private String size;
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products products;
    
    public int getSizeId()
    {
        return sizeId;
    }

    public void setSizeId(int sizeId)
    {
        this.sizeId = sizeId;
    }

    public Products getProducts()
    {
        return products;
    }

    public void setProducts(Products products)
    {
        this.products = products;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }
    
}
