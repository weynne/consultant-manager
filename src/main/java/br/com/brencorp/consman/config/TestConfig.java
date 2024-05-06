
package br.com.brencorp.consman.config;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.brencorp.consman.entities.Cat;
import br.com.brencorp.consman.entities.Cidade;
import br.com.brencorp.consman.entities.Consultor;
import br.com.brencorp.consman.entities.Estado;
import br.com.brencorp.consman.entities.FormacaoAcademica;
import br.com.brencorp.consman.entities.Profissao;
import br.com.brencorp.consman.entities.Projeto;
import br.com.brencorp.consman.repositories.CatRepository;
import br.com.brencorp.consman.repositories.CidadeRepository;
import br.com.brencorp.consman.repositories.ConsultorRepository;
import br.com.brencorp.consman.repositories.EstadoRepository;
import br.com.brencorp.consman.repositories.FormacaoAcademicaRepository;
import br.com.brencorp.consman.repositories.ProfissaoRepository;
import br.com.brencorp.consman.repositories.ProjetoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ProjetoRepository projetoRepository;

	@Autowired
	private CatRepository catRepository;

	@Autowired
	private ProfissaoRepository profissaoRepository;

	@Autowired
	private FormacaoAcademicaRepository formacaoAcademicaRepository;

	@Autowired
	private ConsultorRepository consultorRepository;

	@Override
	public void run(String... args) throws Exception {

		FormacaoAcademica f1 = new FormacaoAcademica(null, "Administração", "UFPE", "Bacharelado", 2018);
		FormacaoAcademica f2 = new FormacaoAcademica(null, "Engenharia de Software", "UPE", "Mestrado", 2023);
		FormacaoAcademica f3 = new FormacaoAcademica(null, "Letras", "UNICAP", "Licenciatura", 2010);
		FormacaoAcademica f4 = new FormacaoAcademica(null, "Administração", "CESAR School", "Tecnólogo", 2025);
		FormacaoAcademica f5 = new FormacaoAcademica(null, "Administração", "UFPE", "Bacharelado", 2018);

		formacaoAcademicaRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5));

		Profissao prof1 = new Profissao(null, "Desenvolvedor", "Backend");
		Profissao prof2 = new Profissao(null, "Desenvolvedor", "Frontend");
		Profissao prof3 = new Profissao(null, "Engenheiro de Produção", "Dados");
		Profissao prof4 = new Profissao(null, "Biólogo", "Aves Silvestres");

		profissaoRepository.saveAll(Arrays.asList(prof1, prof2, prof3, prof4));

		Cat cat1 = new Cat(null, "Descrição Cat1");
		Cat cat2 = new Cat(null, "Descrição Cat2");
		Cat cat3 = new Cat(null, "Descrição Cat3");
		Cat cat4 = new Cat(null, "Descrição Cat4");

		catRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

		Projeto p1 = new Projeto(null, "Descrição projeto 1");
		Projeto p2 = new Projeto(null, "Descrição projeto 2");
		Projeto p3 = new Projeto(null, "Descrição projeto 3");
		Projeto p4 = new Projeto(null, "Descrição projeto 4");

		projetoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

		Estado e1 = new Estado(null, "PE");
		Estado e2 = new Estado(null, "PB");

		Cidade c1 = new Cidade(null, "João Pessoa", e2);
		Cidade c2 = new Cidade(null, "Recife", e1);
		Cidade c3 = new Cidade(null, "Campina Grande", e2);
		Cidade c4 = new Cidade(null, "Caruaru", e1);

		estadoRepository.saveAll(Arrays.asList(e1, e2));

		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

		Consultor cons1 = new Consultor(null, "00011122290", null, "Weynne Guimarães", "8188888888",
				"wjgcl@cesar.school", LocalDate.parse("01/09/1991", DateTimeFormatter.ofPattern("dd/MM/yyyy")), c2);
		Consultor cons2 = new Consultor(null, "99911122290", null, "Pedro Silva", "8199999999", "pcs2@cesar.school",
				LocalDate.parse("01/11/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")), c3);

		consultorRepository.saveAll(Arrays.asList(cons1, cons2));

		cons1.getCat().add(cat4);
		cons1.getCat().add(cat3);
		cons1.getFormacao().add(f3);
		cons1.getFormacao().add(f4);
		cons1.getProfissao().add(prof2);
		cons1.getProfissao().add(prof3);
		cons1.getProjeto().add(p1);
		cons1.getProjeto().add(p2);
		cons1.getProjeto().add(p3);

		cons2.getCat().add(cat1);
		cons2.getFormacao().add(f1);
		cons2.getProfissao().add(prof3);
		cons2.getProjeto().add(p2);

		consultorRepository.saveAll(Arrays.asList(cons1, cons2));

	}

}
