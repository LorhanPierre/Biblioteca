package system.view;

import system.service.EmprestimoService;
import system.service.LivroService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaView {

    static Scanner input = new Scanner(System.in);

    public void mostrarMenu(){
        System.out.print("""
                --------------------------------------
                             Bibliotech
                --------------------------------------
                1 - Cadastrar Livro
                2 - Consultar Livros
                3 - Registrar Empréstimo de Livro
                4 - Registrar Devolução de Livro
                
                0 - sair
                --------------------------------------
                """);
    }

    public int capturarOpcao(){
        System.out.print("| Escolha uma opção: ");

        int opcao = 0 ;
        boolean OpcaoValida = false;

        while (!OpcaoValida){
            try{
                opcao = input.nextInt();
                OpcaoValida = true;
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.print("\n# Você digitou um valor errado! Tente um número!\n:");
                input.nextLine();
            }
        }

        switch (opcao){
            case 1 ->{
                var livro = new LivroService();
                livro.CadastrarLivro();
            }
            case 2 ->{}
            case 3 ->{
                var emprestimo = new EmprestimoService();
                emprestimo.RegistrarEmprestimo();
            }
            case 4 ->{}
            case 0 ->{
                System.out.println("\nObrigado por usar o Bibliotech!");
            }
            default ->{
                System.out.println("\n# Não existe a opção escolhida! Tente Novamente!\n");
            }
        }

        return opcao;
    }
}
