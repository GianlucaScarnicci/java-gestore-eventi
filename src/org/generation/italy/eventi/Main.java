package org.generation.italy.eventi;


import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String titolo;
		String dataInserita;
		LocalDate data;
		int postiTotali;
		int prenotazioni;
		int disdette;
		boolean riinizia = true;
		while (riinizia) {
			System.out.println("inserisci un nuovo evento");
			System.out.print("inserisci un titolo");
			titolo = scan.nextLine();
			System.out.println("inserisci la data dell'evento: yyyy-MM-dd");
			dataInserita = scan.nextLine();

			System.out.println("inserire i posti totali maggiore di zero");
			postiTotali = scan.nextInt();
			try {
				data = LocalDate.parse(dataInserita);
				Evento evento = new Evento(titolo, data, postiTotali);
				do {

					System.out.println("Quante prenotazioni vuoi fare? Devono essere minori dei posti totali");
					prenotazioni = scan.nextInt();
					for (int i = 0; i < prenotazioni; i++) {
						try {
							evento.prenota();
						} catch (Exception e) {
							e.getMessage();
						}

					}
				} while (prenotazioni > postiTotali);
				System.out.println("i posti prenotati sono : " + evento.getPostiPrenotati());
				int postiDisponibili = evento.getPostiTotali() - evento.getPostiPrenotati();
				System.out.println("i posti disponibili sono :" + postiDisponibili);
				prenotazioni=evento.getPostiPrenotati();
				do {

					System.out.println("Quanti posti vuoi disdire?Non puoi disdire più posti di quelli che ci sono");
					disdette = scan.nextInt();
					
					for (int i = 0; i < disdette; i++) {
						try {
							evento.disdici();
						} catch (Exception e) {
							e.getMessage();
						}

					}
				} while (disdette>prenotazioni);
				System.out.println("i posti prenotati sono : "+evento.getPostiPrenotati());
				 postiDisponibili = evento.getPostiTotali() - evento.getPostiPrenotati();
				System.out.println("i posti disponibili sono :" + postiDisponibili);
				riinizia = false;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
		scan.close();
	}
}
