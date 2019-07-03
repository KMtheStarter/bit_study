/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

/**
 *
 * @author bit
 */
public class Winner {
    private int won;
    private int game;

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }
    
    public double winRate() {
        double winRate = getWon() * 100 / getGame();
        return winRate;
    }
    
}
