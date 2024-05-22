import api from '../api';

const BASE_URL = 'projetos';

export const getProjetosAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getProjetosBuscarAPI = async (nome) => {
  const response = await api.get(BASE_URL + '/buscar', { nome });
  return response.data;
};

export const getProjetosIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putProjetoIdAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteProjetoIdAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postProjetosAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
