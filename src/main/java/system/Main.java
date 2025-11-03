package system;

import system.view.BibliotecaView;

public class Main {
    public static void main(String[] args) {

        BibliotecaView menu = new BibliotecaView();

        do{
            menu.mostrarMenu();
        }while(menu.capturarOpcao() != 0);
    }
}