package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    private WebDriver navegator;

    public ListaDeProdutosPage(WebDriver navegator){
        this.navegator = navegator;
    }

    public FormulariodeAdicaoDeProdutosPage acessarFormularioAdicaoProduto(){
        navegator.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        return new FormulariodeAdicaoDeProdutosPage(navegator);
    }

    public String capturarMensagemApresentada(){
        return navegator.findElement(By.cssSelector(".toast.rounded")).getText();
    }
    
    public ListaDeProdutosPage excluirItem(){
        navegator.findElement(By.xpath("/html/body/div[2]/div/ul/li[1]/a")).click();
        return this;
    }

    public String capturarTextPage(String text){
        return navegator.findElement(By.id("logo-container")).getText();
    }
}
