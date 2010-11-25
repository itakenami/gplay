package models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="analistas")
public class Analista extends Model{

    @Required(message="Nome é obrigatorio.")
    public String nome;
    
    @Required(message="Especialidade é obrigatorio.")
    public String especialidade;

    @ManyToOne(fetch=FetchType.LAZY)
    public Cargo cargo;

}
