package br.com.spedison.buscapalavras.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestOperadores {
    @Test
    public void teste() {
        int a = 5;
        int b = 10;
        int c = a + b;
        c += 1;
        Assertions.assertEquals(c , 16);
    }
}
