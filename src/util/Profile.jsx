import { number } from 'prop-types';
import { createContext, useState } from 'react';

const ProfileContext = createContext({
  profileData: {
    id: '',
    cpf: '',
    cnpj: '',
    nome: '',
    telefone: '',
    email: '',
    dataNascimento: '',
    idade: '',
    cidade: {
      id: '',
      nome: '',
      estado: {
        id: '',
        uf: '',
      },
    },
    formacoes: [
      {
        id: '',
        nome: '',
        instituicao: '',
        tipo: '',
        anoConclusao: '',
        tempoFormacao: '',
      },
    ],
    profissoes: [
      {
        id: '',
        nome: '',
        area: '',
      },
    ],
    projetos: [
      {
        id: '',
        nome: '',
      },
    ],
    cat: [
      {
        id: '',
        descricao: '',
      },
    ],
  },
  setProfileData: () => {},
});

export const ProfileDataProvider = ({ children }) => {
  const [profileData, setProfileData] = useState({
    id: '',
    cpf: '',
    cnpj: '',
    nome: '',
    telefone: '',
    email: '',
    dataNascimento: '',
    idade: '',
    cidade: {
      id: '',
      nome: '',
      estado: {
        id: '',
        uf: '',
      },
    },
    formacoes: [
      {
        id: '',
        nome: '',
        instituicao: '',
        tipo: '',
        anoConclusao: '',
        tempoFormacao: '',
      },
    ],
    profissoes: [
      {
        id: '',
        nome: '',
        area: '',
      },
    ],
    projetos: [
      {
        id: '',
        nome: '',
      },
    ],
    cat: [
      {
        id: '',
        descricao: '',
      },
    ],
  });

  const [selectedId, setSelectedId] = useState('');

  return (
    <ProfileContext.Provider value={{ profileData, setProfileData }}>
      {children}
    </ProfileContext.Provider>
  );
};

export default ProfileContext;
