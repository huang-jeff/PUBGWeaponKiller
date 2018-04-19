/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datascigraphs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Sam Pak
 */
public class DataSciGraphs extends Application {
    
    final static ArrayList<String> DistinctDeaths = new ArrayList<String>();
    final static ArrayList<Integer> DistinctDeathsCount = new ArrayList<Integer>();
    
    
    @Override
    public void start(Stage primaryStage) {
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        */
        
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Death Count");
        xAxis.setLabel("Cause of Death");
        yAxis.setLabel("Number of Deaths");
        
        
        
        String csvFile = "SmallMIRAMARDeath.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
                //File0
               br = new BufferedReader(new FileReader(csvFile));
               line = br.readLine();
               int i = 0;
               while ((line = br.readLine()) != null) {

                   String[] entry = line.split(cvsSplitBy);
                   CreateList(entry);
                   
               }
               br.close();
        } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           } finally {
               if (br != null) {
                   try {
                       br.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           }
        
        
        XYChart.Series series = new XYChart.Series();
        //series.getData().add(new XYChart.Data(entry[0], 1));
        for(int i = 0;i < DistinctDeaths.size(); i++){
            series.getData().add(new XYChart.Data(DistinctDeaths.get(i), DistinctDeathsCount.get(i)));
        }
        
        
        //StackPane root = new StackPane();
        //root.getChildren().add(bc);
        bc.getData().addAll(series);
        
        
        Scene scene = new Scene(bc, 1500,1000);
        
        primaryStage.setTitle("Bar Chart");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void CreateList(String[] entry) {
        if(!DistinctDeaths.contains(entry[0])){
            DistinctDeaths.add(entry[0]);
            DistinctDeathsCount.add(1);
        }
        else{
            DistinctDeathsCount.set(DistinctDeaths.indexOf(entry[0]), DistinctDeathsCount.get(DistinctDeaths.indexOf(entry[0])) + 1);
        }
    }
}

 