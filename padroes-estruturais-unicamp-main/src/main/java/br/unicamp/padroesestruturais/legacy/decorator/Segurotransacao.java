package br.unicamp.padroesestruturais.legacy.decorator;

public class SeguroTransacao extends AjusteDecorator {

    private static final double VALOR_FIXO = 4.90;

    public SeguroTransacao(EstrategiaValor estrategia) {
        super(estrategia);
    }

    @Override
    protected double aplicarAjuste(double valor) {
        return valor + VALOR_FIXO;
    }
}