package system.infrastructure.repository;

import system.domain.Emprestimos;
import system.infrastructure.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmprestimoRepository {

    public void RegistrarEmprestimo(Emprestimos emprestimo, int tempoDevolucao) throws SQLException {

        String query = "INSERT INTO emprestimos (livro_id,data_emprestimo,data_devolucao) VALUES (?,?,?)";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getIdLivro());
            stmt.setObject(2, LocalDate.now());
            stmt.setObject(3, LocalDate.now().plusDays(tempoDevolucao));
            stmt.executeUpdate();
        }

    }

    public void RegistrarDevolucao(){

    }
}
