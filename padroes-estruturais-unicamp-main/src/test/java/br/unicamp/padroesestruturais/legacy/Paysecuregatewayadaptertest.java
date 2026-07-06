package br.unicamp.padroesestruturais.legacy;

import br.unicamp.padroesestruturais.legacy.domain.FormaPagamento;
import br.unicamp.padroesestruturais.legacy.domain.ResultadoCobranca;
import br.unicamp.padroesestruturais.legacy.gateway.PaySecureGatewayAdapter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaySecureGatewayAdapterTest {

    @Test
    void deveAprovarTransacaoDentroDoLimite() {
        PaySecureGatewayAdapter adapter = new PaySecureGatewayAdapter();
        ResultadoCobranca resultado = adapter.processar("PED-001", "Joao Silva", 500.0, FormaPagamento.CARTAO_CREDITO);

        assertEquals("APROVADA", resultado.getStatus());
        assertNotNull(resultado.getReferencia());
        assertTrue(resultado.getReferencia().startsWith("PSEC-"));
    }

    @Test
    void deveRecusarTransacaoAcimaDoLimite() {
        PaySecureGatewayAdapter adapter = new PaySecureGatewayAdapter();
        ResultadoCobranca resultado = adapter.processar("PED-003", "Construtora ABC Ltda", 15000.0, FormaPagamento.CARTAO_CREDITO);

        assertEquals("RECUSADA", resultado.getStatus());
    }
}