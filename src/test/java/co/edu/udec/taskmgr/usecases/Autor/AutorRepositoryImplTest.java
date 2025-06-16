package co.edu.udec.taskmgr.usecases.Autor;

import co.edu.udec.taskmgr.domain.entidades.Autor;
import co.edu.udec.taskmgr.domain.puertos.IAutorRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.AutorRepositoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class AutorRepositoryImplTest {

    private IAutorRepository repo;

    @Before
    public void setUp() {
        SQLiteDatabaseInitializer.initialize();
        repo = new AutorRepositoryImpl();
        limpiar(); // ← Garantiza base limpia antes de cada test
    }

    @After
    public void tearDown() {
        limpiar(); // ← Limpieza adicional por si el orden de ejecución lo requiere
    }

    private void limpiar() {
        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM autor");
            System.out.println(">> Base de datos limpiada");
        } catch (Exception e) {
            throw new RuntimeException("Error al limpiar la base de datos", e);
        }
    }

    @Test
    public void testSave() {
        Autor a = new Autor("Juana", "juana@correo.com", "Uni Cartagena", "Colombia",
                "Fac. Ingeniería", "10 años", "PhD", "Premio nacional",
                "IEEE", "Colab. UNESCO", "IA y educación");

        repo.save(a);

        assertTrue(a.getId() > 0); // Confirmar que se asignó un ID
    }

    @Test
    public void testFindAll() {
        Autor a = new Autor("Ana", "ana@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);

        List<Autor> lista = repo.findAll();
        assertEquals(1, lista.size());
        assertEquals("Ana", lista.get(0).getNombre());
    }

    @Test
    public void testFindById() {
        Autor a = new Autor("Mario", "mario@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);

        Autor encontrado = repo.findById(a.getId());
        assertNotNull(encontrado);
        assertEquals("Mario", encontrado.getNombre());
    }

    @Test
    public void testUpdate() {
        Autor a = new Autor("Carlos", "c@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);

        Autor original = repo.findById(a.getId());
        original.setNombre("Carlos Mejia");
        original.setGradoAcademico("Magister");

        repo.update(original);

        Autor actualizado = repo.findById(original.getId());
        assertEquals("Carlos Mejia", actualizado.getNombre());
        assertEquals("Magister", actualizado.getGradoAcademico());
    }

    @Test
    public void testDelete() {
        Autor a = new Autor("Luis", "l@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);

        int id = a.getId();
        repo.deleteById(id);

        Autor eliminado = repo.findById(id);
        assertNull(eliminado);
    }
}