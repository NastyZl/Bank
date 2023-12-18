import java.util.concurrent.Callable;

public class BackSystemTimeOut  implements Callable<Long> {
    private final long balance;
    private final int sleepTime;
    private BackSystemBank backSystemBank;

    public BackSystemTimeOut(long balance, int sleepTime) {
        this.balance = balance;
        this.sleepTime = sleepTime;
    }
    @Override
    public Long call() throws Exception {
        Thread.sleep(sleepTime);
       // backSystemBank
        return balance;
    }
}
