/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

/**
 * This class represents the environment
 * 
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class Home {
    private static Home instance = null;
    private double hTemperature = 17.0; // Home temperature
    private double confortTemperature = 20.0; // Home temperature
    protected Home() {
      // Exists only to defeat instantiation.
    }
   
    public static Home getInstance() {
      if(instance == null) {
         instance = new Home();
      }
      return instance;
    
    }
    
    public double getTemperature(){
        return hTemperature;
    }
    
    public void setTemperature(double pTemperature){
        hTemperature = pTemperature;
    }
    
    public double getConfortTemperature(){
        return confortTemperature;
    }
    
    public void setConfortTemperature(double pTemperature){
        confortTemperature = pTemperature;
    }
}
