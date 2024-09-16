package br.com.spedison.buscapalavras.vo;

public class VOUsuario {
    private String usuario;
    private String senha;

    public VOUsuario() {
    }

    public VOUsuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }
}
