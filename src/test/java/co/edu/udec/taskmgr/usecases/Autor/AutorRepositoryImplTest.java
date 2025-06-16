package co.edu.udec.taskmgr.usecases.Autor;

import co.edu.udec.taskmgr.domain.entidades.Autor;
import co.edu.udec.taskmgr.domain.puertos.IAutorRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;
import co.edu.udec.taskmgr.infrastructure.config.SQLiteDatabaseInitializer;
import co.edu.udec.taskmgr.infrastructure.repository.AutorRepositoryImpl;
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
        limpiar();
    }

    private void limpiar() {
        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DELETE FROM autor");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSaveAndFind() {
        Autor a = new Autor("Juana", "juana@correo.com", "Uni Cartagena", "Colombia",
                            "Fac. Ingeniería", "10 años", "PhD", "Premio nacional",
                            "IEEE", "Colab. UNESCO", "IA y educación");
        repo.save(a);

        List<Autor> lista = repo.findAll();
        assertEquals(1, lista.size());

        Autor encontrado = repo.findById(lista.get(0).getId());
        assertNotNull(encontrado);
        assertEquals("Juana", encontrado.getNombre());
    }

    @Test
    public void testUpdate() {
        Autor a = new Autor("Carlos", "c@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);
        Autor original = repo.findAll().get(0);
        original.setNombre("Carlos Mejia");
        original.setGradoAcademico("Magister");

        repo.update(original);
        Autor actualizado = repo.findById(original.getId());
        assertEquals("Carlos Mejia", actualizado.getNombre());
    }

    @Test
    public void testDelete() {
        Autor a = new Autor("Luis", "l@correo.com", "", "", "", "", "", "", "", "", "");
        repo.save(a);
        int id = repo.findAll().get(0).getId();
        repo.deleteById(id);
        assertNull(repo.findById(id));
    }
}
