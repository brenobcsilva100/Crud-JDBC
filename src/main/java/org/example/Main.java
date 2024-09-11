package org.example;

import org.example.persistence.connection.ConnectionFactory;
import org.example.persistence.dao.FilmeDao;
import org.example.persistence.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static Connection con = ConnectionFactory.getConnection();

    public static void main(String[] args) {
        buscarTodos();
    }

    private static void buscarTodos() {
        FilmeDao filmeDao = new FilmeDao();
        List<Filme> f = filmeDao.buscarTodos();

        for(Filme filme: f){
            System.out.println(filme);
        }
    }

    private static void buscarPorId() {

        FilmeDao filmeDao = new FilmeDao();
        Filme filme = filmeDao.buscarPorId(3);

        System.out.println(filme);

    }


    private static void excluir() {
        Filme filme = new Filme();
        filme.setId(1);

        FilmeDao filmeDao = new FilmeDao();
        filmeDao.excluir(filme);

        System.out.println("Excluido com sucesso!");
    }

    private static void alterar() {
        Filme filme = new Filme();
        filme.setId(1);
        filme.setNome("Titanic alterado");
        filme.setDescricao("Rico joga os probes na agua alterado");

        FilmeDao filmeDao = new FilmeDao();
        filmeDao.alterar(filme);

        System.out.println("Alterado com sucesso!");
    }

    private static void cadastrar() {
        Filme filme = new Filme();
        filme.setNome("Titanic");
        filme.setDescricao("Rico joga os probes na agua");

        FilmeDao filmeDao = new FilmeDao();
        filmeDao.cadastrar(filme);

        System.out.println("Cadastrar com sucesso!");
    }
}