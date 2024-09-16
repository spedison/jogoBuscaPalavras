package br.com.spedison.buscapalavras.vo;

import br.com.spedison.buscapalavras.vo.enums.EstrategiaDirecao;
import br.com.spedison.buscapalavras.vo.enums.EstrategiaSentido;

import java.util.List;

public class VOJogoInicial {
    char[] [] dados;
    EstrategiaDirecao[] direcao;
    EstrategiaSentido[] sentido;
    List<String> palavrasParaProcurar;

    public VOJogoInicial(char[][] dados, EstrategiaDirecao[] direcao, EstrategiaSentido[] sentido, List<String> palavrasParaProcurar) {
        this.dados = dados;
        this.direcao = direcao;
        this.sentido = sentido;
        this.palavrasParaProcurar = palavrasParaProcurar;
    }

    public VOJogoInicial() {
    }

    public char[][] getDados() {
        return dados;
    }

    public void setDados(char[][] dados) {
        this.dados = dados;
    }

    public EstrategiaDirecao[] getDirecao() {
        return direcao;
    }

    public void setDirecao(EstrategiaDirecao[] direcao) {
        this.direcao = direcao;
    }

    public EstrategiaSentido[] getSentido() {
        return sentido;
    }

    public void setSentido(EstrategiaSentido[] sentido) {
        this.sentido = sentido;
    }

    public List<String> getPalavrasParaProcurar() {
        return palavrasParaProcurar;
    }

    public void setPalavrasParaProcurar(List<String> palavrasParaProcurar) {
        this.palavrasParaProcurar = palavrasParaProcurar;
    }
}