package org.example;

public class TransactionThreads {
 public static  void main(String[] args) throws Exception{
     AnalysisTransaction analysisTransaction1=new AnalysisTransaction();
     Thread thread1=new Thread(analysisTransaction1::displayRemarks,"Medh");
     thread1.start();
     Thread thread2=new Thread(analysisTransaction1::displayAmount,"poh");
     thread2.start();
     Thread thread3=new Thread(analysisTransaction1::displayTransactionTo,"bob");
     thread3.start();
     Thread thread4=new Thread(analysisTransaction1,"Medh");
     thread4.start();
     Thread thread5=new Thread(analysisTransaction1,"Babu");
     thread5.start();
     Thread thread6=new Thread(analysisTransaction1,"Abdul");
     thread6.start();
     Thread thread7=new Thread(analysisTransaction1,"Sham");
     thread7.start();
     Thread thread8=new Thread(analysisTransaction1,"Madan");
     thread8.start();
     Thread thread9=new Thread(analysisTransaction1,"Aradhya");
     thread9.start();
     Thread thread10=new Thread(analysisTransaction1,"Pakathi");
     thread10.start();
 }
}
