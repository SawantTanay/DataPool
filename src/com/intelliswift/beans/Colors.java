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
public class Colors
{
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private int colorId;
    @Column (name="Color")
    private String color;
    @Column (name="ColorSwatchURL")
    private String colorSwatchURL;
    @Column (name="ColorImageURL")
    private String colorImageURL;
    @Column (name="RGB")
    private String RGB;
    @ManyToOne
    @JoinColumn(name="ProductID")
    private Products products;

    public int getColorId()
    {
        return colorId;
    }
    public void setColorId(int colorId)
    {
        this.colorId = colorId;
    }
    public Products getProducts()
    {
        return products;
    }
    public void setProducts(Products products)
    {
        this.products = products;
    }
    public String getColor()
    {
        return color;
    }
    public void setColor(String color)
    {
        this.color = color;
    }
    public String getColorSwatchURL()
    {
        return colorSwatchURL;
    }
    public void setColorSwatchURL(String colorSwatchURL)
    {
        this.colorSwatchURL = colorSwatchURL;
    }
    public String getColorImageURL()
    {
        return colorImageURL;
    }
    public void setColorImageURL(String colorImageURL)
    {
        this.colorImageURL = colorImageURL;
    }
    public String getRGB()
    {
        return RGB;
    }
    public void setRGB(String rGB)
    {
        RGB = rGB;
    }



}
