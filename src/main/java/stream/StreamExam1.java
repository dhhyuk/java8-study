package stream;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * Created by gimdonghyeog on 2017. 9. 19..
 */
public class StreamExam1 {

    static final List<Dish> menu = Arrays.asList(
            new Dish("salad", 150),
            new Dish("stake", 480),
            new Dish("noodle", 280),
            new Dish("sandwich", 320)
    );

    public static void main(String[] args) {

        // Menu중 칼로리가 400미만인 Menu의 name으로 이루어진 List만들기
        // Java 7
        final List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish d: menu) {
            if (d.getCalories() < 400) {
                lowCaloricDishes.add(d);
            }
        }

        Collections.sort(lowCaloricDishes, Comparator.comparing(Dish::getCalories));

        final List<String> lowCaloricDishesName7 = new ArrayList<>();
        for (Dish d: lowCaloricDishes) {
            lowCaloricDishesName7.add(d.getName());
        }
        System.out.println("Java 7 : " + lowCaloricDishesName7);

        // Java 8
        final List<String> lowCaloricDishesName8 =
                menu.parallelStream()
                        .filter(dish -> dish.getCalories() < 400)
                        .sorted(Comparator.comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());
        System.out.println("Java 8 : " + lowCaloricDishesName8);
    }

    static class Dish {
        private String name;
        private int calories;

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Dish(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }

        @Override
        public String toString() {
            return "{\"name\":\"" + name +"\", \"calories\":" + calories + "}";
        }
    }
}
