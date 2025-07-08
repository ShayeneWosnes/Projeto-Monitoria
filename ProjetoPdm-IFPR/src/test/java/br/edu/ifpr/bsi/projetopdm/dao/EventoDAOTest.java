package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Evento;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventoDAOTest {

    @Test
    public void testSalvarEventos() {
        Evento evento1 = new Evento();
        evento1.setTitulo("Feira de Ciências");
        evento1.setDescricao("Feira de ciências com exposição de projetos.");
        evento1.setData(LocalDate.parse("2025-09-15")); // ✅ CORRIGIDO
        evento1.setLocal("IFPR - Auditório");

        Evento evento2 = new Evento();
        evento2.setTitulo("Semana da Tecnologia");
        evento2.setDescricao("Palestras e workshops sobre inovação e tecnologia.");
        evento2.setData(LocalDate.parse("2025-10-20")); // ✅ CORRIGIDO
        evento2.setLocal("IFPR - Bloco D, Sala D13");

        EventoDAO dao = new EventoDAO();
        dao.salvar(evento1);
        dao.salvar(evento2);

        assertNotNull(evento1.getId());
        assertNotNull(evento2.getId());

        System.out.println("Eventos salvos com sucesso. IDs: " + evento1.getId() + ", " + evento2.getId());
    }

    @Test
    public void testBuscarPorTitulo() {
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.buscarPorTitulo("Feira de Ciências"); // Ajuste o título conforme salvo

        assertNotNull(evento);
        System.out.println("Evento encontrado: " + evento.getTitulo() + ", Data: " + evento.getData());
    }

    @Test
    public void testListarEventos() {
        EventoDAO dao = new EventoDAO();
        List<Evento> lista = dao.listar();

        assertNotNull(lista);
        assertFalse(lista.isEmpty());

        for (Evento e : lista) {
            System.out.println("ID: " + e.getId() +
                    " - Título: " + e.getTitulo() +
                    " - Data: " + e.getData() +
                    " - Local: " + e.getLocal());
        }
    }

    @Test
    public void testRemoverEvento() {
        EventoDAO dao = new EventoDAO();
        Evento evento = dao.buscarPorTitulo("Semana da Tecnologia"); // Ajuste conforme salvo

        assertNotNull(evento);

        dao.remover(evento);

        Evento removido = dao.buscarPorTitulo("Semana da Tecnologia");
        assertNull(removido);

        System.out.println("Evento removido com sucesso.");
    }
}
