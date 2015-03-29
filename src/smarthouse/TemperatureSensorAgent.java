/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;


import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;

import jade.lang.acl.ACLMessage;

import java.util.Random;

/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class TemperatureSensorAgent extends Agent{
    
	protected void setup() {
        
        System.out.println("Agent "+getLocalName()+" started.");
  	addBehaviour(new MyBehaviour(this, 5000));

    
  	// Make this agent terminate
  	//doDelete();
    }
    
    /**
     * Behaviour that generates random values slighly different from actual Home temperature as sensed temperatura
     */
    class MyBehaviour extends TickerBehaviour{
        private boolean finished = false;
        private double temp;
        private double rangeTemp = 3.0;
        public MyBehaviour(Agent a, int t){
            super(a,t);
        }
        protected void onTick() {           
            temp = randomInRange(Home.getInstance().getTemperature()-rangeTemp, Home.getInstance().getTemperature()+rangeTemp);
                
            // Message to controller
            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            msg.addReceiver(new AID("controlador",AID.ISLOCALNAME));
            msg.addReceiver(new AID("db",AID.ISLOCALNAME));
            msg.setContent(Double.toString(temp));
            myAgent.send(msg);
        }
    }
    /**
     * Generates a random double value between min and max 
     */
    private double randomInRange(double min, double max){
        double range = (max - min) + 1;     
        return (Math.round(Math.random() * range) + min + 0.5);
    }    
}
