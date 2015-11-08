package br.com.glapp.Modelo;

import java.io.Serializable;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Patrick
 */
@Entity
@Singleton
@NamedQueries({
    @NamedQuery(name = "Produto.findBy.All", query = "SELECT P FROM Produto AS P"),
    @NamedQuery(name = "Produto.findBy.idProduto", query = "SELECT P FROM Produto AS P WHERE P.idProduto = :idProduto"),
    @NamedQuery(name = "Produto.findBy.nome", query = "SELECT P FROM Produto AS P WHERE P.nome LIKE :nome"),
    @NamedQuery(name = "Produto.findBy.codigoEAN", query = "SELECT P FROM Produto AS P WHERE P.codigoEAN LIKE :codigoEAN"),
    @NamedQuery(name = "Produto.findBy.estabelecimento.nome", query = "SELECT P FROM Produto AS P INNER JOIN P.estabelecimentos AS EST WHERE  EST.nome LIKE :estabelecimento")})
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduto;
    private String nome;
    private String marca;
    private String ingredientes;
    private String codigoEAN;
    private String qrCode;
    private boolean contemGluten;
    private boolean contemLactose;
    private double pesoQuant;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "produto_estabelecimento", joinColumns = @JoinColumn(name = "produto_idProduto"),
            inverseJoinColumns = @JoinColumn(name = "estabelecimento_idEmpresa"))
    @Fetch(FetchMode.SUBSELECT)    
    private List<Empresa> estabelecimentos;
    @OneToOne
    private TipoProduto tipoProduto;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCodigoEAN() {
        return codigoEAN;
    }

    public void setCodigoEAN(String codigoEAN) {
        this.codigoEAN = codigoEAN;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isContemGluten() {
        return contemGluten;
    }

    public void setContemGluten(boolean contemGluten) {
        this.contemGluten = contemGluten;
    }

    public boolean isContemLactose() {
        return contemLactose;
    }

    public void setContemLactose(boolean contemLactose) {
        this.contemLactose = contemLactose;
    }

    public double getPesoQuant() {
        return pesoQuant;
    }

    public void setPesoQuant(double pesoQuant) {
        this.pesoQuant = pesoQuant;
    }

    public List<Empresa> getEstabelecimento() {
        return estabelecimentos;
    }

    public void setEstabelecimento(List<Empresa> estabelecimentos) {
        this.estabelecimentos = estabelecimentos;
    }

    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

}
