import api from '../api';

const BASE_URL = 'formacoes';

export const getFormacoesAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getFormacoesBuscarAPI = async (params) => {
  const response = await api.get(BASE_URL + '/buscar', { params });
  return response.data;
};

export const getFormacoesIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putFormacaoIdAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteFormacoesAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postFormacoesAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
