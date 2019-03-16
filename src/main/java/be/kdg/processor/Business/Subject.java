package be.kdg.processor.Business;

import be.kdg.processor.model.CameraBericht;

//interface voeg observers toe, verwijderd en update
public interface Subject {
    public void register(Observer o);
    public void unregister(Observer o);
    public void notifyObserver(CameraBericht cameraBericht);
}
