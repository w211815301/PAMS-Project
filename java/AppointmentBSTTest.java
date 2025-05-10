import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

public class AppointmentBSTTest {

	@Test
	public void testAddAppointment_emptyTree() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient", "09:00");
		bst.addAppointment(p1);
		assertNotNull(bst.getRoot());
		assertEquals(p1, bst.getRoot().data);
	}

	@Test
	public void testAddAppointment_leftAndRightChild() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "09:00");
		PatientAppointment p3 = new PatientAppointment(3, "Test Patient 3", "11:00");
		bst.addAppointment(p1);
		bst.addAppointment(p2);
		bst.addAppointment(p3);
		assertEquals(p2, bst.getRoot().left.data);
		assertEquals(p3, bst.getRoot().right.data);
	}

	@Test
	public void testAddAppointment_duplicateTime() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "10:00"); // Duplicate time
		bst.addAppointment(p1);
		bst.addAppointment(p2); // Should not add
		assertEquals(null, bst.getRoot().left);
		assertEquals(null, bst.getRoot().right);
	}

	@Test
	public void testInOrderTraversal() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient 1", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "09:00");
		PatientAppointment p3 = new PatientAppointment(3, "Test Patient 3", "11:00");
		bst.addAppointment(p1);
		bst.addAppointment(p2);
		bst.addAppointment(p3);

		StringBuilder output = new StringBuilder();
		bst.inOrderTraversal(bst.getRoot(), output);
		String expectedOutput = "2, Test Patient 2, 09:00\n1, Test Patient 1, 10:00\n3, Test Patient 3, 11:00\n";
		assertEquals(expectedOutput, output.toString());
	}

	@Test
	public void testDeleteAppointment_rootNode() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient", "10:00");
		bst.addAppointment(p1);
		bst.deleteAppointment("10:00");
		assertNull(bst.getRoot());
	}

	@Test
	public void testDeleteAppointment_leftChild() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient 1", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "09:00");
		PatientAppointment p3 = new PatientAppointment(3, "Test Patient 3", "11:00");
		bst.addAppointment(p1);
		bst.addAppointment(p2);
		bst.addAppointment(p3);
		bst.deleteAppointment("09:00");
		assertNull(bst.getRoot().left);
		assertEquals("10:00", bst.getRoot().data.appointmentTime);
	}

	@Test
	public void testDeleteAppointment_rightChild() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient 1", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "09:00");
		PatientAppointment p3 = new PatientAppointment(3, "Test Patient 3", "11:00");
		bst.addAppointment(p1);
		bst.addAppointment(p2);
		bst.addAppointment(p3);
		bst.deleteAppointment("11:00");
		assertNull(bst.getRoot().right);
		assertEquals("10:00", bst.getRoot().data.appointmentTime);
	}

	@Test
	public void testDeleteAppointment_nodeNotFound() {
		AppointmentBST bst = new AppointmentBST();
		PatientAppointment p1 = new PatientAppointment(1, "Test Patient 1", "10:00");
		PatientAppointment p2 = new PatientAppointment(2, "Test Patient 2", "09:00");
		PatientAppointment p3 = new PatientAppointment(3, "Test Patient 3", "11:00");
		bst.addAppointment(p1);
		bst.addAppointment(p2);
		bst.addAppointment(p3);
		bst.deleteAppointment("12:00"); // Attempt to delete a non-existent node
		assertEquals("10:00", bst.getRoot().data.appointmentTime); // Check root is still the same
		assertEquals("09:00", bst.getRoot().left.data.appointmentTime);
		assertEquals("11:00", bst.getRoot().right.data.appointmentTime);
	}
}