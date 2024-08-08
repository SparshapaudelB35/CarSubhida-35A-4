/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Acer
 */
public class BookModel {
    private final String bookid;
    private final String registration;
    private final String id;
    private final String datepick;
    private final String datedrop;
   
    
    public BookModel(String bookid, String registration, String id,  String datepick, String datedrop ) {
        this.bookid=bookid;
        this.id = id;
        this.registration = registration;
        this.datepick = datepick;
        this.datedrop = datedrop;
    }
    public String getBookID(){
        return bookid;
    }
    public String getRegBox(){
        return registration;
    }
    public String getIDbox(){
        return id;
    }
    public String getPickDate(){
        return datepick;
    }
    public String getDropDate(){
        return datedrop;
    }
}
