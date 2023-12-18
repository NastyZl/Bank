public class BackSystemBank {

    private long balance;

    public BackSystemBank(long balance) {
        this.balance = balance;
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
    public synchronized void increase(Request request, String nameProcessor) {
        balance += request.getAmount();
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balance);
    }

    public synchronized void decrease(Request request, String nameProcessor) {
        long amount = request.getAmount();
        while (balance - amount < 0) {
            System.out.printf("БЭК система: Заявка %s НЕ ВЫПОЛНЕНА. Получена от %s. Сумма больше баланса банка. Баланс банка = %d\n",
                    request, nameProcessor, balance);
            return;
        }
        balance -= amount;
        System.out.printf("БЭК система: Заявка %s УСПЕШНО ВЫПОЛНЕНА. Получена от %s. Баланс банка = %d\n",
                request, nameProcessor, balance);
    }
}
