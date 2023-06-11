package model.gameHandlers;

import model.Player;
import model.SlotMachine;

public class VOIDHandler extends Handler {
    public VOIDHandler() {
        super(null);
    }

    @Override
    public void handle(int result, Player player, SlotMachine slotMachine) {
        if (player.getCoins() <= 0) {
            System.out.println("Вы проиграли все монеты!");
        } else if (slotMachine.getCoins() <= 0) {
            System.out.println("Автомат проиграл все свои монеты!");
        }
    }
}
