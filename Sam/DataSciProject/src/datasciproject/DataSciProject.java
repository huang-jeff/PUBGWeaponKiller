/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasciproject;

import java.io.*;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Sam Pak
 */


public class DataSciProject extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        Pane sp = new Pane();
        Image img = new Image("File:miramar.jpg");
        ImageView imgView = new ImageView(img);
        sp.getChildren().add(imgView);
        

        String csvFile = "SmallMIRAMARDeath.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
                //File0
               br = new BufferedReader(new FileReader(csvFile));
               line = br.readLine();
               while ((line = br.readLine()) != null) {

                   String[] entry = line.split(cvsSplitBy);
                   Rectangle rectangle = RecMaker(Double.parseDouble(entry[10]), Double.parseDouble(entry[11]), entry[0]);
                   
                   sp.getChildren().add(rectangle);
                   
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
        
        Scene scene = new Scene(sp,1000,1000);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public static Rectangle RecMaker(double x, double y, String causeOfDeath) {
        Rectangle rectangle = new Rectangle(0, 0, .5, .5);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setLayoutX((x/800)-15);
        rectangle.setLayoutY((y/800)-10);
        
        //uncomment set of cases to show on map 
        switch (causeOfDeath) {
            
            //shotguns         
            /*         
            case "S686":  rectangle.setStroke(Color.BLUE);
                     break;
            case "S1897": rectangle.setStroke(Color.BLUE);
                     break;
            case "death.WeapSawnoff_C": rectangle.setStroke(Color.BLUE);
                     break;
            case "S12K": rectangle.setStroke(Color.BLUE);
                     break;         
            */
                     
            //Pistols         
            /*         
            case "P92":  rectangle.setStroke(Color.YELLOW);
                     break;
            
            case "P1911": rectangle.setStroke(Color.YELLOW);
                     break;
            case "P18C": rectangle.setStroke(Color.YELLOW);
                     break;
            case "R45": rectangle.setStroke(Color.YELLOW);
                     break;       
            */         
              
            //SMGs
            /*
            case "Tommy Gun": rectangle.setStroke(Color.PURPLE);
                     break;
            case "Micro UZI": rectangle.setStroke(Color.PURPLE);
                     break;
            case "UMP9": rectangle.setStroke(Color.PURPLE);
                     break;
            case "Vector": rectangle.setStroke(Color.PURPLE);
                     break;
            case "VSS": rectangle.setStroke(Color.PURPLE);
                     break;         
            */
                     
            //ARs     
            ///*
            case "M249": rectangle.setStroke(Color.RED);
                     break;
            case "SCAR-L":  rectangle.setStroke(Color.RED);
                     break;
            case "M416":  rectangle.setStroke(Color.RED);
                     break;  
            case "AKM":  rectangle.setStroke(Color.RED);
                     break;
            case "M16A4": rectangle.setStroke(Color.RED);
                     break;
            case "Groza": rectangle.setStroke(Color.RED);
                     break;
            case "AUG": rectangle.setStroke(Color.RED);
                     break;        
            //*/  
               
            //Snipers         
            /*
            case "Win94":  rectangle.setStroke(Color.GREEN);
                     break;
            case "Kar98k": rectangle.setStroke(Color.GREEN);
                     break;
            case "SKS": rectangle.setStroke(Color.GREEN);
                     break;
            case "Mini 14": rectangle.setStroke(Color.GREEN);
                     break;
            case "Mk14": rectangle.setStroke(Color.GREEN);
                     break;
            case "AWM": rectangle.setStroke(Color.GREEN);
                     break;
            case "M24": rectangle.setStroke(Color.GREEN);
                     break;
            */         
              
            //Melee         
            /*
            case "Punch":  rectangle.setStroke(Color.BROWN);
                     break;
            case "Pan": rectangle.setStroke(Color.BROWN);
                     break;
            case "Crossbow": rectangle.setStroke(Color.BROWN);
                     break;         
            case "Machete": rectangle.setStroke(Color.BROWN);
                     break;
            case "Crowbar": rectangle.setStroke(Color.BROWN);
                     break;
            case "Sickle": rectangle.setStroke(Color.BROWN);
                     break;
            */
                     
            //     
            /*         
            case "Grenade":  rectangle.setStroke(Color.SKYBLUE);
                     break;
            case "death.ProjMolotov_DamageField_C": rectangle.setStroke(Color.SKYBLUE);
                     break;
            case "death.ProjMolotov_C": rectangle.setStroke(Color.SKYBLUE);
                     break;         
            */
             
            // the rest         
            /*        
            //case "Down and Out":  rectangle.setStroke(Color.ORANGE); // too vauge to be on map but can see if needed
                     //break;
            case "Hit by Car": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Bluezone": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Falling": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Motorbike (SideCar)": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Buggy": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Van": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Drown": rectangle.setStroke(Color.ORANGE);
                     break;
            case "RedZone": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Pickup Truck": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Motorbike": rectangle.setStroke(Color.ORANGE);
                     break;
            case "death.RedZoneBomb_C": rectangle.setStroke(Color.ORANGE);
                     break;
            case "Aquarail": rectangle.setStroke(Color.ORANGE);
                     break;
            case "death.Buff_FireDOT_C": rectangle.setStroke(Color.ORANGE);
                     break;
            */
            
            default:break;
         
        }
        
        return rectangle;
    }
    
    
    
    
}
