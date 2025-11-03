package system.infrastructure.repository;

import system.domain.Livros;
import system.infrastructure.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public static void ConsultarTodosLivros(){

    }

    public static void AtualizarStatusLivro(){

    }

}
