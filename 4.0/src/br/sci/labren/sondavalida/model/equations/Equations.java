package br.sci.labren.sondavalida.model.equations;

import static java.lang.Math.*;

/**
 * 
 * @version 1.0
 * @author Rafael Carvalho Chagas
 * @since Copyright 2012-2014
 *
 */

public class Equations {
	private final double ISC = 1368;
	private final double CDR = PI/180;
		
	private double[][] solve;
	
	private double day_angle;
	private double dia_jul;
	private double dec;
	private double eqtime;
	private double tcorr;
	private double hour_angle;
	private double horacorr;
	private double div;
	private double num;
	
	public Equations() {
		super();
	}
	
	public void solved(double[][] data, double latitude, double longitude) {
		solve = new double[data.length][7];
		
		for (int i= 0; i< data.length; i++) {
			num = data[i][3];
            div = num/60;                             // Measurement time in utc time
            dia_jul = (int) data[i][2];
            
            // Calculating astronomical data
            day_angle = (2*PI/365.25*dia_jul);
            dec = (0.006918-0.399912*cos(day_angle)+0.070257*sin(day_angle)-0.006758*cos(2*day_angle)+0.000907*sin(2*day_angle)-0.002697*cos(3*day_angle)+0.001480*sin(3*day_angle));
            eqtime = (0.000075+0.001868*cos(day_angle)-0.032077*sin(day_angle)-0.014615*cos(2*day_angle)-0.040890*sin(2*day_angle))*229.18;
            tcorr = (eqtime+4*(longitude-0))/60;
            horacorr = tcorr+div;   // Local time obtained from utc time
            hour_angle = (12.00-horacorr)*15;
            solve[i][0] = 1.000110+ 0.034221*cos(day_angle)+ 0.001280*sin(day_angle)+0.000719*cos(2*day_angle)+0.000077*sin(2*day_angle);
            solve[i][1] = sin(dec)*sin(latitude*CDR)+cos(dec)*cos(latitude*CDR)*cos(hour_angle*CDR);
            solve[i][2] = (acos(solve[i][1]))*180/PI;
            solve[i][3] = ISC*solve[i][0];
			solve[i][4] = solve[i][3]*solve[i][1];
			solve[i][5] = data[i][4]/solve[i][4];
			solve[i][6] = data[i][28]/data[i][4];
		}
	}
	
	public double[][] getSolve() {
		return solve;
	}
}