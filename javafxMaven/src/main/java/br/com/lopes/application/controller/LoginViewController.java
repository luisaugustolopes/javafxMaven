package br.com.lopes.application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.lopes.application.MainApp;
import br.com.lopes.dao.UsuarioDao;
import br.com.lopes.exception.LoginException;


public class LoginViewController implements Initializable{

	private static final Logger log = LoggerFactory.getLogger(LoginViewController.class);
	
    @FXML
    private TextField textFieldUsuario;
    
    @FXML
    private PasswordField textFieldSenha;

	
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void btnEntrarClick(ActionEvent event){
		
		String login = textFieldUsuario.getText();
		String senha = textFieldSenha.getText();
		
		if (!login.isEmpty() && !senha.isEmpty()){
		
			MainApp.SCENE.setCursor(Cursor.WAIT);
			
			try {
				
				log.debug("Usuário ["+login+"] tentando efetuar logon no sistema");
				logarNoSistema(login, senha);
				log.debug("Usuário ["+login+"] conectou no sistema");
				
				MenuController menu = new MenuController();
				menu.carregarMenu();
				
				
			} catch (LoginException e) {
				Dialogs.create()
						.title("Mensagem de erro")
						.masthead("Dados incorretos")
						.message("Usuário e senha inválidos")
						.showError();
			} finally{
				MainApp.SCENE.setCursor(Cursor.DEFAULT);
			}
		}
		
	}

	
    public boolean logarNoSistema(String login, String senha) throws LoginException{
    	UsuarioDao usuarioDao = new UsuarioDao();
    	return !usuarioDao.getByLoginSenha(login, senha).equals(null);    	
    }
	
	public void btnSairClick(ActionEvent actionEvent){
		Platform.exit();
	}


}
