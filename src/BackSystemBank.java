import java.util.concurrent.atomic.AtomicLong;

public class BackSystemBank {
    private final AtomicLong balance = new AtomicLong(0);
    public void getBalance() {
        System.out.println("Общий баланс банка - " + balance);
    }

    public void processRequest(Request request, String nameProcessor) {
        OperationType operationType = request.getOperationType();
        switch (operationType) {
            case REPAYMENT: {
                increase(request, nameProcessor);
                break;
            }
            case CREDIT: {
                decrease(request, nameProcessor);
                break;
            }
        }
    }
    public void increase(Request request, String nameProcessor) {
        this.balance.getAndUpdate(a -> request.getAmount() + a);
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balance.get());
    }

    public void decrease(Request request, String nameProcessor) {
        long amount = request.getAmount();
        while (balance.get() - amount < 0) {
            System.out.printf("БЭК система: Заявка %s НЕ ВЫПОЛНЕНА. Получена от %s. Сумма больше баланса банка. Баланс банка = %d\n",
                    request, nameProcessor, balance.get());
            return;
        }
        balance.getAndUpdate(a -> a-amount);
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balance.get());
    }

    public void setBalance(long balance) {
        this.balance.getAndUpdate(a -> balance + a);
        System.out.printf("БЭК система: Баланс банка пополнен на %d и составляет = %d\n", balance, this.balance.get());
    }
}
