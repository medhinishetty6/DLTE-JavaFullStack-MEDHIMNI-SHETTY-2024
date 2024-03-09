package org.example;

public interface Activity<T> {
    String insert(T object);
    T read(int index);
    String delete(int index);
    void update(int index,T object);
}
