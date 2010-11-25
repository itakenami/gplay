package models;

import play.db.jpa.*;

import javax.persistence.*;
import play.data.validation.CheckWith;
import play.data.validation.Required;
import validator.Quantidade;

@Entity(name="cargos")
public class Cargo extends Model{

    //Validação Server-Side
    @Required(message="O campo Nome é obrigatório")
    //Validação personalizada
    @CheckWith(value=Quantidade.class,message="O campo deve ter mais do que 3 carcateres")
    public String nome;

    
}
