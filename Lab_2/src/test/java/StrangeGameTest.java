import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class StrangeGameTest {

    @Mock Hour Mock_hour;
    @Mock Prison Mock_prison;
    @Spy Prison Spy_prison;
    @Mock GAMEDb Mock_db;

    StrangeGame Test_StrangeGame = new StrangeGame();
    Player notorious_player = new Player();

    /* ===============================================================================
        (a) If a notorious player enter the game on 0:00 - 11:59,
            verify that prison doesn't do any imprisonment.
    ===============================================================================*/
    @Test
    public void test_a() throws InterruptedException {
        for (int i=0; i<12; i++){
            when(Mock_hour.getHour()).thenReturn(i);
            Test_StrangeGame.hour = Mock_hour;
            Test_StrangeGame.setPrison(Mock_prison);

            assertTrue(notorious_player.getReputation() < 0);
            Test_StrangeGame.enterGame(notorious_player);
            verify(Mock_prison, never()).crime(notorious_player);
        }
    }

    /* ===============================================================================
        (b) If a notorious player enter the game on 12:00 - 23:59,
            assert the output correct.
    ===============================================================================*/
    @Test
    public void test_b() throws InterruptedException {
        doNothing().when(Spy_prison).imprisonment(any(Player.class));
        for (int i=12; i<24; i++){
            when(Mock_hour.getHour()).thenReturn(i);
            Test_StrangeGame.hour = Mock_hour;
            Test_StrangeGame.setPrison(Spy_prison);

            assertTrue(notorious_player.getReputation() < 0);
            String outputString = Test_StrangeGame.enterGame(notorious_player);
            String expectedString = "After a long period of punishment, the player can leave! :)";
            assertEquals(expectedString, outputString);
        }
    }

    /* ===============================================================================
        (c) Suppose 3 players go to the prison.
            Verify prisonerLog in prison will record prisoner’s playerid with spy method.
            Don’t stub getLog function.
    ===============================================================================*/
    @Test
    public void test_c() throws InterruptedException {
        // ------------- Method 1 -------------
        // Declare @Spy Prison Spy_Prison in global;

        // ------------- Method 2 -------------
        /*
        Prison prison = new Prison();
        Prison Spy_prison = spy(prison);
        */

        // ------------- Setting -------------
        doNothing().when(Spy_prison).imprisonment(any(Player.class));
        when(Mock_hour.getHour()).thenReturn(15); // Available Time : (12, 24)
        Test_StrangeGame.hour = Mock_hour;
        Test_StrangeGame.setPrison(Spy_prison);

        // -----3 notorious players enter-----
        String[] Player_IDs = {"9587","9687", "9787"};
        Player[] Players = new Player[3];
        for (int i = 0 ; i < 3 ; i++){
            Players[i] = new Player(Player_IDs[i], -1);
            assertTrue(Players[i].getReputation() < 0 );
            Test_StrangeGame.enterGame(Players[i]);
        }

        // -------------- getLog -------------
        ArrayList prison_log = Spy_prison.getLog();
        for (int i = 0 ; i < 3 ; i++) {
            assertEquals(Player_IDs[i], prison_log.get(i));
        }

    }

    /* ===============================================================================
        (d) Use stub method to test getScore function (PlayerID = your StudentID)
        to avoid connection to outer database.
    ===============================================================================*/
    @Test
    public void test_d() {
        // ---------- Setting params ----------
        int score = 87;
        final String Student_id = "0616086";

        // ------------- Method 1 -------------
        /* class GAMEdbStub implements GAMEDb{
            public int getScore(String playerId){
                return score;
            }
        }
        Test_StrangeGame.db = new GAMEdbStub(); */

        // ------------- Method 2 -------------
        //doReturn(score).when(Mock_db).getScore(Student_id);

        // ------------- Method 3 -------------
        when(Mock_db.getScore(Student_id)).thenReturn(score);
        Test_StrangeGame.db = Mock_db;

        // ------ Test the returned value ------
        int returned_score = Test_StrangeGame.db.getScore(Student_id);
        assertEquals(score, returned_score);
    }

    /* ===============================================================================
        (e) implement paypalService interface as a fake object to test donate function.
    ===============================================================================*/
    @Test
    public void test_e() {
        fake_paypalService Fake_paypalService = new fake_paypalService();
        assertEquals("Thank you", Test_StrangeGame.donate(Fake_paypalService));
    }
    public class fake_paypalService implements paypalService {
        public String doDonate() {
            return "Success";
        }
    }

}