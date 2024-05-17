import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useValidation } from '../util/ValidationContext';
import {
  Box,
  Button,
  CardHeader,
  Container,
  Divider,
  MenuItem,
  Select,
  TextField,
} from '@mui/material';

const Cadastrar = () => {
  const [formData, setFormData] = useState({
    nome: '',
    nascimento: '',
    cpf: '',
    cnpj: '',
    email: '',
    telefone: '',
    estado: '',
    cidade: '',
  });

  const { errors, handleBlur, handleInputChange, handleSubmit } =
    useValidation();

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

  const onSubmit = (event) => {
    event.preventDefault();
    const dadosCombinados = {
      ...formData,
      formacoes,
      profissoes,
      cats,
      projetos,
    };
    if (handleSubmit(dadosCombinados)) {
      //       <--------------------Enviar os dados para o backend
      console.log('Dados válidos:', dadosCombinados);
      setCombinedData(dadosCombinados);
    }
  };

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
                onChange={handleChange}
              />
              <TextField
                name="nascimento"
                value={formData.nascimento}
                onChange={handleChange}
                onFocus={() => setVisibleText(true)}
                onBlur={() => setVisibleText(false)}
                helperText={visibleText ? 'Data de Nascimento' : ''}
                type="date"
              />
            </div>
            <div className="formCpfCnpj">
              <TextField
                label="CPF"
                name="cpf"
                value={formData.cpf}
                onChange={handleChange}
                onBlur={handleBlurField}
                error={!!errors.cpf}
                helperText={errors.cpf}
              />
              <TextField
                label="CNPJ"
                name="cnpj"
                value={formData.cnpj}
                onChange={handleChange}
                onBlur={handleBlurField}
                error={!!errors.cnpj}
                helperText={errors.cnpj}
              />
            </div>
            <div className="formContatos">
              <TextField
                label="Email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                onBlur={handleBlurField}
                error={!!errors.email}
                helperText={errors.email}
                type="email"
              />
              <TextField
                label="Telefone"
                name="telefone"
                value={formData.telefone}
                onChange={handleChange}
                onBlur={handleBlurField}
                error={!!errors.telefone}
                helperText={errors.telefone}
                type="tel"
              />
            </div>
            <div className="formCidadeUf">
              <Select
                name="estado"
                value={selectValue}
                onChange={(event) => {
                  setSelectValue(event.target.value);
                  handleChange(event);
                }}
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
                onChange={handleChange}
              />
            </div>
          </div>

          <CardHeader
            title="Informações Acadêmicas"
            disableTypography={true}
            sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
            className="cardHeaderForm"
          />
          <div className="formInfosAcademicas">
            {formacoes.map((formacao, index) => (
              <div className="formFormacao" key={index}>
                <TextField
                  label="Formação"
                  name="formacao"
                  value={formacao.formacao}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'formacao',
                      e.target.value,
                      'formacao',
                    )
                  }
                />
                <TextField
                  label="Instituição"
                  name="instituicao"
                  value={formacao.instituicao}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'instituicao',
                      e.target.value,
                      'formacao',
                    )
                  }
                />
                <TextField
                  label="Tipo de Formação"
                  name="tipo"
                  placeholder="ex: Licenciatura, mestrado..."
                  value={formacao.tipo}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'tipo',
                      e.target.value,
                      'formacao',
                    )
                  }
                />
                <TextField
                  label="Ano de Conclusão"
                  name="anoConclusao"
                  type="number"
                  value={formacao.anoConclusao}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'anoConclusao',
                      e.target.value,
                      'formacao',
                    )
                  }
                />
                <Button
                  variant="outlined"
                  sx={{
                    color: '#1CB5D5',
                    borderColor: '#1CB5D5',
                    maxWidth: 120,
                    height: 'maxContent',
                  }}
                  onClick={() => removeFormacao(index)}
                  disabled={formacoes.length <= 1}
                >
                  Remover
                </Button>
              </div>
            ))}
            <Button
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5', width: 150 }}
              startIcon={<img src="/img/plusIcon.svg" />}
              onClick={addNewFormacao}
            >
              Formação
            </Button>
          </div>

          <CardHeader
            title="Informações Profissionais"
            disableTypography={true}
            sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
            className="cardHeaderForm"
          />
          <div className="formInfosProfissionais">
            {profissoes.map((profissao, index) => (
              <div className="formProfissao" key={index}>
                <TextField
                  label="Profissão"
                  name="profissao"
                  value={profissao.profissao}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'profissao',
                      e.target.value,
                      'profissao',
                    )
                  }
                />
                <TextField
                  label="Área de Atuação"
                  name="area"
                  value={profissao.area}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'area',
                      e.target.value,
                      'profissao',
                    )
                  }
                />
                <Button
                  variant="outlined"
                  sx={{
                    color: '#1CB5D5',
                    borderColor: '#1CB5D5',
                    maxWidth: 120,
                    height: 'maxContent',
                  }}
                  onClick={() => removeProfissao(index)}
                  disabled={profissoes.length <= 1}
                >
                  Remover
                </Button>
              </div>
            ))}
            <Button
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5', width: 150 }}
              startIcon={<img src="/img/plusIcon.svg" />}
              onClick={addNewProfissao}
            >
              Profissão
            </Button>
            <Divider />
            {cats.map((cat, index) => (
              <div className="formCat" key={index}>
                <TextField
                  label="Descrição do CAT"
                  name="descricao"
                  value={cat.descricao}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'descricao',
                      e.target.value,
                      'cat',
                    )
                  }
                />
                <Button
                  variant="outlined"
                  size="medium"
                  sx={{
                    color: '#1CB5D5',
                    borderColor: '#1CB5D5',
                    maxWidth: 120,
                    height: 'maxContent',
                  }}
                  onClick={() => removeCat(index)}
                  disabled={cats.length <= 1}
                >
                  Remover
                </Button>
              </div>
            ))}
            <Button
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5', width: 150 }}
              startIcon={<img src="/img/plusIcon.svg" />}
              onClick={addNewCat}
            >
              CAT
            </Button>
            <Divider />
            {projetos.map((projeto, index) => (
              <div className="formProjeto" key={index}>
                <TextField
                  label="Nome do Projeto"
                  name="nome"
                  value={projeto.nome}
                  onChange={(e) =>
                    handleInfosInputChange(
                      index,
                      'nome',
                      e.target.value,
                      'projeto',
                    )
                  }
                />
                <Button
                  variant="outlined"
                  sx={{
                    color: '#1CB5D5',
                    borderColor: '#1CB5D5',
                    maxWidth: 120,
                    height: 'maxContent',
                  }}
                  onClick={() => removeProjeto(index)}
                  disabled={projetos.length <= 1}
                >
                  Remover
                </Button>
              </div>
            ))}
            <Button
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5', width: 150 }}
              startIcon={<img src="/img/plusIcon.svg" />}
              onClick={addNewProjeto}
            >
              Projeto
            </Button>
          </div>
        </form>
        <div className="formFooter">
          <Button
            size="medium"
            variant="contained"
            sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
            onClick={onSubmit}
          >
            Salvar
          </Button>
          <Link to={'/'}>
            <Button
              id="btnCancel"
              size="medium"
              variant="outlined"
              sx={{ color: '#C23229', borderColor: '#C23229' }}
            >
              Cancelar
            </Button>
          </Link>
        </div>
      </Box>
    </Container>
  );
};

export default Cadastrar;
