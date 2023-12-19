import java.util.concurrent.Callable;

public class BackSystemTimeOut  implements Callable<Void> {
    private final BackSystemBank backSystemBank;
    private final long balance;
    private final int sleepTime;

    public BackSystemTimeOut(long balance, int sleepTime, BackSystemBank backSystemBank) {
        this.balance = balance;
        this.sleepTime = sleepTime;
        this.backSystemBank = backSystemBank;

    }
    @Override
    public Void call() {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        backSystemBank.setBalance(balance);
        return null;
    }
}
