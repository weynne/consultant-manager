package br.com.brencorp.consman.services.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ProjetoDTO;
import br.com.brencorp.consman.entities.Projeto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjetoServiceUtil {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static Projeto insert(ProjetoDTO projetoDTO) {
		return modelMapper.map(projetoDTO, Projeto.class);
	}

	public static void update(Projeto projeto, ProjetoDTO projetoDTO) {
		projeto.setNome(projetoDTO.getNome());
	}
}
