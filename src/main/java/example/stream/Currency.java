package example.stream;

/**
 * Created by gimdonghyeog on 2017. 8. 31..
 */
public class Currency {
    private String title;

    public Currency(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Currency) {
            return title.equals(((Currency) obj).getTitle());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
