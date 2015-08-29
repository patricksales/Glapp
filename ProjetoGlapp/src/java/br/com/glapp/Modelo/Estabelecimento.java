package br.com.glapp.Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Patrick
 */
@Entity
@DiscriminatorValue(value = "Estabelecimento")
public class Estabelecimento extends Empresa implements Serializable {

    private String unidade;
    private Date horarioAbertura;
    private Date horarioFechamento;

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

    public Estabelecimento() {
    }

}
