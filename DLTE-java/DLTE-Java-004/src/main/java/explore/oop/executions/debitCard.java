package explore.oop.executions;

import java.util.Scanner;

public class debitCard extends  accountsDetail {
    private Long cardno;
    private Integer cardPIN;

    public Long getCardno() {
        return cardno;
    }

    public void setCardno(Long cardno) {
        this.cardno = cardno;
    }

    public Integer getCardPIN() {
        return cardPIN;
    }

    public void setCardPIN(Integer cardPIN) {
        this.cardPIN = cardPIN;
    }

    public debitCard(Long accountNumber, Long accountBalance, String accountHolder, Long cardno, Integer cardPIN) {
        super(accountNumber, accountBalance, accountHolder);
        this.cardno = cardno;
        this.cardPIN = cardPIN;
    }
    public  void Withdraw() {
        System.out.println("Withdraw amount");
        Scanner scanner = new Scanner(System.in);
        int enterPIN = 0;
        System.out.println("Enter the current PIN");
        enterPIN = scanner.nextInt();
        long WithdrawAmount = 0L;
        if (enterPIN == getCardPIN()) {
            System.out.println("Enter the amount to withdraw");
            WithdrawAmount=scanner.nextLong();
            if(WithdrawAmount<=getAccountBalance()){
            System.out.println("Your Amount is withdrawn");
            this.setAccountBalance((this.getAccountBalance() - WithdrawAmount));
        } else {
            System.out.println("Insufficient balance");
        }
    }else{
        System.out.println("Wrong PIN");
    }
}
}
