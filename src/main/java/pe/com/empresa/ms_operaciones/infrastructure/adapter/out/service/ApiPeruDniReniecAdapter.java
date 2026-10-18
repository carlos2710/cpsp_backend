package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import pe.com.empresa.ms_operaciones.application.port.out.ConsultarDniReniecPort;
import pe.com.empresa.ms_operaciones.domain.model.DniReniec;
import pe.com.empresa.ms_operaciones.domain.model.DniReniecConsulta;

import java.util.List;

@Component
public class ApiPeruDniReniecAdapter implements ConsultarDniReniecPort {
    private final RestClient restClient;
    private final String endpoint;
    private final String token;

    public ApiPeruDniReniecAdapter(
            @Value("${apiperu.dni-direccion.url}") String endpoint,
            @Value("${apiperu.dni-direccion.token:}") String token
    ) {
        this.restClient = RestClient.create();
        this.endpoint = endpoint;
        this.token = token;
    }

    @Override
    public DniReniecConsulta consultar(String dni) {
        if (token.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    "La integración con RENIEC no está configurada."
            );
        }

        ApiPeruResponse response;
        try {
            response = restClient.post()
                    .uri(endpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .body(new ApiPeruDniRequest(dni))
                    .retrieve()
                    .body(ApiPeruResponse.class);
        } catch (RestClientException exception) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "No se pudo completar la consulta al servicio RENIEC.",
                    exception
            );
        }

        if (response == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "El servicio RENIEC devolvió una respuesta vacía."
            );
        }

        return new DniReniecConsulta(response.success(), response.message(), mapear(response.data()));
    }

    private DniReniec mapear(ApiPeruData data) {
        if (data == null) {
            return null;
        }
        return new DniReniec(
                data.numero(),
                data.nombreCompleto(),
                data.nombres(),
                data.apellidoPaterno(),
                data.apellidoMaterno(),
                data.codigoVerificacion(),
                data.direccion(),
                data.direccionCompleta(),
                data.departamento(),
                data.provincia(),
                data.distrito(),
                data.ubigeoReniec(),
                data.ubigeoSunat(),
                data.ubigeo()
        );
    }

    private record ApiPeruDniRequest(String dni) {
    }

    private record ApiPeruResponse(boolean success, String message, ApiPeruData data) {
    }

    private record ApiPeruData(
            String numero,
            @JsonProperty("nombre_completo") String nombreCompleto,
            String nombres,
            @JsonProperty("apellido_paterno") String apellidoPaterno,
            @JsonProperty("apellido_materno") String apellidoMaterno,
            @JsonProperty("codigo_verificacion") Integer codigoVerificacion,
            String direccion,
            @JsonProperty("direccion_completa") String direccionCompleta,
            String departamento,
            String provincia,
            String distrito,
            @JsonProperty("ubigeo_reniec") String ubigeoReniec,
            @JsonProperty("ubigeo_sunat") String ubigeoSunat,
            List<String> ubigeo
    ) {
    }
}
