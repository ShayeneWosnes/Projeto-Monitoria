package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.AvaliacaoDAO;
import br.edu.ifpr.bsi.projetopdm.model.Avaliacao;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named("avaliacaoBean")
@SessionScoped
public class AvaliacaoBean implements Serializable {

    private Avaliacao avaliacao = new Avaliacao();
    private List<Avaliacao> minhasAvaliacoes = new ArrayList<>();

    @PostConstruct
    public void init() {
        UsuarioSistema aluno = UsuarioSistemaSingleton.getInstance();
        buscarMinhasAvaliacoes(aluno);
    }

    public void salvarAvaliacao(Monitoria monitoriaSelecionada) {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setAluno(UsuarioSistemaSingleton.getInstance());
        novaAvaliacao.setMonitoria(monitoriaSelecionada);

        novaAvaliacao.setData(LocalDate.now()); // CORRIGIDO

        novaAvaliacao.setNota(avaliacao.getNota());

        AvaliacaoDAO dao = new AvaliacaoDAO();
        dao.salvar(novaAvaliacao);

        buscarMinhasAvaliacoes(UsuarioSistemaSingleton.getInstance());

        avaliacao = new Avaliacao();
    }

    public void buscarMinhasAvaliacoes(UsuarioSistema aluno) {
        AvaliacaoDAO dao = new AvaliacaoDAO();
        minhasAvaliacoes = dao.buscarAvaliacoesPorAluno(aluno);
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Avaliacao> getMinhasAvaliacoes() {
        return minhasAvaliacoes;
    }

    public void setMinhasAvaliacoes(List<Avaliacao> minhasAvaliacoes) {
        this.minhasAvaliacoes = minhasAvaliacoes;
    }
}
