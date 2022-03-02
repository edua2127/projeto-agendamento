package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AbstractEntity {
    Long id;
    Timestamp cadastro;
    Timestamp atualizado;
    Timestamp excluido;

    //bigdecimal -> decimal( 8, 3)
    //localdate -> timestamp


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCadastro() {
        return cadastro;
    }

    public void setCadastro(Timestamp cadastro) {
        this.cadastro = cadastro;
    }

    public Timestamp getAtualizado() {
        return atualizado;
    }

    public void setAtualizado(Timestamp atualizado) {
        this.atualizado = atualizado;
    }

    public Timestamp getExcluido() {
        return excluido;
    }

    public void setExcluido(Timestamp excluido) {
        this.excluido = excluido;
    }
}
