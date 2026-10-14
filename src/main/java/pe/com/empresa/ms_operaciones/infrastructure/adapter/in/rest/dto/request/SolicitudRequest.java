package pe.com.empresa.ms_operaciones.infrastructure.adapter.in.rest.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request para registrar una solicitud de colegiatura")
public record SolicitudRequest(

        @Schema(description = "Datos generales de la solicitud")
        datosSolicitud datosSolicitud,

        @Schema(description = "Datos personales del solicitante")
        General general,

        @Schema(description = "Formación académica del solicitante")
        datosAcademicos datosAcademicos,

        @Schema(description = "Actividad profesional")
        actividadProfesional actividadProfesional,

        @Schema(description = "Actividad no profesional")
        actividadNoProfesional actividadNoProfesional,

        @Schema(description = "Necesidades de capacitación")
        necesidadCapacitacion necesidadCapacitacion,

        @Schema(description = "Anexos y documentos adjuntos")
        anexosCarga anexosCarga

) {
    public record datosSolicitud(
      Integer codSolicitud,
      Integer codLocal,
      String fechaRecepcion,
      String horaRecepcion,
      Integer codEstado,
      String fechaIncorporacion,
      Integer numeroResolucion,
      Integer codColegiatura
    ){}

    public record General(
      String apellidoP,
      String apellidoM,
      String nombre1,
      String nombre2,
      Integer codDepartamento,
      Integer codProvincia,
      Integer codDistrito,
      String fechaNacimiento,
      Integer codTipoDocumento,
      String numeroDocumento,
      String numeroRUC,
      String celular1,
      String celular2,
      String fijo,
      String correo1,
      String correo2,
      Integer codEstadoCivil,
      Integer codGrupoSang,
      String nombreConyugue,
      String profesionConyugue,
      String datosHijos
    ){
    }

    public record datosAcademicos(
            Integer codUniversidad,
            String tituloObtenido,
            Integer codObtencion,
            String fechaObtencion,
            String tituloObtencion,
            String otroObtencion,
            List<Integer> areaEspecializacion,
            String datosRevalidacion,
            String datosMaestria,
            String datosDoctorado,
            String datosIdioma,
            String datosOtrosEstudios
    ){
    }

    public record actividadProfesional(
            Boolean codEjerce,
            String institucionTrabajo,
            String institucionTrabajo2,
            String actividadParticular,
            String actividadParticular2
    ){}

    public record actividadNoProfesional(
            String actividadProfesional,
            String actividadProfesional2
    ){}

    public record necesidadCapacitacion(
            List<Integer> areas,
            String otros,
            List<String> listTemas,
            List<String> listBienestar,
            String otrosBienestar
    ){}

    public record anexosCarga(
      String rutaColegiaturaCDN,
      String operacionColegiaturaCDN,
      String fechaColegiaturaCDN,
      String rutaFondo,
      String operacionFondo,
      String fechaFondo,
      String rutaCuota,
      String operacionCuota,
      String fechaCuota,
      String rutaColegiaturaCDR,
      String operacionColegiaturaCDR,
      String fechaColegiaturaCDR,
      String rutaFoto,
      String rutaFirma,
      String rutaDNI,
      String rutaDiploma,
      String rutaAntecedentes
    ){}

}
