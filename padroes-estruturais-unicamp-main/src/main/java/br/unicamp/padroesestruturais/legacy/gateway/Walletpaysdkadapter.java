package br.unicamp.padroesestruturais.legacy.gateway;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.externo.ChargeRequest;
import br.unicamp.padroesestruturais.legacy.externo.ChargeResponse;
import br.unicamp.padroesestruturais.legacy.externo.ChargeStatus;
import br.unicamp.padroesestruturais.legacy.externo.WalletPaySDK;

public class WalletPaySDKAdapter implements GatewayPagamento {

    private final WalletPaySDK walletPaySDK;

    public WalletPaySDKAdapter() {
        this.walletPaySDK = new WalletPaySDK();
    }

    @Override
    public ResultadoCobranca processar(String pedidoId, String cliente, double valor, FormaPagamento forma) {
        long amountInCents = (long) (valor * 100);
        ChargeRequest request = new ChargeRequest(pedidoId, cliente, amountInCents);
        ChargeResponse response = walletPaySDK.charge(request);

        String status = response.getStatus() == ChargeStatus.CONFIRMED ? "APROVADA" : "RECUSADA";
        return new ResultadoCobranca(pedidoId, valor, status, response.getWalletTransactionId(), forma);
    }
}