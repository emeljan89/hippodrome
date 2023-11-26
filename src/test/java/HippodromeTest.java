import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void constructor_NullListParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Horses cannot be null.";
        List<Horse> horses = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Horses cannot be empty.";
        List<Horse> horses = new ArrayList<>();

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getHorses_ReturnsListWithAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("TestHorse" + i, 1, 2));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        assertNotNull(hippodrome.getHorses());
        assertEquals(30, hippodrome.getHorses().size());
        for (int i = 0; i < 30; i++) {
            assertEquals("TestHorse" + i, hippodrome.getHorses().get(i).getName());
        }
    }

    @Test
    void move_CallMoveMethodForAllHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinner_ReturnsHorseWithLargestDistance() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("TestHorse1", 2, 10),
                new Horse("TestHorse2", 2.2, 12),
                new Horse("TestHorse3", 2.3, 14),
                new Horse("TestHorse4", 2.5, 16)
        ));
        assertEquals("TestHorse4", hippodrome.getWinner().getName());

    }
}
