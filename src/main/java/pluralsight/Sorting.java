package pluralsight;

import java.util.Comparator;
import java.util.List;

public class Sorting {
    public static void sortTransaction(List<Transaction> transactions, SortOption option){
        if(transactions == null || transactions.isEmpty()) return;

        switch (option){
            case OLDEST -> transactions.sort(
                    Comparator.comparing((transaction -> transaction.getDate().atTime(transaction.getTime())))
            );
            case NEWEST -> transactions.sort(
                    Comparator.comparing((Transaction transaction) -> transaction.getDate().atTime(transaction.getTime()))
                            .reversed()
            );
            case HIGHEST -> transactions.sort(
                    Comparator.comparing(Transaction::getAmount).reversed()
            );
            case LOWEST -> transactions.sort(
                    Comparator.comparing(Transaction::getAmount)
            );
            case BACK -> Ledger.legerOperation();
        }
    }
}
