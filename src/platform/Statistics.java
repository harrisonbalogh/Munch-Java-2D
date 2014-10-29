package platform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Statistics {
	
	private final static String RECENT_RECORD_FILE_NAME = "recent_records.txt";
	private final static String TOP_RECORD_FILE_NAME = "top_records.txt";
	private static String recentRecords = "";
	private static String topRecords = "";
	private static int[] topTen = new int[10];

	public Statistics() {
		for(int x = 0; x < 10; x++) topTen[x] = 0;
		recentRecords = readRecentRecords();
		topRecords = readTopRecords();
	}
	
	public static void addStat(int currentScore){
		// DATE & TIME
		String currentTime = getTime();
		
		// SAVING RECENT RECORDS
		if(recentRecords != "error")
			saveRecentRecords(currentScore, currentTime);
		else
			newRecentRecords(currentScore, currentTime);
		recentRecords = readRecentRecords();
		
		// SAVING TOP RECORDS
		if(topRecords != "error"){
			for(int x = 0; x < 10; x++){
				if(currentScore >= topTen[x]){
					saveTopRecords(currentScore, currentTime, x);
					break;
				}
			}
		}
		else
			newTopRecords(currentScore, currentTime);
		topRecords = readTopRecords();
	}
	
	public static String getRecentRecords(){
		return recentRecords;
	}
	public static String getTopRecords(){
		return topRecords;
	}
	
	
	private static String readRecentRecords(){
		try {
            File f = new File(RECENT_RECORD_FILE_NAME);
            Scanner sc = new Scanner(f);
            StringBuilder stringBuilder = new StringBuilder();
            int capacity = 0;
            while(sc.hasNextLine() && capacity < 30){
            	stringBuilder.append(sc.nextLine() + "\n");
            	capacity++;
            }
            return stringBuilder.toString();
            
        } catch (FileNotFoundException e) {         
            e.printStackTrace();
            return "error";
        }
	}
	private static String readTopRecords(){
		try {
            File f = new File(TOP_RECORD_FILE_NAME);
            Scanner sc = new Scanner(f);
            StringBuilder stringBuilder = new StringBuilder();
            int capacity = 0;
            while(sc.hasNextLine() && capacity < 10){
            	String scoreText = sc.nextLine();
            	stringBuilder.append(scoreText + "\n");
            	scoreText = scoreText.substring(0, scoreText.indexOf("@") - 1);
            	try{
            		topTen[capacity] = Integer.parseInt(scoreText); 
            	}
            	catch(NumberFormatException ex){
            		ex.printStackTrace();
            	}
            	capacity++;
            }
            return stringBuilder.toString();
            
        } catch (FileNotFoundException e) {         
            e.printStackTrace();
            return "error";
        }
	}
	
	private static void saveRecentRecords(int score, String date){
		try {
			FileWriter fw = new FileWriter(RECENT_RECORD_FILE_NAME, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(score + " @ " + date + "\n" + recentRecords);
			recentRecords = (score + " @ " + date + "\n" + recentRecords);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}
	private static void saveTopRecords(int score, String date, int rank){
		try {
			FileWriter fw = new FileWriter(TOP_RECORD_FILE_NAME, false);
			BufferedWriter bw = new BufferedWriter(fw);
			Scanner sc = new Scanner(topRecords);
			StringBuilder sb = new StringBuilder();
			for(int x = 0; x < rank; x++){
				sb.append(sc.nextLine() + "\n");
			}
			sb.append(score + " @ " + date + "\n");
			for(int x = rank; x < 10; x++)
				if(sc.hasNextLine()) sb.append(sc.nextLine() + "\n");
			bw.write(sb.toString());
			topRecords = (sb.toString());
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}
	
	private static void newRecentRecords(int score, String date){
		try {
			FileWriter fw = new FileWriter(RECENT_RECORD_FILE_NAME, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("" + score + " @ " + date);
			recentRecords = ("" + score + " @ " + date);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}
	private static void newTopRecords(int score, String date){
		try {
			FileWriter fw = new FileWriter(TOP_RECORD_FILE_NAME, false);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("" + score + " @ " + date);
			topRecords = ("" + score + " @ " + date);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}
	
	private static String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd, HH:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private static void reset(){
		try {
			FileWriter fw1 = new FileWriter(RECENT_RECORD_FILE_NAME, false);
			FileWriter fw2 = new FileWriter(TOP_RECORD_FILE_NAME, false);
			BufferedWriter bw1 = new BufferedWriter(fw1);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw1.write("");
			bw2.write("");
			recentRecords = "";
			topRecords = "";
			bw1.close();
			bw2.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error" + e.getMessage());
		}
	}
}
