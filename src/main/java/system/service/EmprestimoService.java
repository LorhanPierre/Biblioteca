package system.service;

import system.domain.Emprestimos;
import system.infrastructure.repository.EmprestimoRepository;
import system.infrastructure.repository.LivroRepository;

import java.util.Scanner;

public class EmprestimoService {

    static Scanner input = new Scanner(System.in);

    public void RegistrarEmprestimo(){
        System.out.print("""
                --------------------------------------
                    Área de Registro de Empréstimo
                --------------------------------------
                """);
        System.out.print("| Digite o Id do Livro que está sendo emprestado\n:");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("| Digite por quantos dias o livro vai ser emprestado\n:");
        int tempoDeEmprestimo = input.nextInt();
        input.nextLine();

        try{
            var emprestimo = new Emprestimos(id);
            var emprestimoRepository = new EmprestimoRepository();
            emprestimoRepository.RegistrarEmprestimo(emprestimo, tempoDeEmprestimo);

            System.out.println("Empréstimo registrado com sucesso!");

        }catch (Exception e){
            System.out.println("Erro ao tentar registrar emprestimo");
        }
    }

    public void RegistrarDevolucao(){

    }
}
