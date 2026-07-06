package br.unicamp.padroesestruturais.legacy.decorator;

public class TaxaAntecipacaoRecebivel extends AjusteDecorator {

    private static final double TAXA = 0.015;

    public TaxaAntecipacaoRecebivel(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor + (valor * TAXA);
    }
}