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


public class RiceRule extends BinFormulae{
    
        // inherting the memeber varible using super 
    public  RiceRule(List<Double> _exampleData) {
        super(_exampleData);
    }

    //calculate using rice rule that inhertied from the parent class
    @Override
    public void calculateNumberOfBins() {
        setNumberOfBins((int)(2*Math.cbrt(getLengthOf_N())));
    }



    
}
