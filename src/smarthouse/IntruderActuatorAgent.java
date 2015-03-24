/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthouse;

import jade.core.Agent;
/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández 
 */
public class IntruderActuatorAgent extends Agent{
   protected void setup() {
  	System.out.println("I am a "+getLocalName());
  	
  	// Make this agent terminate
  	doDelete();
  } 
}