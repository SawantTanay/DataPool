package com.intelliswift.Utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.TimeZone;

import javax.swing.SizeSequence;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.intelliswift.beans.AlternateImage;
import com.intelliswift.beans.Category;
import com.intelliswift.beans.Colors;
import com.intelliswift.beans.InStock;
import com.intelliswift.beans.Price;
import com.intelliswift.beans.ProductURL;
import com.intelliswift.beans.Products;
import com.intelliswift.beans.Sizes;
import com.intelliswift.beans.XMLConstants;


public class XMLHandler extends DefaultHandler
{
    private final Stack<String> tagsStack = new Stack<String>();

    private List<Products> productsList = null;
    private static SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z");
    private Products products;
    private Sizes sizes;
    private Price price;
    private Colors color;
    private InStock instock;
    private ProductURL productURL;
    private AlternateImage alternateImage;
    private Category category;
    private String categoryPart;
    

    private boolean feed = false;
    private boolean direct = false;
    private boolean sdc = false;

    private final StringBuilder tempVal = new StringBuilder();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        // TODO Auto-generated method stub
        super.characters(ch, start, length);
        tempVal.append(ch,start,length);
        
    }

    @Override
    public void endDocument() throws SAXException
    {
        // TODO Auto-generated method stub
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException, EmptyStackException
    {
        // TODO Auto-generated method stub
        super.endElement(uri, localName, qName);
        String tag = peekTag();
        if(!qName.equals(tag)){
            throw new InternalError();
        }

        popTag();

        String parentTag = peekTag();

        switch(tag) {
            case XMLConstants.TAG_BRAND :
                products.setBrand(tempVal.toString());
                break;
            case XMLConstants.TAG_DESCRIPTION :
                products.setDescription(tempVal.toString());
                break;
            case XMLConstants.TAG_PRODUCTID :
                products.setProductId(tempVal.toString());
                break;
            case XMLConstants.TAG_RETAILER : 
                products.setRetailerName(tempVal.toString());
                break;
            case XMLConstants.TAG_SIZECHARTURL :
                products.setSizeChartURL(tempVal.toString());
                break;
            case XMLConstants.TAG_UPC : 
                products.setUpc(tempVal.toString());
                break;
            case XMLConstants.TAG_SWATCHURL :
                color.setColorSwatchURL(tempVal.toString());
                break;
            case XMLConstants.TAG_RGB :
                color.setRGB(tempVal.toString());
                break;
            case XMLConstants.TAG_SKU :
                instock = new InStock();
                products.getInStockList().add(instock);
                instock.setSku(tempVal.toString());
                break;
            case XMLConstants.TAG_TIME :
                String inputDate = tempVal.toString();
                System.out.println(inputDate);
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                try {
                    products.setDatetime(sdf.parse(inputDate));
                }
                catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
        }

        if(XMLConstants.TAG_SIZE.equalsIgnoreCase(tag)) {
            String size = tempVal.toString();
            if(XMLConstants.TAG_PRODUCT.equalsIgnoreCase(parentTag)) {
                sizes.setSize(size);
                products.getSizeList().add(sizes);
            }
            else if(XMLConstants.TAG_INSTOCK.equalsIgnoreCase(parentTag)) {
                instock.setSize(size);
            }
        }
        else if(XMLConstants.TAG_PRICE.equalsIgnoreCase(tag)) {
            String priceRange = tempVal.toString();
            if(XMLConstants.TAG_PRODUCT.equalsIgnoreCase(parentTag)) {
                products.setPrice(price);
                if(priceRange.contains("–")) {
                    String []priceArr = priceRange.split("–");
                    price.setMinPrice(priceArr[0]);
                    price.setMaxPrice(priceArr[1]);
                }
                else {
                    price.setMinPrice(priceRange);
                    price.setMaxPrice(priceRange);
                }
            }
            else  if(XMLConstants.TAG_INSTOCK.equalsIgnoreCase(parentTag)){
                instock.setPrice(priceRange);
            }
        }
        else if(XMLConstants.TAG_SALEPRICE.equalsIgnoreCase(tag)) {
            String salePriceRange = tempVal.toString();
            if(XMLConstants.TAG_PRODUCT.equalsIgnoreCase(parentTag)) {
                if(salePriceRange.contains("–")) {
                    String []priceArr = salePriceRange.split("–");
                    price.setMinSalePrice(priceArr[0]);
                    price.setMaxSalePrice(priceArr[1]);
                }
                else {
                    price.setMinSalePrice(salePriceRange);
                    price.setMaxSalePrice(salePriceRange);
                }
            }
            else  if(XMLConstants.TAG_INSTOCK.equalsIgnoreCase(parentTag)){
                instock.setSalePrice(salePriceRange);
            }
        }
        else if(XMLConstants.TAG_NAME.equalsIgnoreCase(tag)) {
            String name = tempVal.toString();
            if(XMLConstants.TAG_PRODUCT.equalsIgnoreCase(parentTag)) {
                products.setProductName(name);
            }
            else if(XMLConstants.TAG_COLOR.equalsIgnoreCase(parentTag)) {
                color = new Colors();
                color.setColor(name);
                products.getColorList().add(color);
            }
        }
        else if(XMLConstants.TAG_IMAGEURL.equalsIgnoreCase(tag)) {
            String imageUrl = tempVal.toString();
            if(XMLConstants.TAG_PRODUCT.equalsIgnoreCase(parentTag)){
                products.setImageURL(imageUrl);
            }
            else if (XMLConstants.TAG_COLOR.equalsIgnoreCase(parentTag)) {
                color.setColorImageURL(imageUrl);
            }
            else if(XMLConstants.TAG_ALTERNATEIMAGES.equalsIgnoreCase(parentTag)) {
                alternateImage.setAlternateImage(imageUrl);
                products.getAlternateImageList().add(alternateImage);
            }
        }
        else if (XMLConstants.TAG_COLOR.equalsIgnoreCase(tag)) {
            String colorName = tempVal.toString();
            if(XMLConstants.TAG_INSTOCK.equalsIgnoreCase(parentTag)) {
                instock.setColor(colorName);
            }
        }
        else if (XMLConstants.TAG_PRODUCTURL.equalsIgnoreCase(tag)) {
            if(feed){
                productURL.setFeed(tempVal.toString());
                feed = false;
            }
            else if(direct) {
                productURL.setDirect(tempVal.toString());
                direct = false;
            }
            else if(sdc) {
                productURL.setSdc(tempVal.toString());
                sdc= false;
            }
            else if ((!feed) && (!sdc) && (!direct)) {
                productURL.setDirect(tempVal.toString());
            }
            products.setProductUrl(productURL);
        }
        else if(XMLConstants.TAG_PART.equalsIgnoreCase(tag)) {
            if(null != categoryPart && ! categoryPart.isEmpty() ) {
                categoryPart =categoryPart +" > "+tempVal.toString();    
            }
            else {
                categoryPart = tempVal.toString();
            }    
        }
        else if (XMLConstants.TAG_CATEGORY.equalsIgnoreCase(tag)) {
            category.setCategory(categoryPart);
            products.getCategoryList().add(category);
        }

    }

    @Override
    public void startDocument() throws SAXException
    {
        // TODO Auto-generated method stub
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException
    {
        // TODO Auto-generated method stub
        super.startElement(uri, localName, qName, attributes);
        pushTag(qName);
        tempVal.setLength(0);

        //System.out.println(qName);
        if(XMLConstants.TAG_PRODUCTS.equalsIgnoreCase(qName)){
            productsList = new ArrayList<Products>();
        }
        else if (XMLConstants.TAG_PRODUCT.equalsIgnoreCase(qName)) {
            products = new Products();
            productsList.add(products);
            productURL = new ProductURL();
        }
        else if (XMLConstants.TAG_PRODUCTURL.equalsIgnoreCase(qName)) {
            if (attributes.getLength() > 0) {
                if(attributes.getValue(0).equalsIgnoreCase("feed")){
                    feed = true;
                }
                else if(attributes.getValue(0).equalsIgnoreCase("direct")) {
                    direct= true;
                }
                else if (attributes.getValue(0).equalsIgnoreCase("sdc")) {
                    sdc=true;
                }
            }
        }
        else if (XMLConstants.TAG_SIZE.equalsIgnoreCase(qName)) {
            sizes = new Sizes();
        }
        else if(XMLConstants.TAG_PRICE.equalsIgnoreCase(qName)) {
            price = new Price();
        }
        else if (XMLConstants.TAG_CATEGORY.equalsIgnoreCase(qName)){
            categoryPart = new String();
            category = new Category();
        }
        else if (XMLConstants.TAG_ALTERNATEIMAGES.equalsIgnoreCase(qName)){
            alternateImage = new AlternateImage();
        }
        
    }

    public void pushTag(String qName) {
        tagsStack.push(qName);
//        System.out.println(tagsStack);
    }

    public String peekTag() {
        String element = null;
        try {
            element = tagsStack.peek();
        }
        catch(EmptyStackException e){
            e.printStackTrace();
        }
        return element;
    }

    public String popTag() {
        String element = null;
        try {
            element = tagsStack.pop();
        }
        catch(EmptyStackException e) {
            e.printStackTrace();
        }
        return element;
    }

    /**
     * @return the productsList
     */
    public List<Products> getProductsList()
    {
        return productsList;
    }

    /**
     * @param productsList the productsList to set
     */
    public void setProductsList(List<Products> productsList)
    {
        this.productsList = productsList;
    }
}
