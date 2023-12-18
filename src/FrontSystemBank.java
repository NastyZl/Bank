import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ArrayBlockingQueue - имеет capacity, блокирует, если пытаемся взять из пустой queue или пытаеся положить в полную.
 */
public class FrontSystemBank {
    private final BlockingQueue<Request> requestsQueue = new ArrayBlockingQueue<>(2);
    public void addRequest(Request request) throws InterruptedException {
        requestsQueue.put(request);
    }
    public Request getRequest() throws InterruptedException {
        return requestsQueue.take();
    }
}
