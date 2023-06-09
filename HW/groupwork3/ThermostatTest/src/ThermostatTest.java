import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThermostatTest {
    /*
     * a:   curTemp < dTemp - thresholdDiff
     * b:   override
     * c:   curTemp < overTemp - thresholdDiff
     * d:   timeSinceLastRun > minLag
     *
     * predicate 1 (L28:30) : (a || (b && c)) && d
     * predicate 2 (L34): b (reach iff p1)
     *
     * Fix curTemp = 63, thresholdDiff = 5, timeSinceLastRun = 12
     * "a" is true iff dTemp > 68
     * "c" is true iff overTemp > 68
     * "d" is true iff minLag < 12
     */

    Thermostat thermostat;
    ProgrammedSettings settings;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        thermostat = new Thermostat();
        settings = new ProgrammedSettings();
    }

    void setClauses(boolean a, boolean b, boolean c, boolean d) {
        settings.setSetting(Period.MORNING, DayType.WEEKDAY, a ? 70 : 60);
        thermostat.setPeriod(Period.MORNING);
        thermostat.setDay(DayType.WEEKDAY);
        thermostat.setCurrentTemp(63);
        thermostat.setThresholdDiff(5);
        thermostat.setOverride(b);
        thermostat.setOverTemp(c ? 70 : 66);
        thermostat.setMinLag(d ? 10 : 14);
        thermostat.setTimeSinceLastRun(12);
    }

    /*
     *  Predicate Coverage
     */

    // p1: true, p2: true -> a: true, b: true, c: true, d: true -> return true
    @Test
    void testPCP1P2() {
        setClauses(true, true, true, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }

    // p1: false, p2: not reach -> a: false, b: false, c: false, d: false -> return false
    @Test
    void testPCNP1() {
        setClauses(false, false, false, false);
        assertFalse(thermostat.turnHeaterOn(settings));
    }

    // p1: true, p2: false -> a: true, b: false, c: true, d: true
    @Test
    void testPCP1NP2() {
        setClauses(true, false, true, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }


    /*
     *  Clause Coverage
     */
    @Test
    void testCC_true(){
        setClauses(true, true, true, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCC_false(){
        setClauses(false, false, false, false);
        assertFalse(thermostat.turnHeaterOn(settings));
    }

    
    /*
     *  Correlated Active Clause Coverage
     */

    @Test
    void testCACCPaT(){
        setClauses(true, true, false, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCACCPaF(){
        setClauses(false, true, false, true);
        assertFalse(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCACCPbT(){
        setClauses(false, true, true, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCACCPbF(){
        setClauses(false, false, true, true);
        assertFalse(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCACCPdT(){
        setClauses(true, true, true, true);
        assertTrue(thermostat.turnHeaterOn(settings));
    }

    @Test
    void testCACCPdF(){
        setClauses(true, true, true, false);
        assertFalse(thermostat.turnHeaterOn(settings));
    }
}