
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AppointmentBST bst = new AppointmentBST();
        try (BufferedReader br = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String time = parts[2];
                bst.addAppointment(new PatientAppointment(id, name, time));
            }
        } catch (Exception e) {
            System.out.println("Error reading file.");
        }

        System.out.println("Appointments in order:");
        bst.inOrderTraversal(bst.getRoot());
    }
}
