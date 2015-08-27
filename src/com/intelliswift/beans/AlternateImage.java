package com.intelliswift.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class AlternateImage
{
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int imageId;
    @Lob
    @Column(name="AlternateImage")
    private String alternateImage;
    
    @ManyToOne 
    @JoinColumn (name="ProductID")
    private Products products;
    public int getImageId()
    {
        return imageId;
    }
    public void setImageId(int imageId)
    {
        this.imageId = imageId;
    }
    public String getAlternateImage()
    {
        return alternateImage;
    }
    public void setAlternateImage(String alternateImage)
    {
        this.alternateImage = alternateImage;
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
