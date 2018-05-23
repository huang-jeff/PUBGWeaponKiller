/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linegraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author Sam Pak
 */
public class LineGraph extends Application {

	final static ArrayList<String> DistinctDeaths = new ArrayList<String>();
	final static ArrayList<int[]> DistinctDeathsCount = new ArrayList<int[]>();


	@Override
	public void start(Stage primaryStage) {
		String csvFile = "DataFiles/compressed/MIRAMARdeaths.csv";
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

		//defining the axes
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Time in Seconds/50");
		//creating the chart
		final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);        
		lineChart.setTitle("Death count by Time");


		for(int i = 0;i < DistinctDeaths.size(); i++){
			//add new if conntition to see which line you want
			if(DistinctDeaths.get(i).equals("Bluezone") || DistinctDeaths.get(i).equals("Down and Out") || DistinctDeaths.get(i).equals("SCAR-L") || DistinctDeaths.get(i).equals("M16A4") || DistinctDeaths.get(i).equals("RedZone") || DistinctDeaths.get(i).equals("M416") || DistinctDeaths.get(i).equals("Kar98k") || DistinctDeaths.get(i).equals("AKM")){
				XYChart.Series series = new XYChart.Series();
				series.setName(DistinctDeaths.get(i));

				for(int x = 0; x < 46; x++){
					series.getData().add(new XYChart.Data(x, DistinctDeathsCount.get(i)[x]));
				}
				lineChart.getData().add(series);
			}
		}


		Scene scene  = new Scene(lineChart, 1500,1000);

		primaryStage.setTitle("Line Chart");
		primaryStage.setScene(scene);
		primaryStage.show();


	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}


	public static void CreateList(String[] entry) {
		if(!DistinctDeaths.contains(entry[0])){
			DistinctDeaths.add(entry[0]);
			int[] temp = new int[46];
			temp[Integer.parseInt(entry[7])/50] = 1;
			DistinctDeathsCount.add(temp);

		}
		else{
			int[] temp = DistinctDeathsCount.get(DistinctDeaths.indexOf(entry[0]));
			temp[Integer.parseInt(entry[7])/50] += 1;

			DistinctDeathsCount.set(DistinctDeaths.indexOf(entry[0]),temp);
		}
	}

}

