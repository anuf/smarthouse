/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class FireSensorAgent extends Agent{
    protected void setup() {
        
        System.out.println("Agent "+getLocalName()+" started.");
  	addBehaviour(new MyBehaviour(this, 10000)); /*Looks for home occupacy every 10s*/
        
  	// Make this agent terminate
  	//doDelete();
    }
    
    /**
     * Behaviour that generates random binary outputs
     */
    class MyBehaviour extends TickerBehaviour{
        private boolean finished = false;
        
        private String text, fire;
        private boolean alert = false;
        public MyBehaviour(Agent a, int t){
            super(a,t);
        }
        protected void onTick() {
            // Simulate the fire
            if(Math.random() < 0.05){ // Fire is detected
                alert = true;
            }
            
            //System.out.println(door+" *** "+window+" *** "+casa);
            if (alert){
                text = "FIRE DETECTED";
                System.out.println("ALERT:  "+text);
            }else{
                text = "FIRE STATUS: NORMAL";
            }
            
            if(alert){
                // Message direcctly to Actuator
                ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("actorFire",AID.ISLOCALNAME));
                msg.setContent(text);
                myAgent.send(msg);
            }
            // Message to controller (STATUS TO BE LOGGED)
            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("controlador",AID.ISLOCALNAME));
            msg.setContent(text);
            myAgent.send(msg);
        }
    }   
}
