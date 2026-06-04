package br.com.webacademy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.webacademy.Conexao;
import br.com.webacademy.Produto;

public class ProdutoDAO {
    public void salvar(Produto produto) throws Exception {
        var sql = "insert into produto "
                + "(nome, quantidade, valor) values (?, ?, ?)";

        try (var conexao = Conexao.obterConexao();
                var statement = conexao.prepareStatement(sql)) {

            statement.setString(1, produto.nome());
            statement.setInt(2, produto.quantidade());
            statement.setDouble(3, produto.valor());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erro ao salvar o produto: " + e.getMessage());
        }
    }

    public List<Produto> buscarTodos() throws Exception {
        var sql = "select * from produto";
        List<Produto> produtos = new ArrayList<>();

        try (var conexao = Conexao.obterConexao();
                var statement = conexao.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Produto produto = new Produto(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getInt("quantidade"),
                            resultSet.getDouble("valor"));
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return produtos;
    }

    public Produto buscarPorId(Long id) throws Exception {
        var sql = "select * from produto where id = ?";
        Produto produto = null;

        try (var conexao = Conexao.obterConexao();
                var statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    produto = new Produto(
                            resultSet.getLong("id"),
                            resultSet.getString("nome"),
                            resultSet.getInt("quantidade"),
                            resultSet.getDouble("valor"));
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return produto;
    }

    public void atualizar(Produto produto) throws Exception {
        var sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = ?";

        try (var conexao = Conexao.obterConexao();
                var statement = conexao.prepareStatement(sql)) {

            statement.setString(1, produto.nome());
            statement.setInt(2, produto.quantidade());
            statement.setDouble(3, produto.valor());
            statement.setLong(4, produto.id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void excluir(Long id) throws Exception {
        var sql = "delete from produto where id = ?";

        try (var conexao = Conexao.obterConexao();
                var statement = conexao.prepareStatement(sql)) {

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

}