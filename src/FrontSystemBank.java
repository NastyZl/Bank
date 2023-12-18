import java.util.ArrayDeque;

public class FrontSystemBank {
    private final ArrayDeque<Request> requestsQueue = new ArrayDeque<>(2);
    private final int MAX_SIZE_REQUEST = 2;
    public synchronized void addRequest(Request request) throws InterruptedException {
        while (requestsQueue.size()>=MAX_SIZE_REQUEST){
            wait();
        }
        requestsQueue.add(request);
        notifyAll();
    }
    public synchronized Request getRequest() throws InterruptedException {
        while (requestsQueue.isEmpty()){
            wait();
        }
        var returnRequest = requestsQueue.poll();
        notifyAll();
        return returnRequest;
    }
}
