package org.example.persistence.dao;

import org.example.persistence.connection.ConnectionFactory;
import org.example.persistence.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmeDao {

    private static Connection con = ConnectionFactory.getConnection();

    public void cadastrar(Filme filme) {
        String sql = "insert into filme(nome,descricao) values(?,?)";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, filme.getNome());
            preparador.setString(2, filme.getDescricao());

            preparador.execute();
            preparador.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(Filme filme) {
        String sql = "update filme set nome=?,descricao=? where id=?";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setString(1, filme.getNome());
            preparador.setString(2, filme.getDescricao());
            preparador.setInt(3, filme.getId());

            preparador.execute();
            preparador.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(Filme filme) {
        String sql = "delete from filme where id=?";

        try{
            PreparedStatement preparador = con.prepareStatement(sql);
            preparador.setInt(1, filme.getId());

            preparador.execute();
            preparador.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Filme buscarPorId(Integer id) {
        String sql = "select * from filme where id=?";

        try(PreparedStatement preparador = con.prepareStatement(sql)){
            preparador.setInt(1,id);

            ResultSet resultado = preparador.executeQuery();
            if(resultado.next()){
                Filme filme = new Filme();
                filme.setId(resultado.getInt("id"));
                filme.setNome(resultado.getString("nome"));
                filme.setDescricao(resultado.getString("descricao"));
               return filme;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public List<Filme> buscarTodos() {
        String sql = "select * from filme";
        List<Filme> filmes = new ArrayList<>();

        try(PreparedStatement preparador = con.prepareStatement(sql)){
            ResultSet resultado = preparador.executeQuery();
            while (resultado.next()){
                Filme filme = new Filme();
                filme.setId(resultado.getInt("id"));
                filme.setNome(resultado.getString("nome"));
                filme.setDescricao(resultado.getString("descricao"));
                filmes.add(filme);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return filmes;
    }
}
