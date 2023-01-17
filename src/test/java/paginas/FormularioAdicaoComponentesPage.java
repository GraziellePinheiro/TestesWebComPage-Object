package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoComponentesPage {
    protected WebDriver navegator;

    public FormularioAdicaoComponentesPage(WebDriver navegator){
        this.navegator = navegator;
    }

    public FormularioAdicaoComponentesPage informarNomeComponente(String nomeComponente){
        navegator.findElement(By.id("componentenomeadicionar")).sendKeys(nomeComponente);
        return this;
    }
    public FormularioAdicaoComponentesPage informarQuantComponente(String quantComponente){
        navegator.findElement(By.name("componentequantidadeadicionar")).sendKeys(quantComponente);
        return this;    
    }

    public FormularioDeEdicaoProdutosPage submeteAdicaoComponente(){
        navegator.findElement(By.linkText("SALVAR COMPONENTE")).click();
        return new FormularioDeEdicaoProdutosPage(navegator);
    }
    
    
}
