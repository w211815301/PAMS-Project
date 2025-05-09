import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		AppointmentBST bst = new AppointmentBST();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("appointments.txt"));
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(", ");
				int id = Integer.parseInt(parts[0]);
				String name = parts[1];
				String time = parts[2];
				bst.addAppointment(new PatientAppointment(id, name, time));
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.out.println("Error closing file: " + e.getMessage());
				}
			}
		}

		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\nPatient Appointment Management System");
			System.out.println("1. Add Appointment");
			System.out.println("2. View Appointments");
			System.out.println("3. Cancel Appointment");
			System.out.println("0. Exit");
			System.out.print("Enter your choice: ");
			try {
				choice = scanner.nextInt();
				scanner.nextLine(); // Consume newline
				switch (choice) {
				case 1:
					addAppointment(bst, scanner);
					break;
				case 2:
					viewAppointments(bst);
					break;
				case 3:
					cancelAppointment(bst, scanner);
					break;
				case 0:
					System.out.println("Exiting system.");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine(); // Consume the invalid input
				choice = -1; // Loop again
			}
		} while (choice != 0);
		scanner.close();
	}

	private static void addAppointment(AppointmentBST bst, Scanner scanner) {
		System.out.print("Enter patient ID: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter patient name: ");
		String name = scanner.nextLine();
		System.out.print("Enter appointment time (HH:MM): ");
		String time = scanner.nextLine();
		bst.addAppointment(new PatientAppointment(id, name, time));
		System.out.println("Appointment added.");
	}

	private static void viewAppointments(AppointmentBST bst) {
		if (bst.getRoot() == null) {
			System.out.println("No appointments scheduled.");
			return;
		}
		System.out.println("Appointments in order:");
		bst.inOrderTraversal(bst.getRoot());
	}

	private static void cancelAppointment(AppointmentBST bst, Scanner scanner) {
		System.out.print("Enter the appointment time to cancel: ");
		String time = scanner.nextLine();
		bst.deleteAppointment(time);
		System.out.println("Appointment canceled (if it existed).");
	}
}