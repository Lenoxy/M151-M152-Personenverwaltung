package ch.lu.bbzw.backendpersonenverwaltung.service;

import ch.lu.bbzw.backendpersonenverwaltung.entity.PersonEntity;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtService{

    private final String ISSUER = "m151-m152-personenverwaltung";
    private final Algorithm ALGORITHM = Algorithm.HMAC256("very_secret"); // TODO

    private final JWTVerifier verifier = JWT.require(ALGORITHM)
            .withIssuer(ISSUER)
            .withClaimPresence("user")
            .withClaimPresence("is-admin")
            .build();


    public String createJwt(PersonEntity personEntity){
        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("user", personEntity.getUsername())
                .withClaim("is-admin", personEntity.isAdmin())
                .sign(ALGORITHM);
    }

    public boolean isJwtValid(String jwt){
        try{
            DecodedJWT decodedJWT = verifier.verify(jwt);
            return true;
        }catch(JWTVerificationException e){
            return false;
        }
    }

    public String getUserNameFromClaim(String jwt){
        try{
            DecodedJWT decodedJWT = verifier.verify(jwt);
            return decodedJWT.getClaim("user").asString();
        }catch(JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }

    public Boolean isAdminFromClaim(String jwt){
        try{
            DecodedJWT decodedJWT = verifier.verify(jwt);
            return decodedJWT.getClaim("is-admin").asBoolean();
        }catch(JWTVerificationException e){
            e.printStackTrace();
            return null;
        }
    }
}
