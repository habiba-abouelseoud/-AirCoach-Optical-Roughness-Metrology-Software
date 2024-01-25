
/*
To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statutils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import binmethod.*;
/**
 *
 * @author habib
 */

public class Statutils 
{
    private double mean;
    private double variance;
    private double max;
    private double min;
    private  List<Double> data;
    private int sizeOfData;
    private double widthOfBin;
    public List<Double> binData;

    // empty constructor
    public Statutils() {
    }

    
    //constructour for data  to get the size and store it 
    public Statutils(List<Double> _data) {
        data = _data;
        sizeOfData=data.size();
    }

    
      public void setData(List<Double> _data){
        data = _data;
        sizeOfData = data.size();
    }
      // getter for bin data
    public List<Double> getBinData() {
        return binData;
    }
                      
   //Mean function 
    public double getMean(){
        double sum = 0;
        for(int i = 0; i < sizeOfData; i++){
            sum += data.get(i);
        }
        mean = (sum/sizeOfData);
        return (sum/sizeOfData);
    }
    
    //Variance function
    public double getVariance(){
        //Get variance
        
        double sum = 0;
        for(int i = 0; i < sizeOfData; i++){
            sum +=  Math.pow((data.get(i)-mean), 2);
        }
        variance = (sum/sizeOfData);
        return (sum/sizeOfData);
    }
        //Max function using collections features that sort out the list and choses the max function
    public double getMax(){
        max = Collections.max(data);
        return Collections.max(data);        
    }
       
     //Minimum function using collections features that sort out the list and choses the minimum function
    public double getMin(){
        
        min = Collections.min(data);
        return Collections.min(data); 
    }                   
        

//Median function calculation giving the 2 conditions for even and odd
    public double getMedian(){
    
 
   // use collection.sort() to specify the list in ascending order
    Collections.sort(data);
    double median; 
    if (sizeOfData % 2 == 0){
        
        double x = data.get(sizeOfData/2);
        double y = data.get((sizeOfData/2)-1);
        median = (x+y)/2;
      
    } else {
        median = data.get((int)((sizeOfData/2)-0.5));
    }
  
    return median;
    }
    
    
    //Standard deviation calculation using the variance function
    public double getStandard_deviation(){
        //Take root of variance
        return (double) Math.sqrt(variance);
    }
    
//    function used the bin method package that was developed before in order 
//    to get the number of bins using and instance that gets the number of bins
//    with getNumberOfBins()  function in binmethod package , and then after getting
//    the number of bins the function gets the size of each bin by finding the difference
//    of the maximum and minimum and then divide it by the getNumberOfbins() and this calculation
//   stored in Double widthOfBin variable  so by that after getting the bin size then the data in 
//    each bin can be determined, the next step after having the width of the bin is counting up
//   from minimum and everything that is within that bin will be counted and this is done by declaring 2 double variables 
//   called Average and Average_2 in which the first variable stores the minimum value and the second variable stores the 
//    minimum value plus the width of the bin and by using nested for loop where the inner loop counts from 0 to the size of 
//    data and implementing an if statement inside the inner loop that states if the data is greater than the Average variable 
//    and less than or equal the Average_2 variable then it will count up by 1 else it will count up by 0 where the outer for loop
//   will keep looping for getting the number of bins and then adding all these values to binData and also increase the averages .
    
    
    public void binDataCount( BinFormulae the_formulae){
        
        binData = new ArrayList<Double>();
       widthOfBin = ((max-min)/the_formulae.getNumberOfBins());
        
        
        double Average = min;
        double Average_2 = min+widthOfBin;
        for(int j = 0; j < the_formulae.getNumberOfBins(); j++){
            double sum = (double) 0.0;
            for(int i = 0; i < data.size(); i++){
                
                if(data.get(i) > Average && data.get(i) <= Average_2){
                    sum += 1.0;
                } else {
                    sum += 0;
                }
            }
            
             binData.add(sum);
                //Increase 
                Average += widthOfBin;
                Average_2 += widthOfBin;
        } 
    }

    // the function for normalised histogram , the theory is that the normalised frequency is obtained by getting 
    // the observation number for each bin and then divide it by unormalies data multiplied by the size of bin #
    //  bin size = (max(measurement data)-min(measurement data))/k where the k is the number of bins
    // also doing an instance form bin method
    
    public List<Double> Normalised_hist(BinFormulae BinInstance){
        
        List<Double> Normalised_freq = new ArrayList<Double>();
        
        double sum1 = 0;
        for(int i = 0; i <  binData.size(); i++){
            sum1 +=  binData.get(i)*widthOfBin;
        }
        
        for(int i = 0; i <  binData.size(); i++){
            Normalised_freq.add(( binData.get(i)/sum1)*widthOfBin);
            System.out.printf("%f the  binned data \n", ( binData.get(i)/sum1)*widthOfBin);
        }
        
        return Normalised_freq;
        
    }
    
}
   
    

