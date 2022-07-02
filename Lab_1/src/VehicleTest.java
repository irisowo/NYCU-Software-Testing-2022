import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private Vehicle set_vehicle;
    private Vehicle default_vehicle;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        set_vehicle = new Vehicle();
        default_vehicle = new Vehicle();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        if (Vehicle.totalVehicle() > 0){
            set_vehicle.finalize();
            default_vehicle.finalize();
        }
    }

    @org.junit.jupiter.api.Test
    void testFinalize() {
        set_vehicle.finalize();
        default_vehicle.finalize();
        assertEquals(0, Vehicle.totalVehicle());
    }

    @org.junit.jupiter.api.Test
    void setSpeed() {
        int test_spd = 87;
        set_vehicle.setSpeed(test_spd);
        assertEquals(test_spd, set_vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void setDir() {
        String test_dir = "south";
        set_vehicle.setDir(test_dir);
        assertEquals(test_dir, set_vehicle.getDir());
    }

    @org.junit.jupiter.api.Test
    void getSpeed() {
        assertEquals(0, default_vehicle.getSpeed());
    }

    @org.junit.jupiter.api.Test
    void getDir() {
        assertEquals("north", default_vehicle.getDir());
    }

    @org.junit.jupiter.api.Test
    void totalVehicle() {
        assertEquals(2, Vehicle.totalVehicle());
    }
}