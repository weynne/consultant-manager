package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.FormacaoAcademicaDTO;
import br.com.brencorp.consman.entities.FormacaoAcademica;

public class FormacaoAcademicaServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static FormacaoAcademica insert(FormacaoAcademicaDTO formacaoDTO) {
		return modelMapper.map(formacaoDTO, FormacaoAcademica.class);
	}

	public static void update(FormacaoAcademica formacao, FormacaoAcademicaDTO formacaoDTO) {
		formacao.setNome(formacaoDTO.getNome());
		formacao.setInstituicao(formacaoDTO.getInstituicao());
		formacao.setTipo(formacaoDTO.getTipo());
		formacao.setAnoConclusao(formacaoDTO.getAnoConclusao());
	}
}
