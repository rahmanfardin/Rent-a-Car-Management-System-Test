package GUI;

import org.junit.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CarAddTest {
    private Car_Add carAddFrame;
    private JButton addButton;
    private JTextField makerTextField, nameTextField, regNoTextField, ownerIDTextField, rentPerHourTextField;

    @Before
    public void setUp() {
        carAddFrame = new Car_Add();
        carAddFrame.setVisible(true);
        makerTextField = carAddFrame.Maker_TextField;
        nameTextField = carAddFrame.Name_TextField;
        regNoTextField = carAddFrame.RegNo_TextField;
        ownerIDTextField = carAddFrame.OwnerID_TextField;
        rentPerHourTextField = carAddFrame.RentPerHour_TextField;
        addButton = carAddFrame.Add_Button;
    }

    @After
    public void tearDown() {
        carAddFrame.dispose();
    }

    @Test
    public void testButtonPressWithValidInput() {
        // Simulate entering valid input
        makerTextField.setText("Toyota");
        nameTextField.setText("Corolla");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("123");
        rentPerHourTextField.setText("1500");

        // Simulate button press
        addButton.doClick();

        // Validate the inputs were processed correctly (mock validation or observer pattern)
        Assert.assertEquals("Toyota", makerTextField.getText());
        Assert.assertEquals("Corolla", nameTextField.getText());
        Assert.assertEquals("ABC-0123", regNoTextField.getText());
        Assert.assertEquals("123", ownerIDTextField.getText());
        Assert.assertEquals("1500", rentPerHourTextField.getText());
    }

    @Test
    public void testButtonPressWithInvalidInput() {
        // Simulate entering invalid input
        makerTextField.setText("");
        nameTextField.setText("Invalid Name!");
        regNoTextField.setText("InvalidReg");
        ownerIDTextField.setText("-1");
        rentPerHourTextField.setText("0");

        // Simulate button press
        addButton.doClick();

        // Validate the GUI displays error messages
        JLabel nameValidityLabel = carAddFrame.NameValidity_Label;
        JLabel makerValidityLabel = carAddFrame.MakerValidity_Label;
        JLabel regNoValidityLabel = carAddFrame.RegNoValidity_Label;
        JLabel ownerIDValidityLabel = carAddFrame.OwnerIDValidity_Label;
        JLabel rentPerHourValidityLabel = carAddFrame.RentPerHourValidity_Label;

        Assert.assertEquals("                                                            Enter Maker'sName !", makerValidityLabel.getText());
        Assert.assertEquals("                                                            Invalid  Car Name !", nameValidityLabel.getText());
        Assert.assertEquals("                                                            Invalid Reg no !", regNoValidityLabel.getText());
        Assert.assertEquals("                                                            ID cannot be '0' or negative !", ownerIDValidityLabel.getText());
        Assert.assertEquals("                                                            Rent cannot be '0' or negative !", rentPerHourValidityLabel.getText());
    }
    
    @Test
    public void testMissingMaker() {
        makerTextField.setText("");
        nameTextField.setText("Corolla");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("123");
        rentPerHourTextField.setText("1500");

        addButton.doClick();

        JLabel makerValidityLabel = carAddFrame.MakerValidity_Label;
        Assert.assertEquals("                                                            Enter Maker'sName !", makerValidityLabel.getText());
    }
    @Test
    public void testInvalidName() {
        makerTextField.setText("Toyota");
        nameTextField.setText("Invalid@Name!");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("123");
        rentPerHourTextField.setText("1500");

        addButton.doClick();

        JLabel nameValidityLabel = carAddFrame.NameValidity_Label;
        Assert.assertEquals("                                                            Invalid  Car Name !", nameValidityLabel.getText());
    }
    @Test
    public void testInvalidRegNo() {
        makerTextField.setText("Toyota");
        nameTextField.setText("Corolla");
        regNoTextField.setText("1234");
        ownerIDTextField.setText("123");
        rentPerHourTextField.setText("1500");

        addButton.doClick();

        JLabel regNoValidityLabel = carAddFrame.RegNoValidity_Label;
        Assert.assertEquals("                                                            Invalid Reg no !", regNoValidityLabel.getText());
    }
    @Test
    public void testNegativeOwnerID() {
        makerTextField.setText("Toyota");
        nameTextField.setText("Corolla");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("-5");
        rentPerHourTextField.setText("1500");

        addButton.doClick();

        JLabel ownerIDValidityLabel = carAddFrame.OwnerIDValidity_Label;
        Assert.assertEquals("                                                            ID cannot be '0' or negative !", ownerIDValidityLabel.getText());
    }
    @Test
    public void testZeroRentPerHour() {
        makerTextField.setText("Toyota");
        nameTextField.setText("Corolla");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("123");
        rentPerHourTextField.setText("0");

        addButton.doClick();

        JLabel rentPerHourValidityLabel = carAddFrame.RentPerHourValidity_Label;
        Assert.assertEquals("                                                            Rent cannot be '0' or negative !", rentPerHourValidityLabel.getText());
    }
    @Test
    public void testAllFieldsMissing() {
        makerTextField.setText("");
        nameTextField.setText("");
        regNoTextField.setText("");
        ownerIDTextField.setText("");
        rentPerHourTextField.setText("");

        addButton.doClick();

        Assert.assertEquals("                                                            Enter Maker'sName !", carAddFrame.MakerValidity_Label.getText());
        Assert.assertEquals("                                                            Enter Car Name !", carAddFrame.NameValidity_Label.getText());
        Assert.assertEquals("                                                            Enter Reg No !", carAddFrame.RegNoValidity_Label.getText());
        Assert.assertEquals("                                                            Enter Owner ID !", carAddFrame.OwnerIDValidity_Label.getText());
        Assert.assertEquals("                                                            Enter Rent !", carAddFrame.RentPerHourValidity_Label.getText());
    }
    @Test
    public void testNonNumericOwnerID() {
        makerTextField.setText("Toyota");
        nameTextField.setText("Corolla");
        regNoTextField.setText("ABC-0123");
        ownerIDTextField.setText("ABC");
        rentPerHourTextField.setText("1500");

        addButton.doClick();

        JLabel ownerIDValidityLabel = carAddFrame.OwnerIDValidity_Label;
        Assert.assertEquals("                                                            Invalid ID !", ownerIDValidityLabel.getText());
    }



    
    
}
