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
    Double velocity = 0.25;
    protected void setup(){
    
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null){
                    System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                    System.out.println( "Setting temperature: " +Math.round(Double.valueOf(msg.getContent())));
                    Home.getInstance().setTemperature(Math.round(Double.valueOf(msg.getContent())));
                }
                block();
            }
        });
    }  
}