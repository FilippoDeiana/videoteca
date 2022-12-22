package com.training.videoteca.security;

/*
@Log
public class AuthEntryPoint extends BasicAuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        log.warning("Errore sicurezza" +" "+ authException.getMessage());
        //response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");


        PrintWriter writer = response.getWriter();
        writer.println("User o password non corretti");
    }



    @Override
    public void afterPropertiesSet() {
        setRealmName("REAME");
        super.afterPropertiesSet();
    }

}


 */