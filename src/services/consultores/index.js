import api from "../api";

const BASE_URL = "consultores";

export const getConsultoresAPI = async () => {
  const response = await api.get(BASE_URL);

  return response.data;
};

export const getConsultorAPI = async (id) => {
  const response = await api.get(BASE_URL + "/" + id);

  return response.data;
};

export const deleteConsultorAPI = async (id) => {
  const response = await api.delete(BASE_URL + "/" + id);

  return response.data;
};

export const postConsultorAPI = async (consultor) => {
  const response = await api.post(BASE_URL, consultor);

  return response.data;
};

export const getConsultorBuscarAPI = async (
  nome,
  cidade,
  estado,
  formacao,
  anoDeFormacao,
  idade
) => {
  const params = { nome, cidade, estado, formacao, anoDeFormacao, idade };
  const response = await api.get(BASE_URL + "/buscar", { params });

  return response.data;
};

export const putConsultorAPI = async (consultor) => {
  const response = await api.put(BASE_URL + "/" + consultor.id, consultor);

  return response.data;
};
