import { createContext, useState } from 'react';
import {
  getConsultoresAPI,
  getConsultorAPI,
  deleteConsultorAPI,
  postConsultorAPI,
  getConsultorBuscarAPI,
  putConsultorAPI,
} from '../../services/consultores';
import { useContext } from 'react';

const ConsultoresContext = createContext({});

export const ConsultoresProvider = ({ children }) => {
  const [data, setData] = useState([]);

  const getConsultores = async () => {
    try {
      setData(await getConsultoresAPI());
    } catch (error) {
      console.error(error);
    }
  };

  const getConsultor = async (id) => {
    try {
      return await getConsultorAPI(id);
    } catch (error) {
      console.error(error);
    }
  };

  const deleteConsultor = async (id) => {
    try {
      await deleteConsultorAPI(id);
      setData(await getConsultoresAPI());
    } catch (error) {
      console.error(error);
    }
  };

  const postConsultor = async (id) => {
    try {
      await postConsultorAPI(id);
      setData(await getConsultoresAPI());
    } catch (error) {
      console.error(error);
    }
  };

  const getConsultorBuscar = async ({
    nome = '',
    cidade = '',
    estado = '',
    formacao = '',
    anoDeFormacao = '',
    idade = '',
  } = {}) => {
    const params = { nome, cidade, estado, formacao, anoDeFormacao, idade };
    const filteredParams = Object.entries(params).reduce(
      (acc, [key, value]) => {
        if (value) {
          // Checa se o valor não é vazio
          acc[key] = value;
        }
        return acc;
      },
      {},
    );
    try {
      await getConsultorBuscarAPI(filteredParams).then((response) =>
        setData(response),
      );
    } catch (error) {
      return console.error(error);
    }
  };

  const putConsultor = async (id) => {
    try {
      await putConsultorAPI(id);
      setData(await getConsultoresAPI());
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <ConsultoresContext.Provider
      value={{
        data,
        getConsultores,
        getConsultor,
        deleteConsultor,
        postConsultor,
        getConsultorBuscar,
        putConsultor,
      }}
    >
      {children}
    </ConsultoresContext.Provider>
  );
};

export const useConsultores = () => useContext(ConsultoresContext);
