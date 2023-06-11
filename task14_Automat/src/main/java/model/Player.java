package model;

public class Player {
    private int coins;

    public Player(int coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins) {
        if (this.coins >= coins) {
            this.coins -= coins;
        } else {
            System.out.println("У игрока недостаточно монет!");
        }
    }
}
