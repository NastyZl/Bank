import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystemBank backSystemBank = new BackSystemBank();
        FrontSystemBank frontSystemBank = new FrontSystemBank();
        ExecutorService executorDopSystemBank = Executors.newFixedThreadPool(3);
        ExecutorService executorProcessor = Executors.newFixedThreadPool(2);
        ExecutorService executorClient = Executors.newCachedThreadPool();

        List<BackSystemTimeOut> backSystemTimeOuts = new ArrayList<>();
        backSystemTimeOuts.add( new BackSystemTimeOut(5000,5000));
        backSystemTimeOuts.add( new BackSystemTimeOut(7000,7000));
        backSystemTimeOuts.add( new BackSystemTimeOut(10000,10000));

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

        try {
            executorDopSystemBank.invokeAll(backSystemTimeOuts);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        executorDopSystemBank.shutdown();

        executorProcessor.submit(processorRequest1);
        executorProcessor.submit(processorRequest2);

        executorClient.submit(client1);
        executorClient.submit(client2);
        executorClient.submit(client3);
        executorClient.submit(client4);
        executorClient.submit(client5);
    }
}