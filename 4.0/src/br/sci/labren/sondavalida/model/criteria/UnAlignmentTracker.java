package br.sci.labren.sondavalida.model.criteria;

/**
 * 
 * @version 1.0
 * @author Rafael Carvalho Chagas
 * @since Copyright 2012-2014
 *
 */

public class UnAlignmentTracker {
	
	public UnAlignmentTracker() {
		super();
	}
	
	public void unAlignmentTracker(double[][] data, double[][] iqbal, int[][] code) {
		for (int i= 0; i< data.length; i++) {
			if (iqbal[i][2] < 87) {
				if ((data[i][4] != 3333) && (data[i][4] != -6999) && (data[i][4] != -5555)) {
					if ((data[i][6] != 3333) && (data[i][6] != -6999) && (data[i][6] != -5555)) {
						if ((data[i][19] != 3333) && (data[i][19] != -6999) && (data[i][19] != -5555)) {
							if (data[i][4] > 50) {
								if (iqbal[i][5] >= 50) {
									if (iqbal[i][6] > 0.30) {
										code[i][1] = 9;
										code[i][2] = 9;
									} else {
										code[i][1] = 5552;
										code[i][2] = 5552;
									}
								} else if ((iqbal[i][5] >= 0.40) && (iqbal[i][5] < 0.50)) {
									if (iqbal[i][6] > 0.10) {
										code[i][1] = 9;
										code[i][2] = 9;
									} else {
										code[i][1] = 5552;
										code[i][2] = 5552;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}