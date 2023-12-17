import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystemBank backSystemBank = new BackSystemBank( 0);
        FrontSystemBank frontSystemBank = new FrontSystemBank();

        Thread processorRequest1 = new Thread(new ProcessorRequest("Обработчик заявок №1",
                frontSystemBank, backSystemBank));
        Thread processorRequest2 = new Thread(new ProcessorRequest("Обработчик заявок №2",
                frontSystemBank, backSystemBank));

        Thread client1 = new Thread(new Client("Клиент №1",
                new Request("Клиент №1", 10000, OperationType.REPAYMENT), frontSystemBank));
        Thread client2 = new Thread(new Client("Клиент №2",
                new Request("Клиент №2", 15000, OperationType.REPAYMENT), frontSystemBank));
        Thread client3 = new Thread(new Client("Клиент №3",
                new Request("Клиент №3", 20000, OperationType.REPAYMENT), frontSystemBank));
        Thread client4 = new Thread(new Client("Клиент №4",
                new Request("Клиент №4", 5000, OperationType.CREDIT), frontSystemBank));
        Thread client5 = new Thread(new Client("Клиент №5",
                new Request("Клиент №5", 15000, OperationType.CREDIT), frontSystemBank));


        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        processorRequest1.start();
        processorRequest2.start();
    }
}