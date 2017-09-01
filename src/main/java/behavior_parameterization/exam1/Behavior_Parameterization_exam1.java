package behavior_parameterization.exam1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gimdonghyeog on 2017. 9. 1..
 */
public class Behavior_Parameterization_exam1 {

    public static void main(String[] args) {
        final List<Apple> inventory = Arrays.asList(new Apple("green", 100), new Apple("red", 200), new Apple("green", 1000), new Apple("green", 300));

        final List<Apple> greenApples = filterApplesByColor(inventory, "green");
        final List<Apple> redApples = filterApplesByColor(inventory, "red");
    }

    /**
     * 1. 농장 재고목록 애플리케이션에 리스트에서 녹색 사과만 필터링 하는 기능을 추가
     */
    protected static List<Apple> filterGreenApples(List<Apple> inventory) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 비슷한 코드를 구현한 다음에 추상화
     *
     * 2. 필터링하려는 색상을 다른 색도 가능하게 -> 색을 파라미터화할 수 있도록 메서드에 파라미터를 추가
     */
    protected static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 3. 무게로도 필터링하게 -> 무게에 따라 필터하는 메서드를 추가
     */
    protected static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 4. 모든 속성을 메서드 인수로 추가
     *
     * flag가
     * true  일 때는 Color에 대한 필터,
     * false 일 때는 Weight에 대한 필터
     */
    protected static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        final List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }
        return result;
    }


    /**
     * 동작 파라미터화 사용
     *
     * 위의 내용
     * 사과의 어떤 속성에 기초해서 불린값을 반환 -> Predicate(선택 조건을 결정)
     */

    protected interface ApplePredicate {
        boolean test(Apple apple);
    }

    protected class AppleHeavyWeightPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    protected class AppleGreenColorPredicate implements ApplePredicate {
        public boolean test(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    /**
     * 
     */
}
