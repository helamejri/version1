package tn.esprit.edu.iservices;

import java.util.List;

public interface Iservices<T,R> {
    void add(T t);
    void update(T t);
    void remove(T t);
    List<T> findAll();
    T findById(R r);
}
