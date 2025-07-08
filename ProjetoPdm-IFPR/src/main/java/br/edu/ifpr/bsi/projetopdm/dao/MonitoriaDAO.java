package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.helpers.HibernateHelper;
import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MonitoriaDAO extends GenericDAO<Monitoria> {

    public MonitoriaDAO() {
        super();
    }

    public Monitoria buscarPorTitulo(String titulo) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        Monitoria monitoria = null;

        try {
            String hql = "FROM Monitoria m WHERE m.titulo = :titulo";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("titulo", titulo);
            monitoria = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return monitoria;
    }

    public List<Monitoria> listarPorAlunoId(Long alunoId) {
        Session session = HibernateHelper.getFabricaDeSessoes().openSession();
        List<Monitoria> lista = null;

        try {
            String hql = "SELECT m FROM Monitoria m JOIN m.alunosInscritos a WHERE a.id = :alunoId";
            Query<Monitoria> query = session.createQuery(hql, Monitoria.class);
            query.setParameter("alunoId", alunoId);
            lista = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lista;
    }
}
