package br.unicamp.padroesestruturais.legacy.decorator;

public class JurosParcelamento extends AjusteDecorator {

    private static final double TAXA = 0.0299;

    public JurosParcelamento(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor + (valor * TAXA);
    }
}