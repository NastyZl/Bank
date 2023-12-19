import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BackSystemBank backSystemBank = new BackSystemBank();
        FrontSystemBank frontSystemBank = new FrontSystemBank();
        ExecutorService executorDopSystemBank = Executors.newFixedThreadPool(3);
        ExecutorService executorSystem = Executors.newCachedThreadPool();

        List<BackSystemTimeOut> backSystemTimeOuts = new ArrayList<>();
        backSystemTimeOuts.add( new BackSystemTimeOut(5000,5000, backSystemBank));
        backSystemTimeOuts.add( new BackSystemTimeOut(7000,7000, backSystemBank));
        backSystemTimeOuts.add( new BackSystemTimeOut(10000,10000, backSystemBank));

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
            backSystemBank.getBalance();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        executorDopSystemBank.shutdown();

        executorSystem.submit(processorRequest1);
        executorSystem.submit(processorRequest2);

        executorSystem.submit(client1);
        executorSystem.submit(client2);
        executorSystem.submit(client3);
        executorSystem.submit(client4);
        executorSystem.submit(client5);
    }
}