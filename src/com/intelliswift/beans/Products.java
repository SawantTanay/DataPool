package com.intelliswift.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;



@Entity
public class Products
{
     @Transient
    private String retailerName;
    @Id
    @Column (name="ProductID")
    private String productId;
    @Column (name="Name")
    private String productName;
    @Column (name="Brand")
    private String brand;
    @Column (name="UPC")
    private String upc;
    @Lob
    @Column (name="Description")
    private String description;
    @Column (name="ImageURL")
    private String imageURL;
    @Column (name="SizeChartURL")
    private String sizeChartURL;

    @OneToOne (mappedBy="products")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Price price = new Price();
    @OneToOne (mappedBy="products")
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private ProductURL productUrl = new ProductURL();
    @OneToMany (mappedBy="products",cascade=CascadeType.ALL)
    private List<AlternateImage> alternateImageList = new ArrayList<AlternateImage>();
    @OneToMany (mappedBy="products",cascade=CascadeType.ALL)
    private List<Category> categoryList = new ArrayList<Category>();
    @OneToMany (mappedBy="products",cascade=CascadeType.ALL)
    private List<Sizes> sizeList = new ArrayList<Sizes>();
    @OneToMany (mappedBy="products", cascade=CascadeType.ALL)
    private List<Colors> colorList = new ArrayList<Colors>();
    @OneToMany (mappedBy="products",cascade=CascadeType.ALL)
    private List<InStock> inStockList = new ArrayList<InStock>();
    @Temporal (TemporalType.TIMESTAMP)
    private Date datetime;
    @ManyToOne
    @JoinColumn(name="Site_Id")
    private RetailerConfigs retailerConfigs;
    
    
    public String getRetailerName()
    {
        return retailerName;
    }
    public void setRetailerName(String retailerName)
    {
        this.retailerName = retailerName;
    }
    public String getProductId()
    {
        return productId;
    }
    public void setProductId(String productId)
    {
        this.productId = productId;
    }
    public String getProductName()
    {
        return productName;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }
    public String getBrand()
    {
        return brand;
    }
    public void setBrand(String brand)
    {
        this.brand = brand;
    }
    public String getUpc()
    {
        return upc;
    }
    public void setUpc(String upc)
    {
        this.upc = upc;
    }
    public Price getPrice()
    {
        return price;
    }
    public void setPrice(Price price)
    {
        this.price = price;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public String getImageURL()
    {
        return imageURL;
    }
    public void setImageURL(String imageURL)
    {
        this.imageURL = imageURL;
    }
    public List<Sizes> getSizeList()
    {
        return sizeList;
    }
    public void setSizeList(List<Sizes> sizeList)
    {
        this.sizeList = sizeList;
    }
    public List<Colors> getColorList()
    {
        return colorList;
    }
    public void setColorList(List<Colors> colorList)
    {
        this.colorList = colorList;
    }
    public Date getDatetime()
    {
        return datetime;
    }
    public void setDatetime(Date date)
    {
        this.datetime = date;
    }
    public RetailerConfigs getRetailerConfigs()
    {
        return retailerConfigs;
    }
    public void setRetailerConfigs(RetailerConfigs retailerConfigs)
    {
        this.retailerConfigs = retailerConfigs;
    }
    /**
     * @return the sizeChartURL
     */
    public String getSizeChartURL()
    {
        return sizeChartURL;
    }
    /**
     * @param sizeChartURL the sizeChartURL to set
     */
    public void setSizeChartURL(String sizeChartURL)
    {
        this.sizeChartURL = sizeChartURL;
    }

    public List<InStock> getInStockList()
    {
        return inStockList;
    }
    public void setInStockList(List<InStock> inStockList)
    {
        this.inStockList = inStockList;
    }

    public ProductURL getProductUrl()
    {
        return productUrl;
    }
    public void setProductUrl(ProductURL productUrl)
    {
        this.productUrl = productUrl;
    }
    public List<AlternateImage> getAlternateImageList()
    {
        return alternateImageList;
    }
    public void setAlternateImageList(List<AlternateImage> alternateImageList)
    {
        this.alternateImageList = alternateImageList;
    }
    public List<Category> getCategoryList()
    {
        return categoryList;
    }
    public void setCategoryList(List<Category> categoryList)
    {
        this.categoryList = categoryList;
    }


}
