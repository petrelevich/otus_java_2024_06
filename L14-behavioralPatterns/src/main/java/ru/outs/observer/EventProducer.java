package ru.outs.observer;

import java.util.ArrayList;
import java.util.List;

class EventProducer {

    private final List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    void event(String data) {
        for (int i = 0; i < listeners.size(); ++i) {
            try {
                listeners.get(i).onUpdate(data);
            } catch (Exception ex) {
                // логирование исключения
            }
        }
    }
}
