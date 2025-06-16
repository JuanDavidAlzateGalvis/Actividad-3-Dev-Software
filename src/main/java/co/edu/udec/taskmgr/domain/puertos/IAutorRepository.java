package co.edu.udec.taskmgr.domain.puertos;

import co.edu.udec.taskmgr.domain.entidades.Autor;
import java.util.List;

public interface IAutorRepository {
    void save(Autor autor);
    Autor findById(int id);
    List<Autor> findAll();
    void update(Autor autor);
    void deleteById(int id);
}