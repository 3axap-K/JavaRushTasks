package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    private LRUCache cache = new LRUCache<>(16);
    private OriginalRetriever retriever;

    public CachingProxyRetriever(Storage storage) {
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        Object object = cache.find(id);
        if (object == null){
            object = retriever.retrieve(id);
            cache.set(id, object);
        }
        return object;
    }
}
