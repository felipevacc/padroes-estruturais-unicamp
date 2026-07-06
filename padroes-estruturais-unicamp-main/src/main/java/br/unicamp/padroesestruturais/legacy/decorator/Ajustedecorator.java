package br.unicamp.padroesestruturais.legacy.decorator;

public abstract class AjusteDecorator implements EstrategiaValor {

    protected EstrategiaValor estrategia;

    public AjusteDecorator(EstrategiaValor estrategia) {
        this.estrategia = estrategia;
    }

    @Override
    public double calcular(double valor) {
        return aplicarAjuste(estrategia.calcular(valor));
    }

    protected abstract double aplicarAjuste(double valor);
}