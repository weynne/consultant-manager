package br.com.brencorp.consman.util.date;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ConverteDataEmPeriodo {

	private ConverteDataEmPeriodo() {
	}
	
	public static Integer periodoTempoData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Period periodo = Period.between(LocalDate.parse(data, formatter), LocalDate.now());
		return periodo.getYears();
	}
	
	public static Integer periodoTempoAno(String anoConclusao) {
		Integer conclusao = Integer.parseInt(anoConclusao);
		return LocalDate.now().getYear() - conclusao;
	}
}
