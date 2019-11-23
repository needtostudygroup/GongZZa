package com.dongkyoo.gongzza.cache;

public interface CacheCallback<T> {

    void onReceive(T t);
}
