package com.intelliswift.main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.hibernate.Session;
import org.xml.sax.SAXException;

import com.intelliswift.Utility.HibernateUtility;
import com.intelliswift.Utility.XMLHandler;
import com.intelliswift.beans.AlternateImage;
import com.intelliswift.beans.Category;
import com.intelliswift.beans.Colors;
import com.intelliswift.beans.InStock;
import com.intelliswift.beans.Price;
import com.intelliswift.beans.ProductURL;
import com.intelliswift.beans.Products;
import com.intelliswift.beans.Sizes;

public class DataPool
{

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
    	
        DataPool dp = new DataPool();
        List<Products> myList = dp.getProductList();
        Session session =  HibernateUtility.buildSessionFactory();
        session.beginTransaction();
        for (Iterator iterator = myList.iterator(); iterator.hasNext();) {
            Products products = (Products) iterator.next();
            Price pc = new Price();
            pc = products.getPrice();
            pc.setProducts(products);
            
            ProductURL productURL = new ProductURL();
            productURL = products.getProductUrl();
            productURL.setProducts(products);
            
            List<Sizes> sizeList = products.getSizeList();
            for(Sizes size : sizeList) {
                size.setProducts(products);
            }
            
            List<Colors> colorList = products.getColorList();
            for(Colors color : colorList){
                color.setProducts(products);
            }
              
            List<InStock> instockList = products.getInStockList();
            for (InStock instock : instockList) {
                instock.setProducts(products);
            }
            
            List<AlternateImage> alternateImageList = products.getAlternateImageList();
            for(AlternateImage alternateImage : alternateImageList) {
                alternateImage.setProducts(products);
            }
            
            List<Category> categoryList = products.getCategoryList();
            for(Category cat : categoryList){
                cat.setProducts(products);
            }
            session.save(products);
        }
        session.getTransaction().commit();
        session.close();
        System.out.println("Hello!   Program Executed.....");
        System.exit(0);
    }
    
    public List<Products> getProductList() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        XMLHandler xmlHandler = new XMLHandler();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(System.getenv("HOME")+"/service/data/feed/image/"+"agentprovocateur/agentprovocateur.xml"),xmlHandler);
        }
        catch (SAXException | IOException | ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return xmlHandler.getProductsList();
        
    }

}
