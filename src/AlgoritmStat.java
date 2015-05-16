
import java.util.ArrayList;
import jdk.internal.dynalink.beans.StaticClass;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Semih Bozdemir
 */
public class AlgoritmStat {
    
    private int inputSize;
    private StatisticItem keyCompStat;
    private StatisticItem runningTimeStat;

    public AlgoritmStat(int inputSize) {
        this.inputSize = inputSize;
        keyCompStat = new StatisticItem();
        runningTimeStat = new StatisticItem();
    }

    public int getInputSize() {
        return inputSize;
    }    

    public StatisticItem getKeyCompStat() {
        return keyCompStat;
    }

    public StatisticItem getRunningTimeStat() {
        return runningTimeStat;
    }
    
    
    
}
