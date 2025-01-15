package GUI;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class MainMenuTest {

	private MainMenu mainMenu;
	private JFrame frame;

	@Before
	public void setUp() {
		Parent_JFrame.getMainFrame();

		mainMenu = new MainMenu();
		frame = new JFrame();
		frame.setContentPane(mainMenu.getMainPanel());
		frame.setSize(1366, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Test
	public void testCustomerButtonVisibility() {
		JButton customerButton = findButtonByText("Customer", mainMenu.getMainPanel());
		assertTrue(customerButton.isVisible());
	}

	@Test
	public void testCarsButtonVisibility() {
		JButton carsButton = findButtonByText("Cars", mainMenu.getMainPanel());
		assertTrue(carsButton.isVisible());
	}

	@Test
	public void testOwnerButtonVisibility() {
		JButton ownerButton = findButtonByText("Owner", mainMenu.getMainPanel());
		assertTrue(ownerButton.isVisible());
	}

	@Test
	public void testLogoutButtonVisibility() {
		JButton logoutButton = findButtonByText("Logout", mainMenu.getMainPanel());
		assertTrue(logoutButton.isVisible());
	}

	@Test
	public void testBookingDetailsButtonVisibility() {
		JButton bookingDetailsButton = findButtonByText("Booking Details", mainMenu.getMainPanel());
		assertTrue(bookingDetailsButton.isVisible());
	}

	@Test
	public void testCustomerButton() {
		JButton customerButton = findButtonByText("Customer", mainMenu.getMainPanel());
		customerButton.doClick();
		assertNotNull("Customer_Details should be loaded", Parent_JFrame.getMainFrame().getContentPane());
	}

	@Test
	public void testCarsButton() {
		JButton carsButton = findButtonByText("Cars", mainMenu.getMainPanel());
		carsButton.doClick();
		assertNotNull("Car_Details should be loaded", Parent_JFrame.getMainFrame().getContentPane());
	}

	@Test
	public void testOwnerButton() {
		JButton ownerButton = findButtonByText("Owner", mainMenu.getMainPanel());
		ownerButton.doClick();
		assertNotNull("CarOwner_Details should be loaded", Parent_JFrame.getMainFrame().getContentPane());
	}

	@Test
	public void testLogoutButton() {
		JButton logoutButton = findButtonByText("Logout", mainMenu.getMainPanel());
		logoutButton.doClick();
		assertNotNull("Frame should be disposed", Parent_JFrame.getMainFrame().getContentPane());
	}

	@Test
	public void testBookingDetailsButton() {
		JButton bookingDetailsButton = findButtonByText("Booking Details", mainMenu.getMainPanel());
		bookingDetailsButton.doClick();
		assertNotNull("Booking_Details should be loaded", Parent_JFrame.getMainFrame().getContentPane());
	}

	private JButton findButtonByText(String text, JPanel panel) {
		for (java.awt.Component comp : panel.getComponents()) {
			if (comp instanceof JButton && ((JButton) comp).getText().equals(text)) {
				return (JButton) comp;
			}
		}
		return null;
	}
}
