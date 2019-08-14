package com.andrekunitz.money.api.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    private Long codigo;
    private String nome;
    @ManyToOne
    @JoinColumn(name = "codigo_estado")
    private Estado estado;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade estado = (Cidade) o;
        return Objects.equals(codigo, estado.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
