package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeEdicaoProdutosPage {
    private WebDriver navegator;

    public FormularioDeEdicaoProdutosPage(WebDriver navegator){
        this.navegator = navegator;
    }

    public String capturarMensagemApresentada(){
        return navegator.findElement(By.cssSelector(".toast.rounded")).getText();
    }

    public FormularioAdicaoComponentesPage acessarFormularioAdicaoComponentes(){
        navegator.findElement(By.linkText("ADICIONAR COMPONENTE")).click();
        return new FormularioAdicaoComponentesPage(navegator);
    }
}


