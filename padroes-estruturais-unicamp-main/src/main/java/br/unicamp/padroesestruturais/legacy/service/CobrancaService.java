package br.unicamp.padroesestruturais.legacy.service;

import br.unicamp.padroesestruturais.legacy.decorator.DescontoFidelidade;
import br.unicamp.padroesestruturais.legacy.decorator.EstrategiaValor;
import br.unicamp.padroesestruturais.legacy.decorator.JurosParcelamento;
import br.unicamp.padroesestruturais.legacy.decorator.SeguroTransacao;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaAntecipacaoRecebivel;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaEmissaoNotaFiscal;
import br.unicamp.padroesestruturais.legacy.decorator.TaxaInternacional;
import br.unicamp.padroesestruturais.legacy.decorator.ValorBase;
import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.Pedido;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.gateway.GatewayFactory;
import br.unicamp.padroesestruturais.legacy.gateway.GatewayPagamento;

import java.util.ArrayList;
import java.util.List;

public class CobrancaService {

    public ResultadoCobranca cobrar(Pedido pedido, FormaPagamento forma,
                                     boolean aplicarDescontoFidelidade,
                                     boolean aplicarJurosParcelamento,
                                     boolean aplicarTaxaInternacional,
                                     boolean aplicarSeguro) {

        EstrategiaValor estrategia = construirEstrategia(aplicarDescontoFidelidade,
                aplicarJurosParcelamento, aplicarTaxaInternacional, aplicarSeguro);

        double valorFinal = estrategia.calcular(pedido.getValorBase());
        GatewayPagamento gateway = GatewayFactory.criar(forma);

        return gateway.processar(pedido.getId(), pedido.getCliente(), valorFinal, forma);
    }

    public List<ResultadoCobranca> cobrarEmLote(List<Pedido> pedidos, FormaPagamento forma,
                                                  boolean aplicarDescontoFidelidade,
                                                  boolean aplicarJurosParcelamento,
                                                  boolean aplicarTaxaInternacional,
                                                  boolean aplicarSeguro) {

        EstrategiaValor estrategia = construirEstrategia(aplicarDescontoFidelidade,
                aplicarJurosParcelamento, aplicarTaxaInternacional, aplicarSeguro);

        List<ResultadoCobranca> resultados = new ArrayList<>();
        GatewayPagamento gateway = GatewayFactory.criar(forma);

        for (Pedido pedido : pedidos) {
            double valorFinal = estrategia.calcular(pedido.getValorBase());
            resultados.add(gateway.processar(pedido.getId(), pedido.getCliente(), valorFinal, forma));
        }

        return resultados;
    }

    private EstrategiaValor construirEstrategia(boolean aplicarDescontoFidelidade,
                                                 boolean aplicarJurosParcelamento,
                                                 boolean aplicarTaxaInternacional,
                                                 boolean aplicarSeguro) {
        EstrategiaValor estrategia = new ValorBase();

        if (aplicarDescontoFidelidade) {
            estrategia = new DescontoFidelidade(estrategia);
        }
        if (aplicarJurosParcelamento) {
            estrategia = new JurosParcelamento(estrategia);
        }
        if (aplicarTaxaInternacional) {
            estrategia = new TaxaInternacional(estrategia);
        }
        if (aplicarSeguro) {
            estrategia = new SeguroTransacao(estrategia);
        }

        return estrategia;
    }
}