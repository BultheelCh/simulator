/*
package be.kdg.processor.Service;

import java.util.ArrayList;

public class BoeteGrabber implements Subject {
    private ArrayList<Observer> observers;

    public BoeteGrabber(ArrayList<Observer> obsservers) {
        this.observers = new ArrayList<Observer>();
    }

    @Override
    public void register(Observer newObserver) {
        observers.add(newObserver);
    }

    @Override
    public void unregister(Observer deleteObserver) {
        int indexObserver = observers.indexOf(deleteObserver);
        observers.remove(indexObserver);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer: observers){
            observer.berekenBoete();
        }
    }

    public void BerekenBoete(){
        notifyObserver();
    }
}
*/
