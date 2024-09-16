package br.com.spedison.buscapalavras.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRegularExpressionURL {
    @Test
    public void teste() {

        String str = "/teste/RecebeNomeHttpAction";
        boolean r = str.matches(".*");
        Assertions.assertTrue(r);
        r = str.matches("/teste/.*");
        Assertions.assertTrue(r);
        r = str.matches("/teste/RecebeNomeHttpAction[/]?");
        Assertions.assertTrue(r);
    }
}
