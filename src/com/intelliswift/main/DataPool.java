package com.intelliswift.main;

import java.io.File;
import java.io.FileNotFoundException;
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
import com.intelliswift.Utility.masterUtility;
import com.intelliswift.beans.AlternateImage;
import com.intelliswift.beans.Category;
import com.intelliswift.beans.Colors;
import com.intelliswift.beans.InStock;
import com.intelliswift.beans.Price;
import com.intelliswift.beans.ProductURL;
import com.intelliswift.beans.Products;
import com.intelliswift.beans.RetailerConfigs;
import com.intelliswift.beans.SiteLog;
import com.intelliswift.beans.Sizes;

public class DataPool
{
	
	private XMLHandler xmlHandler;
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		// TODO Auto-generated method stub
		boolean testing = false;
		String feedName="";
		String region = "";
		
		for (int i = 0; i < args.length; i++) {
			if(args[i].equalsIgnoreCase("-testing")) {
				testing = true;
			}
			else if(args[i].equalsIgnoreCase("-feed")) {
				i++;
				feedName = args[i].trim();
			}
			else if(args[i].equalsIgnoreCase("-region")) {
				i++;
				region = args[i].trim();
			}
			else {
				System.out.println("Argument Unknown : "+args[i]);
				System.exit(-1);
			}
		}

		masterUtility master = new masterUtility();
		RetailerConfigs retailerConfigs = master.getConfig(feedName);
		DataPool dp = new DataPool();
		List<Products> myList = dp.getProductList(testing,feedName,region);
		
		Session session =  HibernateUtility.buildSessionFactory();
		session.beginTransaction();
		
		SiteLog siteLog = new SiteLog();
		siteLog.setLogId(dp.xmlHandler.getEntryTime());
		siteLog.setNumProds(dp.xmlHandler.getTotalProducts());
		siteLog.setRetailerConfigs(retailerConfigs);
		retailerConfigs.setProductList(myList);
		retailerConfigs.setSiteLog(siteLog);
		
		for (Iterator iterator = myList.iterator(); iterator.hasNext();) {
			Products products = (Products) iterator.next();
			
			
			products.setRetailerConfigs(retailerConfigs);
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
//			session.save(products);
			session.saveOrUpdate(retailerConfigs);
		}
		session.getTransaction().commit();
		session.close();
		System.out.println("Hello!   Program Executed..... with"+dp.xmlHandler.getTotalProducts()+" Products");
		System.exit(0);
	}

	public List<Products> getProductList(boolean testing, String feedName, String region) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		xmlHandler = new XMLHandler(region);
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			String file = "";
			if(testing)
				file = System.getenv("HOME")+"/Desktop/"+feedName+".xml";
			else 
				file = System.getenv("HOME")+"/service/data/feed/image/"+feedName+"/"+feedName+".xml";
			saxParser.parse(new File(file),xmlHandler);
		}
		catch (SAXException | IOException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xmlHandler.getProductsList();

	}

}
