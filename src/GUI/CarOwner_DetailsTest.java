package GUI;

import org.junit.Before;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CarOwner_DetailsTest {

    private CarOwner_Details details;
    private JFrame frame;

    @Before
    public void setUp() {

        details = new CarOwner_Details();
        frame = new JFrame();
        frame.setContentPane(details.getMainPanel());
        frame.setSize(1366, 730);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Test
    public void testSearchIDButton() {
        assertNotNull(details.SearchID_Button);
        assertTrue(details.SearchID_Button.isVisible());
    }

    @Test
    public void testSearchNameButton() {
        assertNotNull(details.SearchName_Button);
        assertTrue(details.SearchName_Button.isVisible());
    }

    @Test
    public void testUpdateButton() {
        assertNotNull(details.Update_Button);
        assertTrue(details.Update_Button.isVisible());
    }

    @Test
    public void testAddButton() {
        assertNotNull(details.Add_Button);
        assertTrue(details.Add_Button.isVisible());
    }

    @Test
    public void testRemoveButton() {
        assertNotNull(details.Remove_Button);
        assertTrue(details.Remove_Button.isVisible());
    }

    @Test
    public void testBackButton() {
        assertNotNull(details.Back_Button);
        assertTrue(details.Back_Button.isVisible());
    }

    @Test
    public void testLogoutButton() {
        assertNotNull(details.Logout_Button);
        assertTrue(details.Logout_Button.isVisible());
    }

    @Test
    public void testClearBalanceButton() {
        assertNotNull(details.ClearBalance_Button);
        assertTrue(details.ClearBalance_Button.isVisible());
    }

    @Test
    public void testDetailsFrameVisibility() {
        assertTrue(frame.isVisible());
    }

    @Test
    public void testTableData() {
        assertTrue(details.jTable1.getRowCount() > 0);
    }
}
