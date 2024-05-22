import {
  Card,
  CardContent,
  CardHeader,
  Divider,
  Button,
  Box,
  Container,
} from '@mui/material';
import React, { useEffect } from 'react';
import ProfileContext from '../util/Profile';
import { Link } from 'react-router-dom';

const Visualizar = () => {
  window.addEventListener('load', () => (window.location.href = '/'));

  const { profileData } = React.useContext(ProfileContext);

  return (
    <Container>
      <Box className="cardsContainer" sx={{ boxShadow: 2 }}>
        <CardHeader
          title="Dados do Consultor"
          disableTypography={true}
          sx={{ fontFamily: 'Montserrat', fontWeight: 'bold', fontSize: 20 }}
        />
        <Divider />
        <div className="cardsArea">
          <Card sx={{ bgcolor: '#EBF6FF' }}>
            <div className="cardTitles">
              <img id="icon1" src="/img/BlPersonFilled.svg" alt="" />
              <CardHeader
                disableTypography={true}
                sx={{ paddingLeft: 0.5 }}
                title="Informações Pessoais"
              />
            </div>
            <Divider />
            <CardContent>
              <div className="cardContent">
                <p>
                  Nome:<span>{profileData.nome}</span>
                </p>
                <p>
                  Nascimento:<span>{profileData.dataNascimento}</span>
                </p>
                <p>
                  Idade:<span>{profileData.idade} anos</span>
                </p>
                <p>
                  CPF:<span>{profileData.cpf}</span>
                </p>
                <p>
                  CNPJ:<span>{profileData.cnpj}</span>
                </p>
                <p>
                  Email:<span>{profileData.email}</span>
                </p>
                <p>
                  Telefone:<span>{profileData.telefone}</span>
                </p>
                <p>
                  Estado:<span>{profileData.cidade?.estado?.uf}</span>
                </p>
                <p>
                  Cidade:<span>{profileData.cidade?.nome}</span>
                </p>
              </div>
            </CardContent>
          </Card>
          <Card sx={{ bgcolor: '#EBF6FF' }}>
            <div className="cardTitles">
              <img id="icon1" src="/img/malaIcon.svg" alt="" />
              <CardHeader
                disableTypography={true}
                sx={{ paddingLeft: 0.5 }}
                title="Informações Profissionais"
              />
            </div>
            <Divider />
            <CardContent>
              <div className="cardContent">
                {profileData.profissoes?.map((profissao, index) => (
                  <div key={index}>
                    <p>
                      Profissão: <span>{profissao.nome}</span>
                    </p>
                    <p>
                      Area: <span>{profissao.area}</span>
                    </p>
                    {profileData.profissoes.length > 1 && (
                      <Divider sx={{ marginTop: 2 }} />
                    )}
                  </div>
                ))}
                {profileData.cat?.map((catDesc, index) => (
                  <div key={index}>
                    <p>
                      CAT: <span>{catDesc.descricao}</span>
                    </p>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
          <Card sx={{ bgcolor: '#EBF6FF' }}>
            <div className="cardTitles">
              <img id="icon1" src="/img/paperIcon.svg" alt="" />
              <CardHeader
                disableTypography={true}
                sx={{ paddingLeft: 0.5 }}
                title="Informações Acadêmicas"
              />
            </div>
            <Divider />
            <CardContent>
              <div className="cardContent">
                {profileData.formacoes?.map((formacao, index) => (
                  <div key={index}>
                    <p>
                      Formação: <span>{formacao.nome}</span>
                    </p>
                    <p>
                      Tipo de Formação: <span>{formacao.tipo}</span>
                    </p>
                    <p>
                      Ano de Formação: <span>{formacao.anoConclusao}</span>
                    </p>
                    <p>
                      Tempo de Formação:{' '}
                      <span>{formacao.tempoFormacao} anos</span>
                    </p>
                    <p>
                      Instituição: <span>{formacao.instituicao}</span>
                    </p>
                    {profileData.formacoes.length > 1 &&
                      index !== profileData.formacoes.length - 1 && (
                        <Divider sx={{ marginTop: 2 }} />
                      )}
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
        </div>
        <div className="visuButtons">
          <Link to={'/'}>
            <Button
              size="medium"
              variant="outlined"
              sx={{ boxShadow: 2, color: '#1CB5D5', borderColor: '#1CB5D5' }}
              startIcon={<img src="/img/pointerLeftIcon.svg" />}
            >
              Página Anterior
            </Button>
          </Link>
          <Button
            size="medium"
            variant="contained"
            sx={{ boxShadow: 2, bgcolor: '#1CB5D5' }}
            startIcon={<img src="/img/pensilIcon.svg" />}
          >
            Editar
          </Button>
        </div>
      </Box>
    </Container>
  );
};

export default Visualizar;
