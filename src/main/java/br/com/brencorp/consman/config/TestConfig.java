
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

    private final EstadoRepository estadoRepository;

    private final  CidadeRepository cidadeRepository;

    private final ProjetoRepository projetoRepository;

    private final CatRepository catRepository;

    private final ProfissaoRepository profissaoRepository;

    private final FormacaoAcademicaRepository formacaoAcademicaRepository;

    private final ConsultorRepository consultorRepository;

    @Autowired
    public TestConfig(EstadoRepository estadoRepository, CidadeRepository cidadeRepository,
                      ProjetoRepository projetoRepository, CatRepository catRepository,
                      ProfissaoRepository profissaoRepository,
                      FormacaoAcademicaRepository formacaoAcademicaRepository,
                      ConsultorRepository consultorRepository) {
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.projetoRepository = projetoRepository;
        this.catRepository = catRepository;
        this.profissaoRepository = profissaoRepository;
        this.formacaoAcademicaRepository = formacaoAcademicaRepository;
        this.consultorRepository = consultorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        FormacaoAcademica f1 = new FormacaoAcademica(null, "Administração", "UFPE", "Bacharelado", 2012);
        FormacaoAcademica f2 = new FormacaoAcademica(null, "Engenharia de Software", "UPE", "Mestrado", 2018);
        FormacaoAcademica f3 = new FormacaoAcademica(null, "Letras - Português", "UNICAP", "Licenciatura", 2010);
        FormacaoAcademica f4 = new FormacaoAcademica(null, "Gestão de Tecnologia da Informação", "CESAR School", "Tecnólogo", 2019);
        FormacaoAcademica f5 = new FormacaoAcademica(null, "Computação", "UFPE", "Técnico", 2012);
        FormacaoAcademica f6 = new FormacaoAcademica(null, "Contabilidade", "UNIBRA", "Profissioanlizante", 2013);
        FormacaoAcademica f7 = new FormacaoAcademica(null, "Educação Física", "UNIFBV", "Licenciatura", 2014);
        FormacaoAcademica f8 = new FormacaoAcademica(null, "Direito Tributário", "UNINASSAU", "Mestrado", 2015);
        FormacaoAcademica f9 = new FormacaoAcademica(null, "Direito - LGPD", "UNIFG", "Pós-Gradução", 2016);
        FormacaoAcademica f10 = new FormacaoAcademica(null, "Administração", "UNINABUCO", "Doutorado", 2017);

        formacaoAcademicaRepository.saveAll(Arrays.asList(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10));

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

        Projeto p1 = new Projeto(null, "Projeto nome 1");
        Projeto p2 = new Projeto(null, "Projeto nome 2");
        Projeto p3 = new Projeto(null, "Projeto nome 3");
        Projeto p4 = new Projeto(null, "Projeto nome 4");

        projetoRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        Estado e1 = new Estado(null, "PE");
        Estado e2 = new Estado(null, "PB");
        Estado e3 = new Estado(null, "RJ");
        Estado e4 = new Estado(null, "BA");
        Estado e5 = new Estado(null, "RN");
        Estado e6 = new Estado(null, "RS");

        Cidade c1 = new Cidade(null, "João Pessoa", e2);
        Cidade c2 = new Cidade(null, "Recife", e1);
        Cidade c3 = new Cidade(null, "Campina Grande", e2);
        Cidade c4 = new Cidade(null, "Caruaru", e1);
        Cidade c5 = new Cidade(null, "Natal", e5);
        Cidade c6 = new Cidade(null, "Salvador", e4);
        Cidade c7 = new Cidade(null, "Rio de Janeiro", e3);
        Cidade c8 = new Cidade(null, "Porto Alegre", e6);
        Cidade c9 = new Cidade(null, "Mossoró", e5);
        Cidade c10 = new Cidade(null, "Niterói", e3);

        estadoRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6));

        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));

        String cpf = "00011122200";
        String cnpj = "00111222000100";
        String telefone = "81912345678";
        String dataFormatter = "dd/MM/yyyy";

        Consultor cons1 = new Consultor(cpf, null, "Weynne Guimarães", telefone,
                "dbc@cesar.school", LocalDate.parse("01/10/1995", DateTimeFormatter.ofPattern(dataFormatter)), c1);
        Consultor cons2 = new Consultor(null, cnpj, "Wendislau Silva", telefone, "dts@cesar.school",
                LocalDate.parse("01/06/1999", DateTimeFormatter.ofPattern(dataFormatter)), c2);
        Consultor cons3 = new Consultor(cpf, null, "Pedro Silva", telefone,
                "igl@cesar.school", LocalDate.parse("01/08/2000", DateTimeFormatter.ofPattern(dataFormatter)), c5);
        Consultor cons4 = new Consultor(null, cnpj, "Isabel Gonçalves", telefone, "pcs2@cesar.school",
                LocalDate.parse("01/11/2005", DateTimeFormatter.ofPattern(dataFormatter)), c9);
        Consultor cons5 = new Consultor(null, cnpj, "Diego Tavares", telefone,
                "wvss@cesar.school", LocalDate.parse("01/12/1998", DateTimeFormatter.ofPattern(dataFormatter)), c4);
        Consultor cons6 = new Consultor(cpf, null, "Débora Carvalho", telefone, "wjgcl@cesar.school",
                LocalDate.parse("24/09/1991", DateTimeFormatter.ofPattern(dataFormatter)), c3);

        consultorRepository.saveAll(Arrays.asList(cons1, cons2, cons3, cons4, cons5, cons6));

        cons1.getCat().add(cat1);
        cons1.getCat().add(cat2);
        cons1.getFormacao().add(f1);
        cons1.getFormacao().add(f2);
        cons1.getProfissao().add(prof1);
        cons1.getProfissao().add(prof2);
        cons1.getProjeto().add(p1);
        cons1.getProjeto().add(p2);

        cons2.getCat().add(cat3);
        cons2.getFormacao().add(f3);
        cons2.getProfissao().add(prof3);
        cons2.getProjeto().add(p3);

        cons3.getCat().add(cat4);
        cons3.getFormacao().add(f4);
        cons3.getProfissao().add(prof4);
        cons3.getProjeto().add(p4);

        cons4.getCat().add(cat1);
        cons4.getFormacao().add(f5);
        cons4.getProfissao().add(prof1);
        cons4.getProjeto().add(p1);

        cons5.getCat().add(cat2);
        cons5.getFormacao().add(f6);
        cons5.getProfissao().add(prof2);
        cons5.getProjeto().add(p2);

        cons6.getCat().add(cat3);
        cons6.getFormacao().add(f7);
        cons6.getProfissao().add(prof3);
        cons6.getProjeto().add(p3);

        consultorRepository.saveAll(Arrays.asList(cons1, cons2, cons3, cons4, cons5, cons6));

    }

}
