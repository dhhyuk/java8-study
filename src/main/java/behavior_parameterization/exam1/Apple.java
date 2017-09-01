package behavior_parameterization.exam1;

/**
 * Created by gimdonghyeog on 2017. 9. 1..
 */
class Apple {
    private String color;
    private int weight;

    public Apple(String color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    @Override
    public String toString() {
        return "color : " + color + ", weight : " + weight;
    }
}
