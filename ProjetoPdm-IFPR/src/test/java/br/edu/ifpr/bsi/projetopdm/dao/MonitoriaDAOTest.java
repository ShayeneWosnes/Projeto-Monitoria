package br.edu.ifpr.bsi.projetopdm.dao;

import br.edu.ifpr.bsi.projetopdm.model.Monitoria;
import br.edu.ifpr.bsi.projetopdm.model.UsuarioSistema;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MonitoriaDAOTest {

    @Test
    public void testSalvarMonitoria() {
        MonitoriaDAO dao = new MonitoriaDAO();
        UsuarioSistemaDAO usuarioDao = new UsuarioSistemaDAO();

        // ✅ Busca o UsuarioSistema do banco, gerenciado!
        UsuarioSistema monitor = usuarioDao.buscarPorId(1L);
        assertNotNull(monitor, "UsuarioSistema com ID 1 não existe!");

        // Cria a primeira monitoria
        Monitoria monitoria1 = new Monitoria();
        monitoria1.setTitulo("Monitoria de POO");
        monitoria1.setDescricao("Programação Orientada a Objetos em Java");
        monitoria1.setDataHora("2025-03-25");
        monitoria1.setLocal("IFPR");
        monitoria1.setCertificado("certificado_poo.pdf");
        monitoria1.setUsuarioSistema(monitor);

        // Cria a segunda monitoria
        Monitoria monitoria2 = new Monitoria();
        monitoria2.setTitulo("Monitoria de WEB");
        monitoria2.setDescricao("HTML, CSS e JS");
        monitoria2.setDataHora("2025-01-23");
        monitoria2.setLocal("IFPR");
        monitoria2.setCertificado("certificado_web.pdf");
        monitoria2.setUsuarioSistema(monitor);

        dao.salvar(monitoria1);
        dao.salvar(monitoria2);

        assertNotNull(monitoria1.getId(), "ID da monitoria1 não gerado");
        assertNotNull(monitoria2.getId(), "ID da monitoria2 não gerado");

        System.out.println("Monitoria 1 salva com ID: " + monitoria1.getId());
        System.out.println("Monitoria 2 salva com ID: " + monitoria2.getId());
    }

    @Test
    public void testBuscarPorTitulo() {
        MonitoriaDAO dao = new MonitoriaDAO();

        // Busca monitoria salva
        Monitoria monitoria = dao.buscarPorTitulo("Monitoria de POO");

        assertNotNull(monitoria, "Monitoria não encontrada pelo título");
        assertEquals("Monitoria de POO", monitoria.getTitulo());

        System.out.println("ID: " + monitoria.getId());
        System.out.println("Título: " + monitoria.getTitulo());
    }

    @Test
    public void testAlterarMonitoria() {
        MonitoriaDAO dao = new MonitoriaDAO();

        // Busca monitoria existente
        Monitoria monitoria = dao.buscarPorTitulo("Monitoria de POO");
        assertNotNull(monitoria, "Monitoria para alteração não encontrada");

        monitoria.setDescricao("Design Patterns em Java");
        dao.salvarOuAtualizar(monitoria); // ✅ Usa GenericDAO

        Monitoria atualizado = dao.buscarPorId(monitoria.getId());

        assertNotNull(atualizado);
        assertEquals("Design Patterns em Java", atualizado.getDescricao());

        System.out.println("\n--- MONITORIA ATUALIZADA ---");
        System.out.println("ID: " + atualizado.getId());
        System.out.println("Título: " + atualizado.getTitulo());
        System.out.println("Nova Descrição: " + atualizado.getDescricao());
    }

    @Test
    public void testListarMonitorias() {
        MonitoriaDAO dao = new MonitoriaDAO();
        List<Monitoria> lista = dao.listar();

        assertFalse(lista.isEmpty(), "Nenhuma monitoria encontrada no banco");

        System.out.println("\n--- LISTA DE MONITORIAS ---");
        for (Monitoria m : lista) {
            System.out.println("ID: " + m.getId() + " - Título: " + m.getTitulo());
        }
    }

    @Test
    public void testRemoverPorId() {
        MonitoriaDAO dao = new MonitoriaDAO();

        Long idParaRemover = 3L; // Ajuste para o ID real

        Monitoria monitoria = dao.buscarPorId(idParaRemover);
        assertNotNull(monitoria, "Monitoria não encontrada para remoção");

        dao.remover(monitoria);

        Monitoria removida = dao.buscarPorId(idParaRemover);
        assertNull(removida, "Monitoria não foi removida");

        System.out.println("Monitoria removida com sucesso (ID: " + idParaRemover + ")");
    }
}
