package system.infrastructure.repository;

import system.domain.Emprestimos;
import system.domain.Livros;
import system.dto.EmprestimoDTO;
import system.infrastructure.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    public void RegistrarEmprestimo(Emprestimos emprestimo) throws SQLException {

        String query = "INSERT INTO emprestimos (livro_id,data_emprestimo) VALUES (?,?)";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, emprestimo.getIdLivro());
            stmt.setObject(2, LocalDate.now());
            stmt.executeUpdate();
        }

    }

    public void RegistrarDevolucao(int id) throws SQLException {

        String query = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setObject(1, LocalDate.now());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }

    }

    public int buscarEmprestimo(int id) throws SQLException {

        String query = """
                SELECT 
                id
                FROM emprestimos
                WHERE livro_id = ? and data_devolucao is null; 
                """;

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt("id");
            }
        }
        return 0;
    }

    public List<EmprestimoDTO> buscarEmprestimos() throws SQLException {

        List<EmprestimoDTO> emp = new ArrayList<>();

        String query = """
                SELECT emp.id , l.titulo, emp.data_emprestimo, emp.data_devolucao
                FROM emprestimos emp 
                JOIN livros l
                ON emp.livro_id = l.id
                """;

        try(Connection conn = Connect.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                LocalDate dataEmprestimo = rs.getObject("data_emprestimo", LocalDate.class);
                LocalDate dataDevolucao = rs.getObject("data_devolucao", LocalDate.class);

                var emprestimos = new EmprestimoDTO(id, titulo, dataEmprestimo, dataDevolucao);
                emp.add(emprestimos);
            }
        }

        return emp;
    }

}

