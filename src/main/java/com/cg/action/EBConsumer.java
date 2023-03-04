package com.cg.action;

@FunctionalInterface
public interface EBConsumer<T, U, V, W> {

    void accept(T t, U u, V v, W w);

}