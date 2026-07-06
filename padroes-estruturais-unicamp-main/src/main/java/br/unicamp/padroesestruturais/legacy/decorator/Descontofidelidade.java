package br.unicamp.padroesestruturais.legacy.decorator;

public class DescontoFidelidade extends AjusteDecorator {

    private static final double TAXA = 0.05;

    public DescontoFidelidade(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor - (valor * TAXA);
    }
}