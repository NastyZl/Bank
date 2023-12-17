public class Request {
    private final String clientName;
    private final int amount;
    private final OperationType operationType;

    public Request(String clientName, int amount, OperationType operationType) {
        this.clientName = clientName;
        this.amount = amount;
        this.operationType = operationType;
    }
    public String getClientName() {
        return clientName;
    }

    public int getAmount() {
        return amount;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return '{'+"clientName='" + clientName + '\'' +
                ", amount=" + amount +
                ", operationType=" + operationType +
                '}';
    }
}
