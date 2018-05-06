package datamanipulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataManipulator {
	
	private static String csvSplit = ",";
	private static PrintWriter writerM, writerE, writerMFocused, writerEFocused;
	private static int globalM = 0, globalE = 0;
	public static int[][] coordCountM = new int[16][16];
	private static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) throws IOException {
		
		try {
			writerM = new PrintWriter(new File("DataFiles/compressed/MIRAMARdeaths.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			writerE = new PrintWriter(new File("DataFiles/compressed/ERANGELdeaths.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			writerMFocused = new PrintWriter(new File("DataFiles/compressed/smallMIRAMARdeaths.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			writerEFocused = new PrintWriter(new File("DataFIles/compressed/smallERANGLdeaths.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		HashMap<String, ArrayList<DeathInfo>> miramar = new HashMap<String, ArrayList<DeathInfo>>();
		HashMap<String, ArrayList<DeathInfo>> erangel = new HashMap<String, ArrayList<DeathInfo>>();
		ArrayList<DeathInfo> deathMira = new ArrayList<DeathInfo>();
		ArrayList<DeathInfo> deathEran = new ArrayList<DeathInfo>();
		int i = 0, counter = 0, counter2 = 0;
		//for(i = 0; i < 5; i++) {
			String fileName = retrieveFile(i);
			System.out.println("reading >> " + fileName);
			File file = new File(fileName);
			String line = "";
			int linesRead = 0;
			try (BufferedReader br = new BufferedReader(new FileReader(file))){
				line = br.readLine();
				while ((line = br.readLine()) != null && (counter < 1000000 || counter2 < 1000000)) {
//					System.out.println(line);
					linesRead++;
					String[] record = line.split(csvSplit);
					record = checkRecordVals(record);
					String map = record[5];
					int time = Integer.parseInt(record[7]);
					String kType = record[0];
					String kName = record[1];
					Double kXPos = Math.abs(Double.parseDouble(record[3]));
					Double kYPos = Math.abs(Double.parseDouble(record[4]));
					String vName = record[8];
					Double vXPos = Math.abs(Double.parseDouble(record[10]));
					Double vYPos = Math.abs(Double.parseDouble(record[11]));
					DeathInfo entry = new DeathInfo(kType, kName, kXPos, kYPos, time, vName, vXPos, vYPos);
					if(map.equals("MIRAMAR") && counter < 1000000) {
						/*
						deathMira.add(entry);
						miramar.put(kType, deathMira);
						*/
						addToFile(counter, line, "M", entry);
						counter++;
					} else if(map.equals("ERANGEL") && counter2 < 1000000){
						/*
						deathEran.add(entry);
						erangel.put(kType, deathEran);
						*/
						addToFile(linesRead, line, "E", entry);
						counter2++;
					}
					//System.out.printf("R: MIRAMAR > %7d | ERANGEL > %7d\n", counter, counter2);
				}
			} catch (IOException e) {
				System.out.println(">> ERROR: File not found.");
				e.printStackTrace();
			}
		//}
		/*
		Set set = miramar.entrySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()) {
			Map.Entry me = (Map.Entry)iterator.next();
			((DeathInfo) me.getValue()).printInfo();
		}
		*/
		writerE.close();
		writerM.close();
		for(i = 15; i >= 0; i--) {
			for(int j = 0; j < 16; j++) {
				System.out.printf("%7d" , coordCountM[i][j]);
			}
			System.out.print("\n");
		}
		System.out.println("PROGRAM END");
	}
	
	public static void addToFile(int count, String entry, String mapCode, DeathInfo death) {
		entry = entry + "\n";
		String output = death.convertToCSVcompact();
		if(mapCode.equals("M")) {
			writerM.write(entry);
			writerMFocused.write(output);
			String[] temp = output.trim().split(",");
			String coord = temp[temp.length-1];
			char letter = coord.charAt(0);
			int xCoord = alpha.indexOf(letter);
			String num = temp[temp.length-1].substring(1, temp[temp.length-1].length());
			int yCoord = Integer.parseInt(num) - 1;
			//System.out.println(count + " : " + output + " - " + xCoord + " " + yCoord);
			coordCountM[xCoord][yCoord]++;
			globalM++;
		} else if (mapCode.equals("E")) {
			writerE.write(entry);
			writerEFocused.write(output);
			globalE++;
		}
		//System.out.printf("W: MIRAMAR > %7d | ERANGEL > %7d\n", globalM, globalE);
	}
	
	public static String[] checkRecordVals(String[] record) {
		if(record[3].isEmpty() || record[4].isEmpty()) {
			record[3] = "0";
			record[4] = "0";
		}
		return record;
	}
	
	public static String retrieveFile(int fileNum) {
		System.out.print("Input data file >> ");
		Scanner input = new Scanner(System.in);
		String fileLocation = "DataFiles/deaths/kill_match_stats_final_" + fileNum + ".csv";
		System.out.println("Input file location: " + fileLocation);
		return fileLocation;
	}
}
