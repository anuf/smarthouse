/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class ControllerAgent extends Agent{
    protected void setup(){
        System.out.println("Agent "+getLocalName()+" started.");
        
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null){
                    if(msg.getSender().getLocalName().equals("termometro")){
                        System.out.println( " * " +myAgent.getLocalName() + 
                                " <- message received from: " +msg.getSender().getLocalName() +
                                ". Current temperature: " + msg.getContent());
                        // Check temperature and decide.
                        double temp = Double.valueOf(msg.getContent());
                        double confortTemp = Home.getInstance().getConfortTemperature();
                        
                        if (temp != confortTemp) {
                            // Message to actorTemperatura
                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            msg2.addReceiver(new AID("actorTemperatura",AID.ISLOCALNAME));
                            if (temp > confortTemp) {
                                msg2.setContent("-0.5");
                            } else if (temp < confortTemp) {
                                msg2.setContent("0.5");
                            }
                            myAgent.send(msg2);
                        }

                    }else if(msg.getSender().getLocalName().equals("actorIntruder")){
                        System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                        System.out.println( " MSG: " +msg.getContent());
                    }else{
                        System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                        System.out.println( " MSG: " +msg.getContent());
                    }
                }
                block();
            }
        });
    } 
}