package br.com.brencorp.consman.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ConverteData {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static LocalDate stringLocalDate(String dataString) {
		LocalDate data = LocalDate.parse(dataString, formatter);
		return data;
	}

	public static Integer periodoLocalDate(LocalDate data) {
		Period periodo = Period.between(data, LocalDate.now());
		return periodo.getYears();
	}

	public static Integer periodoInteger(Integer data) {
		return LocalDate.now().getYear() - data;
	}
}
