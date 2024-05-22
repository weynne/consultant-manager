import api from '../api';

const BASE_URL = 'cidades';

export const getCidadesAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getCidadesBuscarAPI = async (params) => {
  const response = await api.get(BASE_URL + '/buscar', { params });
  return response.data;
};

export const getCidadeIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putCidadeIdAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteCidadeIdAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postCidadeAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
