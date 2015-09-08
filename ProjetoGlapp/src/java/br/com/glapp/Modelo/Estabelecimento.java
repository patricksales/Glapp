package br.com.glapp.Modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Patrick
 */
@Entity
@DiscriminatorValue(value = "Estabelecimento")
@Singleton
public class Estabelecimento extends Empresa implements Serializable {

    private String unidade;
    @Temporal(TemporalType.TIME)
    private Date horarioAbertura;
    @Temporal(TemporalType.TIME)
    private Date horarioFechamento;
    @ManyToMany(mappedBy = "estabelecimentos")
    private List<Produto> produtos;

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Date getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(Date horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public Date getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(Date horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Estabelecimento() {
    }

}
