package system.service;

import system.domain.Livros;
import system.infrastructure.repository.LivroRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LivroService {

    static Scanner input = new Scanner(System.in);

    public void CadastrarLivro(){

        System.out.print("""
                --------------------------------------
                       Área de Cadastro de Livros
                --------------------------------------
                """);

        System.out.print("| Digite o Titulo do Livro\n:");
        String titulo = input.nextLine();

        System.out.print("| Digite o Autor do Livro\n:");
        String autor = input.nextLine();

        System.out.print("| Digite o ano do Livro\n:");
        int ano = input.nextInt();
        input.nextLine();

        try{
            var livros  = new Livros(titulo,autor,ano);
            var livroRepository = new LivroRepository();

            livroRepository.InserirLivro(livros);
            System.out.println("| Livro cadastrado com sucesso!");
        }catch (Exception e){
            System.out.println("| Erro ao cadastrar livro!");
        }
    }

    public void ConsultarLivrosCadastrados() throws SQLException {

        var livrosRepository = new LivroRepository();

        List<Livros> livrosCadastrados = livrosRepository.buscarLivros();

        System.out.print("""
                --------------------------------------
                          Consulta de Livros
                --------------------------------------
                """);

        for (Livros livro : livrosCadastrados) {
            System.out.println("| id: " + livro.getIdLivro());
            System.out.println("| Titulo do Livro: " + livro.getTitulo());
            System.out.println("| Autor do Livro: " + livro.getAutor());
            System.out.println("| ano do Livro: " + livro.getAno());
            if(livro.isDisponivel()){
                System.out.println("| Disponibilidade: Disponivel");
            }else{
                System.out.println("| Disponibilidade: Indisponivel");
            }
            System.out.println("-------------------------------------");
        }
    }

}
