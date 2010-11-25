import models.Cargo;
import play.test.*;
import org.junit.*;

/*
O Model é testado através de testes funcionais
*/
public class CargosModel extends UnitTest {



    //Roda uma única vez ao inicializar a classe
    @BeforeClass
    public static void iniciar() {
        Fixtures.delete(Cargo.class);//Apaga todos os Cargos
        Fixtures.load("data.yml"); // Carrega os dados do arquivo
    }


    
    @Test
    public void inserirCargo() {
        Cargo c = new Cargo();
        c.nome = "Teste Programador";
        c.save();
        c = Cargo.find("nome = ?", "Teste Programador").first();
        assertNotNull(c);
    }

    @Test
    public void alterarCargo() {
        Cargo c = Cargo.find("nome = ?", "Teste Programador").first();
        c.nome = "Teste Programador Alterado";
        c.save();
        c = Cargo.find("nome = ?", "Teste Programador Alterado").first();
        assertNotNull(c);
    }

    @Test
    public void excluirCargo() {
        Cargo c = Cargo.find("nome = ?", "Teste Programador Alterado").first();
        assertNotNull(c);
        c.delete();
        c = Cargo.find("nome = ?", "Teste Programador Alterado").first();
        assertNull(c);
        
    }

}
