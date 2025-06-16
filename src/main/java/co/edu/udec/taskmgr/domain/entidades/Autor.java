package co.edu.udec.taskmgr.domain.entidades;



public class Autor {

    private int id;
    private String nombre;
    private String correo;
    private String centroTrabajo;
    private String paisOrigen;
    private String afiliacionUniversitaria;
    private String experienciaProfesional;
    private String gradoAcademico;
    private String premios;
    private String asociacionesProfesionales;
    private String colaboracionesPrevias;
    private String temasInvestigacion;

    public Autor(String nombre, String correo, String centroTrabajo, String paisOrigen, String afiliacionUniversitaria,
                 String experienciaProfesional, String gradoAcademico, String premios,
                 String asociacionesProfesionales, String colaboracionesPrevias, String temasInvestigacion) {

        this.nombre = (nombre != null) ? nombre : "";
        this.correo = (correo != null) ? correo : "";
        this.centroTrabajo = (centroTrabajo != null) ? centroTrabajo : "";
        this.paisOrigen = (paisOrigen != null) ? paisOrigen : "";
        this.afiliacionUniversitaria = (afiliacionUniversitaria != null) ? afiliacionUniversitaria : "";
        this.experienciaProfesional = (experienciaProfesional != null) ? experienciaProfesional : "";
        this.gradoAcademico = (gradoAcademico != null) ? gradoAcademico : "";
        this.premios = (premios != null) ? premios : "";
        this.asociacionesProfesionales = (asociacionesProfesionales != null) ? asociacionesProfesionales : "";
        this.colaboracionesPrevias = (colaboracionesPrevias != null) ? colaboracionesPrevias : "";
        this.temasInvestigacion = (temasInvestigacion != null) ? temasInvestigacion : "";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getCentroTrabajo() { return centroTrabajo; }
    public String getPaisOrigen() { return paisOrigen; }
    public String getAfiliacionUniversitaria() { return afiliacionUniversitaria; }
    public String getExperienciaProfesional() { return experienciaProfesional; }
    public String getGradoAcademico() { return gradoAcademico; }
    public String getPremios() { return premios; }
    public String getAsociacionesProfesionales() { return asociacionesProfesionales; }
    public String getColaboracionesPrevias() { return colaboracionesPrevias; }
    public String getTemasInvestigacion() { return temasInvestigacion; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setCentroTrabajo(String centroTrabajo) { this.centroTrabajo = centroTrabajo; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }
    public void setAfiliacionUniversitaria(String afiliacionUniversitaria) { this.afiliacionUniversitaria = afiliacionUniversitaria; }
    public void setExperienciaProfesional(String experienciaProfesional) { this.experienciaProfesional = experienciaProfesional; }
    public void setGradoAcademico(String gradoAcademico) { this.gradoAcademico = gradoAcademico; }
    public void setPremios(String premios) { this.premios = premios; }
    public void setAsociacionesProfesionales(String asociacionesProfesionales) { this.asociacionesProfesionales = asociacionesProfesionales; }
    public void setColaboracionesPrevias(String colaboracionesPrevias) { this.colaboracionesPrevias = colaboracionesPrevias; }
    public void setTemasInvestigacion(String temasInvestigacion) { this.temasInvestigacion = temasInvestigacion; }
}