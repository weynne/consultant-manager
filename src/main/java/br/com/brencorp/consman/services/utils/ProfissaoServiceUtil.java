package br.com.brencorp.consman.services.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ProfissaoDTO;
import br.com.brencorp.consman.entities.Profissao;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfissaoServiceUtil {

	private static final ModelMapper modelMapper = new ModelMapper();

	public static Profissao insert(ProfissaoDTO profissaoDTO) {
		return modelMapper.map(profissaoDTO, Profissao.class);
	}

	public static void update(Profissao profissao, ProfissaoDTO profissaoDTO) {
		profissao.setNome(profissaoDTO.getNome());
		profissao.setArea(profissaoDTO.getArea());
	}
}
