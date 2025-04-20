
public class AppointmentNode {
    PatientAppointment data;
    AppointmentNode left, right;

    public AppointmentNode(PatientAppointment data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
