package example.code_handover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by gimdonghyeog on 2017. 8. 31..
 */
public class CodeHandOver_Exam {

    public static void main(String[] args) {

        final List<Apple> appleList = Arrays.asList(new Apple("green", 100), new Apple("red", 200), new Apple("green", 1000), new Apple("green", 300));

        // before java 8
        final List<Apple> before8_ColorFilteredApples = filterGreenApples(appleList);
        final List<Apple> before8_WeightFilteredApples = filterHeavyApples(appleList);

        // after java 8
        final List<Apple> after8_ColorFilteredApples = filterApples(appleList, Apple::isGreenApple);
        final List<Apple> after8_WeightFilteredApples = filterApples(appleList, Apple::isHeavyApple);

        // using lambda
        final List<Apple> lambda_ColorFilteredApples = filterApples(appleList, apple -> "green".equals(apple.getColor()));
        final List<Apple> lambda_WeightFilteredApples = filterApples(appleList, apple -> apple.getWeight() > 150);
        final List<Apple> lambda_ColorOrWeightFilteredApples = filterApples(appleList, apple -> "green".equals(apple.getColor()) || apple.getWeight() > 150);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }


    // Predicate : 인수로 값을 받아 true나 false로 반환하는 함수
    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
