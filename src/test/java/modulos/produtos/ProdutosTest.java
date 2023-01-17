package modulos.produtos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import paginas.FormulariodeAdicaoDeProdutosPage;
import paginas.LoginPage;





@DisplayName("Testes Web do modulo de produtos.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutosTest {
    protected WebDriver navegator;
    
    @BeforeEach
    public void beforeEach(){
        //Configurando e abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver109.0.5414.74.exe");
        this.navegator = new ChromeDriver();
         
        //Definindo uma espera de 5 segundos;
        this.navegator.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        navegator.manage().window().maximize();
        navegator.get("http://165.227.93.41/lojinha-web/v2/");
        navegator.getCurrentUrl();
    }

    @AfterEach
    public void AfterEach(){
        navegator.quit();
    }

    
    
    @Order(1)
    @DisplayName("Login sem sucesso Email e Senha Incorretos")
    @Test
    public void TC01testLoginSemSucessoEmaileSenhaIncorretos(){
        String msgToast = new LoginPage(navegator)
            .informarUsuario("aeiou")
            .informarSenha("aeiou")
            .submeterFormulario()
            .capturarMensagemApresentada();
        String msgEsperada = "Falha ao fazer o login";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(2)
    @DisplayName("Login sem sucesso campo Email vazio")
    @Test
    public void TC01testLoginSemSucessoEmailVazio(){
        String msgToast = new LoginPage(navegator)
            .informarUsuario("")
            .informarSenha("adm")
            .submeterFormulario()
            .capturarMensagemApresentada();
        String msgEsperada = "Falha ao fazer o login";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(3)
    @DisplayName("Login sem sucesso campo senha vazio")
    @Test
    public void TC01testLoginSemSucessoCampoSenhaVazio(){
        String msgToast = new LoginPage(navegator)
            .informarUsuario("adm")
            .informarSenha("")
            .submeterFormulario()
            .capturarMensagemApresentada();
        String msgEsperada = "Falha ao fazer o login";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(4)
    @DisplayName("Login com sucesso")
    @Test
    public void TC01testLoginComSucesso(){
        new LoginPage(navegator)
            .informarUsuario("adm")
            .informarSenha("adm")
            .submeterFormulario()
            .capturarTextPage("Lojinha");
    }

    @Order(6)
    @DisplayName("Adicionando produto com valor igual a zero")
    @Test
    public void TC0001testNaoSaoAceitosValoresZerados(){
        String msgToast = new LoginPage(navegator)
        .informarUsuario("admin")
        .informarSenha("admin")
        .submeterFormulario()
        .acessarFormularioAdicaoProduto()
        .informarNomeProduto("Computador")
        .informarValorProduto("0")
        .informarCoresProduto("Azul")
        .submeterFormularioAdicaoErro()
        .capturarMensagemApresentada();

        String msgEsperada = "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(7)
    @DisplayName("Adicionando produto com valor de Sete Mil e um")
    @Test
    public void TC0002testAdicionandoProdutoAcimaDeSeteMil(){
        String msgToast = new LoginPage(navegator)
        .informarUsuario("admin")
        .informarSenha("admin")
        .submeterFormulario()
        .acessarFormularioAdicaoProduto()
        .informarNomeProduto("Computador")
        .informarValorProduto("7000.01")
        .informarCoresProduto("Azul")
        .submeterFormularioAdicaoErro()
        .capturarMensagemApresentada();

        String msgEsperada = "O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(8)
    @DisplayName("Adicionando produto com valor de Sete Mil")
    @Test
    public void TC0003testAdicionandoProdutoNoValorDeSeteMil(){
        String msgToast = new LoginPage(navegator)
        .informarUsuario("admin")
        .informarSenha("admin")
        .submeterFormulario()
        .acessarFormularioAdicaoProduto()
        .informarNomeProduto("Computador")
        .informarValorProduto("7000.00")
        .informarCoresProduto("Azul")
        .submeterFormularioComAcerto()
        .capturarMensagemApresentada();

        String msgEsperada = "Produto adicionado com sucesso";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    @Order(9)
    @DisplayName("Adicionando produto com valor de Um centavo")
    @Test
    public void TC0004testAdicionandoProdutoNoValorUmCentavo(){
        String msgToast = new LoginPage(navegator)
        .informarUsuario("admin")
        .informarSenha("admin")
        .submeterFormulario()
        .acessarFormularioAdicaoProduto()
        .informarNomeProduto("Computador")
        .informarValorProduto("00.1")
        .informarCoresProduto("Azul")
        .submeterFormularioComAcerto()
        .capturarMensagemApresentada();

        String msgEsperada = "Produto adicionado com sucesso";
        Assertions.assertEquals(msgEsperada, msgToast);
    }

    // @Order(5)
    // @DisplayName("Adicionar um componente com sucesso")
    // @Test
    // public void TC0004testeAdicionandoComponentes(){
    //     String msgToast = new LoginPage(navegator)
    //     .informarUsuario("admin")
    //     .informarSenha("admin")
    //     .submeterFormulario()
    //     .acessarFormularioAdicaoProduto()
    //     .informarNomeProduto("Computador")
    //     .informarValorProduto("0001")
    //     .informarCoresProduto("Azul")
    //     .submeterFormularioComAcerto()
    //     .acessarFormularioAdicaoComponentes()
    //     .informarNomeComponente("Controle")
    //     .informarQuantComponente("1")
    //     .submeteAdicaoComponente()
    //     .capturarMensagemApresentada();

    //     String msgEsperada = "Componente de produto adicionado com sucesso";
    //     Assertions.assertEquals(msgEsperada, msgToast );
    // }

    @Order(10)
    @DisplayName("Excluir o primeiro item da lista")
    @Test
    public void TC0004testeExcluirPrimeiroItemDaLista(){
        String msgToast = new LoginPage(navegator)
        .informarUsuario("admin")
        .informarSenha("admin")
        .submeterFormulario()
        .excluirItem()
        .capturarMensagemApresentada();

        String msgEsperada = "Produto removido com sucesso";
        Assertions.assertEquals(msgEsperada, msgToast);
    }
}