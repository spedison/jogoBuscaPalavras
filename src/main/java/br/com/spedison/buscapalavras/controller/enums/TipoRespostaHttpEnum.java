package br.com.spedison.buscapalavras.controller.enums;

public enum TipoRespostaHttpEnum {

    JSON("Json","application/json", "utf-8"),
    TXT("Text","text/plain", "utf-8"),
    CSV("CSV","text/csv", "utf-8"),
    HTML("Html","text/html", "utf-8"),
    XML("Xml","application/xml","utf-8");

    TipoRespostaHttpEnum(String nome, String contentType, String charsetName) {
        this.nome = nome;
        this.contentType = contentType;
        this.charsetName = charsetName;
    }

    String nome;
    String contentType;
    String charsetName;

    public String getNome() {
        return nome;
    }

    public String getContentType() {
        return contentType + ";charset=" + charsetName;
    }
}
