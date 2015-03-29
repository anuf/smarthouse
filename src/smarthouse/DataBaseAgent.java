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
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Manuel Castro Payán
 * @author Adolfo Núñez Fernández
 */
public class DataBaseAgent extends Agent {

    private final String temperaturePath = "temperatureLog.txt";
    private final String lightPath = "lightLog.txt";
    private final String intruderPath = "intruderLog.txt";
    private final String firePath = "fireLog.txt";
    private FileWriter temperatureFile;
    private FileWriter lightFile;
    private FileWriter intruderFile;
    private FileWriter fireFile;
    private LinkedList<String> lastTemperatures;
    private final int maxSize = 3;

    protected void setup() {
        System.out.println("Agent "+getLocalName()+" started.");
        lastTemperatures = new LinkedList<>();
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();
                if (msg != null) {
                    try {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Calendar cal = Calendar.getInstance();
                        String line = "[" + dateFormat.format(cal.getTime()) + "] ";
                        if (msg.getSender().getLocalName().equals("termometro")) {
                            temperatureFile = new FileWriter(temperaturePath, true);
                            temperatureFile.write(line + msg.getContent() + "\n");
                            temperatureFile.close();
                            lastTemperatures.add(msg.getContent());
                            if (lastTemperatures.size() > maxSize)
                                lastTemperatures.removeFirst();      
                        } else if (msg.getSender().getLocalName().equals("sensorLuz")) {
                            lightFile = new FileWriter(lightPath, true);
                            lightFile.write(line + msg.getContent() + "\n");
                            lightFile.close();
                        } else if (msg.getSender().getLocalName().equals("fireAlarm")) {
                            fireFile = new FileWriter(firePath, true);
                            fireFile.write(line + "Fire detected! \n");
                            fireFile.close();
                        } else if (msg.getSender().getLocalName().equals("intruderAlarm")) {
                            intruderFile = new FileWriter(intruderPath, true);
                            intruderFile.write(line + "Intruder detected! \n");
                            intruderFile.close();
                        } else if (msg.getSender().getLocalName().equals("controlador")) {
                            // Message to controller
                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            msg2.addReceiver(new AID("controlador",AID.ISLOCALNAME));
                            msg2.setContent(lastTemperatures.toString());
                            myAgent.send(msg2);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DataBaseAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                block();
            }
        });
    }
}
