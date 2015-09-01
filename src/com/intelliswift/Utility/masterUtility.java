package com.intelliswift.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.intelliswift.beans.RetailerConfigs;

public class masterUtility {

	public RetailerConfigs getConfig(String feedName) throws IOException, FileNotFoundException {
		RetailerConfigs retailerConfigs = new RetailerConfigs();
		File file = new File(System.getenv("HOME")+"/code/trunk/etc/RetailersConfig.csv");

		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;

		while((line=br.readLine())!= null){
			String[] lineArr = line.split(",");
			if(lineArr[0].equalsIgnoreCase(feedName)){
				retailerConfigs.setSite_id(getSiteKey(line));
				retailerConfigs.setSiteName(lineArr[0]);
				retailerConfigs.setRetailer(lineArr[1]);
				lineArr[2]=lineArr[2].replace("en_US", "US");
				lineArr[2]=lineArr[2].replace("de_DE", "DE");
				lineArr[2]=lineArr[2].replace("en_GB", "UK");
				lineArr[2]=lineArr[2].replace("fr_FR", "FR");
	                        lineArr[2]=lineArr[2].replace("ja_JP", "JP");
	                        lineArr[2]=lineArr[2].replace("en_AU", "AU");
	                        lineArr[2]=lineArr[2].replace("en_CA", "CA");
	                        retailerConfigs.setLocale(lineArr[2]);
				retailerConfigs.setDomain(lineArr[3]);
			}
		}
		return retailerConfigs;
	}
	
	 public static String getSiteKey(String comboKey)
	    {
	        MessageDigest md;
	        String skuId;
	        int id = 0;
	        try {
	            md = MessageDigest.getInstance("MD5");
	            md.update(comboKey.getBytes());
	            byte[] bytes = md.digest();
	            StringBuilder sb = new StringBuilder();
	            for (int i = 0; i < bytes.length; i++) {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            for (int i = 0; i < sb.length(); i++) {
	                int cha = sb.codePointAt(i);
	                id = ((id << 5) - id) + cha;
	                id |= 0;
	            }
	            id = Math.abs(id);
	        }
	        catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        skuId = Integer.toString(id);
	        return skuId;
	    }

}
