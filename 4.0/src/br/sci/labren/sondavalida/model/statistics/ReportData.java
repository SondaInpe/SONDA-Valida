package br.sci.labren.sondavalida.model.statistics;

/**
 * 
 * @version 1.0
 * @author Rafael Carvalho Chagas
 * @since Copyright 2012-2014
 *
 */

public class ReportData {
	private int cont_gl1n= 0, cont_gl2n= 0, cont_gl3n= 0, cont_gl4n= 0, cont_glna= 0, cont_glv= 0;
	private int cont_di1n= 0, cont_di2n= 0, cont_di3n= 0, cont_di4n= 0, cont_dina= 0, cont_div= 0;
	private int cont_df1n= 0, cont_df2n= 0, cont_df3n= 0, cont_df4n= 0, cont_dfna= 0, cont_dfv= 0;
	private int cont_lw1n= 0, cont_lw2n= 0, cont_lw3n= 0, cont_lwna= 0, cont_lwv= 0;
	private int cont_pa1n= 0, cont_pa2n= 0, cont_pa3n= 0, cont_pana= 0, cont_pav= 0;
	private int cont_lx1n= 0, cont_lx2n= 0, cont_lx3n= 0, cont_lxna= 0, cont_lxv= 0;
	private int cont_tp1n= 0, cont_tp2n= 0, cont_tp3n= 0, cont_tpna= 0, cont_tpv= 0;
	private int cont_rh1n= 0, cont_rhna= 0, cont_rhv= 0;
	private int cont_ps1n= 0, cont_ps2n= 0, cont_psna= 0, cont_psv= 0;
	private int cont_pc1n= 0, cont_pc2n= 0, cont_pc3n= 0, cont_pcna= 0, cont_pcv= 0;
	private int cont_ws1n= 0, cont_ws2n= 0, cont_ws3n= 0, cont_wsna= 0, cont_wsv= 0;
	private int cont_wd1n= 0, cont_wd2n= 0, cont_wd3n= 0, cont_wdna= 0, cont_wdv= 0;
	
	private int cont_vgl = 0;
	private int cont_nagl = 0;
	private int cont_vdi = 0;
	private int cont_nadi = 0;
	private int cont_vdf = 0;
	private int cont_nadf = 0;
	private int cont_vlw = 0;
	private int cont_nalw = 0;
	private int cont_vpa = 0;
	private int cont_napa = 0;
	private int cont_vlx = 0;
	private int cont_nalx = 0;
	
	private int flag_gl= 0;
	private int flag_di= 0;
	private int flag_df= 0;
	private int flag_lw= 0;
	private int flag_pa= 0;
	private int flag_lx= 0;
	private int flag_tp= 0;
	private int flag_rh= 0;
	private int flag_ps= 0;
	private int flag_pc= 0;
	private int flag_ws= 0;
	private int flag_wd= 0;
	
	private double[][] avg_gl;
	private double[][] avg_di;
	private double[][] avg_df;
	private double[][] avg_lw;
	private double[][] avg_pa;
	private double[][] avg_lx;
	private double[][] avg_tp;
	private double[][] avg_rh;
	private double[][] avg_ps;
	private double[][] avg_pc;
	private double[][] avg_ws;
	private double[][] avg_wd;
	
	public ReportData() {
		avg_gl = new double[1][7];
		avg_di = new double[1][7];
		avg_df = new double[1][7];
		avg_lw = new double[1][6];
		avg_pa = new double[1][6];
		avg_lx = new double[1][6];
		avg_tp = new double[1][6];
		avg_rh = new double[1][4];
		avg_ps = new double[1][5];
		avg_pc = new double[1][6];
		avg_ws = new double[1][6];
		avg_wd = new double[1][6];
	}
	
