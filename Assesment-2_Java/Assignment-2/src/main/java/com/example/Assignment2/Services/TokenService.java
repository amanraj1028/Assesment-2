package com.example.Assignment2.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class TokenService {
        public static final String token_secret = "dsFSA24351FSAkmkdkfd";

        //Create a Token
        public String createToken(ObjectId userID){

            try{
                Algorithm algo = Algorithm.HMAC256(token_secret);
                String token = JWT.create().withClaim("userID", userID.toString()).
                        withClaim("createAt", new Date()).
                        sign(algo);
                return token;
            }
            catch(UnsupportedEncodingException | JWTCreationException e){
                e.printStackTrace();
            }

            return null;
        }

        //Decoding token
        public String getUserToken(String token)
        {
            try{
                Algorithm algo = Algorithm.HMAC256(token_secret);
                JWTVerifier jwtVerifier = JWT.require(algo).build();

                DecodedJWT decodedJWT = jwtVerifier.verify(token);

                return decodedJWT.getClaim("userID").asString();
            }
            catch (JWTCreationException | UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        public boolean isTokenValid(String token)
        {
            String userID = this.getUserToken(token);
            return userID != null;
        }
}

