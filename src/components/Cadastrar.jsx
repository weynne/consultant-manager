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
