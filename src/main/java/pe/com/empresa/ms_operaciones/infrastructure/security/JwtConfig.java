package pe.com.empresa.ms_operaciones.infrastructure.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class JwtConfig {

    @Bean
    public SecretKey jwtSecretKey(
            @Value("${app.jwt.secret-base64}") String secretBase64){
        if (secretBase64 == null || secretBase64.isBlank()) {
            throw new IllegalStateException(
                    "Debe configurar JWT_SECRET_BASE64 con una clave Base64 de al menos 32 bytes.");
        }

        byte[] keyBytes;
        try {
            keyBytes = Base64.getDecoder().decode(secretBase64);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "JWT_SECRET_BASE64 no contiene un valor Base64 válido.", exception);
        }

        if (keyBytes.length < 32) {
            throw new IllegalArgumentException(
                    "JWT_SECRET_BASE64 debe decodificar a 32 bytes como mínimo.");
        }

        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        return new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey));
    }

    @Bean
    public JwtDecoder jwtDecoder(
            SecretKey jwtSecretKey,
            @Value("${app.jwt.issuer}") String issuer) {

        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(jwtSecretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(issuer));

        return decoder;
    }
}
