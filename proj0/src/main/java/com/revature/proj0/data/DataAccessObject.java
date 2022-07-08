package com.revature.proj0.data;

import com.revature.proj0.ds.List;

public interface DataAccessObject<T> {
	public T create(T t);
	public T findById(int id);
	public List<T> findAll();
	public void update (T t);
	public void delete(T t);
	
}
