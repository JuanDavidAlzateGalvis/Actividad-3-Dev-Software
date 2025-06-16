package co.edu.udec.taskmgr.infrastructure.repository;

import co.edu.udec.taskmgr.domain.entidades.Autor;
import co.edu.udec.taskmgr.domain.puertos.IAutorRepository;
import co.edu.udec.taskmgr.infrastructure.config.PersistenceManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorRepositoryImpl implements IAutorRepository {

    @Override
    public void save(Autor autor) {
        String sql = "INSERT INTO autor (nombre, correo, centro_trabajo, pais_origen, afiliacion_universitaria, experiencia_profesional, grado_academico, premios, asociaciones_profesionales, colaboraciones_previas, temas_investigacion) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getCorreo());
            stmt.setString(3, autor.getCentroTrabajo());
            stmt.setString(4, autor.getPaisOrigen());
            stmt.setString(5, autor.getAfiliacionUniversitaria());
            stmt.setString(6, autor.getExperienciaProfesional());
            stmt.setString(7, autor.getGradoAcademico());
            stmt.setString(8, autor.getPremios());
            stmt.setString(9, autor.getAsociacionesProfesionales());
            stmt.setString(10, autor.getColaboracionesPrevias());
            stmt.setString(11, autor.getTemasInvestigacion());

            stmt.executeUpdate();

            // Obtener ID generado
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    autor.setId(generatedKeys.getInt(1));
                }
            }

    } catch (SQLException e) {
        throw new RuntimeException("Error al guardar autor", e);
    }
}

    @Override
    public Autor findById(int id) {
        String sql = "SELECT * FROM autor WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapAutor(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar autor por ID", e);
        }
    }

    @Override
    public List<Autor> findAll() {
        String sql = "SELECT * FROM autor";
        List<Autor> lista = new ArrayList<>();

        try (Connection conn = PersistenceManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapAutor(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar autores", e);
        }

        return lista;
    }

    @Override
    public void update(Autor autor) {
        String sql = "UPDATE autor SET nombre = ?, correo = ?, centro_trabajo = ?, pais_origen = ?, afiliacion_universitaria = ?, experiencia_profesional = ?, grado_academico = ?, premios = ?, asociaciones_profesionales = ?, colaboraciones_previas = ?, temas_investigacion = ? WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, autor.getNombre());
            stmt.setString(2, autor.getCorreo());
            stmt.setString(3, autor.getCentroTrabajo());
            stmt.setString(4, autor.getPaisOrigen());
            stmt.setString(5, autor.getAfiliacionUniversitaria());
            stmt.setString(6, autor.getExperienciaProfesional());
            stmt.setString(7, autor.getGradoAcademico());
            stmt.setString(8, autor.getPremios());
            stmt.setString(9, autor.getAsociacionesProfesionales());
            stmt.setString(10, autor.getColaboracionesPrevias());
            stmt.setString(11, autor.getTemasInvestigacion());
            stmt.setInt(12, autor.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar autor", e);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM autor WHERE id = ?";

        try (Connection conn = PersistenceManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar autor", e);
        }
    }

    private Autor mapAutor(ResultSet rs) throws SQLException {
        Autor autor = new Autor(
            rs.getString("nombre"),
            rs.getString("correo"),
            rs.getString("centro_trabajo"),
            rs.getString("pais_origen"),
            rs.getString("afiliacion_universitaria"),
            rs.getString("experiencia_profesional"),
            rs.getString("grado_academico"),
            rs.getString("premios"),
            rs.getString("asociaciones_profesionales"),
            rs.getString("colaboraciones_previas"),
            rs.getString("temas_investigacion")
        );
        autor.setId(rs.getInt("id"));
        return autor;
    }
}
