/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer
 */
public class Carsmodel {
    private final String registration;
    private final String model;
    private final String brand;
    private final String year;
    private final String price;
    private final String status;
    
    public Carsmodel(String registration, String model,  String brand, String year,String price, String status) {
        this.registration = registration;
        this.model = model;
        this.brand = brand;
        this.year= year;
        this.price  = price;
        this.status= status;
    }
    public String getRegister(){
        return registration;
    }
    public String getModel(){
        return model;
    }
    public String getBrand(){
        return brand;
    }
    public String getYear(){
        return year;
    }
    public String getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }
    
}
