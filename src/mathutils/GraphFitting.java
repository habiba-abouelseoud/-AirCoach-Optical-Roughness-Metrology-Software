/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathutils;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.pow;
import org.apache.commons.math3.fitting.*;
import static java.lang.Math.exp;

/**
 *
 * @author habib
 */


public class GraphFitting 
{
    private static double alpha;
    private static double mu;
    private static double sigma;
    private static  WeightedObservedPoints obs ;  // used for gaussian fit
    private static List <Double> NormalisedFrequency;
    private static double[] DataSets;
    private static List <Double> LinesFitIn;
   
   public double[] GetDataFitting(){
        return DataSets;
    } 
    
   // constructour
    public  GraphFitting (List<Double> normalised_freq)
    {
        NormalisedFrequency = new ArrayList<Double>();
        NormalisedFrequency= normalised_freq;
    }
  
    // function used to calculate  the fitting parameters of the histogram
   public static void perform_calculations(){
        
        obs = new WeightedObservedPoints();
        
        for(int i = 0; i < NormalisedFrequency.size(); i++){
            obs.add(i, NormalisedFrequency.get(i));
        }
        DataSets = GaussianCurveFitter.create().fit(obs.toList());
        alpha = DataSets[0];
        mu = DataSets[1];
        sigma = DataSets[2]; 
        
        
    } 
    
    public static List<Double> Generate_Data_lines(){
        
        // the calculation needed alpha * e ^ (-0.5((x-muo)/sigma)^2
        LinesFitIn = new ArrayList<Double>();
        double Raised_Lines;
        double Normal_PDF;
        System.out.printf("%f Alpha \n", alpha);
        System.out.printf("%f Mu \n", mu);
        System.out.printf("%f Sigma \n", sigma);
        for(int i = 0; i < NormalisedFrequency.size(); i++)
        {
            Raised_Lines = (double) (-0.5*pow(((i-mu)/sigma),2));
            Normal_PDF = (double) (alpha * exp(Raised_Lines));
            LinesFitIn.add(Normal_PDF); 
        }
        return LinesFitIn;
    }
    
    //get sigma , mean and normalised factor and store it 
         public double getSigma(){
        return DataSets[2];
    }
     public double getMean(){
        return DataSets[1];
    }

     public double getNormalisedFactor(){
        return DataSets[0];
    }
     
   
    
}
