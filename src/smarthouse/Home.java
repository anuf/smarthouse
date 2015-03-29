/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

/**
 * This class represents the environment. Implements the Singleton design pattern 
 * 
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class Home {
    private static Home instance = null;
    private double hTemperature = 17.0; // Home temperature
    private double confortTemperature = 20.0; // Confort temperature
    private int MAX_BRIGHTNESS = 90;
    private int MIN_BRIGHTNESS = 30;
    private int hBrightness = 60; // Home brightness
    private int confortBrightness = 55; // Confort Brightness
    private boolean hLightsAreOn = false;
    private boolean hFireAlarm = false; // Fire alarm
    private boolean hAntiTheft = false; // anti-theft system
    private boolean hDoorOpen = true; // Door state
    private boolean hWindowOpen = true; // Windows state
    private boolean hHomeEmpty = false; // Occupacy state
    
    protected Home() {
      // Exists only to defeat instantiation.
    }
   
    public static Home getInstance() {
      if(instance == null) {
         instance = new Home();
      }
      return instance;
    
    }
    
    /* Getters & setters*/
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
    
    public boolean getDoorOpen(){
        return hDoorOpen;
    }
    
    public void setDoorOpen(boolean pOpen){
        hDoorOpen = pOpen;
    }
    
    public boolean getWindowOpen(){
        return hWindowOpen;
    }
    
    public void setWindowOpen(boolean pOpen){
        hWindowOpen = pOpen;
    }
    
    public boolean getHomeEmpty(){
        return hHomeEmpty;
    }
    
    public void setHomeEmpty(boolean pEmpty){
        hHomeEmpty = pEmpty;
    }
    
    public int getBrightness(){
        return hBrightness;
    }
    
    public void setBrightness(int pBrightness){
        if(pBrightness > MAX_BRIGHTNESS){
            hBrightness = MAX_BRIGHTNESS;
        }else if(pBrightness < MIN_BRIGHTNESS){
            hBrightness = MIN_BRIGHTNESS;
        }else{
            hBrightness = pBrightness;
        }
        
    }
    
    public int getConfortBrightness(){
        return confortBrightness;
    }
    
    public void setConfortBrightness(int pConfBrightness){
        confortBrightness = pConfBrightness;
    }
    
    public void setLightsOn(boolean pLights){        
        hLightsAreOn = pLights;
    }
    
    public boolean getLightsOn(){
        return hLightsAreOn;
    }
    
}
