package com.example.trabalhoiago.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contato")
public class Contato {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    private String apelido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_telefone")
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "id_email")
    private Email email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Telefone getTelefones() {
        return telefone;
    }

    public void setTelefones(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEnderecos() {
        return endereco;
    }

    public void setEnderecos(Endereco endereco) {
        this.endereco = endereco;
    }

    public Email getEmails() {
        return email;
    }

    public void setEmails(Email email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato)) return false;
        Contato contato = (Contato) o;
        return Objects.equals(getId(), contato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
