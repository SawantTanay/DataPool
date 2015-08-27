package com.intelliswift.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;

@Entity
public class RetailerConfigs
{
    
    @Column (name="Site")
    private String siteName;
    @Column (name="Retailer")
    private String retailer;
    @Column (name="Locale")
    private String locale;
    @Column (name="Domain")
    private String domain;
    @OneToMany 
    private List<Products> productList = new ArrayList<Products>();
    
    
}
