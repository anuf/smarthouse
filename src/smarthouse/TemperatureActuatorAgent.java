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
                    if (increment > 0)
                        System.out.println(" MSG: INCREASING TEMPERATURE ");
                    else
                        System.out.println(" MSG: DECREASING TEMPERATURE ");
                    Home.getInstance().setTemperature(newTemp);
                }
                block();
            }
        });
    }  
}