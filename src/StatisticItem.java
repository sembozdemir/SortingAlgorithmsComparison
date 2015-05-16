
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Semih Bozdemir
 */
public class StatisticItem {
    public static int TEST_REPEAT = 10;
    
    private long average;
    private double stdDev;
    private long total;
    private long karetoplam;

    public StatisticItem() {
        average = 0;
        stdDev = 0;
        total = 0;
        karetoplam = 0;
    }

    public long getAverage() {
        return average;
    }

    public double getStdDev() {
        return stdDev;
    }

    public String getStdDevFormatted() {
        DecimalFormat df = new DecimalFormat("#.##");        
        return df.format(stdDev);
    }

    public long getTotal() {
        return total;
    }
    
    public void addTotal(long value) {
        total = total + value;
    }
    
    public void calculateAverage() {
        average = total / TEST_REPEAT;
    }
    
    public void calculateStdDev() {        
        // TODO: Standart sapma hesabı doğru mu kontrol et
        long bolunecek = karetoplam - (TEST_REPEAT*average*average);
        double kokualinacak = bolunecek / TEST_REPEAT;
        
        stdDev = Math.sqrt(kokualinacak);        
    }

    public void addKareToplam(long value) {
        karetoplam = karetoplam + value*value;
    }
    
}
