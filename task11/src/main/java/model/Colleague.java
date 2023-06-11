package model;

import java.util.ArrayList;

public abstract class Colleague {
    protected Mediator mediator;

    protected ArrayList<Qweston> receivedMessage;

    public Colleague(Mediator mediator) {
        this.mediator = mediator;
        this.receivedMessage = new ArrayList<>();
    }

    public abstract void notifyColleague(ArrayList<Qweston> message);

    public void receive(ArrayList<Qweston> message){
        this.receivedMessage=message;
    }

    public  ArrayList<Qweston> getReceivedMessage() {
        return this.receivedMessage;
    }
}
