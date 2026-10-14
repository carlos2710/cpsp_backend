package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request.SolicitudRequest;

import java.util.List;

public record SolicitudResponse(
        @Schema(description = "Datos generales de la solicitud")
        DatosPersonales datosPersonales,

        @Schema(description = "Datos de Vivienda del solicitante")
        Vivienda vivienda,

        @Schema(description =  "Datos de Contacto del solicitante")
        Contacto contacto,

        @Schema(description =  "Datos de Familia del solicitante")
        Familia familia,

        @Schema(description =  "Datos de Formacion Academica del solicitante")
        FormacionAcademica formacionAcademica,

        @Schema(description =  "Datos de PostGrado del solicitante")
        Postgrado postgrado,

        @Schema(description =  "Datos de Idiomas del solicitante")
        Idiomas idiomas,

        @Schema(description =  "Datos de Experiencia Laboral del solicitante")
        ExperienciaLaboral experienciaLaboral,

        @Schema(description =  "Datos de Actividades del solicitante")
        Actividades actividades,

        @Schema(description =  "Datos de Capacitacion del solicitante")
        Capacitacion capacitacion,

        @Schema(description =  "Datos de Documentos del solicitante")
        Documentos documentos,

        @Schema(description =  "Datos de Contacto del solicitante")
        EstadoSolicitud estadoSolicitud
) {

    // =========================================================
    // DATOS PERSONALES
    // =========================================================

    public record DatosPersonales(
            String apellidoPaterno,
            String apellidoMaterno,
            String primerNombre,
            String segundoNombre,
            Long tipoDocumento,
            String numeroDocumento
    ) {
    }

    // =========================================================
    // VIVIENDA
    // =========================================================

    public record Vivienda(
            Long departamento,
            Long provincia,
            Long distrito,
            String fechaNacimiento
    ) {
    }

    // =========================================================
    // CONTACTO
    // =========================================================

    public record Contacto(
            String correoPrincipal,
            String correoSecundario,
            String domicilio,
            String numero,
            String urbanizacion,
            String codigoPostal,
            String celularPrincipal,
            String celularSecundario,
            String telefonoFijo
    ) {
    }

    // =========================================================
    // FAMILIA
    // =========================================================

    public record Familia(
            String estadoCivil,
            String tipoSangre,
            String factorSangre,
            String nombreEsposo,
            String profesionEsposo,
            List<Hijo> hijos
    ) {
    }

    public record Hijo(
            String nombre,
            String sexo,
            Long edad,
            String ocupacion
    ) {
    }

    // =========================================================
    // FORMACIÓN ACADÉMICA
    // =========================================================

    public record FormacionAcademica(
            String universidadProcedencia,
            String tituloObtenido,
            String formatoTitulo,
            String fechaTitulo,
            String titulo,
            String areaEspecializacion
    ) {
    }

    // =========================================================
    // POSTGRADO
    // =========================================================

    public record Postgrado(
            FormacionPostgrado maestria,
            FormacionPostgrado doctorado
    ) {
    }

    public record FormacionPostgrado(
            String nombre,
            String mencion,
            String universidad,
            String tiempo,
            String titulo
    ) {
    }

    // =========================================================
    // IDIOMAS
    // =========================================================

    public record Idiomas(
            List<Idioma> idiomas
    ) {
    }

    public record Idioma(
            String idioma,
            String centro,
            String tiempo,
            String certificado
    ) {
    }

    // =========================================================
    // EXPERIENCIA LABORAL
    // =========================================================

    public record ExperienciaLaboral(
            Boolean actividadEjercida,
            List<Experiencia> experiencias
    ) {
    }

    public record Experiencia(
            String institucion,
            String sector,
            String cargo,
            String tiempo,
            String tipoServicio,
            String direccion,
            String telefono,
            String nota
    ) {
    }

    // =========================================================
    // ACTIVIDADES
    // =========================================================

    public record Actividades(
            List<Actividad> actividades,
            List<Negocio> negocios
    ) {
    }

    public record Actividad(
            String tipo,
            String tiempo,
            String cargo,
            String direccion,
            String telefono
    ) {
    }

    public record Negocio(
            String tipo,
            String tiempo,
            String cargo,
            String direccion,
            String telefono
    ) {
    }

    // =========================================================
    // CAPACITACIÓN
    // =========================================================

    public record Capacitacion(
            String area,
            String tema1,
            String tema2,
            String tema3,
            String tipoBien
    ) {
    }

    // =========================================================
    // DOCUMENTOS
    // =========================================================

    public record Documentos(
            Documento cdn,
            Documento fondo,
            Documento cuota,
            Documento cdr,
            String archivoFoto,
            String archivoFirma,
            String archivoDni,
            String archivoDiploma,
            String archivoAntecedentes
    ) {
    }

    public record Documento(
            String archivo,
            String codigo,
            String fecha
    ) {
    }

    // =========================================================
    // ESTADO DE SOLICITUD
    // =========================================================

    public record EstadoSolicitud(
            String descripcion,
            String motivo,
            Long codigo,
            Long codigoColegiatura,
            String resolucion,
            String fechaResolucion
    ) {
    }
}