/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class TemperatureActuatorAgent extends Agent{
    
    protected void setup(){
    
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null){
                    System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                    double increment = Double.valueOf(msg.getContent());
                    double newTemp = Home.getInstance().getTemperature() + increment;
                    //System.out.println( "Setting temperature: " + newTemp);
                    if (increment > 0)
                        System.out.println("Increasing temperature");
                    else
                        System.out.println("Decreasing temperature");
                    Home.getInstance().setTemperature(newTemp);
                }
                block();
            }
        });
    }  
}