import React, { useEffect, useState } from 'react';
import {
  Container,
  Box,
  CardHeader,
  Divider,
  TextField,
  Select,
  MenuItem,
  Button,
} from '@mui/material';
import { Link, useParams } from 'react-router-dom';
import { useConsultores } from '../context/consultores';
import { putCidadeIdAPI } from '../services/cidades';
import { putEstadoIdAPI } from '../services/estados';

const Editar = () => {
  const { id } = useParams();
  console.log(id);

  const { getConsultor, putConsultor } = useConsultores();

  const [consultor, setConsultor] = useState(null);

  //getConsultor
  useEffect(() => {
    const carregarConsultores = async () => {
      try {
        const resposta = await getConsultor(id);
        setConsultor(resposta);
        console.log(resposta);
      } catch (error) {
        console.error('Erro ao carregar consultores:', error);
      }
    };

    carregarConsultores();
  }, [id, getConsultor]);

  //infos pessoais
  const [personalInfo, setPersonalInfo] = useState({
    nome: '',
    dataNascimento: '',
    cpf: '',
    cnpj: '',
    email: '',
    telefone: '',
  });

  const [originalPersonalInfo, setOriginalPersonalInfo] = useState({});

  const formatDateForInput = (date) => {
    const [day, month, year] = date.split('/');
    return `${year}-${month}-${day}`;
  };

  useEffect(() => {
    if (consultor) {
      setPersonalInfo({
        nome: consultor.nome || '',
        dataNascimento: consultor.dataNascimento
          ? formatDateForInput(consultor.dataNascimento)
          : '',
        cpf: consultor.cpf || '',
        cnpj: consultor.cnpj || '',
        email: consultor.email || '',
        telefone: consultor.telefone || '',
      });
      setOriginalPersonalInfo({
        nome: consultor.nome || '',
        dataNascimento: consultor.dataNascimento
          ? formatDateForInput(consultor.dataNascimento)
          : '',
        cpf: consultor.cpf || '',
        cnpj: consultor.cnpj || '',
        email: consultor.email || '',
        telefone: consultor.telefone || '',
      });
    }
  }, [consultor]);

  const handlePersonalInfoChange = (e) => {
    const { name, value } = e.target;
    setPersonalInfo((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlePersonalInfoSubmit = async (e) => {
    e.preventDefault();
    try {
      await putConsultor(id, personalInfo);
      alert('Informações pessoais atualizadas com sucesso!');
    } catch (error) {
      console.error('Erro ao atualizar informações pessoais:', error);
    }
  };

  const handlePersonalInfoCancel = () => {
    setPersonalInfo(originalPersonalInfo);
  };

  //localização
  const [locationInfo, setLocationInfo] = useState({
    estado: '',
    cidade: '',
  });
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

  const [originalLocationInfo, setOriginalLocationInfo] = useState({});

  useEffect(() => {
    if (consultor) {
      setLocationInfo({
        estado: consultor.cidade?.estado?.uf || '',
        cidade: consultor.cidade?.nome || '',
      });
      setOriginalLocationInfo({
        estado: consultor.cidade?.estado?.uf || '',
        cidade: consultor.cidade?.nome || '',
      });
    }
  }, [consultor]);

  const handleLocationInfoChange = (e) => {
    const { name, value } = e.target;
    setLocationInfo((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleLocationInfoSubmit = async (e) => {
    e.preventDefault();
    try {
      const envioEstado = {
        id: id,
        uf: locationInfo.estado,
      };
      await putEstadoIdAPI(envioEstado);

      const envioCidade = {
        id: id,
        nome: locationInfo.cidade,
        estado: {
          id: id,
          uf: locationInfo.estado,
        },
      };
      await putCidadeIdAPI(envioCidade);

      alert('Informações de localização atualizadas com sucesso!');
    } catch (error) {
      console.error('Erro ao atualizar informações de localização:', error);
    }
  };

  const handleLocationInfoCancel = () => {
    setLocationInfo(originalLocationInfo);
  };

  return (
    <Container>
      <Box className="formContainer" sx={{ boxShadow: 2 }}>
        <CardHeader
          title="Editar Consultor"
          disableTypography={true}
          sx={{ fontFamily: 'Montserrat', fontWeight: 'bold', fontSize: 20 }}
        />
        <Divider />

        <form className="formCadastro" onSubmit={handlePersonalInfoSubmit}>
          <Box sx={{ display: 'flex', gap: 1 }}>
            <img src="/img/pensilIconBlack.svg" alt="" />
            <CardHeader
              title="Informações Pessoais"
              disableTypography={true}
              sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
              className="cardHeaderForm"
            />
          </Box>
          <div className="formInfosPessoais">
            <Box className="formNomeIdade">
              <TextField
                label="Nome Completo"
                name="nome"
                value={personalInfo.nome}
                onChange={handlePersonalInfoChange}
              />
              <TextField
                label="Data de Nascimento"
                name="dataNascimento"
                value={personalInfo.dataNascimento}
                onChange={handlePersonalInfoChange}
                type="date"
              />
            </Box>
            <Box className="formCpfCnpj">
              <TextField
                label="CPF"
                name="cpf"
                value={personalInfo.cpf}
                onChange={handlePersonalInfoChange}
              />
              <TextField
                label="CNPJ"
                name="cnpj"
                value={personalInfo.cnpj}
                onChange={handlePersonalInfoChange}
              />
            </Box>
            <Box className="formContatos" sx={{ gridColumn: '1/-1' }}>
              <TextField
                label="Email"
                name="email"
                value={personalInfo.email}
                onChange={handlePersonalInfoChange}
                type="email"
              />
              <TextField
                label="Telefone"
                name="telefone"
                value={personalInfo.telefone}
                onChange={handlePersonalInfoChange}
                type="tel"
              />
            </Box>
          </div>
          <Box sx={{ display: 'flex', gap: 1, marginTop: 2 }}>
            <Button
              size="medium"
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
              type="submit"
            >
              Atualizar
            </Button>
            <Button
              id="btnCancel"
              size="medium"
              variant="outlined"
              sx={{ color: '#C23229', borderColor: '#C23229' }}
              onClick={handlePersonalInfoCancel}
            >
              Cancelar
            </Button>
          </Box>
        </form>

        <form className="formCadastro" onSubmit={handleLocationInfoSubmit}>
          <Box sx={{ display: 'flex', gap: 1 }}>
            <img src="/img/pensilIconBlack.svg" alt="" />
            <CardHeader
              title="Localização"
              disableTypography={true}
              sx={{ fontFamily: 'Montserrat', fontSize: 20, paddingLeft: 0 }}
              className="cardHeaderForm"
            />
          </Box>
          <Box className="formCidadeUf">
            <Select
              name="estado"
              value={locationInfo.estado}
              onChange={handleLocationInfoChange}
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
              value={locationInfo.cidade}
              onChange={handleLocationInfoChange}
            />
          </Box>
          <Box sx={{ display: 'flex', gap: 1, marginTop: 2 }}>
            <Button
              size="medium"
              variant="contained"
              sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
              type="submit"
            >
              Atualizar
            </Button>
            <Button
              id="btnCancel"
              size="medium"
              variant="outlined"
              sx={{ color: '#C23229', borderColor: '#C23229' }}
              onClick={handlePersonalInfoCancel}
            >
              Cancelar
            </Button>
          </Box>
        </form>
      </Box>
    </Container>
  );
};

export default Editar;
