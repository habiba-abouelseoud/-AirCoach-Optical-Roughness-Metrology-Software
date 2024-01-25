


import binmethod.SturgesFormula;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author habib
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import binmethod.SturgesFormula;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import statutils.Statutils;
import mathutils.GraphFitting;


/**
 *
 * @author habib
 */
public class Unit_test_mathutils {
   @Test
  public void main(){
    
        List<Double> exampleData = Arrays.asList(1.,2.,3.,4.,5.,6.,7.,8.,9.,10.,11.);
        
        //Instantiate the Statformula class
        Statutils statutilsInstance = new Statutils(exampleData);
        
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        SturgesInstance.calculateNumberOfBins();
        System.out.printf("By Sturges Formula: %d \n", SturgesInstance.getNumberOfBins());
        statutilsInstance.binDataCount(SturgesInstance);
        statutilsInstance.Normalised_hist(SturgesInstance);
        
        GraphFitting GraphFittingInstance = new GraphFitting(statutilsInstance.Normalised_hist(SturgesInstance));
        GraphFittingInstance.perform_calculations();
        
        System.out.printf("Normalisation factor = %f\n", GraphFittingInstance.getNormalisedFactor());
        System.out.printf("Mean = %f\n", GraphFittingInstance.getMean());
        System.out.printf("Sigma = %f\n", GraphFittingInstance.getSigma());
    
    }

    
}