	public void statisticsData(double[][] iqbal, int[][] code) {
		for (int i= 0; i< code.length; i++) {
			
			if (iqbal[i][2] < 90) {
				
				// Sum Global Radiation
				if (code[i][0] == 9999) {
					cont_glv++;
				} else if (code[i][0] == 5999) {
					cont_glv++;
				} else if (code[i][0] == 5552) {
					cont_gl1n++;
				} else if (code[i][0] == 5529) {
					cont_gl2n++;
				} else if (code[i][0] == 5299) {
					cont_gl3n++;
				} else if (code[i][0] == 2999) {
					cont_gl4n++;
				} else if (code[i][0] == -5555) {
					flag_gl = 1;
				} else if ((code[i][0] == 3333) || (code[i][0] == -6999)) {
					cont_glna++;
				}
				
				// Sum Direct Radiation
				if (code[i][1] == 9999) {
					cont_div++;
				} else if (code[i][1] == 5999) {
					cont_div++;
				} else if (code[i][1] == 5552) {
					cont_di1n++;
				} else if (code[i][1] == 5529) {
					cont_di2n++;
				} else if (code[i][1] == 5299) {
					cont_di3n++;
				} else if (code[i][1] == 2999) {
					cont_di4n++;
				} else if (code[i][1] == -5555) {
					flag_di = 1;
				} else if ((code[i][1] == 3333) || (code[i][1] == -6999)) {
					cont_dina++;
				}
				
				// Sum Diffuse Radiation
				if (code[i][2] == 9999) {
					cont_dfv++;
				} else if (code[i][2] == 5999) {
					cont_dfv++;
				} else if (code[i][2] == 5552) {
					cont_df1n++;
				} else if (code[i][2] == 5529) {
					cont_df2n++;
				} else if (code[i][2] == 5299) {
					cont_df3n++;
				} else if (code[i][2] == 2999) {
					cont_df4n++;
				} else if (code[i][2] == -5555) {
					flag_df = 1;
				} else if ((code[i][2] == 3333) || (code[i][2] == -6999)) {
					cont_dfna++;
				}
				
				// Sum LongWave Radiation
				if (code[i][3] == 999) {
					cont_lwv++;
				} else if (code[i][3] == 599) {
					cont_lwv++;
				} else if (code[i][3] == 552) {
					cont_lw1n++;
				} else if (code[i][3] == 529) {
					cont_lw2n++;
				} else if (code[i][3] == 299) {
					cont_lw3n++;
				} else if (code[i][3] == -5555) {
					flag_lw = 1;
				} else if ((code[i][3] == 3333) || (code[i][3] == -6999)) {
					cont_lwna++;
				}
				
				// Sum PAR Radiation
				if (code[i][4] == 999) {
					cont_pav++;
				} else if (code[i][4] == 599) {
					cont_pav++;
				} else if (code[i][4] == 552) {
					cont_pa1n++;
				} else if (code[i][4] == 529) {
					cont_pa2n++;
				} else if (code[i][4] == 299) {
					cont_pa3n++;
				} else if (code[i][4] == -5555) {
					flag_pa = 1;
				} else if ((code[i][4] == 3333) || (code[i][4] == -6999)) {
					cont_pana++;
				}
				
				// Sum LUX Radiation
				if (code[i][5] == 999) {
					cont_lxv++;
				} else if (code[i][5] == 599) {
					cont_lxv++;
				} else if (code[i][5] == 552) {
					cont_lx1n++;
				} else if (code[i][5] == 529) {
					cont_lx2n++;
				} else if (code[i][5] == 299) {
					cont_lx3n++;
				} else if (code[i][5] == -5555) {
					flag_lx = 1;
				} else if ((code[i][5] == 3333) || (code[i][5] == -6999)) {
					cont_lxna++;
				}
			} else {
				
				// Sum Global Radiation
				if ((code[i][0] != 3333) && (code[i][0] != -6999)) {
					cont_vgl++;
				} else {
					cont_nagl++;
				}
				
				// Sum Direct Radiation
				if ((code[i][1] != 3333) && (code[i][1] != -6999)) {
					cont_vdi++;
				} else {
					cont_nadi++;
				}
				
				// Sum Diffuse Radiation
				if ((code[i][2] != 3333) && (code[i][2] != -6999)) {
					cont_vdf++;
				} else {
					cont_nadf++;
				}
				
				// Sum LongWave Radiation
				if ((code[i][3] != 3333) && (code[i][3] != -6999)) {
					cont_vlw++;
				} else {
					cont_nalw++;
				}
				
				// Sum PAR Radiation
				if ((code[i][4] != 3333) && (code[i][4] != -6999)) {
					cont_vpa++;
				} else {
					cont_napa++;
				}
				
				// Sum LUX Radiation
				if ((code[i][5] != 3333) && (code[i][5] != -6999)) {
					cont_vlx++;
				} else {
					cont_nalx++;
				}			
			}
			
			// Sum Air Temperature
			if (code[i][6] == 999) {
				cont_tpv++;
			} else if (code[i][6] == 599) {
				cont_tpv++;
			} else if (code[i][6] == 552) {
				cont_tp1n++;
			} else if (code[i][6] == 529) {
				cont_tp2n++;
			} else if (code[i][6] == 299) {
				cont_tp3n++;
			} else if (code[i][6] == -5555) {
				flag_tp = 1;
			} else if ((code[i][6] == 3333) || (code[i][6] == -6999)) {
				cont_tpna++;
			}
			
			// Sum Relative Humidity
			if (code[i][7] == 9) {
				cont_rhv++;
			} else if (code[i][7] == 2) {
				cont_rh1n++;
			} else if (code[i][7] == -5555) {
				flag_rh = 1;
			} else if ((code[i][7] == 3333) || (code[i][7] == -6999)) {
				cont_rhna++;
			}
			
			// Sum Atmospheric Pressure
			if (code[i][8] == 99) {
				cont_psv++;
			} else if (code[i][8] == 59) {
				cont_psv++;
			} else if (code[i][8] == 52) {
				cont_ps1n++;
			} else if (code[i][8] == 29) {
				cont_ps2n++;
			} else if (code[i][8] == -5555) {
				flag_ps = 1;
			} else if ((code[i][8] == 3333) || (code[i][8] == -6999)) {
				cont_psna++;
			}
			
			// Sum Accumulated Precipitation
			if (code[i][9] == 999) {
				cont_pcv++;
			} else if (code[i][9] == 599) {
				cont_pcv++;
			} else if (code[i][9] == 552) {
				cont_pc1n++;
			} else if (code[i][9] == 529) {
				cont_pc2n++;
			} else if (code[i][9] == 299) {
				cont_pc3n++;
			} else if (code[i][9] == -5555) {
				flag_pc = 1;
			} else if ((code[i][9] == 3333) || (code[i][9] == -6999)) {
				cont_pcna++;
			}
			
			// Sum Wind Speed
			if (code[i][10] == 999) {
				cont_wsv++;
			} else if (code[i][10] == 599) {
				cont_wsv++;
			} else if (code[i][10] == 552) {
				cont_ws1n++;
			} else if (code[i][10] == 529) {
				cont_ws2n++;
			} else if (code[i][10] == 299) {
				cont_ws3n++;
			} else if (code[i][10] == -5555) {
				flag_ws = 1;
			} else if ((code[i][10] == 3333) || (code[i][10] == -6999)) {
				cont_wsna++;
			}
			
			// Sum Wind Direction
			if (code[i][11] == 999) {
				cont_wdv++;
			} else if (code[i][11] == 599) {
				cont_wdv++;
			} else if (code[i][11] == 552) {
				cont_wd1n++;
			} else if (code[i][11] == 529) {
				cont_wd2n++;
			} else if (code[i][11] == 299) {
				cont_wd3n++;
			} else if (code[i][11] == -5555) {
				flag_wd = 1;
			} else if ((code[i][11] == 3333) || (code[i][11] == -6999)) {
				cont_wdna++;
			}
		}
		
		// Percentage Global Radiation
		avg_gl[0][0] = (double)(cont_gl1n * 100) / code.length;
		avg_gl[0][1] = (double)(cont_gl2n * 100) / code.length;
		avg_gl[0][2] = (double)(cont_gl3n * 100) / code.length;
		avg_gl[0][3] = (double)(cont_gl4n * 100) / code.length;
		avg_gl[0][4] = (double)((cont_glna + cont_nagl) * 100) / code.length;
		avg_gl[0][5] = (double)((cont_glv + cont_vgl) * 100) / code.length;
		avg_gl[0][6] = flag_gl;
		
		// Percentage Direct Radiation
		avg_di[0][0] = (double)(cont_di1n * 100) / code.length;
		avg_di[0][1] = (double)(cont_di2n * 100) / code.length;
		avg_di[0][2] = (double)(cont_di3n * 100) / code.length;
		avg_di[0][3] = (double)(cont_di4n * 100) / code.length;
		avg_di[0][4] = (double)((cont_dina + cont_nadi) * 100) / code.length;
		avg_di[0][5] = (double)((cont_div + cont_vdi) * 100) / code.length;
		avg_di[0][6] = flag_di;
		
		// Percentage Diffuse Radiation
		avg_df[0][0] = (double)(cont_df1n * 100) / code.length;
		avg_df[0][1] = (double)(cont_df2n * 100) / code.length;
		avg_df[0][2] = (double)(cont_df3n * 100) / code.length;
		avg_df[0][3] = (double)(cont_df4n * 100) / code.length;
		avg_df[0][4] = (double)((cont_dfna + cont_nadf) * 100) / code.length;
		avg_df[0][5] = (double)((cont_dfv + cont_vdf) * 100) / code.length;
		avg_df[0][6] = flag_df;
		
		// Percentage LongWave Radiation
		avg_lw[0][0] = (double)(cont_lw1n * 100) / code.length;
		avg_lw[0][1] = (double)(cont_lw2n * 100) / code.length;
		avg_lw[0][2] = (double)(cont_lw3n * 100) / code.length;
		avg_lw[0][3] = (double)((cont_lwna + cont_nalw) * 100) / code.length;
		avg_lw[0][4] = (double)((cont_lwv + cont_vlw) * 100) / code.length;
		avg_lw[0][5] = flag_lw;
		
		// Percentage PAR Radiation
		avg_pa[0][0] = (double)(cont_pa1n * 100) / code.length;
		avg_pa[0][1] = (double)(cont_pa2n * 100) / code.length;
		avg_pa[0][2] = (double)(cont_pa3n * 100) / code.length;
		avg_pa[0][3] = (double)((cont_pana + cont_napa) * 100) / code.length;
		avg_pa[0][4] = (double)((cont_pav + cont_vpa) * 100) / code.length;
		avg_pa[0][5] = flag_pa;
		
		// Percentage LUX Radiation
		avg_lx[0][0] = (double)(cont_lx1n * 100) / code.length;
		avg_lx[0][1] = (double)(cont_lx2n * 100) / code.length;
		avg_lx[0][2] = (double)(cont_lx3n * 100) / code.length;
		avg_lx[0][3] = (double)((cont_lxna + cont_nalx) * 100) / code.length;
		avg_lx[0][4] = (double)((cont_lxv + cont_vlx) * 100) / code.length;
		avg_lx[0][5] = flag_lx;
		
		// Percentage Air Temperature
		avg_tp[0][0] = (double)(cont_tp1n * 100) / code.length;
		avg_tp[0][1] = (double)(cont_tp2n * 100) / code.length;
		avg_tp[0][2] = (double)(cont_tp3n * 100) / code.length;
		avg_tp[0][3] = (double)(cont_tpna * 100) / code.length;
		avg_tp[0][4] = (double)(cont_tpv * 100) / code.length;
		avg_tp[0][5] = flag_tp;
		
		// Percentage Relative Humidity
		avg_rh[0][0] = (double)(cont_rh1n * 100) / code.length;
		avg_rh[0][1] = (double)(cont_rhna * 100) / code.length;
		avg_rh[0][2] = (double)(cont_rhv * 100) / code.length;
		avg_rh[0][3] = flag_rh;
		
		// Percentage Atmospheric Pressure
		avg_ps[0][0] = (double)(cont_ps1n * 100) / code.length;
		avg_ps[0][1] = (double)(cont_ps2n * 100) / code.length;
		avg_ps[0][2] = (double)(cont_psna * 100) / code.length;
		avg_ps[0][3] = (double)(cont_psv * 100) / code.length;
		avg_ps[0][4] = flag_ps;
		
		// Percentage Accumulated Precipitation
		avg_pc[0][0] = (double)(cont_pc1n * 100) / code.length;
		avg_pc[0][1] = (double)(cont_pc2n * 100) / code.length;
		avg_pc[0][2] = (double)(cont_pc3n * 100) / code.length;
		avg_pc[0][3] = (double)(cont_pcna * 100) / code.length;
		avg_pc[0][4] = (double)(cont_pcv * 100) / code.length;
		avg_pc[0][5] = flag_pc;
		
		// Percentage Wind Speed
		avg_ws[0][0] = (double)(cont_ws1n * 100) / code.length;
		avg_ws[0][1] = (double)(cont_ws2n * 100) / code.length;
		avg_ws[0][2] = (double)(cont_ws3n * 100) / code.length;
		avg_ws[0][3] = (double)(cont_wsna * 100) / code.length;
		avg_ws[0][4] = (double)(cont_wsv * 100) / code.length;
		avg_ws[0][5] = flag_ws;
		
		// Percentage Wind Direction
		avg_wd[0][0] = (double)(cont_wd1n * 100) / code.length;
		avg_wd[0][1] = (double)(cont_wd2n * 100) / code.length;
		avg_wd[0][2] = (double)(cont_wd3n * 100) / code.length;
		avg_wd[0][3] = (double)(cont_wdna * 100) / code.length;
		avg_wd[0][4] = (double)(cont_wdv * 100) / code.length;
		avg_wd[0][5] = flag_wd;
	}
	
	public double[][] getAvgGl() {
		return avg_gl;
	}
	
	public double[][] getAvgDi() {
		return avg_di;
	}
	
	public double[][] getAvgDf() {
		return avg_df;
	}
	
	public double[][] getAvgLw() {
		return avg_lw;
	}
	
	public double[][] getAvgPa() {
		return avg_pa;
	}
	
	public double[][] getAvgLx() {
		return avg_lx;
	}
	
	public double[][] getAvgTp() {
		return avg_tp;
	}
	
	public double[][] getAvgRh() {
		return avg_rh;
	}
	
	public double[][] getAvgPs() {
		return avg_ps;
	}
	
	public double[][] getAvgPc() {
		return avg_pc;
	}
	
	public double[][] getAvgWs() {
		return avg_ws;
	}
	
	public double[][] getAvgWd() {
		return avg_wd;
	}
}