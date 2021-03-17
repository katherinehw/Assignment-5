package herman;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.ListIterator;

import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.List;

/**reads a text file for real numbers and returns mean and standard deviation
 * 
 * @author KatherineHerman-Williams
 * 
 *creates statistics class with file, linked list, and list iterator
 */
public class StatsCalc {
    File numfile; 
    LinkedList<String> numlist = new LinkedList<>();
    ListIterator<String > list = numlist.listIterator();

	/**
	 * 
	 * @param numfile File containing numbers which will be used to calculate mean and standard deviation
	 */
	public StatsCalc(File numfile) {

			this.numfile = numfile;
		
	}
	
	public void addToList() throws IOException { 
        Scanner in = new Scanner(this.numfile);

        List<String> searchFile = Files.readAllLines(Path.of(String.valueOf(this.numfile)));/*reads through 
        each line of file
        */

        for (String search: searchFile){

            if (search.matches("")){//reads each string
                continue;
            }
            else if (search.matches("^[0-9]+$")){
            	numlist.add(search);//if number, adds number to linked list

            }
            else{
                JOptionPane.showMessageDialog(null, "file error: not a numbers file");/*returns error 
                statement if file contains non numbers
                */            
     
            }
        }
		
	}
	
	/**
	 * 
	 * @return returns mean of all numbers in chosen file
	 */
	public double mean() {//calculates mean
        int sum = 0; //sum of all numbers in file
        double mean = 0.0;// sum/count
        double count = 0.0;//how many numbers in file

        for (String str : numlist) {//looks through each num in linked list
        	sum += Integer.parseInt(str);//parses num to int from string and adds value to sum
            count++;//keeps track of how many numbers in file
            }

            mean = sum/count;


        if (numlist == null){//if list is empty mean is 0
        	mean = 0.0;
        }
        return mean;

	}
	
	/**
	 * 
	 * @return returns standard deviation of all numbers in chosen file
	 */
	public double stdDev() {
        double mean = mean();//uses mean for standard deviation
        double stDev = 0.0;
        double count = 0.0;


        if (numlist == null){//if linked list is empty standard deviation is 0
        	return 0.0;
        }
        else {
            for (String str : numlist) {//for each num in linked list
            	
                stDev += Math.pow(Integer.parseInt(str) - mean, 2);//subtracts that num from the mean, squares the difference
                count++;//tracks how many numbers
            }
        }
            return Math.sqrt(stDev/count);//devides sum of differences by count, square roots to find average difference from mean.
		
	}

	
}
