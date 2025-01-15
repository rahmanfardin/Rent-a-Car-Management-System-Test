package GUI;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class Parent_JFrameTest {

    private Parent_JFrame parentFrame;

    @Before
    public void setUp() {
        parentFrame = new Parent_JFrame();
    }

    @Test
    public void testMainFrameInitializationNotNull() {
        JFrame frame = Parent_JFrame.getMainFrame();
        assertNotNull("MainFrame should not be null", frame);
    }

    @Test
    public void testMainFrameInitializationVisibility() {
        JFrame frame = Parent_JFrame.getMainFrame();
        assertTrue("MainFrame should be visible", frame.isShowing());
    }

    @Test
    public void testMainFrameInitializationTitle() {
        JFrame frame = Parent_JFrame.getMainFrame();
        assertEquals("MainFrame title should be 'Rent-A-Car Management System'", "Rent-A-Car Management System", frame.getTitle());
    }

    @Test
    public void testExitMenuItem() {
        assertNotNull("Exit menu item should be present", findMenuItemByText("Exit"));
    }

    @Test
    public void testAddCarMenuItem() {
        assertNotNull("Add Car menu item should be present", findMenuItemByText("Add Car"));
    }

    @Test
    public void testUpdateCarMenuItem() {
        assertNotNull("Update Car menu item should be present", findMenuItemByText("Update Car"));
    }

    @Test
    public void testRemoveCarMenuItem() {
        assertNotNull("Remove Car menu item should be present", findMenuItemByText("Remove Car"));
    }

    @Test
    public void testViewBookedCarsMenuItem() {
        assertNotNull("View booked Cars menu item should be present", findMenuItemByText("View booked Cars"));
    }

    @Test
    public void testViewUnbookedCarsMenuItem() {
        assertNotNull("View Unbooked Cars menu item should be present", findMenuItemByText("View Unbooked Cars"));
    }

    @Test
    public void testAddCustomerMenuItem() {
        assertNotNull("Add Customer menu item should be present", findMenuItemByText("Add Customer"));
    }

    @Test
    public void testUpdateCustomerMenuItem() {
        assertNotNull("Update Customer menu item should be present", findMenuItemByText("Update Customer"));
    }

    @Test
    public void testRemoveCustomerMenuItem() {
        assertNotNull("Remove Customer menu item should be present", findMenuItemByText("Remove  Customer"));
    }

    @Test
    public void testAddCarOwnerMenuItem() {
        assertNotNull("Add Car Owner menu item should be present", findMenuItemByText("Add Car Owner"));
    }

    @Test
    public void testUpdateCarOwnerMenuItem() {
        assertNotNull("Update Car Owner menu item should be present", findMenuItemByText("Update Car Owner"));
    }

    @Test
    public void testRemoveCarOwnerMenuItem() {
        assertNotNull("Remove Car Owner menu item should be present", findMenuItemByText("Remove Car Owner"));
    }

    @Test
    public void testViewJavaDocMenuItem() {
        assertNotNull("View JavaDoc menu item should be present", findMenuItemByText("View JavaDoc"));
    }

    @Test
    public void testViewDocumentationMenuItem() {
        assertNotNull("View Documentation menu item should be present", findMenuItemByText("View Documentation"));
    }

    @Test
    public void testAboutMenuItem() {
        assertNotNull("About menu item should be present", findMenuItemByText("About"));
    }

    private JMenuItem findMenuItemByText(String text) {
        for (int i = 0; i < Parent_JFrame.getMainFrame().getJMenuBar().getMenuCount(); i++) {
            for (int j = 0; j < Parent_JFrame.getMainFrame().getJMenuBar().getMenu(i).getItemCount(); j++) {
                if (Parent_JFrame.getMainFrame().getJMenuBar().getMenu(i).getItem(j).getText().equals(text)) {
                    return Parent_JFrame.getMainFrame().getJMenuBar().getMenu(i).getItem(j);
                }
            }
        }
        return null;
    }
}
