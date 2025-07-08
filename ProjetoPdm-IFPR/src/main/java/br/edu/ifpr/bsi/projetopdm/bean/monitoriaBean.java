package br.edu.ifpr.bsi.projetopdm.bean;

import br.edu.ifpr.bsi.projetopdm.dao.MonitoriaDAO;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import br.edu.ifpr.bsi.projetopdm.singleton.UsuarioSistemaSingleton;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("monitoriaBean")
@RequestScoped
public class monitoriaBean {

    private Monitoria monitoria = new Monitoria();

    public Monitoria getMonitoria() {
        return monitoria;
    }

    public void setMonitoria(Monitoria monitoria) {
        this.monitoria = monitoria;
    }

    public String salvar() {
        try {
            UsuarioSistema monitor = UsuarioSistemaSingleton.getInstance();
            monitoria.setUsuarioSistema(monitor);

            MonitoriaDAO dao = new MonitoriaDAO();
            dao.salvar(monitoria);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Monitoria salva com sucesso!", ""));

            return "monitor.xhtml?faces-redirect=true";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Erro ao salvar monitoria: " + e.getMessage(), ""));
            return null;
        }
    }
}
