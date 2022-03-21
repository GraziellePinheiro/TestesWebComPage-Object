package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do modulo de produtos")
public class ProdutosTest {
        private WebDriver navegador;
    @BeforeEach
    public void beforeEach() {
        // Abri o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver_win32 (1)\\chromedriver.exe");
        this.navegador = new ChromeDriver();
        //Maximizar a tela
        this.navegador.manage().window().maximize();
        //TIMEOUT
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // Navegar para pagina da lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }
    @Test
    @DisplayName("Não é permitido registrar produto com o valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {

        // Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Boneca")
                .informaValorDoProduto("000")
                .informaCoresDoProduto("Rosa, Azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar produto acima de 7.000,00")
    public void testNaoEPermeitidoRegistrarValoresAcimaDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Boneca")
                .informaValorDoProduto("750000")
                .informaCoresDoProduto("Rosa, Azul")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("posso adicionar produtos que estejam na faixa de 0 a 7.000,00")
    public void testPossoAdicionarProdutosQueEstejamEntreZeroESeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Boneca")
                .informaValorDoProduto("8990")
                .informaCoresDoProduto("Vermelho")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();


        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que tenham o valor de 7.000,00")
    public void testPossoAdicionarProdutosQueTenhamOValorDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarOFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Boneca")
                .informaValorDoProduto("700000")
                .informaCoresDoProduto("Branco")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();


        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        //Fechar Browser
        navegador.quit();
    }
}