package ru.otus.l12.polymorphism.operations;

import ru.otus.l12.polymorphism.Operation;

/**
 * @author sergey created on 09.09.19.
 */
@SuppressWarnings("java:S106")
public class Update implements Operation {
    @Override
    public void action(String data) {
        System.out.println("update, data:" + data);
    }
}
