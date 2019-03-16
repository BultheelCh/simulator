

package be.kdg.processor.Business;

import be.kdg.processor.Service.Observer;
import be.kdg.processor.Service.Subject;
import be.kdg.processor.model.CameraBericht;
import be.kdg.processor.model.SnelheidsOvertreding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public  class CameraBerichten implements Subject {

    @Autowired
    SnelheidsOvertreding snelheidsOvertreding;

    private List<Observer> observers;
    private List<CameraBericht> cameraBerichten = new ArrayList<>();

    public List<CameraBericht> getCameraBerichten() {
        return cameraBerichten;
    }

    //add camerabericht aan list + notifyObserver
    public void addCameraBericht(CameraBericht cameraBericht){
        this.cameraBerichten.add(cameraBericht);
        notifyObserver(cameraBericht);
    }

    //Observer Pattern
    public CameraBerichten() {
        observers = new ArrayList<Observer>();
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
    public void notifyObserver(CameraBericht cameraBericht) {
        for (Observer observer: observers){
            observer.berekenBoete(cameraBericht, snelheidsOvertreding);
        }
    }

}
