import React, { useState } from 'react';
import ProfileContext from '../util/Profile';
import { Link } from 'react-router-dom';
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

  //formacoes
  const [numFormacoesInput, setNumFormacoesInput] = useState('1');
  const [numFormacoes, setNumFormacoes] = useState(1);
  const [formacoes, setFormacoes] = useState([
    { formacao: '', instituicao: '', tipo: '', anoConclusao: '' },
  ]);

  const handleNumFormacoesChange = (event) => {
    const value = event.target.value;
    setNumFormacoesInput(value);

    const newNum = parseInt(value, 10);

    if (!isNaN(newNum) && newNum > 0) {
      setNumFormacoes(newNum);

      const newFormacoes = [...formacoes];
      if (newNum > formacoes.length) {
        for (let i = formacoes.length; i < newNum; i++) {
          newFormacoes.push({
            formacao: '',
            instituicao: '',
            tipo: '',
            anoConclusao: '',
          });
        }
      } else {
        newFormacoes.length = newNum;
      }
      setFormacoes(newFormacoes);
    }
  };

  //profissoes
  const [numProfissoesInput, setNumProfissoesInput] = useState('1');
  const [numProfissoes, setNumProfissoes] = useState(1);
  const [profissoes, setProfissoes] = useState([{ profissao: '', area: '' }]);

  const handleNumProfissoesChange = (event) => {
    const value = event.target.value;
    setNumProfissoesInput(value);

    const novoNum = parseInt(value, 10);

    if (!isNaN(novoNum) && novoNum > 0) {
      setNumProfissoes(novoNum);

      const newProfissoes = [...profissoes];
      if (novoNum > profissoes.length) {
        for (let i = profissoes.length; i < novoNum; i++) {
          newProfissoes.push({
            profissao: '',
            area: '',
          });
        }
      } else {
        newProfissoes.length = novoNum;
      }
      setProfissoes(newProfissoes);
    }
  };

  //projetos
  const [numProjetosInput, setNumProjetosInput] = useState('1');
  const [numProjetos, setNumProjetos] = useState(1);
  const [projetos, setProjetos] = useState([{ projeto: '' }]);

  const handleProjetosChange = (event) => {
    const value = event.target.value;
    setNumProjetosInput(value);

    const novoNum = parseInt(value, 10);

    if (!isNaN(novoNum) && novoNum > 0) {
      setNumProjetos(novoNum);

      const newProjetos = [...projetos];
      if (novoNum > projetos.length) {
        for (let i = projetos.length; i < novoNum; i++) {
          newProjetos.push({
            projeto: '',
          });
        }
      } else {
        newProjetos.length = novoNum;
      }
      setProjetos(newProjetos);
    }
  };

  //cat
  const [numCatsInput, setNumCatsInput] = useState('1');
  const [numCats, setNumCats] = useState(1);
  const [cats, setCats] = useState([{ descricao: '' }]);

  const handleCatsChange = (event) => {
    const value = event.target.value;
    setNumCatsInput(value);

    const novoNum = parseInt(value, 10);

    if (!isNaN(novoNum) && novoNum > 0) {
      setNumCats(novoNum);

      const newCat = [...cats];
      if (novoNum > cats.length) {
        for (let i = cats.length; i < novoNum; i++) {
          newCat.push({
            descricao: '',
          });
        }
      } else {
        newCat.length = novoNum;
      }
      setCats(newCat);
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
          <CardHeader
            title="Informações Acadêmicas"
            disableTypography={true}
            sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
            className="cardHeaderForm"
          />
          <div className="formInfosAcademicas">
            <TextField
              label="Número de Formações"
              name="numDeFormacoes"
              type="number"
              sx={{ width: 250 }}
              value={numFormacoesInput}
              inputProps={{ min: 1 }}
              onChange={handleNumFormacoesChange}
            />
            {formacoes.map((formacao, index) => (
              <div className="formFormacao" key={index}>
                <TextField label={`Formação ${index + 1}`} name="formacao" />
                <TextField label="Instituição" name="instituicao" />
                <TextField
                  label="Tipo de Formação"
                  name="tipo"
                  placeholder="ex: Licenciatura, mestrado..."
                />
                <TextField
                  label="Ano de Conclusão"
                  name="anoConclusao"
                  type="number"
                />
                <Divider sx={{ gridColumn: '1/-1' }} />
              </div>
            ))}
          </div>
          <CardHeader
            title="Informações Profissionais"
            disableTypography={true}
            sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
            className="cardHeaderForm"
          />
          <div className="formInfosProfissionais">
            <TextField
              label="Número de Profissões/Áreas"
              name="numDeProfissoes"
              type="number"
              sx={{ width: 250 }}
              value={numProfissoesInput}
              inputProps={{ min: 1 }}
              onChange={handleNumProfissoesChange}
            />
            {profissoes.map((profissao, index) => (
              <div className="formProfissao" key={index}>
                <TextField label={`Profissão ${index + 1}`} name="profissao" />
                <TextField label={`Área de Atuação ${index + 1}`} name="area" />
                <Divider sx={{ gridColumn: '1/-1' }} />
              </div>
            ))}
            <TextField
              label="Número de Projetos"
              name="numDeProjetos"
              type="number"
              sx={{ width: 250 }}
              value={numProjetosInput}
              inputProps={{ min: 1 }}
              onChange={handleProjetosChange}
            />
            {projetos.map((projeto, index) => (
              <div className="formProjeto" key={index}>
                <TextField label={`Projeto ${index + 1}`} name="projeto" />
                <Divider sx={{ gridColumn: '1/-1' }} />
              </div>
            ))}
            <TextField
              label="Número de CAT"
              name="numCat"
              type="number"
              sx={{ width: 250 }}
              value={numCatsInput}
              inputProps={{ min: 1 }}
              onChange={handleCatsChange}
            />
            {cats.map((cat, index) => (
              <div className="formCat" key={index}>
                <TextField label={`Descrição CAT ${index + 1}`} name="cat" />
                <Divider sx={{ gridColumn: '1/-1' }} />
              </div>
            ))}
          </div>
        </form>
        <div className="formFooter">
          <Button
            size="medium"
            variant="contained"
            sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
          >
            Salvar
          </Button>
          <Link to={'/'}>
            <Button
              id="btnCancel"
              size="medium"
              variant="outlined"
              sx={{
                color: '#C23229',
                borderColor: '#C23229',
              }}
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
