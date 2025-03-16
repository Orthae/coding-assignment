package orthae.com.github.userservice.infrastructure.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Duration;
import java.util.Base64;

@ConfigurationProperties(prefix = "token")
public class TokenProperties {
    private final String privateKey;
    private final String publicKey;
    @Getter
    private final Duration expiration;

    @ConstructorBinding
    public TokenProperties(String privateKey, String publicKey, Duration expiration) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.expiration = expiration;
    }

    public KeyPair getKeyPair() throws NoSuchAlgorithmException, InvalidKeySpecException {
        var keyFactory = KeyFactory.getInstance("RSA");

        var publicKeyBytes = Base64.getDecoder().decode(publicKey);
        var publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        var publicKey = keyFactory.generatePublic(publicKeySpec);

        var privateKeyBytes = Base64.getDecoder().decode(privateKey);
        var privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        var privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }
}
