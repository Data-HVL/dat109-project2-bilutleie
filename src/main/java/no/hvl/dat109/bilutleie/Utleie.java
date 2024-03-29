package no.hvl.dat109.bilutleie;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import no.hvl.dat109.bilutleie.biler.Bil;

public class Utleie {

	private int utleieID;
	private Reservasjon reservasjon;
	private LocalDate utleieDato;
	private LocalDate returDato;
	private int sluttKilometer;
	private double pris;
	
	
	
	/**
	 * 
	 * @param reservasjon
	 */
	public Utleie(Reservasjon reservasjon) {
	    this.utleieID = reservasjon.getID();
	    this.reservasjon = reservasjon;
	    this.utleieDato = reservasjon.getLeieStartDato();
	    
	}
	
	/**
	 * 
	 * @param sluttKilometer
	 * @param faktiskReturDato
	 */
    public void registrerRetur(int sluttKilometer, LocalDate faktiskReturDato) {

        this.sluttKilometer = sluttKilometer;
        this.returDato = faktiskReturDato;
        int kjorteKilometer = sluttKilometer - reservasjon.getBil().getKM();
        
        int leiedager = (int) ChronoUnit.DAYS.between(utleieDato, returDato);
        int ekstraDager = (int) ChronoUnit.DAYS.between(reservasjon.getLeieSluttDato(), returDato);

        pris = leiedager * reservasjon.getBil().hentPris() + Math.max(ekstraDager, 0) * 150;
        reservasjon.getBil().setKM(sluttKilometer); 
        reservasjon.avsluttReservasjon(returDato);
        
    }

    public double sendRegning() {
        return pris;
    }
	
    public int getID() {
    	return this.utleieID;
    }
	
}
