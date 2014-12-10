/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import nim.Exceptions.*;
import java.util.Random;

/**
 * 
 * @author Garrett
 */
public class NimGame {
    
    int[] sticks;
    int numberOfRows;
    
    /**
     * Initiates NimGame and sets-up how many sticks in each row.
     * @param initialSticks initiate the number of sticks in each row.
     */
    public NimGame(int[] initialSticks) throws TooManyInitSticksInRowException {
        numberOfRows = initialSticks.length; // allow to be used with any gui
        
        for(int x = 0; x < numberOfRows; x++) {
            if(initialSticks[x] > 10) {
                throw new TooManyInitSticksInRowException();
            }
        }
        
        sticks = new int[numberOfRows];
        
        System.arraycopy(initialSticks, 0, sticks, 0, numberOfRows);
    }
    
    /**
     * Returns the number of sticks in a row.
     * @param r Select Row.
     * @return number of sticks in selected row
     */
    public int getRow(int r) throws NoSuchRowException {
        if((r < 0)  || (r > numberOfRows-1)) {throw new NoSuchRowException();}
        return sticks[r];
    }
    
    /**
     * Player based move to subtract correct number of sticks from row, if possible.
     * @param r Select Row.
     * @param s Select number of sticks.
     */
    public void play(int r, int s) throws NoSuchRowException, IllegalSticksException, NotEnoughSticksException{
        if((r < 0)  || (r > numberOfRows-1)) {throw new NoSuchRowException();}
        if((s < 1)  || (s > numberOfRows)) {throw new IllegalSticksException();}
            else if (s > sticks[r]) {throw new NotEnoughSticksException();}
        
        sticks[r] = sticks[r] - s;

    }
    
    /**
     * Tells if current game is over or not.
     * @return true if game is over.
     */
    public boolean isOver() {
        boolean over = true;
        
        for(int x=0;x<numberOfRows;x++) {
            if(sticks[x] > 0) {
                over = false;
                break;
            }
        }
        return over;
    }
    
    /**
     * AI based move to subtract random number of sticks from a random row where possible.
     */
    public void AIMove() {
        Random random = new Random();
        int row = random.nextInt(numberOfRows);
        int numSticks = random.nextInt(3)+1;
        
        while(sticks[row] < numSticks) {
            row = random.nextInt(numberOfRows);
            numSticks = random.nextInt(3)+1;
        }
        
        sticks[row] = sticks[row] - numSticks;
        
    }
    
    /**
     * Used for debugging, will show how many rows and current number of sticks in each row. Example: "# of rows: 3 [3] [5] [7] "
     * @return how many rows and current number of sticks in each row.
     */
    public String toString() {
        String out = "# of rows: " + numberOfRows + " ";
        
        for(int x=0;x<numberOfRows;x++) {
            out = out + "[" + sticks[x] + "] "; 
        }
        
        return out;
    }
}
