package com.badlogic.androidgames.framework;

import java.util.ArrayList;
import java.util.List;

//Pool class is used to reuse objects instead of leaving them for garbage collector
public class Pool<T> {
	
	//interface has a single method, createObjct() that will return a new object with the generic 
		//type the Pool/PoolObjectFactory instance
    public interface PoolObjectFactory<T> {
        public T createObject();
    }

    //list will store pooled objects
    private final List<T> freeObjects;
    //PoolObjectFactory used to generate new instances of the type held by the class
    private final PoolObjectFactory<T> factory;
    //maximum number of objects that the Pool can hold (If it runs indefinitely, may run out of memory)
    private final int maxSize;

    //constructor of the Pool class takes a PoolObjectFactory and the maximum number of objects it should store. 
    /**
     * Constructor
     * @param factory 
     * @param maxSize maximum number of objects to store
     */
    public Pool(PoolObjectFactory<T> factory, int maxSize) {
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    //creates new instance of the type held by the Pool, via the PoolObjectFactory.newObject() method,
    //or returns a pooled instance in case there's one in the freeObjectsArrayList
    //if we use this method, we get recycled objects as long as the Pool has some stored in the freeObjects list
    //otherwise  the method creates a new one via the factory
    /**
     * creates new instance of the type held by the Pool, via the PoolObjectFactory.newObject() method
     * @return object from the pool
     */
    public T newObject() {
        T object = null;

        if (freeObjects.isEmpty())
            object = factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size() - 1);
        return object;
    }
    
    //reinsert objects that are no longer in use
    //inserts the object into the freeObjects list if it not filled (otherwise, left for garbage collector)
    /**
     * reinsert objects that are no longer in use
     * @param object to be inserted
     */
    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
