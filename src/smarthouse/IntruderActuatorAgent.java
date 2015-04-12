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
public class IntruderActuatorAgent extends Agent{
    private String text = "";
    int nMessages = 0;
    protected void setup(){
    
        System.out.println("Agent "+getLocalName()+" started.");
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
                ACLMessage msg= receive();
                if (msg!=null && nMessages == 1){
                    if (Double.parseDouble(msg.getContent()) >= 0.5) {
                        System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                        text = "Police has been notice";
                        System.out.println(text);

                        // Message to controller
                        ACLMessage msg2controller=new ACLMessage(ACLMessage.INFORM);
                        msg2controller.addReceiver(new AID("controlador",AID.ISLOCALNAME));
                        msg2controller.addReceiver(new AID("police",AID.ISLOCALNAME));
                        msg2controller.setContent(text);
                        myAgent.send(msg2controller); 
                        nMessages = 0;
                    }else {
                        System.out.println("False alarm.");
                        nMessages = 0;
                    }
                }else {
                    System.out.println("Received first detection. Waiting confirmation.");
                    nMessages++;
                }
                block();
            }
        });
    }
}