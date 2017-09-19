package method_reference;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by gimdonghyeog on 2017. 9. 18..
 */
public class MethodReferenceExam1 {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static void main(String[] args) {
        // 1.
        final Function<String, Integer> stringToIntegerBefore = (String s) -> Integer.parseInt(s);
        final Function<String, Integer> stringToIntegerAfter = Integer::parseInt;

        // 2.
        final BiPredicate<List<String>, String> containsBefore = (list, element) -> list.contains(element);
        final BiPredicate<List<String>, String> containsAfter = List::contains;


        final List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        final List<Apple> apples = map(weights, Apple::new);

        final Apple fruit = (Apple) giveMeFruit("apple", 15);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase()).apply(weight);
    }

    public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        final List<Apple> result = new ArrayList<>();
        for (Integer weight : list) {
            result.add(f.apply(weight));
        }
        return result;
    }

    public static class Apple extends Fruit {

        public Apple(Integer weight) {
            super(weight);
        }
    }

    public static class Orange extends Fruit {
        public Orange(Integer weight) {
            super(weight);
        }
    }

    public static class Fruit {
        private Integer weight;

        public Integer getWeight() {
            return weight;
        }

        public Fruit(Integer weight) {
            this.weight = weight;
        }
    }
}
