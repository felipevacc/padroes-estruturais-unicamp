package br.unicamp.padroesestruturais.legacy.decorator;

public class ValorBase implements EstrategiaValor {

    @Override
    public double calcular(double valor) {
        return valor;
    }
}