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
public class Holzzak {
    
    private Winner winner;
    
    public void playGame(Winner winner, int n){
        this.winner = winner;
        int eo = (int) (Math.random()*10) % 2;
        
        if (n == 0 && eo == 0){
            winner.setGame(winner.getGame()+1);
            winner.setWon(winner.getWon()+1);
        } else if (n == 1 && eo == 1){
            winner.setGame(winner.getGame()+1);
            winner.setWon(winner.getWon()+1);
        } else winner.setGame(winner.getGame()+1);
    }
    
    
}
