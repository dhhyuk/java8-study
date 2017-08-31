package example.stream;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by gimdonghyeog on 2017. 8. 31..
 */
public class Stream_Exam {

    public static void main(String[] args) {
        final List<Transaction> transactions = Arrays.asList(new Transaction(1000, new Currency("title1")), new Transaction(2000, new Currency("title1")), new Transaction(2000, new Currency("title2")),
                new Transaction(3000, new Currency("title2")), new Transaction(4000, new Currency("title1")), new Transaction(10000, new Currency("title3")));

        // before java 8
        final Map<Currency, List<Transaction>> before8_transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1000) {
                final Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency = before8_transactionsByCurrencies.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<>();
                    before8_transactionsByCurrencies.put(currency, transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }

        // after java 8
        final Map<Currency, List<Transaction>> after8_transactionsByCurrencies = transactions.stream()
                .filter(t -> t.getPrice() > 1000)
                .collect(groupingBy(Transaction::getCurrency));

        // print
        for (Currency key: before8_transactionsByCurrencies.keySet()) {
            System.out.println("key : " + key.getTitle());
            for (Transaction transaction : before8_transactionsByCurrencies.get(key)) {
                System.out.println(transaction.toString());
            }
        }
        System.out.println("=======================");
        for (Currency key: after8_transactionsByCurrencies.keySet()) {
            System.out.println("key : " + key.getTitle());
            for (Transaction transaction : before8_transactionsByCurrencies.get(key)) {
                System.out.println(transaction.toString());
            }
        }
    }
}
