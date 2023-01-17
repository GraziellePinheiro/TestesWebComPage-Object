package paginas;

import java.lang.reflect.Constructor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class LoginPage {
    protected WebDriver navegator;

    public LoginPage(WebDriver navegator){
        this.navegator = navegator;
    }
    
    public LoginPage informarUsuario(String usuario){
        navegator.findElement(By.id("usuario")).sendKeys(usuario);
        return this;
    }

    public LoginPage informarSenha(String senha){
        navegator.findElement(By.name("senha")).sendKeys(senha);
        return this;
    }
    public ListaDeProdutosPage submeterFormulario(){
        navegator.findElement(By.name("action")).click();
        return new ListaDeProdutosPage(navegator);
    }

}
