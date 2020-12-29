package com.example.pbs.Customers;

public class Customer_Entity {

    private  String Cust_first_name,Cust_last_name,Area,Cust_category,Maximum_credit,phone_number,email_address;

    public Customer_Entity() {
    }

    public String getCust_first_name() {
        return Cust_first_name;
    }

    public void setCust_first_name(String cust_first_name) {
        Cust_first_name = cust_first_name;
    }

    public String getCust_last_name() {
        return Cust_last_name;
    }

    public void setCust_last_name(String cust_last_name) {
        Cust_last_name = cust_last_name;
    }

    public String getCust_category() {
        return Cust_category;
    }

    public void setCust_category(String cust_category) {
        Cust_category = cust_category;
    }

    public String getMaximum_credit() {
        return Maximum_credit;
    }

    public void setMaximum_credit(String maximum_credit) {
        Maximum_credit = maximum_credit;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    @Override
    public String toString() {
        return "Customer_Entity{" +
                "Cust_first_name='" + Cust_first_name + '\'' +
                ", Cust_last_name='" + Cust_last_name + '\'' +
                ", Cust_category='" + Cust_category + '\'' +
                ", Maximum_credit='" + Maximum_credit + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email_address='" + email_address + '\'' +
                ", Area='" + Area + '\'' +
                '}';
    }

}
