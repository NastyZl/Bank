import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BackSystemBank {
    List<BackSystemTimeOut> backSystemTimeOuts;
    private AtomicLong balance;

    public BackSystemBank() {

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
        long balanceUpdate = balance.getAndUpdate(a -> request.getAmount() + a);
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balanceUpdate);
    }

    public void decrease(Request request, String nameProcessor) {
        long amount = request.getAmount();
        while (balance.get() - amount < 0) {
            System.out.printf("БЭК система: Заявка %s НЕ ВЫПОЛНЕНА. Получена от %s. Сумма больше баланса банка. Баланс банка = %d\n",
                    request, nameProcessor, balance.get());
            return;
        }
        long balanceUpdate = balance.getAndUpdate(a -> a-amount);
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balanceUpdate);
    }
}
