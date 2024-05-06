package br.com.brencorp.consman.util.date;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ConverteDate {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static LocalDate stringLocalDate(String dataString) {
		LocalDate data = LocalDate.parse(dataString, formatter);
		return data;
	}

	public static Integer periodoTempoData(LocalDate data) {
		Period periodo = Period.between(data, LocalDate.now());
		return periodo.getYears();
	}

	public static Integer periodoTempoAno(Integer anoConclusao) {
		return LocalDate.now().getYear() - anoConclusao;
	}
}
