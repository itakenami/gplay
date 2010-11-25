package controllers;

import java.util.List;
import models.Analista;
import models.Projeto;
import models.Requisito;
import net.sf.oval.internal.util.LinkedSet;
import play.mvc.Controller;

public class Projetos extends DefaultController {

    public static void index(){
        List<Projeto> projetos = Projeto.findAll();
        render(projetos);
    }

    public static void busca(String projeto){
        List<Projeto> projetos = Projeto.getProjetosLike(projeto);
        render(projetos);
    }

    public static void view(Long id) {
        if(id != null) {
            Projeto projeto = Projeto.findById(id);
            if(projeto!=null){
                render(projeto);
            }else{
                flash.error("Projeto não encontrado.");
                index();
            }
        }else{
            flash.error("É necessário informar um projeto.");
            index();
        }
        
    }

    public static void form(Long id) {

        //Lista de Analistas que podem participar do projeto
        List<Analista> analistas = Analista.findAll();

        if(id != null) {
            Projeto projeto = Projeto.findById(id);
            if(projeto!=null){
                render(projeto,analistas);
            }else{
                flash.error("Registro não encontrado.");
                index();
            }
            
        }else{
            render(analistas);
        }
        
    }

    public static void save(Long id, Projeto projetoVO){

        Projeto projeto;

        if(id==null){

            projeto = projetoVO;
            projetoVO = null;

            //Verifica os requisitos criados
            String[] nomes = params.getAll("req.nome");
            String[] descs = params.getAll("req.descricao");

            projeto.requisitos = new LinkedSet<Requisito>();

            for(int x=0;x<nomes.length;x++){
                if(!nomes[x].equals("")){
                    Requisito r = new Requisito();
                    r.nome = nomes[x];
                    r.descricao = descs[x];
                    r.projeto = projeto;
                    projeto.requisitos.add(r);
                }
            }

        }else{

            projeto = Projeto.findById(id);
            if(projeto!=null){
                projeto.nome = projetoVO.nome;
                projeto.descricao = projetoVO.descricao;
                projeto.data_inicio = projetoVO.data_inicio;
                projeto.data_fim = projetoVO.data_fim;
                projeto.analistas = projetoVO.analistas;
            }else{
                flash.success("Registro não encontrado.");
                index();
            }

        }

        // Validação
        validation.valid(projeto);
        if(validation.hasErrors()) {
            List<Analista> analistas = Analista.findAll();
            render("@form", projeto, analistas);
        }

        projeto.save();
        flash.success("Registro salvo com sucesso.");
        index();

    }

    public static void delete(Long id){
        try {
            Projeto projeto = Projeto.findById(id);
            projeto.delete();
            flash.success("Registro apagado com sucesso.");
        } catch (Exception ex) {
            flash.success("Erro ao apagar registro.");
        }
        index();

    }

    //Metodos para o srequisitos associados ao projeto
    public static void formRequisitos(Long projeto_id, Long id) {
        if(id == null) {
            //Captura o projeto que será associado ao requisito
            Projeto projeto = Projeto.findById(projeto_id);
            render(projeto);
        }else{
            Requisito requisito = Requisito.findById(id);
            if(requisito!=null){
                Projeto projeto = requisito.projeto;
                render(requisito,projeto);
            }else{
                flash.success("Registro não encontrado.");
                index();
            }
        }
    }
    
    public static void saveRequisitos(Long id, Requisito requisitoVO){

        Requisito requisito;

        if(id==null){
            requisito = requisitoVO;
            requisitoVO = null;
        }else{
            requisito = Requisito.findById(id);
            if(requisito!=null){
                requisito.nome = requisitoVO.nome;
                requisito.descricao = requisitoVO.descricao;
            }else{
                flash.success("Registro não encontrado.");
                index();
            }
        }

        // Validação
        validation.valid(requisito);
        if(validation.hasErrors()) {
            Projeto projeto = Projeto.findById(requisito.projeto.id);
            render("@formRequisitos", requisito, projeto);
        }

        requisito.save();
        flash.success("Registro salvo com sucesso.");
        view(requisito.projeto.id);

    }

}
