/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphical_User_Interface_application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.NumberAxis;
import javafx.stage.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.List;
import statutils.Statutils;
import binmethod.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import mathutils.*;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
/**
 * FXML Controller class
 *
 * @author habib
 */
public class OpticalRoughnessController  {

    
    
    List<Double> exampleData;
    List<Double> NormalisedBin_Data;
    List<Double> Line_fit_data;
    BinFormulae binformula;
    Statutils statutilsInstance;
    XYChart.Series Set_no1;
    XYChart.Series Set_no2;
    GraphFitting GraphFittingInstance;
    
    @FXML
    private Label SigmaLabel;
    @FXML
    private Label MuoLabel;
    @FXML
    private Label AlphaLabel;
    @FXML
    private ComboBox <String> ComboBox1;
    @FXML
    private CheckBox CheckBox1;
    @FXML
    private Button ClearData;
    @FXML
    private Label Meanlabel;
    @FXML
    private Label VARlabel;
    @FXML
    private Label Medianlabel;
    @FXML
    private Label STDlabel;
    @FXML
    private Button CalculateButton;
    @FXML
    private LineChart<Number, Float> Line_Chart;
    @FXML
    private NumberAxis Y_Axis;
    @FXML 
    private BarChart<Number, Float> Bar_Chart;
    @FXML
    private MenuItem OpenFileButton;
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    /**
     * Initializes the controller class.
     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }    

    @FXML
    void calculateButtonPressed()
    {
          //First get the Bin method
        String bin_method =  ComboBox1.getValue();

        //Then set up the bin formula class based on what is selected 
        switch (bin_method) {
            case "Rice Rule":
                binformula = new RiceRule(exampleData);
                break;
            case "Square Root Choice":
                binformula = new SquareRootChoice(exampleData);
                break;
            case "Sturges Formula":
                binformula = new SturgesFormula(exampleData);
                break;
        }
        //We now need to calculate the bin number.

        binformula.calculateNumberOfBins();

        //Number of bins has been calulated and now needs to be 
        statutilsInstance.binDataCount(binformula);

        NormalisedBin_Data = new ArrayList<Double>();
        //List for graph 
        System.out.printf("NormalisedBin_Data.size()--------------------- %d \n", NormalisedBin_Data.size());
        NormalisedBin_Data = statutilsInstance.Normalised_hist(binformula);

        float sum = 0;

        for (int i = 0; i < NormalisedBin_Data.size(); i++) {
            //System.out.printf("%f \n", NormalisedBin_Data.get(i));
            sum += NormalisedBin_Data.get(i);
        }

        System.out.printf("Check %f should equal 1", sum);

        if (CheckBox1.isSelected()) {
            //Do code to produce the line fitting here
            GraphFittingInstance = new GraphFitting(NormalisedBin_Data);

            Line_fit_data = new ArrayList<Double>();
            GraphFittingInstance.perform_calculations();
            Line_fit_data = GraphFittingInstance.Generate_Data_lines();

            //Create line and assign the curveFitter line data to it
            
            Set_no2 = new XYChart.Series<>();

            for (int i = 0; i < Line_fit_data.size(); i++) {
                String n = String.valueOf(i);
                System.out.printf("%f \n", Line_fit_data.get(i));
                Set_no2.getData().add(new XYChart.Data(n, Line_fit_data.get(i)));
            }

            Line_Chart.getData().addAll(Set_no2);
            SigmaLabel.setText(String.valueOf((double) GraphFittingInstance.getSigma()));
            AlphaLabel.setText(String.valueOf((double) GraphFittingInstance.getNormalisedFactor()));
            MuoLabel.setText(String.valueOf((double) GraphFittingInstance.getMean()));

        }

        Set_no1 = new XYChart.Series<>();

        for (int i = 0; i < NormalisedBin_Data.size(); i++) {
            String n = String.valueOf(i);
            Set_no1.getData().add(new XYChart.Data(n, NormalisedBin_Data.get(i)));
        }
        Bar_Chart.setCategoryGap(0);
        Bar_Chart.getData().addAll(Set_no1);
    }

    @FXML
    void clearGraphandData() 
    {
        
        exampleData.clear();
        NormalisedBin_Data.clear();
        //delete all labels
         Medianlabel.setText("0");
        STDlabel.setText("0");
        Meanlabel.setText("0");
        VARlabel.setText("0");
        AlphaLabel.setText("0");
        MuoLabel.setText("0");
        SigmaLabel.setText("0");
        //delete charts
        Set_no1.getData().clear();
        Set_no2.getData().clear();
        Line_Chart.getData().clear();
        Bar_Chart.getData().clear();
        Line_fit_data.clear();

        

    }


    @FXML
     void saveImage() 
    {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Chart as Image");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(stage);

        if (file == null) {
            return;
        }

        BarChart<Number, Float> BarChart1 = Bar_Chart;
        LineChart<Number, Float> LineChart1 = Line_Chart;

        Scene scene2 = new Scene(new Group(), 590, 450);

        ((Group) scene2.getRoot()).getChildren().add(BarChart1);
        ((Group) scene2.getRoot()).getChildren().add(LineChart1);

        WritableImage image = scene2.snapshot(null);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "PNG", file);
        } catch (IOException ex) {
            Logger.getLogger(OpticalRoughnessController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Image Saved");

        System.gc();
    }

    @FXML
  void OpenFileButtonPressed(ActionEvent event)
    {
      Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open the .txt data file");
        File file = fileChooser.showOpenDialog(stage);
        System.out.printf("%s", file);
        exampleData = new ArrayList<Double>();
        try {
            
            Scanner Reader = new Scanner(file);

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                Double data2 = Double.parseDouble(data);
                exampleData.add(data2);
            }
            String inputstr = new String();
            statutilsInstance = new Statutils();

            statutilsInstance.setData(exampleData);
            Meanlabel.setText(inputstr.valueOf(statutilsInstance.getMean()));
            VARlabel.setText(inputstr.valueOf(statutilsInstance.getVariance()));
            Medianlabel.setText(inputstr.valueOf(statutilsInstance.getMedian()));
            STDlabel.setText(inputstr.valueOf(statutilsInstance.getStandard_deviation()));
            statutilsInstance.getMax();
            statutilsInstance.getMin();
            Reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There was an error!");
            e.printStackTrace();
        }  
        
        
    }
    
  @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert CalculateButton != null : "fx:id=\"CalculateButton\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert Bar_Chart != null : "fx:id=\"Bar_Chart\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert ComboBox1 != null : "fx:id=\"ComboBox1\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert CheckBox1 != null : "fx:id=\"CheckBox1\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert Meanlabel != null : "fx:id=\"Meanlabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert VARlabel != null : "fx:id=\"VARlabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert Medianlabel != null : "fx:id=\"Medianlabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert STDlabel != null : "fx:id=\"STDlabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert Y_Axis != null : "fx:id=\"Y_Axis\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert ClearData != null : "fx:id=\"ClearData\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert SigmaLabel != null : "fx:id=\"SigmaLabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert MuoLabel != null : "fx:id=\"MuoLabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert AlphaLabel != null : "fx:id=\"AlphaLabel\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        assert OpenFileButton != null : "fx:id=\"OpenFileButton\" was not injected: check your FXML file 'OpticalRoughness.fxml'.";
        ComboBox1.getItems().setAll("Rice Rule", "Square Root Choice", "Sturges Formula");
        Line_Chart.setLegendVisible(false);
        Line_Chart.setAnimated(false);
        Line_Chart.setCreateSymbols(true);
        Line_Chart.setAlternativeRowFillVisible(false);
        Line_Chart.setAlternativeColumnFillVisible(false);
        Line_Chart.setHorizontalGridLinesVisible(false);
        Line_Chart.setVerticalGridLinesVisible(false);
        Line_Chart.getXAxis().setVisible(false);

    }
  
}
