package br.unicamp.padroesestruturais.legacy.decorator;

public class TaxaEmissaoNotaFiscal extends AjusteDecorator {

    private static final double VALOR_FIXO = 2.50;

    public TaxaEmissaoNotaFiscal(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor + VALOR_FIXO;
    }
}