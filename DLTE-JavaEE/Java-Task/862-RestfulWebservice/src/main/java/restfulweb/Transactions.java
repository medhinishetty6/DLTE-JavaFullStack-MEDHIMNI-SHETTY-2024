package restfulweb;

import java.time.LocalDateTime;

class Transactions {
    private String sender;
    private String receiver;
    private double amount;
    private LocalDateTime transaction_timestamp;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransaction_timestamp() {
        return transaction_timestamp;
    }

    public void setTransaction_timestamp(LocalDateTime transaction_timestamp) {
        this.transaction_timestamp = transaction_timestamp;
    }
}
