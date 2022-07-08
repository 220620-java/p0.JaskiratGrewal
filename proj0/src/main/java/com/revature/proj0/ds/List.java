package com.revature.proj0.ds;

public interface List<T> {
 public void add(T obj);
 public T get(int index);
 public T delete (int index);
 public int indexOf (T obj);
 public int size();
 
 //void add(T obj, int index);
 //void addAll(T... objs);
 //boolean contains(T obj);
 public boolean isEmpty();
// T remove(T obj);
// T remove(int index);

}
