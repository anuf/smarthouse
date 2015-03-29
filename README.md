# smarthouse
Smart House and Home Automation Master Project

Nombres de los agentes para su ejecucuión:

controllerAgent: controlador
temperatureSensorAgent: termometro
temperatureActuatorAgent: actorTemperatura

EN NETBEANS:
Main Class: jade.Boot 
Arguments: -gui -agents controlador:smarthouse.ControllerAgent;actorTemperatura:smarthouse.TemperatureActuatorAgent;termometro:smarthouse.TemperatureSensorAgent;intruderAlarm:smarthouse.IntruderSensorAgent;actorIntruder:smarthouse.IntruderActuatorAgent

EN JAVA (no probado)
java jade.Boot -gui -agents controlador:smarthouse.ControllerAgent,actorTemperatura:smarthouse.TemperatureActuatorAgent,termometro:smarthouse.TemperatureSensorAgent, intruderAlarm:smarthouse.IntruderSensorAgent,actorIntruder:smarthouse.IntruderActuatorAgent