package br.sci.labren.sondavalida.model.cmodel;

import static java.lang.Math.*;

/**
 * 
 * @version 1.0
 * @author Rafael Carvalho Chagas
 * @since Copyright 2012-2014
 *
 */

public class CModel {
	private final double ISC = 1368;
	private final double WO = 0.95;
	private final double FC = 0.84;
	private final double UNDEF = 0;
		
	private double mr, ma, l, u3, ps, pw, u1, tr, to, tg, tw;
	private double ta, ib, taa, tas, idr, ida, pa, pg, idm;
	
	private double[][] model;
	
	public CModel() {
		super();
	}
	
	public void executeModel(double[][] data, double[][] iqbal, double latitude, double longitude, double vis) {
		model = new double[data.length][3];
		
		if (latitude >= -10) {
			l = 0.24;
		} else if ((latitude < -10) && (latitude >= -20)) {
			l = 0.25;
		} else if ((latitude < -20) && (latitude >= -30)) {
			l = 0.28;
		} else if ((latitude < -30) && (latitude >= -40)) {
			l = 0.30;
		}
			
		for (int i= 0; i< data.length; i++) {
			
			mr = 1/(iqbal[i][1]);
			
			ma = mr*(data[i][22]/1013.25);
			
			u3 = l*mr;
			
			ps = exp(26.23-(5416/(data[i][20]+273.15)));
			
			pw = 0.493*(data[i][21]/100)*((ps)/(data[i][20]+273.15));
			
			u1 = pw*mr;
			
			tr = exp(-0.0903*pow(ma,0.84)*(1.0+ma-(pow(ma,1.01))));
			
			// To = Transmittance by Ozone
			to = 1-(0.1611*(u3)*pow((1.0+139.48*(u3)),(-0.3035))-0.002715*(u3)*pow((1.0+0.044*(u3)+0.0003*pow(u3,2)),(-1)));
			
			// Tg = Transmittance by Uniformely Mixed Gases
			tg = exp(-0.0127*pow(ma,0.26));
			
			// Tw = Transmittance by Water Vapor
			tw = 1-2.4959*(u1)*(pow(pow((1.0+79.034*(u1)),0.6828)+(6.385*(u1)),(-1)));
			
			// Ta = Aerosol Transmittance
			//Ta = (0.97-1.265*(VIS(M))**(-0.66))**Ma**0.9
			ta = pow((0.97-1.265*pow(vis,(-0.66))),pow(ma,0.9));
			
			// IRn = Direct Normal Irradiance
			model[i][1] = (iqbal[i][0])*0.9751*ISC*tr*to*tg*tw*ta;
			
			ib = (model[i][1])*(iqbal[i][1]);
			
			// Taa = Transmittance Of Direct Radiation Due To Aerosol Absorptance
			taa = 1-(1-WO)*(1-ma+(pow(ma,1.06))*(1-ta)); // Where Wo = 0.9 Recommended by Bird and Hulstrom
			
			tas = ta/taa;
			
			// Idr = Rayleigh-Scattered diffuse irradiance after through the atmosphere (W/m**2)			
			idr = (iqbal[i][0])*0.79*ISC*(iqbal[i][1])*to*tg*tw*taa*0.5*((1-tr)/(1-ma+(pow(ma,1.02))));
			
			// Ida = Diffuse radiation produced by aerosol scattering that reaches the ground
			//       After the first pass through the atmosphere (W/m**2)
			ida = (iqbal[i][0])*0.79*ISC*(iqbal[i][1])*to*tg*tw*taa*(FC*(1-tas))/(1-ma+(pow(ma,1.02))); // Where Fc = 0.84
			
			pa = 0.0685+(1-FC)*(1-tas);
			
			pg = 0.2;
			
			idm = ((model[i][1])*(iqbal[i][1])+idr+ida)*pg*pa/(1-pg*pa);
			
			model[i][2] = idr+ida+idm;
			
			model[i][0] = ib+(model[i][2]);
			
			if ((Double.isNaN(model[i][0])) || (Double.isNaN(model[i][1])) || (Double.isNaN(model[i][2]))) {
				model[i][0] = UNDEF;
				model[i][1] = UNDEF;
				model[i][2] = UNDEF;
			}
			
			if (model[i][0] > 1368) {
				model[i][0] = UNDEF;
				model[i][1] = UNDEF;
				model[i][2] = UNDEF;				
			}
			
			if (model[i][2] <= 0) {
				model[i][0] = UNDEF;
				model[i][1] = UNDEF;
				model[i][2] = UNDEF;				
			}
			
			if (iqbal[i][2] > 87) {
				model[i][0] = UNDEF;
				model[i][1] = UNDEF;
				model[i][2] = UNDEF;
			}
		}		
	}
	
	public double[][] getModel() {
		return model;
	}	
}