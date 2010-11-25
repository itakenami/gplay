package controllers;

import java.util.List;
import models.Cargo;
import play.mvc.Controller;

public class Cargos extends DefaultController {

    public static void index() {
        List<Cargo> cargos = Cargo.findAll();
        render(cargos);
        /*
        renderArgs.put("cargos", Cargo.findAll());
        render();
         */
    }
    

    public static void form(Long id) {
        if (id != null) {
            Cargo cargo = Cargo.findById(id);
            if (cargo != null) {
                render(cargo);
            } else {
                flash.success("Registro não encontrado.");
                index();
            }
        } else {
            render();
        }
    }

    public static void save(Long id, Cargo cargoVO) {

        Cargo cargo;

        //Verifica se é novo ou atualização
        if (id == null) {
            cargo = cargoVO;
            cargoVO = null;
        } else {
            cargo = Cargo.findById(id);
            if(cargo != null){
                cargo.nome = cargoVO.nome;
            }else{
                flash.success("Registro não encontrado.");
                index();
            }
        }

        // Validação dos Campo
        validation.valid(cargo);
        if (validation.hasErrors()) {
            render("@form", cargo);
        }

        cargo.save();
        flash.success("Registro salvo com sucesso.");
        index();

    }

    public static void delete(Long id) {
        try {
            Cargo cargo = Cargo.findById(id);
            cargo.delete();
            flash.success("Registro apagado com sucesso.");
        } catch (Exception ex) {
            flash.success("Erro ao apagar registro.");
        }
        index();
    }
}
