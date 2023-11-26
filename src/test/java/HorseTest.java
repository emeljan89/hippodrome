import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    void constructor_NullNameParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Name cannot be null.";
        double speed = 3;
        double distance = 2;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "\t", "\t\t", "\n", "\n\n"})
    void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException(String name) {
        String expectedMessage = "Name cannot be blank.";
        double speed = 3;
        double distance = 2;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Speed cannot be negative.";
        String name = "testHorse";
        double speed = -3;
        double distance = 2;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException() {
        String expectedMessage = "Distance cannot be negative.";
        String name = "testHorse";
        double speed = 3;
        double distance = -2;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void getName_ReturnsCorrectName() {
        String name = "testHorse";
        double speed = 3;
        double distance = 2;

        Horse horse = new Horse(name, speed, distance);

        String actualName = horse.getName();

        assertEquals(name, actualName);

    }

    @Test
    void getSpeed_ReturnsCorrectSpeed() {
        String name = "testHorse";
        double speed = 3;
        double distance = 2;

        Horse horse = new Horse(name, speed, distance);

        Double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);

    }

    @Test
    void getDistance_ReturnsCorrectDistance() {
        String name = "testHorse";
        double speed = 3;
        double distance = 2;

        Horse horse = new Horse(name, speed, distance);
        Double actualDistance = horse.getDistance();
        assertEquals(distance, actualDistance);

        Horse horseWithoutDistance = new Horse(name, speed);
        Double actualWithoutDistance = horseWithoutDistance.getDistance();
        assertEquals(0, actualWithoutDistance);

    }

    @Test
    void move_CallsGetRandomDoubleMethod() {
        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            String name = "testHorse";
            double speed = 3;
            double distance = 2;

            Horse horse = new Horse(name, speed, distance);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.4, 0.6, 0.8, 0.9})
    void move_CalculateDistanceAccordingFormula(double fakeRandomValue) {
        String name = "testHorse";
        double speed = 3;
        double distance = 2;
        double min = 0.2;
        double max = 0.9;
        Horse horse = new Horse(name, speed, distance);

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(fakeRandomValue);
            horse.move();
        }
        double expected = distance + speed * fakeRandomValue;
        assertEquals(expected, horse.getDistance());
    }
}
