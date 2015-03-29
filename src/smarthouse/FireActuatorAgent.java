/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class FireActuatorAgent extends Agent{
   private String text = "";
    protected void setup(){
    
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null){
                    System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                    text = "Firemen have been notice";
                    System.out.println(text);
                    
                    // Message to controller
                    ACLMessage msg2controller=new ACLMessage(ACLMessage.INFORM);
                    msg2controller.addReceiver(new AID("controlador",AID.ISLOCALNAME));
                    msg2controller.addReceiver(new AID("firefighter",AID.ISLOCALNAME));
                    msg2controller.setContent(text);
                    myAgent.send(msg2controller);
                    
                }
                block();
            }
        });
    }
}