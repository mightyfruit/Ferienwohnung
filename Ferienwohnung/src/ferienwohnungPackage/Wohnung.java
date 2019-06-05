package ferienwohnungPackage;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Wohnung {
	double preis;
	double groesse;
	int kunden_nr;
	Date[] beginn;
	Date[] ende;

	void buchung(String start, String schluss) {
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
		try {
			beginn[beginn.length - 1] = format.parse(start);
			ende[ende.length - 1] = format.parse(schluss);
		} catch (ParseException ex) {
			System.out.println("Fehler!");
		}
	}

	public boolean neue_buchung(String start, String schluss) {

		// erste Buchung
		if (beginn[0] == null) {
			beginn = new Date[1];
			beginn[0] = new Date();
			ende = new Date[1];
			ende[0] = new Date();
			buchung(start, schluss);
		}
		// weitere Buchungen - Arrays dynamisch vergrössern
		else {
			boolean erg = false;
			DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN);
			try {
				Date s = format.parse(start);
				Date e = format.parse(schluss);

				for (int i = 0; i < beginn.length; i++) {
					if (s.before(ende[i]) && e.before(ende[i]) && s.after(beginn[i])) {
						erg = false;
						break;
					}
					if (s.after(beginn[i]) && e.before(ende[i])) {
						erg = false;
						break;
					}
					if (s.before(beginn[i]) && s.before(ende[i])) {
						erg = true;
					}
					if (s.after(ende[i]))
						erg = true;
					if (s.after(beginn[i]) && s.after(ende[i]))
						erg = true;
				}
			} catch (ParseException e) {
				System.out.println("Falsches Datum!");
			}

			if (erg == false) {
				System.out.println("Wohnung berreits belegt!");
			} else {
				Date[] temp = new Date[beginn.length + 1];
				temp[beginn.length] = new Date();
				System.arraycopy(beginn, 0, temp, 0, beginn.length);
				beginn = temp;

				temp = new Date[ende.length + 1];
				temp[ende.length] = new Date();
				System.arraycopy(ende, 0, temp, 0, ende.length);
				ende = temp;

				buchung(start, schluss);
			}
		}

		for (int i = 0; i < beginn.length; i++) {
			System.out.println("Kunden Nr.: " + (kunden_nr + 1) + " Buchung Nr.:" + (i + 1) + " Zeitraum: "
					+ beginn[i].toString() + "-" + ende[i].toString());
		}
		return true;
	}

	// standart Contructor
	public Wohnung() {
		preis = 0.0;
		groesse = 0.0;
		kunden_nr = 0;
		beginn = new Date[1];
		ende = new Date[1];

	}
}	//Maximilian Wolf