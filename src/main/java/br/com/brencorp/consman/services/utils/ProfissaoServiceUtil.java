package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.entities.Profissao;

public class ProfissaoServiceUtil {

	static ModelMapper modelMapper = new ModelMapper();

	public static Profissao insert(ProfissaoDTO profissaoDTO) {
		Profissao profissao = modelMapper.map(profissaoDTO, Profissao.class);
		return profissao;
	}

	public static void update(Profissao profissao, ProfissaoDTO profissaoDTO) {
		profissao.setNome(profissaoDTO.getNome());
		profissao.setArea(profissaoDTO.getArea());
	}
}
