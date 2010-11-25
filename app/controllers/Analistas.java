package controllers;

import java.util.List;
import models.Analista;
import models.Cargo;
import play.mvc.Controller;

public class Analistas extends DefaultController {

    public static void index(){
        List<Analista> analistas = Analista.findAll();
        render(analistas);
    }

    public static void form(Long id) {

        //Lista todos os cargos associados ao analistas
        List<Cargo> cargos = Cargo.findAll();

        if(id != null) {
            Analista analista = Analista.findById(id);
            if (analista != null) {
                render(analista,cargos);
            } else {
                flash.error("Registro não encontrado.");
                index();
            }
        }else{
            render(cargos);
        }
        
    }

    public static void save(Long id, Analista analistaVO){

        Analista analista;

        if(id == null){
            analista = analistaVO;
            analistaVO = null;
        }else{
            analista = Analista.findById(id);
            if(analista!=null){
                analista.nome = analistaVO.nome;
                analista.especialidade = analistaVO.especialidade;
                analista.cargo = analistaVO.cargo;
            }else{
                flash.success("Registro não encontrado.");
                index();
            }
        }

        // Validação
        validation.valid(analista);
        if(validation.hasErrors()) {
            List<Cargo> cargos = Cargo.findAll();
            render("@form", analista, cargos);
        }

        analista.save();
        flash.success("Registro salvo com sucesso.");
        index();

    }

    public static void delete(Long id){

        try {
            Analista analista = Analista.findById(id);
            analista.delete();
            flash.success("Registro apagado com sucesso.");
        } catch (Exception ex) {
            flash.success("Erro ao apagar registro.");
        }
        index();

    }
}
