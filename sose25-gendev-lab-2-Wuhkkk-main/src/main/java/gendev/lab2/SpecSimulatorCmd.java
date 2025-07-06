package gendev.lab2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import tau.smlab.syntech.controller.executor.ControllerExecutor;
import tau.smlab.syntech.games.controller.jits.BasicJitController;


public class SpecSimulatorCmd {

	public static void main(String[] args) throws IOException {

		Map<String, String> inputs = new HashMap<>();

		// Instantiate a new controller executor
		ControllerExecutor executor = new ControllerExecutor(new BasicJitController(), "out/jit", "Spec");
		Scanner scanner = new Scanner(System.in);
		boolean iniState = true;
		while (true) {
			// Read inputs from user or test case
            System.out.print("Enter carAtEntrance (true/false): ");
            boolean carAtEntrance = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Enter carAtExit (true/false): ");
            boolean carAtExit = Boolean.parseBoolean(scanner.nextLine());

            System.out.print("Enter availableSpots (0-10): ");
            int availableSpots = Integer.parseInt(scanner.nextLine());

            // Update controller state
            executor.update(carAtEntrance, carAtExit, availableSpots);

            // Get outputs
            boolean openEntranceGate = executor.getOpenEntranceGate();
            boolean openExitGate = executor.getOpenExitGate();
            int assignedSpot = executor.getAssignedSpot();

            // Print outputs
            System.out.println("Output:");
            System.out.println("openEntranceGate: " + openEntranceGate);
            System.out.println("openExitGate: " + openExitGate);
            System.out.println("assignedSpot: " + assignedSpot);
            System.out.println("-----------------------------");

			// execute controller
			if (iniState) {
				executor.initState(inputs);
				iniState = false;
			} else {
				executor.updateState(inputs);
			}

			// TODO read outputs and decide how to proceed
		}
	}
}
