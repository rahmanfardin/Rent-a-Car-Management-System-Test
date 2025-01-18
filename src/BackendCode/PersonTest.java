package BackendCode;

import org.junit.*;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void testIsCNICValid_ValidCNIC() {
        assertTrue(Person.isCNICValid("1234567890123"));
    }

    @Test
    public void testIsCNICValid_InvalidCNICLength() {
        assertFalse(Person.isCNICValid("12345678901"));
    }

    @Test
    public void testIsCNICValid_InvalidCNICCharacters() {
        assertFalse(Person.isCNICValid("1234567890abc"));
    }

    @Test
    public void testIsContactNoValid_ValidContactNo() {
        assertTrue(Person.isContactNoValid("03123456789"));
    }

    @Test
    public void testIsContactNoValid_InvalidContactNoLength() {
        assertFalse(Person.isContactNoValid("0312345678"));
    }

    @Test
    public void testIsContactNoValid_InvalidContactNoStart() {
        assertFalse(Person.isContactNoValid("04123456789"));
    }

    @Test
    public void testIsContactNoValid_InvalidContactNoCharacters() {
        assertFalse(Person.isContactNoValid("0312345678a"));
    }

    @Test
    public void testIsNameValid_ValidName() {
        assertTrue(Person.isNameValid("John Doe"));
    }

    @Test
    public void testIsNameValid_InvalidNameCharacters() {
        assertFalse(Person.isNameValid("John Doe123"));
    }

    @Test
    public void testIsIDValid_ValidID() {
        assertTrue(Person.isIDvalid("123"));
    }

    @Test
    public void testIsIDValid_InvalidIDCharacters() {
        assertFalse(Person.isIDvalid("12a"));
    }

    @Test
    public void testIsIDValid_InvalidIDZero() {
        assertFalse(Person.isIDvalid("0"));
    }

    @Test
    public void testIsIDValid_InvalidIDNegative() {
        assertFalse(Person.isIDvalid("-1"));
    }
}