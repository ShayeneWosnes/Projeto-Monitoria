package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.dao.EventoDAO;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.Evento;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("monitorBean")
@SessionScoped
public class MonitorBean implements Serializable {

    private List<Monitoria> monitoriasInscritas;
    private List<Evento> eventosParticipando;

    @PostConstruct
    public void carregarMonitoriasEEventosDoMonitor() {
        UsuarioSistema monitorLogado = UsuarioSistemaSingleton.getInstance();
        if (monitorLogado != null) {
            MonitoriaDAO monitoriaDAO = new MonitoriaDAO();
            EventoDAO eventoDAO = new EventoDAO();
            this.monitoriasInscritas = monitoriaDAO.listarPorAlunoId(monitorLogado.getId());
            this.eventosParticipando = eventoDAO.listarPorUsuarioId(monitorLogado.getId());
        }
    }

    public List<Monitoria> getMonitoriasInscritas() {
        return monitoriasInscritas;
    }

    public List<Evento> getEventosParticipando() {
        return eventosParticipando;
    }

    public UsuarioSistema getUsuarioLogado() {
        return UsuarioSistemaSingleton.getInstance();
    }
}
