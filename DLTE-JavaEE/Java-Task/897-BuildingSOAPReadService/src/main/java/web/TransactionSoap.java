package web;

import org.example.middleware.DatabaseTarget;
import org.omg.IOP.TransactionService;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService()
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionSoap {
    GroupOfTransaction groupOfTransaction=new GroupOfTransaction();
    TransactionService transactionService;
    public TransactionSoap(){
        transactionService = new TransactionService(new DatabaseTarget());
    }
        @WebResult(name="GroupOfTransaction")
        public GroupOfTransaction findAllByType(String type){
        groupOfTransaction.setTransactionList(transactionService.callfindAllByType(type));
        return groupOfTransaction;
        }
    }

