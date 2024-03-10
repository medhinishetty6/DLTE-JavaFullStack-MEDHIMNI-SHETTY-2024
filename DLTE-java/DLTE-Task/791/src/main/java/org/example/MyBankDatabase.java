package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class MyBankDatabase<T> implements Activity<T>{
    ArrayList<T> bankData;

    public String createData(T object){
        bankData.add(object);
        return "data added";
    }
    static ArrayList<CreditCard> creditCard=new ArrayList<>();
    static File file=new File("C:\\Users\\xxshetvm\\Desktop\\DLTE-JavaFullStack-MEDHINISHETTY-2024\\DLTE-java\\DLTE-Task\\791\\src\\main\\CreditCard.txt");
    public void write() throws IOException{
        FileOutputStream fileOutputStream=new FileOutputStream("CreditCard");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(bankData);
        fileOutputStream.close();
        objectOutputStream.close();
    }
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("CreditCard");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        bankData = (ArrayList<T>) objectInputStream.readObject();
        int size = bankData.size();
        for (int index = 0; index < size; index++) {
            if (bankData.get(index) != null) {
                System.out.println(bankData.get(index).toString());
            }
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase<CreditCard> cardMyBankDatabase=new MyBankDatabase<>();
        cardMyBankDatabase.bankData=new ArrayList<>(10);
        CreditCard creditCard1=new CreditCard("Medhini",526398745L,963,new Date(2024,01,05),50000,7253,new Date(2024,02,17),new Date(2024,02,20));
        CreditCard creditCard2=new CreditCard("Meghana",895412367L,233,new Date(2024,03,10),69200,2363,new Date(2024,02,19),new Date(2024,03,20));
        cardMyBankDatabase.createData(creditCard1);
        cardMyBankDatabase.createData(creditCard2);
        cardMyBankDatabase.write();
        cardMyBankDatabase.read();
    }

}
