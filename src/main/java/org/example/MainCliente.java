package org.example;

import org.example.persistence.connection.ConnectionFactory;
import org.example.persistence.dao.ClienteDao;
import org.example.persistence.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MainCliente {

    private static Connection con = ConnectionFactory.getConnection();

    public static void main(String[] args) {
        cadastrar();
    }

    private static void buscarTodos() {
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> f = clienteDao.buscarTodos();

        for(Cliente c: f){
            System.out.println(c);
        }
    }

    private static void buscarPorId() {
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscarPorId(3);

        System.out.println(cliente);
    }

    private static void excluir() {
        Cliente cliente = new Cliente();
        cliente.setId(3);

        ClienteDao clienteDao = new ClienteDao();
        clienteDao.excluir(cliente);

        System.out.println("Cliente excluido com sucesso!");
    }

    private static void alterar() {
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Olinda");
        cliente.setIdade("20");

        ClienteDao clienteDao = new ClienteDao();
        clienteDao.alterar(cliente);

        System.out.println("Cliente alterado com sucesso!");

    }

    private static void cadastrar() {
        Cliente cliente = new Cliente();
        cliente.setNome("Olinda");
        cliente.setIdade("17");

        String idade = cliente.getIdade();
        int idadeInt = Integer.parseInt(idade);

        if(idadeInt<18){
            System.out.println("Menor de idade. Responsavel para alugar filme.");
            cadastrarResponsavelAlugarFilme();
        }else{
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.cadastrar(cliente);

            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

    private static void cadastrarResponsavelAlugarFilme() {
        Cliente cliente = new Cliente();
        cliente.setNome("Rosinaldo");
        cliente.setIdade("16");

        String idade = cliente.getIdade();
        int idadeInt = Integer.parseInt(idade);

        if(idadeInt<18){
            System.out.println("Menor de idade. Responsavel para alugar filme.");
            cadastrarResponsavelAlugarFilme();
        }else{
            ClienteDao clienteDao = new ClienteDao();
            clienteDao.cadastrar(cliente);

            System.out.println("Cliente cadastrado com sucesso!");
        }
    }

}
