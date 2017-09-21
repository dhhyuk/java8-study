package stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by gimdonghyeog on 2017. 9. 19..
 */
public class StreamExam2 {

    static class Dish {

        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Type type;

        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Type getType() {
            return type;
        }

        public Dish(String name, boolean vegetarian, int calories, Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }

        public enum Type {
            MEAT, OTHER, FISH
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static List<Dish> menu = Arrays.asList(
            new Dish("port", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        final List<String> threeHighCaloricDishNames =
                menu.stream()
                    .filter(dish -> dish.getCalories() > 400)
                    .map(Dish::getName)
                    .limit(3)
                    .collect(toList());
        System.out.println(threeHighCaloricDishNames);
        System.out.println();

        /**
         * filter
         * Predicate에 해당하는 값들로만 구성 시킨다
         */
        final List<Dish> vegetarianDishes =
                menu.stream()
                    .filter(Dish::isVegetarian)
                    .collect(toList());
        System.out.println(vegetarianDishes);
        System.out.println();

        /**
         * distinct
         * 중복된 요소들을 제가한다 (hashCode, equals)
         */
        final List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::print);
        System.out.println("\n");

        /**
         * limit
         * 입력한 사이즈 이하의 크기를 갖는 새로운 스트림을 반환
         */
        final List<Dish> limitDishes =
                menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .limit(3)
                    .collect(toList());
        System.out.println(limitDishes);
        System.out.println();

        /**
         * skip
         * 처음 n개의 요소를 제외한 스트림을 반환
         */
        final List<Dish> skipDishes =
                menu.stream()
                    .filter(d -> d.getCalories() > 300)
                    .skip(2)
                    .collect(toList());
        System.out.println(skipDishes);
        System.out.println();

        // final List<Dish> dishes = menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).collect(toList());

        /**
         * map
         * 인수로 제공된 함수를 각 요소에 적용시켜 적용한 결과가 새로운 요소로 매핑
         */
        final List<String> mapDishNames =
                menu.stream()
                    .map(Dish::getName)
                    .collect(toList());
        System.out.println(mapDishNames);

        final List<Integer> mapDishNameLengths =
                menu.stream()
                    .map(Dish::getName)
                    .map(String::length)
                    .collect(toList());
        System.out.println(mapDishNameLengths);
        System.out.println();

        final List<String> words = Arrays.asList("Hello", "World");
        final List<String[]> collect1 = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        System.out.println(collect1);

        final List<Stream<String>> collect2 = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(collect2);

        final List<String> uniqueCharacters =
                words.stream()
                    .map(w -> w.split(""))
                    .flatMap(Arrays::stream)
                    .distinct()
                    .collect(toList());
        System.out.println(uniqueCharacters);
        System.out.println();

        final List<Integer> numbers2 =
                Arrays.asList(1, 2, 3, 4, 5).stream()
                    .map(num -> num * num)
                    .collect(toList());
        System.out.println(numbers2);

        final List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        final List<Integer> numbers4 = Arrays.asList(3, 4);
        System.out.println(
                numbers3.stream()
                    .flatMap(
                            num -> numbers4.stream()
                                        .map(n -> Arrays.asList(num, n))
                    )
                    .collect(toList())
        );

        final List<int[]> values = numbers3.stream()
                .flatMap(i ->
                        numbers4.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                )
                .collect(toList());
        System.out.println(values);
        System.out.println("======================");
        System.out.println();

        // 검색과 매칭

        /**
         * anyMatch
         * 주어진 스트림에서 적어도 한 요소와 일치하는지 확인할 때
         */
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        /**
         * allMatch
         * 스트림의 모든 요소가 주어진 프레디케이트와 일치하는지 검사
         */
        final boolean isHealthyAllMatch = menu.stream().allMatch(d -> d.getCalories() < 1000);
        System.out.println("isHealthyAllMatch : " + isHealthyAllMatch);

        /**
         * 주어진 프레디케이트와 일치하는 요소가 없는지 확인
         */
        final boolean isHealthyNoneMatch = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("isHealthyNoneMatch : " + isHealthyNoneMatch);
        System.out.println();

        /**
         * findAny
         * 현재 스트림에서 임의의 요소를 반환
         */
        final Optional<Dish> dish =
                menu.stream()
                    .filter(Dish::isVegetarian)
                    .findAny();
        System.out.println(dish);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish1 -> System.out.println(dish1.getName()));

        /**
         * findFirst
         * 현재 스트림에서 첫번째 요소를 반환
         *
         * findAny와 findFirst차이
         * 병렬처리에서는 첫 번째 요소를 찾기 어려움으로 반환 순서가 상관없다면 병렬 스트림에서는 제약이 적은 findAny 사용
         */
        final List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        final Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree);
        System.out.println("");


    }
}
