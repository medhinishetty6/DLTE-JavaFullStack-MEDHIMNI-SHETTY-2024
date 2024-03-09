package org.example;

public class MyBankDatabase<T> implements Activity<T> {
    T[] newDataBase;
    public String insert(T object) {
        for (int index = 0; index < newDataBase.length; index++) {
            if (newDataBase[index] == null) {
                newDataBase[index] = object;
                return "object inserted";
            }
        }
        return "object not added";
    }

    @Override
    public T read(int index) {
        if(index>=0&& index<newDataBase.length)
            return  newDataBase[index];
        return null;
    }

    @Override
    public String delete(int index) {
        if(index>=0 && index<newDataBase.length && newDataBase[index]!=null){
            T object=newDataBase[index];
            newDataBase[index]=null;
            return"object deleted";
        }
        return null;
    }

    @Override
    public void update(int index, T object) {
       if(index>=0&&index<newDataBase.length){
           newDataBase[index]=object;
           System.out.println("object updated");
       }
       else
           System.out.println("Object not updated");
    }

}
