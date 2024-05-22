import React, { useState } from 'react';
import ProfileContext from '../util/Profile';
import { Link } from 'react-router-dom';
import {
  Box,
  CardHeader,
  Container,
  Divider,
  MenuItem,
  Select,
  TextField,
} from '@mui/material';
import { useConsultores } from '../context/consultores';
import { postCidadeAPI } from '../services/cidades';
import { postFormacoesAPI } from '../services/formacoes';
import { postProfissaoAPI } from '../services/profissoes';
import { postProjetosAPI } from '../services/projetos';
import { postCatAPI } from '../services/cat';

const Cadastrar = () => {
  const { profileData } = React.useContext(ProfileContext);

  const [formData, setFormData] = React.useState(profileData);

  //envio do form para o back
  const handleSubmit = () => {};

  //dados
  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
    console.log(formData);
  };

  const [selectValue, setSelectValue] = useState(formData.estado);

  //list estados
  const estados = [
    'AC',
    'AL',
    'AP',
    'AM',
    'BA',
    'CE',
    'DF',
    'ES',
    'GO',
    'MA',
    'MT',
    'MS',
    'MG',
    'PA',
    'PB',
    'PR',
    'PE',
    'PI',
    'RJ',
    'RN',
    'RS',
    'RO',
    'RR',
    'SC',
    'SP',
    'SE',
    'TO',
  ];

  const { errors, handleBlur, handleInputChange, handleSubmit } =
    useValidation();
  const { postConsultor } = useConsultores();

  const [combinedData, setCombinedData] = useState([]);
  const [visibleText, setVisibleText] = useState(false);
  const [selectValue, setSelectValue] = useState(formData.estado);
  const [formacoes, setFormacoes] = useState([
    { formacao: '', instituicao: '', tipo: '', anoConclusao: '' },
  ]);
  const [cats, setCats] = useState([{ descricao: '' }]);
  const [projetos, setProjetos] = useState([{ nome: '' }]);
  const [profissoes, setProfissoes] = useState([{ profissao: '', area: '' }]);

  const addNewFormacao = () => {
    setFormacoes([
      ...formacoes,
      { formacao: '', instituicao: '', tipo: '', anoConclusao: '' },
    ]);
  };
  const addNewCat = () => {
    setCats([...cats, { descricao: '' }]);
  };
  const addNewProjeto = () => {
    setProjetos([...projetos, { nome: '' }]);
  };
  const addNewProfissao = () => {
    setProfissoes([...profissoes, { profissao: '', area: '' }]);
  };

  const removeFormacao = (index) => {
    if (formacoes.length > 1) {
      const newFormacoes = formacoes.filter((_, i) => i !== index);
      setFormacoes(newFormacoes);
    }
  };
  const removeCat = (index) => {
    if (cats.length > 1) {
      const newCats = cats.filter((_, i) => i !== index);
      setCats(newCats);
    }
  };
  const removeProjeto = (index) => {
    if (projetos.length > 1) {
      const newProjetos = projetos.filter((_, i) => i !== index);
      setProjetos(newProjetos);
    }
  };
  const removeProfissao = (index) => {
    if (profissoes.length > 1) {
      const newProfissoes = profissoes.filter((_, i) => i !== index);
      setProfissoes(newProfissoes);
    }
  };

  const handleInfosInputChange = (index, field, value, type) => {
    if (type === 'formacao') {
      const newFormacoes = [...formacoes];
      newFormacoes[index][field] = value;
      setFormacoes(newFormacoes);
    } else if (type === 'cat') {
      const newCats = [...cats];
      newCats[index][field] = value;
      setCats(newCats);
    } else if (type === 'projeto') {
      const newProjetos = [...projetos];
      newProjetos[index][field] = value;
      setProjetos(newProjetos);
    } else if (type === 'profissao') {
      const newProfissoes = [...profissoes];
      newProfissoes[index][field] = value;
      setProfissoes(newProfissoes);
    }
  };

  const handleChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
    handleInputChange(name, value);
  };
  const handleBlurField = (event) => {
    const { name, value } = event.target;
    handleBlur(name, value);
  };

  const calcularIdade = (dataNascimento) => {
    const hoje = new Date();
    const nascimento = new Date(dataNascimento);
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mes = hoje.getMonth() - nascimento.getMonth();
    if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
      idade--;
    }
    return idade;
  };
  const calcularFormacao = (anoConclusao) => {
    const hoje = new Date();
    const formacao = new Date(anoConclusao);
    let tempoFormacao = hoje.getFullYear() - formacao.getFullYear();
    return tempoFormacao;
  };

  // ENVIO !! -------------------------------

  const onSubmit = async (event) => {
    event.preventDefault();

    const idade = calcularIdade(formData.dataNascimento);

    const dadosPessoais = {
      id: 0,
      cpf: formData.cpf,
      cnpj: formData.cnpj,
      nome: formData.nome,
      telefone: formData.telefone,
      email: formData.email,
      dataNascimento: formData.dataNascimento,
      idade: idade,
    };

    const dadosLocalizacao = {
      id: 0,
      cidade: {
        nome: formData.cidade,
        estado: {
          uf: formData.estado,
        },
      },
    };

    const dadosAcademicos = {
      formacoes: formacoes.map((formacao) => ({
        id: 0,
        nome: formacao.formacao,
        instituicao: formacao.instituicao,
        tipo: formacao.tipo,
        anoConclusao: parseInt(formacao.anoConclusao, 10),
        tempoFormacao: calcularFormacao(formacao.anoConclusao),
      })),
    };

    const dadosProfissao = {
      profissoes: profissoes.map((profissao) => ({
        id: 0,
        nome: profissao.area,
        area: profissao.area,
      })),
    };

    const dadosProjeto = {
      projetos: projetos.map((projeto) => ({
        id: 0,
        nome: projeto.nome,
      })),
    };

    const dadosCat = {
      cat: cats.map((cat) => ({
        id: 0,
        descricao: cat.descricao,
      })),
    };

    try {
      if (handleSubmit(dadosPessoais)) {
        await postConsultor(dadosPessoais);

        await postCidadeAPI(dadosLocalizacao);

        await postFormacoesAPI(dadosAcademicos);

        await postProfissaoAPI(dadosProfissao);

        await postProjetosAPI(dadosProjeto);

        await postCatAPI(dadosCat);

        console.log('Todos os dados foram enviados com sucesso!');
        setCombinedData({
          ...dadosPessoais,
          ...dadosLocalizacao,
          ...dadosAcademicosProfissionais,
        });
      }
    } catch (error) {
      console.error('Erro ao enviar os dados:', error);
    }
  };

  // const onSubmit = (event) => {
  //   event.preventDefault();

  //   const idade = calcularIdade(formData.dataNascimento);

  //   const dadosCombinados = {
  //     cpf: formData.cpf,
  //     cnpj: formData.cnpj,
  //     nome: formData.nome,
  //     telefone: formData.telefone,
  //     email: formData.email,
  //     dataNascimento: formData.dataNascimento,
  //     idade: idade,
  //     cidade: {
  //       nome: formData.cidade,
  //       estado: {
  //         uf: formData.estado,
  //       },
  //     },
  //     formacoes: formacoes.map((formacao) => ({
  //       nome: formacao.formacao,
  //       instituicao: formacao.instituicao,
  //       tipo: formacao.tipo,
  //       anoConclusao: parseInt(formacao.anoConclusao, 10),
  //       tempoFormacao: calcularFormacao(formacao.anoConclusao),
  //     })),
  //     profissoes: profissoes.map((profissao) => ({
  //       nome: profissao.profissao,
  //       area: profissao.area,
  //     })),
  //     projetos: projetos.map((projeto) => ({
  //       nome: projeto.nome,
  //     })),
  //     cat: cats.map((cat) => ({
  //       descricao: cat.descricao,
  //     })),
  //   };

  //   if (handleSubmit(dadosCombinados)) {
  //     //       <--------------------Enviar os dados para o backend
  //     console.log('Dados válidos:', dadosCombinados);
  //     setCombinedData(dadosCombinados);
  //     postConsultor(dadosCombinados);
  //   }
  // };

  return (
    <Container>
      <Box className="formContainer" sx={{ boxShadow: 2 }}>
        <CardHeader
          title="Dados do Consultor"
          disableTypography={true}
          sx={{ fontFamily: 'Montserrat', fontWeight: 'bold', fontSize: 20 }}
        />
        <Divider />

        <form onSubmit={handleSubmit} className="formCadastro">
          <CardHeader
            title="Informações Pessoais"
            disableTypography={true}
            sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
            className="cardHeaderForm"
          />
          <div className="formInfosPessoais">
            <div className="formNomeIdade">
              <TextField
                label="Nome Completo"
                name="nome"
                value={formData.nome}
                onChange={handleInputChange}
                onBlur={handleInputChange}
              />
              <TextField
                name="nascimento"
                value={formData.nascimento}
                onChange={handleInputChange}
                onBlur={handleInputChange}
                type="date"
              />
            </div>
            <div className="formCpfCnpj">
              <TextField
                label="CPF"
                name="cpf"
                maxLength="14"
                value={formData.cpf}
                onChange={handleInputChange}
                onBlur={handleInputChange}
                inputProps={{ maxLength: 14 }} //14 com os . - (nao botei ainda)
              />
              <TextField
                label="CNPJ"
                name="cnpj"
                value={formData.cnpj}
                onChange={handleInputChange}
                onBlur={handleInputChange}
                inputProps={{ maxLength: 14 }}
              />
            </div>
            <div className="formContatos">
              <TextField
                label="Email"
                name="email"
                value={formData.email}
                onChange={handleInputChange}
                onBlur={handleInputChange}
                type="email"
              />
              <TextField
                label="Telefone"
                name="telefone"
                value={formData.telefone}
                onChange={handleInputChange}
                onBlur={handleInputChange}
                type="tel"
              />
            </div>
            <div className="formCidadeUf">
              <Select
                label="Estado"
                name="estado"
                value={selectValue}
                onChange={(event) => {
                  handleInputChange;
                  setSelectValue(event.target.value);
                }}
                onBlur={handleInputChange}
              >
                {estados.map((estado) => (
                  <MenuItem key={estado} value={estado}>
                    {estado}
                  </MenuItem>
                ))}
              </Select>
              <TextField
                label="Cidade"
                name="cidade"
                value={formData.cidade}
                onChange={handleInputChange}
                onBlur={handleInputChange}
              />
            </div>
          </div>
        </form>
      </Box>
    </Container>
  );
};

export default Cadastrar;
