import api from '../api';

const BASE_URL = 'estados';

export const getEstadosAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getEstadosBuscarAPI = async (uf) => {
  const response = await api.get(BASE_URL + '/buscar', { uf });
  return response.data;
};

export const getEstadosIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putEstadoIdAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteEstadoIdAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postEstadosAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
