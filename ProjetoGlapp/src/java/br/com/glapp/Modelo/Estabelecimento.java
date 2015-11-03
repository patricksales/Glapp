package br.com.glapp.Modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Patrick
 */
@Entity
@DiscriminatorValue(value = "Estabelecimento")
@Singleton
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findBy.All", query = "SELECT E FROM Estabelecimento AS E"),
    @NamedQuery(name = "Estabelecimento.findBy.idEstabelecimento", query = "SELECT E FROM Estabelecimento AS E WHERE E.idEmpresa = :idEstabelecimento"),
    @NamedQuery(name = "Estabelecimento.findBy.nome", query = "SELECT E FROM Estabelecimento AS E WHERE E.nome LIKE :nome"),
    @NamedQuery(name = "Estabelecimento.findBy.cidade", query = "SELECT E FROM Estabelecimento AS E WHERE E.cidade LIKE :cidade"),
    @NamedQuery(name = "Estabelecimento.findBy.endereco", query = "SELECT E FROM Estabelecimento AS E WHERE  E.endereco LIKE :endereco"),
    @NamedQuery(name = "Estabelecimento.findBy.unidade", query = "SELECT E FROM Estabelecimento AS E WHERE E.unidade LIKE :unidade"),
    @NamedQuery(name = "Estabelecimento.findBy.proximidade", query = "SELECT NEW Estabelecimento(E, SQRT((((E.latitude - :latUsuario)*(E.latitude - :latUsuario)) + ((E.longitude - :longUsuario)*(E.longitude - :longUsuario)))) ) FROM Estabelecimento AS E where E.longitude IS NOT NULL AND E.latitude IS NOT NULL ORDER BY (((E.latitude - :latUsuario)*(E.latitude - :latUsuario)) + ((E.longitude - :longUsuario)*(E.longitude - :longUsuario)))"),})
/*@NamedQuery(name = "Empresa.findBy.localidade", query = "SELECT E , FROM Empresa AS E WHERE E.latitude IS NOT NULL AND E.longitude IS NOT NULL ORDER BY E")
 SELECT *
 ,SQRT(POWER([latitude]-(?), 2)+POWER([longitude]-(?), 2)) as distancia
 FROM [Glapp].[dbo].[Empresa]
 WHERE [latitude] is not null and [longitude] is not null
 order by [distancia]*/
public class Estabelecimento extends Empresa implements Serializable {

    private String unidade;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioAbertura;
    @Temporal(TemporalType.TIMESTAMP)
    private Date horarioFechamento;
    @ManyToMany(mappedBy = "estabelecimentos", fetch = FetchType.EAGER)
    private List<Produto> produtos;

    @Transient
    private Double distancia;

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

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Estabelecimento() {
    }

    public Estabelecimento(Estabelecimento estab, Double distancia) {
        super.setTelefone(estab.getTelefone());
        super.setSite(estab.getSite());
        super.setNome(estab.getNome());
        super.setLongitude(estab.getLongitude());
        super.setLatitude(estab.getLatitude());
        super.setIdEmpresa(estab.getIdEmpresa());
        super.setEstado(estab.getEstado());
        super.setEndereco(estab.getEndereco());
        super.setCidade(estab.getCidade());
        this.horarioAbertura = estab.getHorarioAbertura();
        this.horarioFechamento = estab.getHorarioFechamento();
        this.produtos = estab.getProdutos();        
        this.distancia = distancia;
        this.unidade = estab.getUnidade();
    }

}
