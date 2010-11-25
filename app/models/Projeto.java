package models;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity(name="projetos")
public class Projeto extends Model{

    @Required(message="O campo Nome é obrigatório")
    public String nome;
    public String descricao;
    public Date data_inicio;
    public Date data_fim;

    @Required(message="Pelo menos 1 analista é obrigatório")
    @ManyToMany()
    @JoinTable(name="analistas_projetos", joinColumns={@JoinColumn(name="projeto_id")}, inverseJoinColumns={@JoinColumn(name="analista_id")})
    public Set<Analista> analistas;

    @OneToMany(mappedBy="projeto",cascade=CascadeType.ALL)
    public Set<Requisito> requisitos;


    public static List<Projeto> getProjetosLike(String like) {
        return Projeto.find(
                "SELECT p FROM projetos p WHERE p.nome like ?",like).fetch();
    }

    public static List<Projeto> getProjetosLike2(String like) {
        return Projeto.find(
                "SELECT p from projetos p WHERE p.nome like :nome").bind("nome", like).fetch();
    }
    
    
}
