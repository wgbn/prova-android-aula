package br.com.wgbn.pm_20160328_trabalho_prova.utils;

import java.util.ArrayList;

import br.com.wgbn.pm_20160328_trabalho_prova.pojo.ClientePojo;

public class ClienteCollection {
    private static ClienteCollection ourInstance = new ClienteCollection();
    private ArrayList<ClientePojo> clientes;

    public static ClienteCollection getInstance() {
        return ourInstance;
    }

    private ClienteCollection() {
        clientes = new ArrayList<ClientePojo>();
    }

    public ArrayList<ClientePojo> getClientes(){
        return clientes;
    }

    public boolean adicionaCliente(ClientePojo cliente){
        return clientes.add(cliente);
    }

    public boolean removeCliente(ClientePojo cliente){
        return clientes.remove(cliente);
    }

    public ClientePojo getByCpf(String cpf){
        for (ClientePojo c : clientes){
            if (c.getCpf() != null && c.getCpf().contains(cpf))
                return c;
        }
        return null;
    }
}
