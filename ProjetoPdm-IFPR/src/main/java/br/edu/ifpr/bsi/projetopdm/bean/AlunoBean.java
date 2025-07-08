package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("alunoBean")
@SessionScoped
public class AlunoBean implements Serializable {

    private UsuarioSistema alunoLogado;
    private List<Monitoria> monitoriasDisponiveis;
    private List<Monitoria> minhasInscricoes;

    // Para Avaliação
    private Monitoria monitoriaSelecionada;
    private int notaAvaliacao;
    private String comentarioAvaliacao;

    @PostConstruct
    public void init() {
        this.alunoLogado = UsuarioSistemaSingleton.getInstance();
        this.monitoriasDisponiveis = new MonitoriaDAO().listar();
        this.minhasInscricoes = new ArrayList<>();
    }

    public UsuarioSistema getAlunoLogado() {
        return alunoLogado;
    }

    public List<Monitoria> getMonitoriasDisponiveis() {
        return monitoriasDisponiveis;
    }

    public List<Monitoria> getMinhasInscricoes() {
        return minhasInscricoes;
    }

    // Método para inscrever
    public void inscreverEmMonitoria(Monitoria m) {
        if (!minhasInscricoes.contains(m)) {
            minhasInscricoes.add(m);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Inscrição realizada!", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Você já está inscrito nessa monitoria.", ""));
        }
    }

    // ------- BLOCO DE AVALIAÇÃO -------
    public Monitoria getMonitoriaSelecionada() {
        return monitoriaSelecionada;
    }

    public void setMonitoriaSelecionada(Monitoria monitoriaSelecionada) {
        this.monitoriaSelecionada = monitoriaSelecionada;
    }

    public int getNotaAvaliacao() {
        return notaAvaliacao;
    }

    public void setNotaAvaliacao(int notaAvaliacao) {
        this.notaAvaliacao = notaAvaliacao;
    }

    public String getComentarioAvaliacao() {
        return comentarioAvaliacao;
    }

    public void setComentarioAvaliacao(String comentarioAvaliacao) {
        this.comentarioAvaliacao = comentarioAvaliacao;
    }

    public void prepararAvaliacao(Monitoria m) {
        this.monitoriaSelecionada = m;
        this.notaAvaliacao = 5; // default
        this.comentarioAvaliacao = "";
    }

    public void avaliarMonitoria() {
        System.out.println("Monitoria avaliada: " + monitoriaSelecionada.getTitulo());
        System.out.println("Nota: " + notaAvaliacao);
        System.out.println("Comentário: " + comentarioAvaliacao);

        // Aqui faria o persist do objeto Avaliacao, se existir.
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Avaliação salva!", ""));
    }

}
