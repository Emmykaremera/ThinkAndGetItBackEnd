package backend.utils;

import backend.application.AuthAPI;

public class TokenManager {

    private static String token;

    public static String getToken(){

        if(token==null){

            AuthAPI authAPI=
                    new AuthAPI();

            token=
                    authAPI
                            .login(
                                    ConfigReader.getProperty(
                                            "user.email"
                                    ),
                                    ConfigReader.getProperty(
                                            "user.password"
                                    )
                            )
                            .jsonPath()
                            .getString(
                                    "data.token"
                            );
        }

        return token;
    }
}