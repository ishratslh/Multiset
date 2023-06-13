package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	HashMap<T, Integer> tab; //elem dans le multi-ensemble + nb d’occ
	int size=0;

	public HashMultiSet(){
		//construit un multi-ensemble vide
		
		tab= new HashMap<T, Integer>();
		size=0;
		
	}
	
	public HashMultiSet(Collection<T> col){
		//initialisant le nouveau multi-ensemble avec le contenu de la collection 
		
		tab= new HashMap<T, Integer>();
		for (T e : col) {
			this.add(e);
		}
	}
	
	
	@Override
	public boolean add(T e, int count) { //ajoute count occurrences de l’objet e 
		if (count==0) return false;
		else {
			Integer cur = tab.get(e);
			if(cur==null) {
				tab.put(e, count);
				size+=count;
			}else {
				tab.put(e, count+cur);
				size+=count;
			}
			return true;
		}
	}

	@Override
	public boolean add(T e) { //count =1
		Integer cur = tab.get(e);
		if(cur==null) {
			tab.put(e, 1);
			size+=1;
		}else {
			tab.put(e, 1+cur);
			size+=1;
		}
		return true;
		
	}

	@Override
	public boolean remove(Object e) { //count =1
		T e1 = (T)e;
		Integer cur = tab.get(e1);
		if(cur==0) return false;
		else {
			if(cur>=1) {
				//tab.remove(e, cur-1);
				tab.put(e1, cur-1);
				size--;
			}
		}
		return true;
	}

	
	@Override
	public boolean remove(Object e, int count) { //enleve count occurrences de l’objet e 
		T e1 = (T)e;
		if (count==0) return false;
		else {
			Integer cur = tab.get(e1);
			if(cur==null) return false;
			else {
				if(count<=cur) {
					//tab.remove(e, cur-count);
					tab.put(e1, cur-count);
					size-=count;
				}
			}
			return true;
		}
	}

	@Override
	public int count(T e) { //nombre d’occ de l’objet e dans la collection
		if (!tab.containsKey(e)) return 0;
		
		else return tab.get(e); 
	}

	@Override
	public void clear() {
		tab.clear();
		size=0;
	}

	@Override
	public int size() {		
		return this.size;
		/*int res = 0;
		for (Integer e : tab.values()) {
			res+=e;
		}
		return res;*/
	}
	
    public Iterator<T> iterator(){
        HashMultiSetIterator it = new HashMultiSetIterator();
        return it;
    }
	
	
    public class HashMultiSetIterator implements Iterator<T>{
        private Iterator<Map.Entry<T,Integer>> i = tab.entrySet().iterator();
        private Map.Entry<T, Integer> suiv;

        private int cpt = 0;
            
        @Override
        public boolean hasNext() {
            if( i.hasNext() || cpt >0 ) return true;
            else return false;
        }

        @Override
        public T next() {
        	if(!hasNext()) throw new NoSuchElementException();
        	
            if(cpt == 0) {
                suiv = (Map.Entry<T,Integer>) i.next();
                cpt = suiv.getValue();
                cpt--;
                return suiv.getKey();
            }else {
                cpt--;
                return suiv.getKey();
            }
        }
        
    }


	@Override
	public List<T> elements() {
	    List<T> res = new ArrayList<T>(tab.keySet());
		return res;
	}

	

		
	
}