package br.com.brencorp.consman.util.date;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ConverteDataEmPeriodo {

	private ConverteDataEmPeriodo() {
	}
	
	public static Integer idadeAtual(String nascimento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Period periodo = Period.between(LocalDate.parse(nascimento, formatter), LocalDate.now());
		return periodo.getYears();
	}
	
	public static Integer tempoFormacao(Integer anoConclusao) {
		return LocalDate.now().getYear() - anoConclusao;
	}
}
