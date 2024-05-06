package br.com.brencorp.consman.services.utils;

import org.modelmapper.ModelMapper;

import br.com.brencorp.consman.dto.ConsultorDTO;
import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;

public class UpdateConsultor {

	public static void update(Consultor consultor, ConsultorDTO consultorDTO) {
		
		ModelMapper modelMapper = new ModelMapper();
		
		consultor.setCpf(consultorDTO.getCpf());
		consultor.setCnpj(consultorDTO.getCnpj());
		consultor.setNome(consultorDTO.getNome());
		consultor.setTelefone(consultorDTO.getTelefone());
		consultor.setEmail(consultorDTO.getEmail());
		consultor.setDataNascimento(consultorDTO.getDataNascimento());
		consultor.setCidade(consultorDTO.getCidade());

		for (int i = 0; i < consultorDTO.getFormacoes().size(); i++) {
			FormacaoAcademica formacoes = modelMapper.map(consultorDTO.getFormacoes().get(i), FormacaoAcademica.class);
			consultor.getFormacao().set(i, formacoes);
		}

		for (int i = 0; i < consultorDTO.getProfissoes().size(); i++) {
			Profissao profissoes = modelMapper.map(consultorDTO.getProfissoes().get(i), Profissao.class);
			consultor.getProfissao().set(i, profissoes);
		}

		for (int i = 0; i < consultorDTO.getProjetos().size(); i++) {
			Projeto projetos = modelMapper.map(consultorDTO.getProjetos().get(i), Projeto.class);
			consultor.getProjeto().set(i, projetos);
		}

		for (int i = 0; i < consultorDTO.getCat().size(); i++) {
			Cat cats = modelMapper.map(consultorDTO.getCat().get(i), Cat.class);
			consultor.getCat().set(i, cats);
		}
	}
}
