package system.infrastructure.repository;

import system.domain.Livros;
import system.infrastructure.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    public void InserirLivro(Livros livro) throws SQLException {

        String query = "INSERT INTO livros (titulo, autor, ano) Values (?, ?, ?)";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.executeUpdate();

        }
    }

    public static void ConsultarTodosLivros() throws SQLException {

    }

    public void AtualizarStatusLivroIndisponivel(int id) throws SQLException {

        String query = "UPDATE livros SET disponivel = 0 WHERE id = ?";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void AtualizarStatusLivroDisponivel(int id) throws SQLException {

        String query = "UPDATE livros SET disponivel = 1 WHERE id = ?";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public List<Livros> buscarLivros() throws SQLException {

        List<Livros> livros = new ArrayList<>();

        String query = """
                SELECT 
                id,
                titulo,
                autor,
                ano,
                disponivel
                FROM livros
                """;

        try(Connection conn = Connect.conectar();
        PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id =  rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano = rs.getInt("ano");
                boolean disponivel = rs.getBoolean("disponivel");

                var book = new Livros(id, titulo, autor, ano, disponivel);
                livros.add(book);
            }
        }

        return livros;
    }

}
