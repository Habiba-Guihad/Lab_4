/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab4_1;

/**
 *
 * @author Abdallah
 */
public class Product implements Item{
    private String productId;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    
    public Product(String productId, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        this.productId = productId;
        this.productName = productName;
        this.manufacturerName = manufacturerName;
        this.supplierName = supplierName;
        this.quantity = quantity;
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }
    
    @Override
    public  String lineRepresentation() {
        return productId + "," + productName + "," + manufacturerName + "," +
               supplierName + "," + quantity + "," + price;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getSearchKey() {
        return productId;
    }

    public float getPrice() {
        return price;
    }
    
    
    
    
}