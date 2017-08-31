package example.stream;

/**
 * Created by gimdonghyeog on 2017. 8. 31..
 */
public class Transaction {
    private int price;
    private Currency currency;

    public Transaction(int price, Currency currency) {
        this.price = price;
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "price : " + price + ", currency : " + currency.getTitle();
    }
}
