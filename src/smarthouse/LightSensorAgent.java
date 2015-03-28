/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import java.util.Random;
/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class LightSensorAgent extends Agent{
    protected void setup() {
        //AID id = new AID();
        //id.setLocalName("LightSensor");
        System.out.println("Agent "+getLocalName()+" started.");
  	//System.out.println("I am a "+id.getLocalName());
  	
        //addBehaviour(new MyBehaviour(this));
        
        // Add the TickerBehaviour (period 1 sec)
        addBehaviour(new TickerBehaviour(this, 1000) {
            Random rand = new Random();
            Boolean lightOn = false;
            protected void onTick() {
                //System.out.println("Agent "+myAgent.getLocalName()+": tick="+getTickCount());
                int randomInt = rand.nextInt(100);
                if (randomInt<10){
                    lightOn = !lightOn;
                }
                System.out.println("Light is: "+ (lightOn ? "ON": "OFF"));
          } 
        });
    
  	// Make this agent terminate
  	//doDelete();
    }
    
    class MyBehaviour extends SimpleBehaviour{
        private boolean finished = false;
        public MyBehaviour(Agent a){
            super(a);
        }
        public void action(){
        // Generar encendidos y apagados de luz cada cierto intervalo de tiempo aleatorio
        // Comportamiento cíclico que envíe cada segundo una señal a
            
            for (int idx = 1; idx <= 10; ++idx){
                
            }
            finished = true;
        }
        
        public boolean done(){
            return finished;
        }
    }
}
