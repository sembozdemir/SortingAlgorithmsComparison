
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Semih Bozdemir
 */
public class Analyser {

    private int[] arraySizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

    private String output;
    private String results;
    
    private AlgoritmStat bubbleSortStat;
    private AlgoritmStat quickSortStat;

    public Analyser() {
        output = "";
        results = "";
    }

    public String getOutput() {
        return output;
    }

    public String getResults() {
        return results;
    }

    public void test(int arraySize) {
        //String dialogMessage = "";

        int noOfKeyCompBs = 0;
        int noOfKeyCompQs = 0;
        long runningTimeBs = 0;
        long runningTimeQs = 0;

        // create 3 arrays; A for unsorted, B for Bubble Sort output, C for Quick sort Output
        int A[] = new int[arraySize];
        int B[] = new int[arraySize];
        int C[] = new int[arraySize];

        // generate random numbers
        double k = 0;
        int randomNumber = 0;
        for (int i = 0; i < arraySize; i++) {
            k = Math.random();
            randomNumber = (int) ((k * arraySize) + 1);
            A[i] = randomNumber;
            B[i] = randomNumber;
            C[i] = randomNumber;
        }

        // count the execution time of BubbleSort
        long bsStartTime = System.nanoTime();
        noOfKeyCompBs = SortAlgorithms.bubbleSort(B, B.length);
        long bsEndTime = System.nanoTime();
        runningTimeBs = bsEndTime - bsStartTime;
        //dialogMessage += "Total elapsed time in execution of method BubbleSort :" + runningTimeBs + "\n";

        // count the execution time of QuickSort
        long qsStartTime = System.nanoTime();
        noOfKeyCompQs = SortAlgorithms.quickSort(C, 0, C.length - 1, 0);
        long qsEndTime = System.nanoTime();
        runningTimeQs = qsEndTime - qsStartTime;
        //dialogMessage += "Total elapsed time in execution of method QuickSort :" + runningTimeQs + "\n";

        // Show a dialog about the result
        //JOptionPane.showMessageDialog(null, dialogMessage + "\n" + "Please click OK to continue.", "Total Elapsed Time for input size: " + arraySize, JOptionPane.INFORMATION_MESSAGE);
        // log the process
        log(arraySize, runningTimeBs, runningTimeQs);

        // statistics        
        bubbleSortStat.getKeyCompStat().addTotal(noOfKeyCompBs);
        bubbleSortStat.getKeyCompStat().addKareToplam(noOfKeyCompBs);
        quickSortStat.getKeyCompStat().addTotal(noOfKeyCompQs);
        quickSortStat.getKeyCompStat().addKareToplam(noOfKeyCompQs);
        bubbleSortStat.getRunningTimeStat().addTotal(runningTimeBs);
        bubbleSortStat.getRunningTimeStat().addKareToplam(runningTimeBs);
        quickSortStat.getRunningTimeStat().addTotal(runningTimeQs);
        quickSortStat.getRunningTimeStat().addKareToplam(runningTimeQs);
        

        // create the output string
        output = output + "****************************************************************************************" + "\n"
                + "Algorithm:		BubbleSort		    QuickSort\n"
                + "ArraySize:		" + arraySize + "			    " + arraySize + "\n"
                + "RunningTime(nsn):	" + runningTimeBs + "			    " + runningTimeQs + "\n"
                + "No. of Key Comparasion:	" + noOfKeyCompBs + "			    " + noOfKeyCompQs + "\n"
                + "Input		BubbleSort		QuickSort" + "\n";

        for (int i = 0; i < arraySize; i++) {
            output = output + A[i] + "\t\t" + B[i] + "\t\t\t" + C[i] + "\n";
        }
    }

    public void analyse() {
        
        // create results string        
        results = "    Bubble Sort\t \t \t \t  " + "||" + "QuickSort" + "\n"
                + "    Key Comparison\t \t " + "|" + "Run Time(nsec)\t \t " + "||" + "Key Comparison\t \t " + "|" + "Run Time(msec)" + "\n"
                + "    Average" + "|" + "Std.Dev" + "|" + "Average" + "|" + "Std.Dev" + "||" + "Average" + "|" + "Std.Dev" + "|" + "Average" + "|" + "Std.Dev" + "||" + "\n";


        for (int i = 0; i < arraySizes.length; i++) {
            bubbleSortStat = new AlgoritmStat(arraySizes[i]);
            quickSortStat = new AlgoritmStat(arraySizes[i]);
            for (int j = 0; j < StatisticItem.TEST_REPEAT; j++) {
                test(arraySizes[i]);
            }
            
            bubbleSortStat.getKeyCompStat().calculateAverage();
            bubbleSortStat.getKeyCompStat().calculateStdDev();
            bubbleSortStat.getRunningTimeStat().calculateAverage();
            bubbleSortStat.getRunningTimeStat().calculateStdDev();
            quickSortStat.getKeyCompStat().calculateAverage();
            quickSortStat.getKeyCompStat().calculateStdDev();
            quickSortStat.getRunningTimeStat().calculateAverage();
            quickSortStat.getRunningTimeStat().calculateStdDev();
            
            results += bubbleSortStat.getInputSize() + "    "
                    + bubbleSortStat.getKeyCompStat().getAverage() + "    "
                    + bubbleSortStat.getKeyCompStat().getStdDevFormatted() + "    "
                    + bubbleSortStat.getRunningTimeStat().getAverage() + "    "
                    + bubbleSortStat.getRunningTimeStat().getStdDevFormatted() + "    "
                    + quickSortStat.getKeyCompStat().getAverage() + "    "
                    + quickSortStat.getKeyCompStat().getStdDevFormatted() + "    "
                    + quickSortStat.getRunningTimeStat().getAverage() + "    "
                    + quickSortStat.getRunningTimeStat().getStdDevFormatted() + "    \n";
        }

        // create txt files
        exportFile(output, "output.txt");
        exportFile(results, "results.txt");
    }

    private void exportFile(String output, String fileName) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    // Create file 
                    FileWriter fstream = new FileWriter(fileName);
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(output);
                    //Close the output stream
                    out.close();
                } catch (Exception e) {//Catch exception if any
                    System.err.println("Error: " + e.getMessage());
                }
            }
        };

        thread.start();
    }

    private void log(int arraySize, long runningTimeBs, long runningTimeQs) {
        System.out.println("Total elapsed time for input size " + arraySize + ":"
                + " Bubble Sort = " + runningTimeBs
                + " QuickSort = " + runningTimeQs);
    }
}
