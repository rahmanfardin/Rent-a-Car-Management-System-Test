package BackendCode;

import org.junit.*;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CarTest {

    private Car car;

    @Before
    public void setUp() {
        // Initialize a test car object
        car = new Car(1, "Toyota", "Corolla", "White", "Sedan", 5, "2020", "New", "ABC-1234", 100, new CarOwner());
    }

    @After
    public void tearDown() {
        // Clean up the serialized file
        java.io.File file = new java.io.File("Car.ser");
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAddCar() {
        car.Add();
        ArrayList<Car> cars = Car.View();
        assertEquals(1, cars.size());
    }

    @Test
    public void testUpdateCar() {
        car.Add();
        car.setName("Camry");
        car.Update();
        Car updatedCar = Car.SearchByID(1);
        assertEquals("Camry", updatedCar.getName());
    }

    @Test
    public void testRemoveCar() {
        car.Add();
        car.Remove();
        Car removedCar = Car.SearchByID(1);
        assertNull(removedCar);
    }

    @Test
    public void testSearchByID() {
        car.Add();
        Car foundCar = Car.SearchByID(1);
        assertEquals("Corolla", foundCar.getName());
    }

    @Test
    public void testSearchByName() {
        car.Add();
        ArrayList<Car> foundCars = Car.SearchByName("Corolla");
        assertEquals(1, foundCars.size());
    }

    @Test
    public void testSearchByRegNo() {
        car.Add();
        Car foundCar = Car.SearchByRegNo("ABC-1234");
        assertEquals("Corolla", foundCar.getName());
    }

    @Test
    public void testNameValidation() {
        assertTrue(Car.isNameValid("Toyota Corolla"));
    }

    @Test
    public void testRegNoValidation() {
        assertTrue(Car.isRegNoValid("ABC-1234"));
    }

    @Test
    public void testIsRented() {
        car.Add();
        assertFalse(car.isRented());
    }
}
