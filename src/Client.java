public class Client implements Runnable{
    private String clientThreadName;
    private Request request;
    private FrontSystemBank frontSystemBank;

    public Client(String clientThreadName, Request request, FrontSystemBank frontSystemBank) {
        this.clientThreadName = clientThreadName;
        this.request = request;
        this.frontSystemBank = frontSystemBank;
    }

    @Override
    public void run() {
        try {
            frontSystemBank.addRequest(request);
            System.out.printf("%s: Заявка %s отправлена в банк%n", clientThreadName, request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
