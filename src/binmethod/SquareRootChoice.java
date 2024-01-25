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

  // inherting the memeber varible using super 

public class SquareRootChoice extends BinFormulae 
{

    public SquareRootChoice(List<Double> _exampleData) {
        super(_exampleData);
    }

  
//calculate using square root that inhertied from the parent class

@Override
    public void calculateNumberOfBins(){
   
   setNumberOfBins((int)(Math.sqrt(getLengthOf_N())));
  
}


}

