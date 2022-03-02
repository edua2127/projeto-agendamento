package model;

import java.math.BigDecimal;

public class Convenio extends AbstractEntity{
    private String nome;
    private BigDecimal custo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal custo) {
        this.custo = custo;
    }
}
