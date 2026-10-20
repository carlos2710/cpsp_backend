package pe.com.empresa.ms_operaciones.infrastructure.adapter.out.persistence;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.empresa.ms_operaciones.application.port.out.UsuarioPort;
import pe.com.empresa.ms_operaciones.domain.model.GuardarUsuario;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class UsuarioJdbcAdapter implements UsuarioPort {
    private static final String SQL = "{call dbo.usp_usuario_guardar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    private final JdbcTemplate jdbcTemplate;

    public UsuarioJdbcAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer guardar(GuardarUsuario usuario) {
        return jdbcTemplate.execute((ConnectionCallback<Integer>) connection -> {
            SQLServerDataTable locales = new SQLServerDataTable();
            locales.addColumnMetadata("loc_codigo", Types.INTEGER);
            for (Integer codigoLocal : usuario.locales()) {
                locales.addRow(codigoLocal);
            }

            try (CallableStatement callable = connection.prepareCall(SQL)) {
                SQLServerCallableStatement sqlServerCallable = callable.unwrap(SQLServerCallableStatement.class);
                sqlServerCallable.setStructured(1, "dbo.TVP_LocalesUsuario", locales);
                callable.setInt(2, usuario.codigoPerfil());
                callable.setInt(3, usuario.codigoNatural());
                callable.setInt(4, usuario.codigoPersona());
                callable.setInt(5, usuario.tipoUsuario());
                callable.setString(6, usuario.usuario());
                callable.setString(7, usuario.clave());
                callable.setInt(8, usuario.estado());
                callable.setInt(9, usuario.marca());
                callable.setString(10, usuario.usuarioRegistro());
                callable.setInt(11, usuario.marcaDetalle());
                setNullableString(callable, 12, usuario.telefono());
                setNullableString(callable, 13, usuario.email());
                setNullableInteger(callable, 14, usuario.codigoEmpresa());
                callable.registerOutParameter(15, Types.INTEGER);
                setNullableInteger(callable, 15, usuario.codigoUsuario());

                boolean hasResults = callable.execute();
                while (true) {
                    if (hasResults) {
                        try (ResultSet resultSet = callable.getResultSet()) {
                            while (resultSet != null && resultSet.next()) {
                                // El SP también expone el código en un SELECT; se usa el parámetro OUTPUT.
                            }
                        }
                    } else if (callable.getUpdateCount() == -1) {
                        break;
                    }
                    hasResults = callable.getMoreResults();
                }

                int codigo = callable.getInt(15);
                if (callable.wasNull()) {
                    throw new SQLException("El SP usp_usuario_guardar no devolvió el código del usuario.");
                }
                return codigo;
            }
        });
    }

    private void setNullableString(CallableStatement callable, int index, String value) throws SQLException {
        if (value == null || value.isBlank()) {
            callable.setNull(index, Types.VARCHAR);
        } else {
            callable.setString(index, value.trim());
        }
    }

    private void setNullableInteger(CallableStatement callable, int index, Integer value) throws SQLException {
        if (value == null) {
            callable.setNull(index, Types.INTEGER);
        } else {
            callable.setInt(index, value);
        }
    }
}
