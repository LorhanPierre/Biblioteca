package system.service;

import system.domain.Emprestimos;
import system.dto.EmprestimoDTO;
import system.infrastructure.Connect;
import system.infrastructure.repository.EmprestimoRepository;
import system.infrastructure.repository.LivroRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmprestimoService {

    static Scanner input = new Scanner(System.in);

    public void RegistrarEmprestimo() {
        System.out.print("""
                --------------------------------------
                    Área de Registro de Empréstimo
                --------------------------------------
                """);
        System.out.print("| Digite o Id do Livro que está sendo emprestado\n:");
        int id = input.nextInt();
        input.nextLine();

        try {
            var emprestimo = new Emprestimos(id);
            var emprestimoRepository = new EmprestimoRepository();
            var livroRepository = new LivroRepository();
            emprestimoRepository.RegistrarEmprestimo(emprestimo);
            livroRepository.AtualizarStatusLivroIndisponivel(id);

            System.out.println("Empréstimo registrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao tentar registrar emprestimo");
        }
    }

    public void RegistarDevolucao() throws SQLException {

        System.out.print("""
                --------------------------------------
                    Área de Registro de Devolução
                --------------------------------------
                """);
        System.out.print("| Digite o Id do Livro que está devolvendo\n:");
        int id = input.nextInt();
        input.nextLine();

        try{
            var emprestimoRepository = new EmprestimoRepository();
            emprestimoRepository.RegistrarDevolucao(emprestimoRepository.buscarEmprestimo(id));
            var livroRepository = new LivroRepository();
            livroRepository.AtualizarStatusLivroDisponivel(id);

            System.out.println("| Devolução registrado com sucesso!");
        }catch (Exception e){

        }
    }

    public void consultarEmprestimos() throws SQLException {

        var emprestimoRepository = new EmprestimoRepository();
        List<EmprestimoDTO> emp =  emprestimoRepository.buscarEmprestimos();

        for (EmprestimoDTO emprestimo : emp){
            System.out.println("| id Emprestimo: "+emprestimo.idEmprestimo());
            System.out.println("| Livro Emprestado: "+emprestimo.titulo());
            System.out.println("| Data do Emprestimo: "+emprestimo.dataEmprestimo());
            if(emprestimo.dataDevolucao() == null){
                System.out.println("!Livro em Emprestimo!");

            }else {
                System.out.println("| Livro Devolvido em: "+emprestimo.dataDevolucao());
            }
            System.out.println("-------------------------------------");
        }
    }
}


