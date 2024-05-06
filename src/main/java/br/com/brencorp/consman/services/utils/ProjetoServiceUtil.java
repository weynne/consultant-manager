package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ProjetoDTO;
import br.com.brencorp.consman.entities.Projeto;

public class ProjetoServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Projeto insert(ProjetoDTO projetoDTO) {
		Projeto projeto = modelMapper.map(projetoDTO, Projeto.class);
		return projeto;
	}

	public static void update(Projeto projeto, ProjetoDTO projetoDTO) {
		projeto.setNome(projetoDTO.getNome());
	}
}
