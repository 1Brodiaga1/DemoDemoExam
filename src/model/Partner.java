package model;

public class Partner {
    private int id;
    private String typeOfCompany;
    private String nameOfCompany;
    private String legalAddress;
    private String tin;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String logo;
    private int rating;
    private double discount; // Расчетная скидка

    public Partner() {
    }

    public Partner(int id, String typeOfCompany, String nameOfCompany, String legalAddress,
                   String tin, String fullName, String phoneNumber, String email,
                   String logo, int rating) {
        this.id = id;
        this.typeOfCompany = typeOfCompany;
        this.nameOfCompany = nameOfCompany;
        this.legalAddress = legalAddress;
        this.tin = tin;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.logo = logo;
        this.rating = rating;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeOfCompany() {
        return typeOfCompany;
    }

    public void setTypeOfCompany(String typeOfCompany) {
        this.typeOfCompany = typeOfCompany;
    }

    public String getNameOfCompany() {
        return nameOfCompany;
    }

    public void setNameOfCompany(String nameOfCompany) {
        this.nameOfCompany = nameOfCompany;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}