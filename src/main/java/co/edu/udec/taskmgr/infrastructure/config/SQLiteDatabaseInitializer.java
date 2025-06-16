package co.edu.udec.taskmgr.infrastructure.config;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDatabaseInitializer {

    public static void initialize() {
        try (Connection conn = PersistenceManager.getConnection()) {
            Statement stmt = conn.createStatement();

            // Habilitar claves foráneas (importante en SQLite)
            stmt.execute("PRAGMA foreign_keys = ON;");

            // Crear tabla de usuarios
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS users (
                    email TEXT PRIMARY KEY,
                    password TEXT NOT NULL,
                    name TEXT NOT NULL,
                    role TEXT NOT NULL
                );
            """);

            // Crear tabla de tareas con clave foránea a users
            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    status TEXT NOT NULL,
                    user_email TEXT NOT NULL,
                    FOREIGN KEY(user_email) REFERENCES users(email)
                );
            """);
            stmt.executeUpdate("""
            CREATE TABLE IF NOT EXISTS autor (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            correo TEXT NOT NULL,
            centro_trabajo TEXT,
            pais_origen TEXT,
            afiliacion_universitaria TEXT,
            experiencia_profesional TEXT,
            grado_academico TEXT,
            premios TEXT,
            asociaciones_profesionales TEXT,
            colaboraciones_previas TEXT,
            temas_investigacion TEXT
            );
            """);

            } catch (SQLException e) {
                throw new RuntimeException("Error initializing database schema", e);
            }
    }
}
