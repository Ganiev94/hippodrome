

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
  public void HorseNull(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }
    @Test
    public void horseNullMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(null));
        assertEquals("Horses cannot be null.", throwable.getMessage());
    }
    @Test
    public void horseEmpty(){
        assertThrows(IllegalArgumentException.class,() ->
                new Hippodrome(new ArrayList<>()));
    }
    @Test
    public void horseEmptyMessage(){
        Throwable throwable = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }
    @Test
    public void getHorsesTest(){
        List<Horse> horseList = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse("horse"+ i, j++);
            horseList.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertArrayEquals(horseList.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    public void moveTest(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 51 ; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < horses.size(); i++) {
            verify(horses.get(i)).move();
        }
    }
    @Test
    public void getWinnerTest(){
        List<Horse> horseList = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < 10; i++) {

            Horse horse = new Horse("horse"+ i, 1, j++);
            horseList.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList.get(9),hippodrome.getWinner());

    }

}