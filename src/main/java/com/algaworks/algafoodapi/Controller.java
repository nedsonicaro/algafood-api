package com.algaworks.algafoodapi;
import model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AtivacaoClienteService;

@org.springframework.stereotype.Controller
public class Controller {

    private AtivacaoClienteService ativacaoClienteService;

    public Controller(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("Controller: " + ativacaoClienteService);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Cliente joao = new Cliente("João", "joa@example.com", "1199999999");
        ativacaoClienteService.ativar(joao);
        return "Hello";
    }
}
