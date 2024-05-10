import { createContext, useState } from 'react';

const ProfileContext = createContext({
  profileData: {
    id: '',
    nome: '',
    nascimento: '',
    idade: '',
    cpf: '',
    cnpj: '',
    email: '',
    telefone: '',
    estado: '',
    cidade: '',
    profissao: '',
    atuacao: '',
    cat: '',
    observacao: '',
    formacao: [],
    tipoDeFormacao: '',
    anoDeFormacao: '',
    tempoDeFormado: '',
    instituicao: '',
  },
  setProfileData: () => {},
});

export const ProfileDataProvider = ({ children }) => {
  const [profileData, setProfileData] = useState({
    id: '',
    nome: '',
    nascimento: '',
    idade: '',
    cpf: '',
    cnpj: '',
    email: '',
    telefone: '',
    estado: '',
    cidade: '',
    profissao: '',
    atuacao: '',
    cat: '',
    observacao: '',
    formacao: [],
    tipoDeFormacao: '',
    anoDeFormacao: '',
    tempoDeFormado: '',
    instituicao: '',
  });

  return (
    <ProfileContext.Provider value={{ profileData, setProfileData }}>
      {children}
    </ProfileContext.Provider>
  );
};

export default ProfileContext;
