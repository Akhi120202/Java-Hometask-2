import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    	
        SystemLog sysLog = new SystemLog("system_log.txt");
        StationLog stLog = new StationLog("station_log.txt");
        EnergyLog engLog = new EnergyLog("energy_log.txt");

        DataExchangeSimulation simulation = new DataExchangeSimulation();
        simulation.simulateDataExchange();

        User externalUser = new User("ExternalUser2", false);
        sysLog.log("New user created, user: ExternalUser, Admin: No");
        User admin = new User("Admin1", true);
        sysLog.log("New user created, user: Admin1, Admin: No");

        // Create charging station
        ChargingStation chargingStation = new ChargingStation("Station1");
        sysLog.log("New station created. Station: Station1");

        chargingStation.loadStateFromFile();

        // Book timeslot and prioritize queue
        chargingStation.showAvailableTimeslots();
        //Scanner scanner = new Scanner(System.in);
        int choice = 3;
        //scanner.close();
        int[] choices = chargingStation.bookTimeslot(externalUser, choice);
        int energyChoice = choices[1];
        int chosenTimeslot = choices[0];
        String stationLogs = null;
        if (chosenTimeslot != -1) {
            stationLogs = externalUser.username + " booked timeslot " + chargingStation.getTimeRange(chosenTimeslot);
            //chargingStation.displayAvailableEnergySources();
            stLog.log(stationLogs);
            //Scanner scan = new Scanner(System.in);
            energyChoice = 1;
            //scan.close();
        }
        chargingStation.prioritizeQueue(admin);
  
        engLog.log("User made a selection "+chargingStation.getSource(energyChoice) +" in the energy management system.");
      

    }
       
}
