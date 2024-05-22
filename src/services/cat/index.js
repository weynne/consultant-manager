import api from '../api';

const BASE_URL = 'cat';

export const getCatAPI = async () => {
  const response = await api.get(BASE_URL);
  return response.data;
};

export const getCatIdAPI = async (id) => {
  const response = await api.get(BASE_URL + `/${id}`);
  return response.data;
};

export const putCatAPI = async (body) => {
  const response = await api.put(BASE_URL + `/${body.id}`, body);
  return response.data;
};

export const deleteCatAPI = async (id) => {
  const response = await api.delete(BASE_URL + `/${id}`);
  return response.data;
};

export const postCatAPI = async (body) => {
  const response = await api.post(BASE_URL, body);
  return response.data;
};
