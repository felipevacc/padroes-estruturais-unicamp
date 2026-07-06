package br.unicamp.padroesestruturais.legacy.decorator;

public class TaxaInternacional extends AjusteDecorator {

    private static final double TAXA = 0.05;

    public TaxaInternacional(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor + (valor * TAXA);
    }
}