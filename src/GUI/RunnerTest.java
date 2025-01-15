package GUI;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RunnerTest {

    private Runner runner;
    

    @Before
    public void setUp() {
        runner = new Runner();
    }

    @Test
    public void testFrameNotNull() {
        JFrame frame = Runner.getFrame();
        assertNotNull("FRAME should not be null", frame);
    }

    @Test
    public void testFrameWidth() {
        JFrame frame = Runner.getFrame();
        assertEquals("FRAME width should be 1000", 1000, frame.getWidth());
    }

    @Test
    public void testFrameHeight() {
        JFrame frame = Runner.getFrame();
        assertEquals("FRAME height should be 534", 534, frame.getHeight());
    }

    @Test
    public void testWelcomeLabelIconNotNull() {
    	JFrame frame = Runner.getFrame();
        JLabel label = (JLabel) Runner.getFrame().getContentPane().getComponent(1);    
        assertNotNull("Label icon should not be null", label);
    }

    @Test
    public void testLoginPanelLoad() {
        try {
            Thread.sleep(1000); // Wait for the splash screen to disappear
            Login loginObject = new Login();
            Runner.getFrame().getContentPane().removeAll();
            Runner.getFrame().add(loginObject.getMainPanel());
            Runner.getFrame().getContentPane().revalidate();
            assertNotNull("Login panel should be loaded", loginObject.getMainPanel());
        } catch (InterruptedException e) {
            fail("InterruptedException should not occur: " + e.getMessage());
        }
    }
}
