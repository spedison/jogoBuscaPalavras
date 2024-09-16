package br.com.spedison.buscapalavras.controller.enums;

public enum HttpResponseCode {
    HTTP_PROCESSING(102),
    HTTP_OK(200),
    HTTP_CRIADO(201),
    HTTP_SOMENTE_ACEITO(202),
    HTTP_CONTEUDO_NAO_AUTORIZADO(203),
    HTTP_SEM_CONTEUDO(204),
    HTTP_CONTEUDO_PARCIAL(206),
    HTTP_MODIFICADO(208),
    HTTP_MOVED_PERMANENTLY(301),
    HTTP_NAO_MODIFICADO(304),
    HTTP_REQUISICAO_RUIM(400),
    HTTP_NAO_AUTORIZADO(401),
    HTTP_NAO_PERMITIDO(403),
    HTTP_NAO_ENCONTRADO(404),
    HTTP_METODO_NAO_PERMITIDO(405),
    HTTP_TIMEOUT(408),
    HTTP_ERRO_INTERNO_SERVIDOR(500);

    HttpResponseCode(int resposta) {
        this.resposta = resposta;
    }

    int resposta;

    public int getResposta(){
        return resposta;
    }
}
