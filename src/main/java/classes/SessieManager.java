package classes;

import org.example.system42.ChatPaginaController;


public abstract class SessieManager {

    public void createSessie(){
        ChatPaginaController chatPaginaController = new ChatPaginaController();

        chatPaginaController.createNewSession();
    }
}
