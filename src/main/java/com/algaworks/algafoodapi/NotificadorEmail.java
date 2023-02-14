package com.algaworks.algafoodapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail implements Notificador {

    @Autowired(required = false)
    private Notificador notificador;

//    @Autowired
//    public AtivacaoClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }
//    public AtivacaoClienteService(String qualquer) {
//    }
    public void ativar(Cliente cliente) {
        cliente.ativar();
        if (notificador != null) {
            notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
        } else {
            System.out.println("Não existe notificador, mas cliente foi ativado");
        }
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Notificando o" + cliente.getNome() + "através do email" +
                cliente.getEmail() + mensagem);
    }

    @Autowired
    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }
}
