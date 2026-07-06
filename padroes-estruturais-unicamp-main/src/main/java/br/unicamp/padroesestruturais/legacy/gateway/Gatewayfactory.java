package br.unicamp.padroesestruturais.legacy.gateway;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;

public class GatewayFactory {

    public static GatewayPagamento criar(FormaPagamento forma) {
        return switch (forma) {
            case BOLETO, PIX -> new GatewayPagamentoInterno();
            case CARTAO_CREDITO -> new PaySecureGatewayAdapter();
            default -> throw new IllegalArgumentException("Forma de pagamento nao suportada: " + forma);
        };
    }

    public static GatewayPagamento criarCarteiraPagamento() {
        return new WalletPaySDKAdapter();
    }
}