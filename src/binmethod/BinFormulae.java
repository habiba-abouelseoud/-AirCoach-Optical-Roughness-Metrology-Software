/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binmethod;
import java.util.List;

/**
 *
 * @author habib
 */
public abstract class BinFormulae
{
// declare variables k and n
  private List<Double> exampleData;
  private double NumberOfBins;

  //Constructers should be declared to make other sub classes inhert it
    public BinFormulae(List<Double> _exampleData) {
        exampleData=_exampleData;
    }

   
    
    
//Setter and getter for NumberOfBins
    public void setNumberOfBins(double no_of_bins){
        NumberOfBins = no_of_bins;
    }
    
    // int shoud be used as the number of bins is an integer
    public int getNumberOfBins(){
        return (int)NumberOfBins;
    }
   
// function to get the size of data and store it in the function
     public double getLengthOf_N()
    {
        return (double)exampleData.size();
    }
   
    public  void calculateNumberOfBins()
    {
        System.out.printf(" calculates number of bins");
    }
    
      
   
   
}
