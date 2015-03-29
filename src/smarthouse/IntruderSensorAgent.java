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
public class IntruderSensorAgent extends Agent{
    protected void setup() {
        
        System.out.println("Agent "+getLocalName()+" started.");
  	addBehaviour(new MyBehaviour(this, 20000)); /*Looks for home occupacy every 20s*/

    
  	// Make this agent terminate
  	//doDelete();
    }
    
    /**
     * Behaviour that generates random binary outputs
     */
    class MyBehaviour extends TickerBehaviour{
        private boolean finished = false;
        
        private String text, door, window, casa;
        private boolean alert = false;
        public MyBehaviour(Agent a, int t){
            super(a,t);
        }
        protected void onTick() {
            alert = false;
            // Simulate the door
            if(Math.random() < 0.3){ // Door is closed
                Home.getInstance().setDoorOpen(false);
                door = "Door: CLOSED";
            }else{ // Door is open
                Home.getInstance().setDoorOpen(true);
                door = "Door: OPEN";
            }
            // Simulate the window
            if(Math.random() < 0.8){ // Window is closed
                Home.getInstance().setWindowOpen(false);
                window = "Window: CLOSED";
            }else{ // Window is open
                Home.getInstance().setWindowOpen(true);
                window = "Window: OPEN";
            }
            
            // Simulate occupacy
            if(Math.random() > 0.6){
                Home.getInstance().setHomeEmpty(true);
                casa = "Home: EMPTY";
            }else{
                Home.getInstance().setHomeEmpty(false);
                casa = "Home: NOT EMPTY";	
            }
            
            //System.out.println(door+" *** "+window+" *** "+casa);
            if (!Home.getInstance().getHomeEmpty() && !Home.getInstance().getDoorOpen() && !Home.getInstance().getWindowOpen() ){
                text = "INTRUDER DETECTED";
                System.out.println("ALERT:  "+text);
                alert = true;
            }else{
                text = "SECURITY STATUS: NORMAL " + door+" *** "+window+" *** "+casa;
            }
            
            if(alert){
                // Message direcctly to Actuator
                ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(new AID("actorIntruder",AID.ISLOCALNAME));
                msg.addReceiver(new AID("db",AID.ISLOCALNAME));
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
