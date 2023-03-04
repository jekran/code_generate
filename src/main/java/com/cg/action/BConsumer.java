package com.cg.action;

@FunctionalInterface
public interface BConsumer<T, U, V> {

    void accept(T t, U u, V v);

}