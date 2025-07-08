package br.edu.ifpr.bsi.projetopdm.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_monitoria")
public class Monitoria extends GenericModel {

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private String dataHora;

    @Column
    private String local;

    @Column
    private String certificado;

    @ManyToOne
    @JoinColumn(name = "usuario_sistema_id")
    private UsuarioSistema usuarioSistema;

    @ManyToMany
    @JoinTable(
            name = "monitoria_alunos",
            joinColumns = @JoinColumn(name = "monitoria_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<UsuarioSistema> alunosInscritos = new ArrayList<>();

    // Getters e Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public List<UsuarioSistema> getAlunosInscritos() {
        return alunosInscritos;
    }

    public void setAlunosInscritos(List<UsuarioSistema> alunosInscritos) {
        this.alunosInscritos = alunosInscritos;
    }
}
