package system.service;

import system.domain.Livros;
import system.infrastructure.repository.LivroRepository;

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

        }catch (Exception e){

        }
    }

    public void ConsultarLivrosCadastrados(){

    }

}
