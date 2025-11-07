package system;

import system.view.BibliotecaView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        BibliotecaView menu = new BibliotecaView();

        do{
            menu.mostrarMenu();
        }while(menu.capturarOpcao() != 0);
    }
}