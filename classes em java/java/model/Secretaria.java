package model;

import java.math.BigDecimal;

public class Secretaria extends Pessoa{
    private BigDecimal salario;
    private BigDecimal dataContracacao;
    private String pis;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getDataContracacao() {
        return dataContracacao;
    }

    public void setDataContracacao(BigDecimal dataContracacao) {
        this.dataContracacao = dataContracacao;
    }

    public String getPis() {
        return pis;
    }

    public void setPis(String pis) {
        this.pis = pis;
    }
}
