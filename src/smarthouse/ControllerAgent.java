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
import jade.lang.acl.MessageTemplate;

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
                            // Message to controller
                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            msg2.addReceiver(new AID("db",AID.ISLOCALNAME));
                            msg2.setContent("Foo");
                            myAgent.send(msg2);
                            
                            // Receive repply.
                            MessageTemplate template = MessageTemplate.MatchSender(new AID("db",AID.ISLOCALNAME));
                            ACLMessage msgList = blockingReceive(template);
                            String lastTemps = msgList.getContent();
                            lastTemps = lastTemps.substring(1, lastTemps.length() - 2);
                            String [] lastValues = lastTemps.split(",");

                            // Message to actorTemperatura
                            ACLMessage msg3 = new ACLMessage(ACLMessage.INFORM);
                            msg3.addReceiver(new AID("actorTemperatura",AID.ISLOCALNAME));
                            if (temp > confortTemp) {
                                int test = 0;
                                if(lastValues.length == 3) {
                                    for (int i=0;i<lastValues.length;i++) {
                                        if (Double.valueOf(lastValues[i]) > confortTemp) {
                                            test++;
                                        }
                                    }
                                }

                                if (test == 3) {
                                    msg3.setContent("-1");
                                    System.out.println(" MSG: FAST DECREASING");
                                } else {
                                    msg3.setContent("-0.5");
                                }
                            } else if (temp < confortTemp) {
                                int test = 0;
                                if(lastValues.length == 3) {
                                    for (int i=0;i<lastValues.length;i++) {
                                        if (Double.valueOf(lastValues[i]) < confortTemp) {
                                            test++;
                                        }
                                    }
                                }

                                if (test == 3) {
                                    msg3.setContent("1");
                                    System.out.println(" MSG: FAST INCREASING");
                                } else {
                                    msg3.setContent("0.5");
                                }
                            }
                            myAgent.send(msg3);
                        }
                    }else if(msg.getSender().getLocalName().equals("sensorLuz")){
                        System.out.println( " * " +myAgent.getLocalName() + 
                                " <- message received from: " +msg.getSender().getLocalName() +
                                ". Current brightness: " + msg.getContent());
                        
                        // Check brightness and decide.
                        int brightness = Integer.valueOf(msg.getContent());
                        int cBrightness = Home.getInstance().getConfortBrightness();
                        boolean lightsOn = Home.getInstance().getLightsOn();
                        String textLights = "";
                        if (brightness > cBrightness && lightsOn) {
                            // Message to actorLuz
                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            msg2.addReceiver(new AID("actorLuz",AID.ISLOCALNAME));
                            msg2.setContent("TURN OFF");
                            myAgent.send(msg2);
                        }else if(brightness < cBrightness && !lightsOn){
                            // Message to actorLuz
                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            msg2.addReceiver(new AID("actorLuz",AID.ISLOCALNAME));
                            msg2.setContent("TURN ON");
                            myAgent.send(msg2);
                        }else{
                            textLights = "LIGHTS ARE OK";
                        }
                        
                        if(!textLights.equals("")){
                            System.out.println(" MSG: " +textLights);
                        }
                        
                        
                    }else{
                        // Messages have gone directly to actuators so they resend a notification 
                        System.out.println( " - " +myAgent.getLocalName() + " <- message received from: " +msg.getSender().getLocalName());
                        System.out.println( " MSG: " +msg.getContent());
                    }
                }
                block();
            }
        });
    } 
}