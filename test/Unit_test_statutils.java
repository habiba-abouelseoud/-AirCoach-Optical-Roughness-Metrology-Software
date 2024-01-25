

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import statutils.*;
import binmethod.RiceRule;
/**
 *
 * @author habib
 */
public class Unit_test_statutils {
    
  // public static void main(String[] args) {
        @Test
    public void main(){
 // Create array of data{
        
        List<Double> exampleData = Arrays.asList(1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.);
        
        //Instant from statutils class
        Statutils statutilsInstance = new Statutils (exampleData);
        
        
        
        //Mean 
        System.out.printf("Mean of data: %f \n",statutilsInstance.getMean());
        
       //Median
        System.out.printf("Median of data: %f \n", statutilsInstance.getMedian());
      
        //Standard Deviation
        System.out.printf("Standard Deviation of data: %f \n", statutilsInstance.getStandard_deviation());
        
        //Variance
        System.out.printf("Variance of data: %f \n", statutilsInstance.getVariance());
    
        //Max
        System.out.printf("Max of data: %f \n", statutilsInstance.getMax());
      
        //Min
        System.out.printf("Min of data: %f \n", statutilsInstance.getMin());
    
       
       
        
        //Test Rice ruleFormula Class
        RiceRule RiceRuleInstance = new RiceRule(exampleData);
        RiceRuleInstance.calculateNumberOfBins();
        System.out.printf("By rice rule Formula: %d \n", RiceRuleInstance.getNumberOfBins());
        statutilsInstance.binDataCount(RiceRuleInstance);
        
  
       // test if it adds up by one then it is normaliesed data 
        for(int i = 0; i < RiceRuleInstance.getNumberOfBins(); i++){
        System.out.printf("%.2f \n",statutilsInstance.Normalised_hist(RiceRuleInstance).get(i));
        }
        
    }

  
}


