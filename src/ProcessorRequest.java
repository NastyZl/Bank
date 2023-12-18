public class ProcessorRequest implements Runnable{
    private final String nameProcessor;
    private final FrontSystemBank frontSystemBank;
    private final BackSystemBank backSystemBank;

    public ProcessorRequest(String nameProcessor, FrontSystemBank frontSystemBank, BackSystemBank backSystemBank) {
        this.nameProcessor = nameProcessor;
        this.frontSystemBank = frontSystemBank;
        this.backSystemBank = backSystemBank;
    }

    @Override
    public void run() {
        while(true){
            try {
                Request request = frontSystemBank.getRequest();
                System.out.printf("%s: Получена заявка на обработку по клиенту - %s\n",
                        this.nameProcessor, request.getClientName());
                backSystemBank.processRequest(request, this.nameProcessor);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
