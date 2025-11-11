package system.dto;

import java.time.LocalDate;

public record EmprestimoDTO(int idEmprestimo, String titulo, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
}
