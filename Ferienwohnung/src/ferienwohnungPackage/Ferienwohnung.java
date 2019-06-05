/**
*
* AB Projekt Ferienwohnungsverwaltung
*
* @version 1.0 vom 16.05.2019
* @author Maximilian Wolf, Havva Artan, Joachim Fiedler, Ali Rmajid 
* 	Dieses Projekt ist in gemeinsamer Arbeit entstanden, eine genaue Trennung nach Methode war nur bedingt möglich
*/

package ferienwohnungPackage;

import java.util.Scanner;
import java.util.*;

public class Ferienwohnung {

	final static int ANZ = 50; // static weil kunden[] static und Methoden auch static
	static String[][] kunden = new String[ANZ][];
	static Wohnung[] wohnungen = new Wohnung[3];
	static boolean run = true;
	static Calendar cal = Calendar.getInstance();

	public static void hinweis() {
		System.out.println("--Ferienwohnungsverwaltung--\n");
		System.out.println("Aktuelles Datum:");
		Date curr = new Date();
		System.out.println(curr.toString());
		System.out.println("----------------------------\n");
		System.out.println("Dieses Programm dient zur Verwaltung von Ferienwohnungen.");
		System.out.println("Sie können selber die Größe und den Preis für die einzelnen Ferienwohnungen festlegen,");
		System.out.println(
				"eine Kundendatenbank anlegen, sowie Reservierungen vornehmen und Kalkulationen ausführen lassen.\n");
		wohnungen[0] = new Wohnung();
		wohnungen[1] = new Wohnung();
		wohnungen[2] = new Wohnung();
	}

	public static void menu() {
		System.out.println("\n--Menü--\n");
		System.out.println("Möchten Sie: ");
		System.out.println("(1) Kundendaten eingeben,");
		System.out.println("(2) Kundendaten ausgeben und ggf. ändern,");
		System.out.println("(3) Tagespreis und Größe der Wohnung festlegen,");
		System.out.println("(4) eine Reservierung vornehmen oder");
		System.out.println("(5) die Auslastungen der Ferienwohnungen anzeigen lassen?");
		System.out.println("(q) Beenden");
	}

	//Ali Rmajid
	public static char fallauswahl(Scanner sc) {
		System.out.print("\nBitte treffen Sie eine Wahl: ");
		char wahl = sc.next().charAt(0);
		sc.nextLine(); // Eingabebuffer leeren
		switch (wahl) {
		case '1':
			add_kunde(Ferienwohnung.neuerKunde_eingabe(sc));
			break;

		case '2':
			System.out.println(
					"Welchen Kunden möchten Sie anzeigen und ggf. ändern? (Alternativ '0' für alle registrierten Kunden)");
			int auswahl = sc.nextInt();
			if (auswahl == 0) {
				for (int i = 0; i <= kunden.length; i++) {
					if (kunden[i] == null)
						break;
					System.out.println("Kundennr.: " + (i + 1));
					System.out.println("Name     : " + kunden[i][0]);
					System.out.println("Vorname  : " + kunden[i][1]);
					System.out.println("Adresse  : " + kunden[i][2] + "\n");
				}
				break;
			} else if (auswahl > 50) {
				System.out.println("Datenbank nicht größer als 50 Elemente!");
			} else if (kunden[auswahl - 1] == null) {
				System.out.println("Kunde nicht vorhanden!");
			} else {
				System.out.println("Kundennr.: " + auswahl);
				System.out.println("Name     : " + kunden[auswahl - 1][0]);
				System.out.println("Vorname  : " + kunden[auswahl - 1][1]);
				System.out.println("Adresse  : " + kunden[auswahl - 1][2]);
				System.out.println("Möchten Sie diesen Kunden ändern? (j/n)");
				char aendern = sc.next().charAt(0);
				if (aendern == 'j') {
					sc.nextLine();
					System.out.println("neuer Name: ");
					kunden[auswahl - 1][0] = sc.nextLine();
					System.out.println("neuer Vorname: ");
					kunden[auswahl - 1][1] = sc.nextLine();
					System.out.println("neue Adresse: ");
					kunden[auswahl - 1][2] = sc.nextLine();
				} else if (aendern == 'n') {
					System.out.println("Abbruch");
					break;
				} else
					System.out.println("Falsche Eingabe!");
				break;
			}
			break;

		case '3':
			wohnung_eingabe(sc);
			break;

		case '4':
			wohnung_buchung(sc);
			break;

		case '5':
			System.out.println("--Hier Auslastung anzeigen!");
			break;
		case 'q':
			System.out.println("Beende......");
			Ferienwohnung.run = false;
			break;
		default:
			System.out.println("Falsche Eingabe!");
		}
		return wahl;
	}

