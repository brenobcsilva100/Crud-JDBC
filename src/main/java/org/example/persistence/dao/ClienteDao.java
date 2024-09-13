package org.example.persistence.dao;

import org.example.persistence.connection.ConnectionFactory;
import org.example.persistence.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {

    private static Connection con = ConnectionFactory.getConnection();


    public void cadastrar(Cliente cliente) {
        String sql = "insert into cliente(nome, idade) values(?,?)";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1,cliente.getNome());
            preparador.setString(2,cliente.getIdade());

            preparador.execute();
            preparador.close();
        }catch (SQLException e){
            throw  new RuntimeException();
        }
    }

    public void alterar(Cliente cliente) {
        String sql = "update cliente set nome=?, idade=? where id=?";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1,cliente.getNome());
            preparador.setString(2,cliente.getIdade());
            preparador.setInt(3,cliente.getId());

            preparador.execute();
            preparador.close();
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    public void excluir(Cliente cliente) {
        String sql = "delete from cliente where id=?";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setInt(1, cliente.getId());

            preparador.execute();
            preparador.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Cliente buscarPorId(Integer id) {
        String sql = "select * from cliente where id=?";

        try(PreparedStatement preparador = con.prepareStatement(sql)){
            preparador.setInt(1,id);

            ResultSet resultado = preparador.executeQuery();
            if(resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setIdade(resultado.getString("idade"));
                return cliente;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Cliente> buscarTodos() {
        String sql = "select * from cliente";
        List<Cliente> clientes = new ArrayList<>();

        try(PreparedStatement preparador = con.prepareStatement(sql)){
            ResultSet resultado = preparador.executeQuery();
            while (resultado.next()){
                Cliente cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setIdade(resultado.getString("idade"));
                clientes.add(cliente);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }
}
