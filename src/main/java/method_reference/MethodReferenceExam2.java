package method_reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by gimdonghyeog on 2017. 9. 18..
 */
public class MethodReferenceExam2 {

    public static class AppleComparator implements Comparator<MethodReferenceExam1.Apple> {
        public int compare(MethodReferenceExam1.Apple a1, MethodReferenceExam1.Apple a2) {
            return a1.getWeight().compareTo(a2.getWeight());
        }
    }

    public static void main(String[] args) {
        final List<MethodReferenceExam1.Apple> inventory = Arrays.asList(new MethodReferenceExam1.Apple(10),
                new MethodReferenceExam1.Apple(15),
                new MethodReferenceExam1.Apple(8));

        inventory.sort(new AppleComparator());

        inventory.sort(new Comparator<MethodReferenceExam1.Apple>() {
            @Override
            public int compare(MethodReferenceExam1.Apple o1, MethodReferenceExam1.Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        inventory.sort(Comparator.comparing(MethodReferenceExam1.Apple::getWeight));
    }
}
