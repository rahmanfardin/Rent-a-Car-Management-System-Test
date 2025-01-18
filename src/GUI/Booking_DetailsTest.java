package GUI;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Booking_DetailsTest {

    private Booking_Details bookingDetails;
    private JFrame frame;

    @Before
    public void setUp() {
        // Ensure Parent_JFrame is initialized
        Parent_JFrame.getMainFrame();

        bookingDetails = new Booking_Details();
        frame = new JFrame();
        frame.setContentPane(bookingDetails.getMainPanel());
        frame.setSize(1366, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Test
    public void testSearchCustomerIDButton() {
        assertNotNull(Booking_Details.SearchCustomerID_Button);
        assertTrue(Booking_Details.SearchCustomerID_Button.isVisible());
    }

    @Test
    public void testSearchCarRegNoButton() {
        assertNotNull(Booking_Details.SearchCarRegNo_Button);
        assertTrue(Booking_Details.SearchCarRegNo_Button.isVisible());
    }

    @Test
    public void testBackButton() {
        assertNotNull(Booking_Details.BackButton);
        assertTrue(Booking_Details.BackButton.isVisible());
    }

    @Test
    public void testLogoutButton() {
        assertNotNull(Booking_Details.LogoutButton);
        assertTrue(Booking_Details.LogoutButton.isVisible());
    }

    @Test
    public void testBookCarButton() {
        assertNotNull(Booking_Details.BookCar_Button);
        assertTrue(Booking_Details.BookCar_Button.isVisible());
    }

    @Test
    public void testUnbookCarButton() {
        assertNotNull(Booking_Details.UnbookCar_Button);
        assertTrue(Booking_Details.UnbookCar_Button.isVisible());
    }

    @Test
    public void testDetailsFrameVisibility() {
        assertTrue(frame.isVisible());
    }

    @Test
    public void testTableData() {
        assertTrue(bookingDetails.jTable1.getRowCount() > 0);
    }
}
