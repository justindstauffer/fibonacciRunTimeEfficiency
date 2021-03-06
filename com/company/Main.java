package com.company;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Main {
    
    public static void main(String[] args) {
        // ArrayLists for running Nth Number of times (1-25) in this case
        ArrayList<fibInstance> recursiveInstances = new ArrayList<fibInstance>();
        ArrayList<fibInstance> iterativeInstances = new ArrayList<fibInstance>();
        // Loop through 25 times counting time it takes to get Fibonacci number of Nth Number (i in this case)
        for (int i = 1; i < 26; i++) {
            long recFibStartTime = System.nanoTime();
            int recFib = fibonacciRecursion(i);
            long recFibEndTime = System.nanoTime();
            long recFibTime = (recFibEndTime - recFibStartTime);
            long iterFibStartTime = System.nanoTime();
            int iterFib = iterativeRecursion(i);
            long iterFibEndTime = System.nanoTime();
            long iterFibTime = (iterFibEndTime - iterFibStartTime);
            // Create new instance of fibInstance (A class I created)
            fibInstance recInstance = new fibInstance(i, recFib, recFibTime);
            fibInstance iterInstance = new fibInstance(i, iterFib, iterFibTime);
            // Add the instance of fibInstance for both recursive and iterative methods
            recursiveInstances.add(recInstance);
            iterativeInstances.add(iterInstance);
        }
        // Write the test results to files on my computer to further assess the data and find averages
        // Test are performed 100 times to find averages
        recursiveWriteToFile(recursiveInstances);
        iterativeWriteToFile(iterativeInstances);
    }
    // Method for running 100 tests and writing results for the recursive method
    private static void recursiveWriteToFile(ArrayList<fibInstance> recursiveInstances) {
        File file = new File("C:\\Users\\stauf\\Documents\\recursiveFib.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "nthNumber", "fibResult", "timeToFib" };
            writer.writeNext(header);

            // add data to csv

            for (int i = 0; i < 100; i++) {
                for (fibInstance fibI: recursiveInstances
                ) {
                    String[] data = { Integer.toString(fibI.getNthNumber()) , Integer.toString(fibI.getFibResult()), Long.toString(fibI.getTimeToFib()) };
                    writer.writeNext(data);
                }
            }


            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // Method for running 100 tests and writing results for the iterative method
    private static void iterativeWriteToFile(ArrayList<fibInstance> iterativeInstances) {
        File file = new File("C:\\Users\\stauf\\Documents\\iterativeFib.csv");
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "nthNumber", "fibResult", "timeToFib" };
            writer.writeNext(header);

            // add data to csv

            for (int i = 0; i < 100; i++) {
                for (fibInstance fibI: iterativeInstances
                ) {
                    String[] data = { Integer.toString(fibI.getNthNumber()) , Integer.toString(fibI.getFibResult()), Long.toString(fibI.getTimeToFib()) };
                    writer.writeNext(data);
                }
            }


            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    // Class that takes the Nth number, Result of the number in Fibonacci sequence, and Time it took to find the result (nano seconds)
    public static class fibInstance {
        int nthNumber;
        int fibResult;
        Long timeToFib;
        fibInstance(int nthNumber, int fibResult, long timeToFib){
            this.nthNumber = nthNumber;
            this.fibResult = fibResult;
            this.timeToFib = timeToFib;
        }

        public int getFibResult() {
            return fibResult;
        }

        public int getNthNumber() {
            return nthNumber;
        }

        public Long getTimeToFib() {
            return timeToFib;
        }
    }
    // Recursive method for the Fibonacci Sequence
    public static int fibonacciRecursion(int nthNumber) {
        //use recursion
        if (nthNumber == 0) {

            return 0;

        } else if (nthNumber == 1) {

            return 1;
        }
        return fibonacciRecursion(nthNumber - 1) + fibonacciRecursion(nthNumber - 2);
    }
    // Iterative method for the Fibonacci Sequence
    public static int iterativeRecursion(int nthNumber){
        int secondPrevious, previousNumber = 0, currentNumber = 1;
        if(nthNumber == 0){
            return 0;
        }
        for(int i = 1; i < nthNumber; i++){
            secondPrevious = previousNumber;
            previousNumber = currentNumber;
            currentNumber = secondPrevious + previousNumber;
        }
        return currentNumber;
    }

}
