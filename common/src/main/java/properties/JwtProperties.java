package properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "property.jwt")
@Data
public class  JwtProperties {

    /**
     * JWT Token for User Login
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

}