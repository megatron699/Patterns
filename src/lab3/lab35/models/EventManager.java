package lab3.lab35.models;

import lab3.lab35.enums.ENameFigure;
import lab3.lab35.interfaces.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    private final Map<ENameFigure, List<Observer>> listeners;

    public EventManager() {
        listeners = new HashMap<>();
    }

    public void subscribe(ENameFigure typeEvent, Observer observer) {
        listeners.computeIfAbsent(typeEvent, k -> new ArrayList<>()).add(observer);
    }

    public void unsubscribe(ENameFigure typeEvent, Observer observer) throws Exception {
        List<Observer> observers = listeners.get(typeEvent);

        if (observers == null) {
            throw new Exception("Данного типа не существует");
        }

        observers.remove(observer);
    }

    public void notify(ENameFigure typeEvent) throws Exception {
        List<Observer> observers = listeners.get(typeEvent);

        if (observers == null) {
            throw new Exception("Данного типа не существует");
        }

        observers.forEach(Observer::update);
    }
}
