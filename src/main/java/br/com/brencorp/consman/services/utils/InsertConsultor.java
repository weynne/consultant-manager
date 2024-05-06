package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;

public class InsertConsultor {

	public static Consultor insert(ConsultorDTO consultorDTO) {

		ModelMapper modelMapper = new ModelMapper();

		Consultor consultor = modelMapper.map(consultorDTO, Consultor.class);

		for (FormacaoAcademica formacao : consultorDTO.getFormacoes()) {
			FormacaoAcademica formacoes = modelMapper.map(formacao, FormacaoAcademica.class);
			consultor.getFormacao().add(formacoes);
		}

		for (Profissao profissao : consultorDTO.getProfissoes()) {
			Profissao profissoes = modelMapper.map(profissao, Profissao.class);
			consultor.getProfissao().add(profissoes);
		}

		for (Projeto projeto : consultorDTO.getProjetos()) {
			Projeto projetos = modelMapper.map(projeto, Projeto.class);
			consultor.getProjeto().add(projetos);
		}

		for (Cat cat : consultorDTO.getCat()) {
			Cat cats = modelMapper.map(cat, Cat.class);
			consultor.getCat().add(cats);
		}

		return consultor;
	}
}
