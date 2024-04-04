package list.cards.mybankdebitcarddao.exception;

public class DebitCardException extends RuntimeException{

    //public DebitCardException(String no_debit_card_available) {
    public DebitCardException(String message) {
                 super(message);
    }
}
