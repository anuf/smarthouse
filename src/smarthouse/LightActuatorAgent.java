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
public class LightActuatorAgent extends Agent{
    protected void setup(){
    
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null){
                    System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                    if(msg.getContent().equals("TURN ON")){
                        Home.getInstance().setLightsOn(true);
                        System.out.println("Lights have been turned ON");
                    }else if(msg.getContent().equals("TURN OFF")){
                        Home.getInstance().setLightsOn(false);
                        System.out.println("Lights have been turned OFF");
                    }else{
                    
                    }
                }
                block();
            }
        });
    }  
}