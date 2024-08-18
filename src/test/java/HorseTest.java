

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.nio.ShortBuffer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class HorseTest {
    @Test
    public void horseNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Horse(null, 1, 1);
                }
            );
    }
    @Test
    public void horseNullMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 0,0));
        assertEquals("Name cannot be null.", throwable.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
    public void horseBlank(String name){

        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name,0,0);
        });

    }
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t"})
  public void horseBlankMessage(String name){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, 0,0));
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }
    @Test
    public void speeddIsNegative(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Horse("Horse", -1, 0);
        });
    }
    @Test
    public void speedIsNegativeMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Horse", -1,0));
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }
    @Test
    public void distanceIsNegative(){
        assertThrows(IllegalArgumentException.class, () ->{
            new Horse("Horse", 0, -1);
        });
    }
    @Test
    public void distanceIsNegativeMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Horse("Horse", 0,-1));
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }
    @Test
    public void getNameTest(){
        String horseName = "Horse";
        Horse horse = new Horse(horseName,1,1);
        assertEquals(horseName, horse.getName());
    }
    @Test
    public void getSpeedTest(){
        int speed = 100;
        Horse horse = new Horse("Horse", speed, 1);
        assertEquals(speed, horse.getSpeed());
    }
    @Test
    public void getDistanceTest(){
        int dist = 100;
        Horse horse = new Horse("Horse", 1, dist);
        assertEquals(horse.getDistance(), dist);
    }
    @Test
    public void getDistanceTestWhenTwoParameters(){
        Horse horse = new Horse("Horse", 1);
        assertEquals(0, horse.getDistance());
    }
    @Test
    public void moveTestGetRandom(){
        MockedStatic <Horse> mockedStatic = Mockito.mockStatic(Horse.class);
        Horse horse = new Horse("Horse", 1,2);
        horse.move();
        mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        mockedStatic.close();

    }
    @ParameterizedTest
    @ValueSource(doubles = {0.3, 0.4, 0.5, 0.6})
    public void MoveTestDistance(double d){
        MockedStatic <Horse> mockedStatic = Mockito.mockStatic(Horse.class);
        mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(d);
        Horse horse = new Horse("Horse", 1,2);
        Double move = horse.getDistance() + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
        horse.move();
        assertEquals(move, horse.getDistance());
        mockedStatic.close();


    }

}