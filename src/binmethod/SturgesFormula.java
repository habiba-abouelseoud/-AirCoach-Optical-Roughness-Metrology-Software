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
public class SturgesFormula extends BinFormulae {

    
     // inherting the memeber varible using super 
    public SturgesFormula(List<Double> _exampleData) {
        super(_exampleData);
    }
    
  
//calculate using sturges formula that inhertied from the parent class 

    
    @Override
    public void calculateNumberOfBins(){
   
   setNumberOfBins((int)(3.3*Math.log10(getLengthOf_N())+1));
  
}

    
   
}