	public static String[] neuerKunde_eingabe(Scanner sc) {
		final int ANZ = 3; // [0]: Name, [1]: Vorname, [2]: Adresse
		String[] neuerKunde = new String[ANZ];
		System.out.println("--Kundendaten--");
		System.out.print("Name: ");
		neuerKunde[0] = sc.nextLine();
		System.out.print("Vorname: ");
		neuerKunde[1] = sc.nextLine();
		System.out.print("Adresse: ");
		neuerKunde[2] = sc.nextLine();
		return neuerKunde;
	}

	public static void add_kunde(String neuerKunde[]) {
		for (int i = 0; i < kunden.length; i++) {
			if (kunden[i] == null) {
				kunden[i] = neuerKunde;
				break;
			}
		}
	}

	//Havva Artan
	public static void wohnung_eingabe(Scanner sc) {
		System.out.println("Bitte Größe (qm) und Preis (€/qm) der Wohnung(en) eingeben!");
		System.out.println("Wohnung 1, 2 oder 3?");
		int wohnung_nr = sc.nextInt() - 1;
		System.out.println("Preis:");
		wohnungen[wohnung_nr].preis = sc.nextDouble();
		System.out.println("Groesse:");
		wohnungen[wohnung_nr].groesse = sc.nextDouble();
	}

	public static void wohnung_buchung(Scanner sc) {
		System.out.println("Auswahl Ferienwohnung: 1) " + wohnungen[0].preis + "€/qm | " + wohnungen[0].groesse + "qm");
		System.out.println("                       2) " + wohnungen[1].preis + "€/qm | " + wohnungen[1].groesse + "qm");
		System.out.println("                       3) " + wohnungen[2].preis + "€/qm | " + wohnungen[2].groesse + "qm");
		int wahl_wohnung = sc.nextInt();
		if (wahl_wohnung > 3 || wahl_wohnung <= 0)
			System.out.println("Falsche Eingabe");
		else {

			System.out.println("Welchen Kunden wählen sie aus? (Hinweis: mit 0 alle Kunden anzeigen!)");
			int input = sc.nextInt();
			if (input == 0) {
				for (int i = 0; i <= kunden.length; i++) {
					if (kunden[i] == null)
						break;
					System.out.println("Kundennr.: " + (i + 1));
					System.out.println("Name     : " + kunden[i][0]);
					System.out.println("Vorname  : " + kunden[i][1]);
					System.out.println("Adresse  : " + kunden[i][2] + "\n");
				}
			} else if (input > 50) {
				System.out.println("Datenbank nicht größer als 50 Elemente!");
				return;
			} else if (kunden[input - 1] == null) {
				System.out.println("Kunde nicht vorhanden!");
				return;
			}
		}
		if (sc.hasNextLine()) {
			sc.nextLine();
		}
		System.out.println("In welchem Zeitraum (Bsp.: 06.06.2019-14.06.2019) soll die Wohnung gebucht werden?");
		String datum = sc.nextLine();

		if (datum.contains("-")) {
			String[] parts = datum.split("-");
			String wunsch_beginn = parts[0];
			String wunsch_ende = parts[1];
			if (datum_check.isCorrect(wunsch_beginn) == false)
				System.out.println("Falsches Anfangsdatum eingegeben!");
			if (datum_check.isCorrect(wunsch_ende) == false)
				System.out.println("Falsches Enddatum eingegeben!");
			else
				wohnungen[wahl_wohnung].neue_buchung(wunsch_beginn, wunsch_ende);
		} else {
			System.out.println("Datum " + datum + " enthält kein - ");
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Ferienwohnung.hinweis();
		do {
			Ferienwohnung.menu();
			Ferienwohnung.fallauswahl(sc);
		} while (Ferienwohnung.run == true);
	}
}
	//Joachim Fiedler
