/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.Agent;
import jade.core.behaviours.*;
/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class LightSensorAgent extends Agent{
    protected void setup() {
  	System.out.println("I am a "+getLocalName());
  	
        addBehaviour(new MyBehaviour(this));
  	// Make this agent terminate
  	doDelete();
    } 
    class MyBehaviour extends SimpleBehaviour{
        public MyBehaviour(Agent a){
            super(a);
        }
        public void action(){
        // Generar encendidos y apagados de luz cada cierto intervalo de tiempo aleatorio
        // Comportamiento cíclico que envíe cada segundo una señal a
        }
        private boolean finished = false;
        public boolean done(){
            return finished;
        }
    }
}
