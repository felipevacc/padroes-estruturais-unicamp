package br.unicamp.padroesestruturais.legacy;

import br.unicamp.padroesestruturais.legacy.decorator.DescontoFidelidade;
import br.unicamp.padroesestruturais.legacy.decorator.EstrategiaValor;
import br.unicamp.padroesestruturais.legacy.decorator.JurosParcelamento;
import br.unicamp.padroesestruturais.legacy.decorator.SeguroTransacao;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaAntecipacaoRecebivel;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaEmissaoNotaFiscal;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaInternacional;
import br.unicamp.padroesestruturais.legacy.decorator.ValorBase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorTest {

    @Test
    void deveAplicarDescontoFidelidade() {
        EstrategiaValor estrategia = new DescontoFidelidade(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(950.0, resultado, 0.001);
    }

    @Test
    void deveAplicarJurosParcelamento() {
        EstrategiaValor estrategia = new JurosParcelamento(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(1029.9, resultado, 0.001);
    }

    @Test
    void deveAplicarTaxaInternacional() {
        EstrategiaValor estrategia = new TaxaInternacional(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(1050.0, resultado, 0.001);
    }

    @Test
    void deveAplicarSeguro() {
        EstrategiaValor estrategia = new SeguroTransacao(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(1004.90, resultado, 0.001);
    }

    @Test
    void deveAplicarTaxaAntecipacaoRecebivel() {
        EstrategiaValor estrategia = new TaxaAntecipacaoRecebivel(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(1015.0, resultado, 0.001);
    }

    @Test
    void deveAplicarTaxaEmissaoNotaFiscal() {
        EstrategiaValor estrategia = new TaxaEmissaoNotaFiscal(new ValorBase());
        double resultado = estrategia.calcular(1000.0);
        assertEquals(1002.50, resultado, 0.001);
    }

    @Test
    void deveComporMultiplosDecorators() {
        EstrategiaValor estrategia = new SeguroTransacao(
                new TaxaInternacional(
                        new JurosParcelamento(
                                new DescontoFidelidade(new ValorBase())
                        )
                )
        );

        double esperado = 1000.0;
        esperado = esperado - (esperado * 0.05);
        esperado = esperado + (esperado * 0.0299);
        esperado = esperado + (esperado * 0.05);
        esperado = esperado + 4.90;

        double resultado = estrategia.calcular(1000.0);
        assertEquals(esperado, resultado, 0.001);
    }

    @Test
    void devePermitirComposicaoEmOutraOrdem() {
        EstrategiaValor estrategia = new DescontoFidelidade(
                new SeguroTransacao(
                        new TaxaInternacional(
                                new JurosParcelamento(new ValorBase())
                        )
                )
        );

        double resultado = estrategia.calcular(1000.0);
        assertNotNull(resultado);
        assertTrue(resultado > 0);
    }
}