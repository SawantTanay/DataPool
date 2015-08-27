package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Category
{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int categoryId;
    @Column(name="Category")
    private String category;
    
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products products;

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
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
