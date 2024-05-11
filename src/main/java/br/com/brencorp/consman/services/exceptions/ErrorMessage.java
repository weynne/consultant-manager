package br.com.brencorp.consman.services.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    CONSULTOR_NAO_ENCONTRADO(". Consultor não encontrado."),
    CIDADE_NAO_ENCONTRADA(". Cidade não encontrada."),
    ESTADO_NAO_ENCONTRADO(" . Estado não encontrado"),
    FORMACAO_NAO_ENCONTRADA(". Formação acadêmica não encontrada."),
    PROFISSAO_NAO_ENCONTRADA(". Profissão não encontrada."),
    PROJETO_NAO_ENCONTRADO(". Projeto não encontrado."),
    CAT_NAO_ENCONTRADO(". Cat não encontrada.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}
