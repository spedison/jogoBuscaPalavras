package br.com.spedison.buscapalavras.controller.enums;

import java.util.Arrays;
import java.util.Objects;

public enum MetodoHttpEnum {
    POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE");
    String nome;

    public static MetodoHttpEnum getFromString(String nome) {
        final MetodoHttpEnum[] ret = {null};
        Arrays.stream(MetodoHttpEnum.values())
                .filter(metodoEnum -> Objects.isNull(ret[0]))
                .forEach(
                        (metodoEnum) -> {
                            if (metodoEnum.comparaNome(nome)) ret[0] = metodoEnum;
                        }
                );
        return ret[0];
    }

    MetodoHttpEnum(String nome) {
        this.nome = nome;
    }

    public boolean comparaNome(String o) {
        if (o == null) return false;
        return o.trim().equalsIgnoreCase(this.nome);
    }

}
