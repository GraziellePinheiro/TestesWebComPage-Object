package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormulariodeAdicaoDeProdutosPage {
    private WebDriver navegator;


    public FormulariodeAdicaoDeProdutosPage(WebDriver navegator){
        this.navegator = navegator;
    }

    public FormulariodeAdicaoDeProdutosPage informarNomeProduto(String produtoNome){
        navegator.findElement(By.id("produtonome")).sendKeys(produtoNome);
        return this;
    }

    public FormulariodeAdicaoDeProdutosPage informarValorProduto(String produtovalor){
        navegator.findElement(By.id("produtovalor")).sendKeys(produtovalor);
        return this;
    }
    
    public FormulariodeAdicaoDeProdutosPage informarCoresProduto(String produtoCores){
        navegator.findElement(By.id("produtocores")).sendKeys(produtoCores);
        return this;
    }
    public ListaDeProdutosPage submeterFormularioAdicaoErro(){
        navegator.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegator);
    }
    public FormularioDeEdicaoProdutosPage submeterFormularioComAcerto(){
        navegator.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioDeEdicaoProdutosPage(navegator);
    }
    
    

}
