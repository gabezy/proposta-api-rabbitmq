package br.com.gabezy.propostaapi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FormatterValorUtils {

    public static String formatarValorParaBRL(double valor) {
        return NumberFormat.getCurrencyInstance().format(valor);
    }

}
