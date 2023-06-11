package model.gameHandlers;

import model.Player;
import model.SlotMachine;

public abstract class Handler {
    private Handler processor;

    public Handler(Handler processor){
        this.processor = processor;
    }

    public abstract void handle(int result, Player player, SlotMachine slotMachine);

    public Handler getProcessor() {
        return processor;
    }
}
