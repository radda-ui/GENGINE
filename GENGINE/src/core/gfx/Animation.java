package core.gfx;

import java.util.ArrayList;
import java.util.List;

public class Animation<T> {
	List<T> list;
	int index;
	T current;
	public Animation () {
		
	}
	public Animation<T> Set(){
		list = new ArrayList<>();
		return this;
	}

	public void update(){}
	
	public void add(T e){
		list.add(e);
	}
	public T get(int i){
		if (i>=0 && i<list.size())
		return list.get(i);
		return null;
	}
	
	public int getIndex() {
		return index;
	}
	
	public T getcurrent(){
		return current;
	}
	
	public List<T> getList() {
		return list;
	}
	public T getCurrent() {
		return current;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void setCurrent(T current) {
		this.current = current;
	}
	
	
	
}
