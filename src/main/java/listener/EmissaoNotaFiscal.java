package listener;

import service.ClienteAtivadoEvent;

public class EmissaoNotaFiscal {
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.println("Emitindo nota fiscal para o cliente " + event.getCliente().getNome());
    }
}