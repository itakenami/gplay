package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="requisitos")
public class Requisito  extends Model{

    @Required(message="O campo Nome é obrigatório")
    public String nome;
    
    public String descricao;

    @ManyToOne(fetch=FetchType.LAZY)
    public Projeto projeto;

}
