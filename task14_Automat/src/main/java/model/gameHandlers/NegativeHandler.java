package model.gameHandlers;

import model.Player;
import model.SlotMachine;

public class NegativeHandler extends Handler {

    public NegativeHandler(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(int result, Player player, SlotMachine slotMachine) {
        player.removeCoins(10);
        slotMachine.addCoins(10);

        if (player.getCoins() > 0) {
            getProcessor().handle(result, player, slotMachine);
        } else {
            getProcessor().handle(result, player, slotMachine);
        }
    }
}