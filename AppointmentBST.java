public class AppointmentBST {
    private AppointmentNode root;

    public AppointmentBST() {
        root = null;
    }

    public void addAppointment(PatientAppointment appointment) {
        root = insertRec(root, appointment);
    }

    private AppointmentNode insertRec(AppointmentNode root, PatientAppointment appointment) {
        if (root == null) {
            return new AppointmentNode(appointment);
        }

        int comparison = appointment.appointmentTime.compareTo(root.data.appointmentTime);

        if (comparison < 0) {
            root.left = insertRec(root.left, appointment);
        } else if (comparison > 0) {
            root.right = insertRec(root.right, appointment);
        } else {
            System.out.println("Duplicate appointment time: " + appointment.appointmentTime + ". Not inserted.");
        }
        return root;
    }

    public void inOrderTraversal(AppointmentNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }

    public void inOrderTraversal(AppointmentNode node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.data).append("\n");
            inOrderTraversal(node.right, sb);
        }
    }

    public void deleteAppointment(String time) {
        root = deleteRec(root, time);
    }

    private AppointmentNode deleteRec(AppointmentNode root, String time) {
        if (root == null) {
            return null; // Node not found
        }

        int comparison = time.compareTo(root.data.appointmentTime);

        if (comparison < 0) {
            root.left = deleteRec(root.left, time);
        } else if (comparison > 0) {
            root.right = deleteRec(root.right, time);
        } else {
            // Node to be deleted found
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = findMin(root.right);
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data.appointmentTime);
        }
        return root;
    }

    private PatientAppointment findMin(AppointmentNode node) {
        PatientAppointment min = node.data;
        while (node.left != null) {
            min = node.left.data;
            node = node.left;
        }
        return min;
    }

    public AppointmentNode getRoot() {
        return root;
    }
}