import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import play.test.*;
import play.mvc.Http.*;


/*
 * Testes funcionais são utilizados para testar o Controlador. Isto é utilizado quando
 * o controlador fornece serviços. Caso seja fornecido Metodos para Tela melhor utilizar Selenium
*/
public class CargosController extends FunctionalTest {

    @Test
    public void inicio() {
        Response response = GET("/");
        assertContentMatch("Projetos", response);//Verifica se a resposta para a requisição possui o seguinte conteúdo
    }

    @Test
    public void inserirCargo() {

        Map<String, String> param = new HashMap<String, String>();
        Map<String, File> file = new HashMap<String, File>();
        
        param.put("cargoVO.nome", "Programador Con");
        Response response = POST("/cargos/save", param, file);
        
        assertStatus(302, response);

        
    }

    
}