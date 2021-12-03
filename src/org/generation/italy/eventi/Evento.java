package org.generation.italy.eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {

	private final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String titolo;
	private LocalDate data;
	private int postiTotali;
	private int postiPrenotati;

	public Evento(String titolo, LocalDate data, int postiTotali) throws Exception {
		if (!isDataValid(data)) {
			throw new Exception("Errore: la data non deve essere passata");

		} else if (!isPostiTotaliValid(postiTotali)) {
			throw new Exception("Errore: i posti totali devono essere più di zero");
		}

		this.postiPrenotati = 0;
		this.titolo = titolo;
		this.data = data;
		this.postiTotali = postiTotali;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getPostiTotali() {
		return postiTotali;
	}

	public int getPostiPrenotati() {
		return postiPrenotati;
	}

	private boolean isDataValid(LocalDate data) {
		if (data.isBefore(LocalDate.now())) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isPostiTotaliValid(int postiTotali) {
		if (postiTotali < 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isPostiPrenotatiVuoto(int postiPrenotati) {
		if (postiPrenotati <= 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isPostiDisponibiliValid(int postiPrenotati, int postiTotali) {
		if (postiPrenotati < postiTotali) {
			return true;
		} else {
			return false;
		}
	}

	public int prenota() throws Exception {
		if (!isPostiDisponibiliValid(postiPrenotati, postiTotali) || !isDataValid(data)) {
			throw new Exception("Errore: la data inserita o non puoi inserire un posto");
		} else {
			return this.postiPrenotati++;
		}
	}

	public int disdici() throws Exception {
		if (isPostiPrenotatiVuoto(postiPrenotati) || !isDataValid(data)) {
			throw new Exception("Errore: la data inserita o non puoi levare un posto");
		} else {
			return this.postiPrenotati--;
		}
	}

	@Override
	public String toString() {
		return data.format(DF) + titolo;
	}

}
