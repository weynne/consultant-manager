import api from '../api';

const BASE_URL = 'profissoes';

export const getProfissoesAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getProfissoesBuscarAPI = async (params) => {
  const response = await api.get(BASE_URL + '/buscar', { params });
  return response.data;
};

export const getProfissoesIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putProfissaoIdAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteProfissaoIdAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postProfissaoAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
