package model;

import java.sql.Date;

public class ImplementationHistory {
    private int id;
    private int partnerId;
    private int productId;
    private int quantity;
    private Date dateOfImplementation;
    private String productName; // Новое поле

    public ImplementationHistory() {}

    public ImplementationHistory(int id, int partnerId, int productId, int quantity, Date dateOfImplementation) {
        this.id = id;
        this.partnerId = partnerId;
        this.productId = productId;
        this.quantity = quantity;
        this.dateOfImplementation = dateOfImplementation;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getPartnerId() { return partnerId; }
    public void setPartnerId(int partnerId) { this.partnerId = partnerId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public Date getDateOfImplementation() { return dateOfImplementation; }
    public void setDateOfImplementation(Date dateOfImplementation) { this.dateOfImplementation = dateOfImplementation; }
    public String getProductName() { return productName; } // Новый геттер
    public void setProductName(String productName) { this.productName = productName; } // Новый сеттер
}