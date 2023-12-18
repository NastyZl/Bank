import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystemBank backSystemBank = new BackSystemBank( 0);
        FrontSystemBank frontSystemBank = new FrontSystemBank();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ExecutorService executorService1 = Executors.newCachedThreadPool();

        ProcessorRequest processorRequest1 = new ProcessorRequest("Обработчик заявок №1",
                frontSystemBank, backSystemBank);
        ProcessorRequest processorRequest2 = new ProcessorRequest("Обработчик заявок №2",
                frontSystemBank, backSystemBank);

        Client client1 = new Client("Клиент №1",
                new Request("Клиент №1", 10000, OperationType.REPAYMENT), frontSystemBank);
        Client client2 = new Client("Клиент №2",
                new Request("Клиент №2", 15000, OperationType.REPAYMENT), frontSystemBank);
        Client client3 = new Client("Клиент №3",
                new Request("Клиент №3", 20000, OperationType.REPAYMENT), frontSystemBank);
        Client client4 = new Client("Клиент №4",
                new Request("Клиент №4", 5000, OperationType.CREDIT), frontSystemBank);
        Client client5 = new Client("Клиент №5",
                new Request("Клиент №5", 15000, OperationType.CREDIT), frontSystemBank);

        executorService.submit(processorRequest1);
        executorService.submit(processorRequest2);

        executorService1.submit(client1);
        executorService1.submit(client2);
        executorService1.submit(client3);
        executorService1.submit(client4);
        executorService1.submit(client5);


    }
}