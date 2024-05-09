package br.com.brencorp.consman.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConverteData {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static LocalDate stringLocalDate(String dataString) {
		return LocalDate.parse(dataString, formatter);
	}

	public static Integer periodoLocalDate(LocalDate data) {
		return Period.between(data, LocalDate.now()).getYears();
	}

	public static Integer periodoInteger(Integer data) {
		return LocalDate.now().getYear() - data;
	}
}
