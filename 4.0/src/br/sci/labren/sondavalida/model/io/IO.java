package br.sci.labren.sondavalida.model.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import br.sci.labren.sondavalida.model.statistics.ReportData;

/**
 * 
 * @version 1.0
 * @author Rafael Carvalho Chagas
 * @since Copyright 2012-2014
 *
 */

public class IO {
	private double[][] data;
	private double[][] limits;
	private double[][] visibility;
	
	private int[][] code;
	
	private int numberOfColumns;
	private int numberOfRows;
	
	private List<Object> rawData;
	
	private String[][] station;
	
	private String inputData;
	
	private String outputCode;
	private String outputData;
	private String outputReport;
	
	public IO() {
		rawData = new ArrayList<>(0);
	}
	
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	
	public void setOutputCode(String outputCode) {
		this.outputCode = outputCode;
	}
	
	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}
	
	public void setOutputReport(String outputReport) {
		this.outputReport = outputReport;
	}
	
	private void readData(String input) throws IOException, NumberFormatException {
		String line;
		double[] lines;
		
		BufferedReader in = new BufferedReader(new FileReader(input));
		
		while ((line = in.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line, ",");
			lines = new double[token.countTokens()];
			
			if (numberOfColumns <= 0) {
				numberOfColumns = token.countTokens();
			}
			
			for (int i= 0; i< lines.length; i++) {
				lines[i] = Double.parseDouble(token.nextToken());
			}
			rawData.add(lines);
		}
		numberOfRows = rawData.size();
		in.close();
	}
	
	private void readStation(String input) throws IOException, NumberFormatException {
		String line;
		String[] lines;
		
		BufferedReader in = new BufferedReader(new FileReader(input));
		
		while ((line = in.readLine()) != null) {
			StringTokenizer token = new StringTokenizer(line, ",");
			lines = new String[token.countTokens()];
			
			if (numberOfColumns <= 0) {
				numberOfColumns = token.countTokens();
			}
			
			for (int i= 0; i< lines.length; i++) {
				lines[i] = token.nextToken();
			}
			rawData.add(lines);			
		}
		numberOfRows = rawData.size();
		in.close();
	}
	
	public void writeData(String output, String id, double[][] data) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(output)));
		
		for (double[] data1 : data) {
			
			// Print Headers
			out.printf("%s;", id);
			out.printf("%.0f;", data1[1]);
			out.printf("%.0f;", data1[2]);
			out.printf("%.0f;", data1[3]);
			
			// Print Global Radiation AVG 60s
			if ((data1[4] == 3333) || (data1[4] == -6999)) {
				out.print("N/A;");
			} else if (data1[4] == -5555) {
				out.print("N/S;");
			} else if (data1[4] == 0) {
				out.printf("%.0f;", data1[4]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[4]);
			}
			
			// Print Direct Radiation AVG 60s
			if ((data1[19] == 3333) || (data1[19] == -6999)) {
				out.print("N/A;");
			} else if (data1[19] == -5555) {
				out.print("N/S;");
			} else if (data1[19] == 0) {
				out.printf("%.0f;", data1[19]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[19]);
			}
			
			// Print Diffuse Radiation AVG 60s
			if ((data1[6] == 3333) || (data1[6] == -6999)) {
				out.print("N/A;");
			} else if (data1[6] == -5555) {
				out.print("N/S;");
			} else if (data1[6] == 0) {
				out.printf("%.0f;", data1[6]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[6]);
			}
			
			// Print LongWave Radiation AVG 60s
			if ((data1[21] == 3333) || (data1[21] == -6999)) {
				out.print("N/A;");
			} else if (data1[21] == -5555) {
				out.print("N/S;");
			} else if (data1[21] == 0) {
				out.printf("%.0f;", data1[21]);
			} else {
				out.printf(Locale.US, "%5.1f;", data1[21]);
			}
			
			// Print PAR Radiation AVG 60s
			if ((data1[8] == 3333) || (data1[8] == -6999)) {
				out.print("N/A;");
			} else if (data1[8] == -5555) {
				out.print("N/S;");
			} else if (data1[8] == 0) {
				out.printf("%.0f;", data1[8]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[8]);
			}
			
			// Print LUX Radiation AVG 60s
			if ((data1[10] == 3333) || (data1[10] == -6999)) {
				out.print("N/A;");
			} else if (data1[10] == -5555) {
				out.print("N/S;");
			} else if (data1[10] == 0) {
				out.printf("%.0f;", data1[10]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[10]);
			}
			
			// Print Air Temperature AVG 60s
			if ((data1[12] == 3333) || (data1[12] == -6999)) {
				out.print("N/A;");
			} else if (data1[12] == -5555) {
				out.print("N/S;");
			} else if (data1[12] == 0) {
				out.printf("%.0f;", data1[12]);
			} else {
				out.printf(Locale.US, "%5.2f;", data1[12]);
			}
			
			// Print Relative Humidity AVG 60s
			if ((data1[13] == 3333) || (data1[13] == -6999)) {
				out.print("N/A;");
			} else if (data1[13] == -5555) {
				out.print("N/S;");
			} else if (data1[13] == 0) {
				out.printf("%.0f;", data1[13]);
			} else {
				out.printf(Locale.US, "%5.2f;", data1[13]);
			}
			
			// Print Atmospheric Pressure AVG 60s
			if ((data1[14] == 3333) || (data1[14] == -6999)) {
				out.print("N/A;");
			} else if (data1[14] == -5555) {
				out.print("N/S;");
			} else if (data1[14] == 0) {
				out.printf("%.0f;", data1[14]);
			} else {
				out.printf(Locale.US, "%5.2f;", data1[14]);
			}
			
			// Print Accumulated Precipitation AVG 60s
			if (data1[15] == 3333) {
				out.print("N/A;");
			} else if (data1[15] == -5555) {
				out.print("N/S;");
			} else if (data1[15] == 0) {
				out.printf("%.0f;", data1[15]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[15]);
			}
			
			// Print Wind Speed AVG 60s
			if ((data1[16] == 3333) || (data1[16] == -6999)) {
				out.print("N/A;");
			} else if (data1[16] == -5555) {
				out.print("N/S;");
			} else if (data1[16] == 0) {
				out.printf("%.0f;", data1[16]);
			} else {
				out.printf(Locale.US, "%5.3f;", data1[16]);
			}
			
			// Print Wind Direction AVG 60s
			if ((data1[17] == 3333) || (data1[17] == -6999)) {
				out.print("N/A\n");
			} else if (data1[17] == -5555) {
				out.print("N/S\n");
			} else if (data1[17] == 0) {
				out.printf("%.0f\n", data1[17]);
			} else {
				out.printf(Locale.US, "%5.1f;", data1[17]);
			}
		}
		out.close();
	}
	
	public void writeCode(String output, String id, int[][] code, double[][] data) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(output)));
			
		for (int i= 0; i< data.length; i++) {
			
			// Print Headers
			out.printf("%s;", id);
			out.printf("%.0f;", data[i][1]);
			out.printf("%.0f;", data[i][2]);
			out.printf("%.0f;", data[i][3]);
			
			// Print Code Global Radiation
			if (code[i][0] == 9999) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == 5999) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == 5552) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == 5529) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == 5299) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == 2999) {
				out.printf("%d;", code[i][0]);
			} else if (code[i][0] == -5555) {
				out.print("N/S;");
			} else if ((code[i][0] == 3333) || (code[i][0] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Direct Radiation
			if (code[i][1] == 9999) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == 5999) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == 5552) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == 5529) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == 5299) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == 2999) {
				out.printf("%d;", code[i][1]);
			} else if (code[i][1] == -5555) {
				out.print("N/S;");
			} else if ((code[i][1] == 3333) || (code[i][1] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Diffuse Radiation
			if (code[i][2] == 9999) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == 5999) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == 5552) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == 5529) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == 5299) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == 2999) {
				out.printf("%d;", code[i][2]);
			} else if (code[i][2] == -5555) {
				out.print("N/S;");
			} else if ((code[i][2] == 3333) || (code[i][2] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code LongWave Radiation
			if (code[i][3] == 999) {
				out.printf("0%d;", code[i][3]);
			} else if (code[i][3] == 599) {
				out.printf("0%d;", code[i][3]);
			} else if (code[i][3] == 552) {
				out.printf("0%d;", code[i][3]);
			} else if (code[i][3] == 529) {
				out.printf("0%d;", code[i][3]);
			} else if (code[i][3] == 299) {
				out.printf("0%d;", code[i][3]);
			} else if (code[i][3] == -5555) {
				out.print("N/S;");
			} else if ((code[i][3] == 3333) || (code[i][3] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code PAR Radiation
			if (code[i][4] == 999) {
				out.printf("0%d;", code[i][4]);
			} else if (code[i][4] == 599) {
				out.printf("0%d;", code[i][4]);
			} else if (code[i][4] == 552) {
				out.printf("0%d;", code[i][4]);
			} else if (code[i][4] == 529) {
				out.printf("0%d;", code[i][4]);
			} else if (code[i][4] == 299) {
				out.printf("0%d;", code[i][4]);
			} else if (code[i][4] == -5555) {
				out.print("N/S;");
			} else if ((code[i][4] == 3333) || (code[i][4] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code LUX Radiation
			if (code[i][5] == 999) {
				out.printf("0%d;", code[i][5]);
			} else if (code[i][5] == 599) {
				out.printf("0%d;", code[i][5]);
			} else if (code[i][5] == 552) {
				out.printf("0%d;", code[i][5]);
			} else if (code[i][5] == 529) {
				out.printf("0%d;", code[i][5]);
			} else if (code[i][5] == 299) {
				out.printf("0%d;", code[i][5]);
			} else if (code[i][5] == -5555) {
				out.print("N/S;");
			} else if ((code[i][5] == 3333) || (code[i][5] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Air Temperature
			if (code[i][6] == 999) {
				out.printf("0%d;", code[i][6]);
			} else if (code[i][6] == 599) {
				out.printf("0%d;", code[i][6]);
			} else if (code[i][6] == 552) {
				out.printf("0%d;", code[i][6]);
			} else if (code[i][6] == 529) {
				out.printf("0%d;", code[i][6]);
			} else if (code[i][6] == 299) {
				out.printf("0%d;", code[i][6]);
			} else if (code[i][6] == -5555) {
				out.print("N/S;");
			} else if ((code[i][6] == 3333) || (code[i][6] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Relative Humidity
			if (code[i][7] == 9) {
				out.printf("000%d;", code[i][7]);
			} else if (code[i][7] == 2) {
				out.printf("000%d;", code[i][7]);
			} else if (code[i][7] == -5555) {
				out.print("N/S;");
			} else if ((code[i][7] == 3333) || (code[i][7] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Atmospheric Pressure
			if (code[i][8] == 99) {
				out.printf("00%d", code[i][8]);
			} else if (code[i][8] == 59) {
				out.printf("00%d;", code[i][8]);
			} else if (code[i][8] == 52) {
				out.printf("00%d;", code[i][8]);
			} else if (code[i][8] == 29) {
				out.printf("00%d;", code[i][8]);
			} else if (code[i][8] == -5555) {
				out.print("N/S;");
			} else if ((code[i][8] == 3333) || (code[i][8] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Accumulated Precipitation
			if (code[i][9] == 999) {
				out.printf("0%d;", code[i][9]);
			} else if (code[i][9] == 599) {
				out.printf("0%d;", code[i][9]);
			} else if (code[i][9] == 552) {
				out.printf("0%d;", code[i][9]);
			} else if (code[i][9] == 529) {
				out.printf("0%d;", code[i][9]);
			} else if (code[i][9] == 299) {
				out.printf("0%d;", code[i][9]);
			} else if (code[i][9] == -5555) {
				out.print("N/S;");
			} else if ((code[i][9] == 3333) || (code[i][9] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Wind Speed
			if (code[i][10] == 999) {
				out.printf("0%d;", code[i][10]);
			} else if (code[i][10] == 599) {
				out.printf("0%d;", code[i][10]);
			} else if (code[i][10] == 552) {
				out.printf("0%d;", code[i][10]);
			} else if (code[i][10] == 529) {
				out.printf("0%d;", code[i][10]);
			} else if (code[i][10] == 299) {
				out.printf("0%d;", code[i][10]);
			} else if (code[i][10] == -5555) {
				out.print("N/S;");
			} else if ((code[i][10] == 3333) || (code[i][10] == -6999)) {
				out.print("N/A;");
			}
			
			// Print Code Wind Direction
			if (code[i][11] == 999) {
				out.printf("0%d\n", code[i][11]);
			} else if (code[i][11] == 599) {
				out.printf("0%d\n", code[i][11]);
			} else if (code[i][11] == 552) {
				out.printf("0%d\n", code[i][11]);
			} else if (code[i][11] == 529) {
				out.printf("0%d\n", code[i][11]);
			} else if (code[i][11] == 299) {
				out.printf("0%d\n", code[i][11]);
			} else if (code[i][11] == -5555) {
				out.print("N/S\n");
			} else if ((code[i][11] == 3333) || (code[i][11] == -6999)) {
				out.print("N/A\n");
			}
		}
		out.close();
	}
	
	public void writeReportData(String output, String[][] station, int year, int month, ReportData rData) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(output)));
		
		out.print("Percentage Values ​​Of the Suspects Data\n");
        out.print("in the Levels 1, 2, 3 and 4 and Gaps (N/A).\n\n\n");
        out.printf("Station: (%s id %s) LON: %s LAT: %s\n", station[0][2], station[0][0].substring(1, 3), station[0][3], station[0][4]);
        out.printf("Date: %d-%d\n", month, year);
        out.printf("Number Of Lines: %d\n\n", numberOfRows);
        
        // Print Averages Global Radiation
        for (double[] avg_gl : rData.getAvgGl()) {
        	if (avg_gl[6] == 0) {
        		out.printf(Locale.US, "Global Level 1 = %.1f%%\n", avg_gl[0]);
        		out.printf(Locale.US, "Global Level 2 = %.1f%%\n", avg_gl[1]);
        		out.printf(Locale.US, "Global Level 3 = %.1f%%\n", avg_gl[2]);
        		out.printf(Locale.US, "Global Level 4 = %.1f%%\n", avg_gl[3]);
        		out.printf(Locale.US, "Global Valid   = %.1f%%\n", avg_gl[5]);
        		out.printf(Locale.US, "Global N/A     = %.1f%%\n\n", avg_gl[4]);
        	} else {
        		out.print("Global Level 1  = N/S\n");
        		out.print("Global Level 2  = N/S\n");
        		out.print("Global Level 3  = N/S\n");
        		out.print("Global Level 4  = N/S\n");
        		out.print("Global Valid    = N/S\n");
        		out.print("Global N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Direct Radiation
        for (double[] avg_di : rData.getAvgDi()) {
        	if (avg_di[6] == 0) {
        		out.printf(Locale.US, "Direct Level 1 = %.1f%%\n", avg_di[0]);
        		out.printf(Locale.US, "Direct Level 2 = %.1f%%\n", avg_di[1]);
        		out.printf(Locale.US, "Direct Level 3 = %.1f%%\n", avg_di[2]);
        		out.printf(Locale.US, "Direct Level 4 = %.1f%%\n", avg_di[3]);
        		out.printf(Locale.US, "Direct Valid   = %.1f%%\n", avg_di[5]);
        		out.printf(Locale.US, "Direct N/A     = %.1f%%\n\n", avg_di[4]);
        	} else {
        		out.print("Direct Level 1  = N/S\n");
        		out.print("Direct Level 2  = N/S\n");
        		out.print("Direct Level 3  = N/S\n");
        		out.print("Direct Level 4  = N/S\n");
        		out.print("Direct Valid    = N/S\n");
        		out.print("Direct N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Diffuse Radiation
        for (double[] avg_df : rData.getAvgDf()) {
        	if (avg_df[6] == 0) {
        		out.printf(Locale.US, "Diffuse Level 1 = %.1f%%\n", avg_df[0]);
        		out.printf(Locale.US, "Diffuse Level 2 = %.1f%%\n", avg_df[1]);
        		out.printf(Locale.US, "Diffuse Level 3 = %.1f%%\n", avg_df[2]);
        		out.printf(Locale.US, "Diffuse Level 4 = %.1f%%\n", avg_df[3]);
        		out.printf(Locale.US, "Diffuse Valid   = %.1f%%\n", avg_df[5]);
        		out.printf(Locale.US, "Diffuse N/A     = %.1f%%\n\n", avg_df[4]);
        	} else {
        		out.print("Diffuse Level 1  = N/S\n");
        		out.print("Diffuse Level 2  = N/S\n");
        		out.print("Diffuse Level 3  = N/S\n");
        		out.print("Diffuse Level 4  = N/S\n\n");
        	}
        }
        
        // Print Averages LongWave Radiation
        for (double[] avg_lw : rData.getAvgLw()) {
        	if (avg_lw[5] == 0) {
        		out.printf(Locale.US, "LongWave Level 1 = %.1f%%\n", avg_lw[0]);
        		out.printf(Locale.US, "LongWave Level 2 = %.1f%%\n", avg_lw[1]);
        		out.printf(Locale.US, "LongWave Level 3 = %.1f%%\n", avg_lw[2]);
        		out.printf(Locale.US, "LongWave Valid   = %.1f%%\n", avg_lw[4]);
        		out.printf(Locale.US, "LongWave N/A     = %.1f%%\n\n", avg_lw[3]);
        	} else {
        		out.print("LongWave Level 1  = N/S\n");
        		out.print("LongWave Level 2  = N/S\n");
        		out.print("LongWave Level 3  = N/S\n");
        		out.print("LongWave Valid    = N/S\n");
        		out.print("LongWave N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages PAR Radiation
        for (double[] avg_pa : rData.getAvgPa()) {
        	if (avg_pa[5] == 0) {
        		out.printf(Locale.US, "PAR Level 1 = %.1f%%\n", avg_pa[0]);
        		out.printf(Locale.US, "PAR Level 2 = %.1f%%\n", avg_pa[1]);
        		out.printf(Locale.US, "PAR Level 3 = %.1f%%\n", avg_pa[2]);
        		out.printf(Locale.US, "PAR Valid   = %.1f%%\n", avg_pa[4]);
        		out.printf(Locale.US, "PAR N/A     = %.1f%%\n\n", avg_pa[3]);
        	} else {
        		out.print("PAR Level 1  = N/S\n");
        		out.print("PAR Level 2  = N/S\n");
        		out.print("PAR Level 3  = N/S\n");
        		out.print("PAR Valid    = N/S\n");
        		out.print("PAR N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages LUX Radiation
        for (double[] avg_lx : rData.getAvgLx()) {
        	if (avg_lx[5] == 0) {
        		out.printf(Locale.US, "LUX Level 1 = %.1f%%\n", avg_lx[0]);
        		out.printf(Locale.US, "LUX Level 2 = %.1f%%\n", avg_lx[1]);
        		out.printf(Locale.US, "LUX Level 3 = %.1f%%\n", avg_lx[2]);
        		out.printf(Locale.US, "LUX Valid   = %.1f%%\n", avg_lx[4]);
        		out.printf(Locale.US, "LUX N/A     = %.1f%%\n\n", avg_lx[3]);
        	} else {
        		out.print("LUX Level 1  = N/S\n");
        		out.print("LUX Level 2  = N/S\n");
        		out.print("LUX Level 3  = N/S\n");
        		out.print("LUX Valid    = N/S\n");
        		out.print("LUX N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Air Temperature
        for (double[] avg_tp : rData.getAvgTp()) {
        	if (avg_tp[5] == 0) {
        		out.printf(Locale.US, "Temperature Level 1 = %.1f%%\n", avg_tp[0]);
        		out.printf(Locale.US, "Temperature Level 2 = %.1f%%\n", avg_tp[1]);
        		out.printf(Locale.US, "Temperature Level 3 = %.1f%%\n", avg_tp[2]);
        		out.printf(Locale.US, "Temperature Valid   = %.1f%%\n", avg_tp[4]);
        		out.printf(Locale.US, "Temperature N/A     = %.1f%%\n\n", avg_tp[3]);
        	} else {
        		out.print("Temperature Level 1  = N/S\n");
        		out.print("Temperature Level 2  = N/S\n");
        		out.print("Temperature Level 3  = N/S\n");
        		out.print("Temperature Valid    = N/S\n");
        		out.print("Temperature N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Relative Humidity
        for (double[] avg_rh : rData.getAvgRh()) {
        	if (avg_rh[3] == 0) {
        		out.printf(Locale.US, "Humidity Level 1 = %.1f%%\n", avg_rh[0]);
        		out.printf(Locale.US, "Humidity Valid   = %.1f%%\n", avg_rh[2]);
        		out.printf(Locale.US, "Humidity N/A     = %.1f%%\n\n", avg_rh[1]);
        	} else {
        		out.print("Humidity Level 1  = N/S\n");
        		out.print("Humidity Valid    = N/S\n");
        		out.print("Humidity N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Atmospheric Pressure
        for (double[] avg_ps : rData.getAvgPs()) {
        	if (avg_ps[4] == 0) {
        		out.printf(Locale.US, "Pressure Level 1 = %.1f%%\n", avg_ps[0]);
        		out.printf(Locale.US, "Pressure Level 2 = %.1f%%\n", avg_ps[1]);
        		out.printf(Locale.US, "Pressure Valid   = %.1f%%\n", avg_ps[3]);
        		out.printf(Locale.US, "Pressure N/A     = %.1f%%\n\n", avg_ps[2]);
        	} else {
        		out.print("Pressure Level 1  = N/S\n");
        		out.print("Pressure Level 2  = N/S\n");
        		out.print("Pressure Valid    = N/S\n");
        		out.print("Pressure N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Accumulated Precipitation
        for (double[] avg_pc : rData.getAvgPc()) {
        	if (avg_pc[5] == 0) {
        		out.printf(Locale.US, "Precipitation Level 1 = %.1f%%\n", avg_pc[0]);
        		out.printf(Locale.US, "Precipitation Level 2 = %.1f%%\n", avg_pc[1]);
        		out.printf(Locale.US, "Precipitation Level 3 = %.1f%%\n", avg_pc[2]);
        		out.printf(Locale.US, "Precipitation Valid   = %.1f%%\n", avg_pc[4]);
        		out.printf(Locale.US, "Precipitation N/A     = %.1f%%\n\n", avg_pc[3]);
        	} else {
        		out.print("Precipitation Level 1  = N/S\n");
        		out.print("Precipitation Level 2  = N/S\n");
        		out.print("Precipitation Level 3  = N/S\n");
        		out.print("Precipitation Valid    = N/S\n");
        		out.print("Precipitation N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Wind Speed
        for (double[] avg_ws : rData.getAvgWs()) {
        	if (avg_ws[5] == 0) {
        		out.printf(Locale.US, "Wind Speed Level 1 = %.1f%%\n", avg_ws[0]);
        		out.printf(Locale.US, "Wind Speed Level 2 = %.1f%%\n", avg_ws[1]);
        		out.printf(Locale.US, "Wind Speed Level 3 = %.1f%%\n", avg_ws[2]);
        		out.printf(Locale.US, "Wind Speed Valid   = %.1f%%\n", avg_ws[4]);
        		out.printf(Locale.US, "Wind Speed N/A     = %.1f%%\n\n", avg_ws[3]);
        	} else {
        		out.print("Wind Speed Level 1  = N/S\n");
        		out.print("Wind Speed Level 2  = N/S\n");
        		out.print("Wind Speed Level 3  = N/S\n");
        		out.print("Wind Speed Valid    = N/S\n");
        		out.print("Wind Speed N/A      = N/S\n\n");
        	}
        }
        
        // Print Averages Wind Direction
        for (double[] avg_wd : rData.getAvgWd()) {
        	if (avg_wd[5] == 0) {
        		out.printf(Locale.US, "Wind Direction Level 1 = %.1f%%\n", avg_wd[0]);
        		out.printf(Locale.US, "Wind Direction Level 2 = %.1f%%\n", avg_wd[1]);
        		out.printf(Locale.US, "Wind Direction Level 3 = %.1f%%\n", avg_wd[2]);
        		out.printf(Locale.US, "Wind Direction Valid   = %.1f%%\n", avg_wd[4]);
        		out.printf(Locale.US, "Wind Direction N/A     = %.1f%%", avg_wd[3]);
        	} else {
        		out.print("Wind Direction Level 1  = N/S\n");
        		out.print("Wind Direction Level 2  = N/S\n");
        		out.print("Wind Direction Level 3  = N/S\n");
        		out.print("Wind Direction Valid    = N/S\n");
        		out.print("Wind Direction N/A      = N/S");
        	}
        }
        out.close();
	}
	
	public void buildMatrixData(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		double[] temp;
		
		numberOfColumns = 0;
		numberOfRows = 0;
		
		readData(input);
		
		data = new double[numberOfRows][numberOfColumns];
		
		for (int i= 0; i< numberOfRows; i++) {
			temp = (double[]) rawData.get(i);
			System.arraycopy(temp, 0, data[i], 0, numberOfColumns);
		}
		rawData.clear();
	}
	
	public void buildMatrixCode(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		numberOfRows = 0;
		
		readData(input);
		
		code = new int[numberOfRows][12];
		
		for (int i= 0; i< numberOfRows; i++) {
			code[i] = new int[12];
		}
	}
	
	private void buildMatrixStation(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		String[] temp;
		
		numberOfColumns = 0;
		numberOfRows = 0;
		
		readStation(input);
		
		station = new String[numberOfRows][numberOfColumns];
		
		for (int i= 0; i< numberOfRows; i++) {
			temp = (String[]) rawData.get(i);
			System.arraycopy(temp, 0, station, 0, numberOfColumns);
		}
		rawData.clear();
	}
	
	private void buildMatrixLimits(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		double[] temp;
		
		numberOfColumns = 0;
		numberOfRows = 0;
		
		readData(input);
		
		limits = new double[numberOfRows][numberOfColumns];
		
		for (int i= 0; i< numberOfRows; i++) {
			temp = (double[]) rawData.get(i);
			System.arraycopy(temp, 0, limits[i], 0, numberOfColumns);
		}
		rawData.clear();
	}
	
	private void buildMatrixVisibility(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		double[] temp;
		
		numberOfColumns = 0;
		numberOfRows = 0;
		
		readData(input);
		
		visibility = new double[numberOfRows][numberOfColumns];
		
		for (int i= 0; i< numberOfRows; i++) {
			temp = (double[]) rawData.get(i);
			System.arraycopy(temp, 0, visibility[i], 0, numberOfColumns);
		}
		rawData.clear();
	}
	
	public String[] listFiles() {
		File file = new File(".");
		
		FilenameFilter filter = (File dir, String name) -> {
			String ext = ".dat";
			String lowerCaseName = name.toLowerCase();
			
			return lowerCaseName.endsWith(ext);
		};
		
		File[] listFiles = file.listFiles(filter);
		String[] files = new String[listFiles.length];
		
		int i = 0;
		for (File f : listFiles) {
			files[i] = f.getName();
			i++;
		}
		return files;
	}
	
	public String getMonth(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixData(input);
		
		String month = null;
		
		if (data[0][2] <= 31) {
			month = "01";
		} else if ((data[0][2] > 31) && (data[data.length][2] <= 60)) {
			month = "02";
		} else if ((data[0][2] >= 60) && (data[data.length][2] <= 91)) {
			month = "03";
		} else if ((data[0][2] >= 91) && (data[data.length][2] <= 121)) {
			month = "04";
		} else if ((data[0][2] >= 121) && (data[data.length][2] <= 152)) {
			month = "05";
		} else if ((data[0][2] >= 152) && (data[data.length][2] <= 182)) {
			month = "06";
		} else if ((data[0][2] >= 182) && (data[data.length][2] <= 213)) {
			month = "07";
		} else if ((data[0][2] >= 213) && (data[data.length][2] <= 244)) {
			month = "08";
		} else if ((data[0][2] >= 244) && (data[data.length][2] <= 274)) {
			month = "09";
		} else if ((data[0][2] >= 274) && (data[data.length][2] <= 305)) {
			month = "10";
		} else if ((data[0][2] >= 305) && (data[data.length][2] <= 335)) {
			month = "11";
		} else {
			month = "12";
		}
		return month;
	}
	
	public String getYear(String input) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixData(input);
		
		String year = null;
		
		year = Double.toString(data[0][1]);
		
		return year;
	}
	
	public double getMonthVisibility(String input, int month) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixVisibility(input);
		
		double vis = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (visibility[i][0] == month) {
				vis = visibility[i][1];
				break;
			}
		}
		return vis;		
	}
	
	public String[][] getStation(String input, String stat) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixStation(input);
		
		String[][] station1 = new String[1][numberOfColumns];
		
		for (int i= 0; i< numberOfRows; i++) {
			if (stat.equals(station[i][0])) {
				station1[0][0] = station[i][0];
				station1[0][1] = station[i][1];
				station1[0][2] = station[i][2];
				station1[0][3] = station[i][3];
				station1[0][4] = station[i][4];
				break;
			}
		}
		return station1;
	}
	
	public double getTempMax(String input, int station, int month) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixLimits(input);
		
		double temp_max = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (limits[i][0] == station) {
				temp_max = limits[i][month];
				break;
			}
		}
		return temp_max;
	}
	
	public double getTempMin(String input, int station, int month) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixLimits(input);
		
		double temp_min = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (limits[i][0] == station) {
				temp_min = limits[i][month];
				break;
			}
		}
		return temp_min;
	}
	
	public double getPresMax(String input, int station) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixLimits(input);
		
		double pres_max = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (limits[i][0] == station) {
				pres_max = limits[i][1];
				break;
			}
		}
		return pres_max;
	}
	
	public double getPresMin(String input, int station) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixLimits(input);
		
		double pres_min = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (limits[i][0] == station) {
				pres_min = limits[i][1];
				break;
			}
		}
		return pres_min;
	}
	
	public double getPrecMax(String input, int station, int month) throws IndexOutOfBoundsException, IOException, NumberFormatException {
		buildMatrixLimits(input);
		
		double prec_max = 0;
		
		for (int i= 0; i< numberOfRows; i++) {
			if (limits[i][0] == station) {
				prec_max = limits[i][month];
				break;
			}
		}
		return prec_max;
	}
	
	public double[][] getData() {
		return data;
	}
	
	public int[][] getCode() {
		return code;
	}
	
	public int getCols() {
		return numberOfColumns;
	}
	
	public int getRows() {
		return numberOfRows;
	}
	
	public String[][] getStation() {
		return station;
	}
	
	public String getInputData() {
		return inputData;
	}
	
	public String getOutputCode() {
		return outputCode;
	}
	
	public String getOutputData() {
		return outputData;
	}
	
	public String getOutputReport() {
		return outputReport;
	}
}