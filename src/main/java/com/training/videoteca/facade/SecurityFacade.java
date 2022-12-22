package com.training.videoteca.facade;



import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.videoteca.security.WindowsLoginToken;
import com.training.videoteca.errors.ExternalCommunicationErrorException;
import com.training.videoteca.errors.pojo.ErrorResponse;
import com.training.videoteca.pojo.GreetingsPojo;
import com.training.videoteca.pojo.SecurityPojo;
import com.training.videoteca.pojo.response.SecurityResponse;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.util.Date;

@Component
public class SecurityFacade {
    GreetingsPojo gp = new GreetingsPojo();


    private static final String CLIENT_ID = "7a9d6681-7188-4ca0-a2e6-8b322ebd0cbe";
    private static final String RESOURCE = "https://servicebus.azure.net";
    private static final String CLIENT_SECRET = "FCN8Q~DAKCiHg7fP3aLrbqBaByCDo3iDzSYw-cnS";
    private static final String GRANT_TYPE = "client_credentials";



    public SecurityResponse security(String username, String password) {
        SecurityPojo sp = new SecurityPojo(username, password);
        ErrorResponse er = new ErrorResponse(new Date(), HttpStatus.OK, "");

        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            try {
                if ("pippo".equals(sp.getUsername()) && "1234".equals(sp.getPassword())) {
                    er.setStatus(HttpStatus.NO_CONTENT);
                } else {
                    er.setStatus(HttpStatus.FORBIDDEN);
                    er.setMessaggio("User non trovato");
                }
            } catch (Exception e) {
                er.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                er.setMessaggio("Errore nella ricerca");

            }
        } else {
            er.setStatus(HttpStatus.BAD_REQUEST);
            er.setMessaggio("Parametri invalidi");
        }
        return new SecurityResponse(new SecurityPojo(), er);
    }


    public WindowsLoginToken authorize() throws IOException, ExternalCommunicationErrorException {

        RestTemplate restTemplate = new RestTemplate();

        ObjectMapper mapper = new ObjectMapper();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("grant_type", GRANT_TYPE);
        requestBody.add("resource", RESOURCE);
        requestBody.add("client_secret", CLIENT_SECRET);

        final HttpEntity<?> requestEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://login.microsoftonline.com/e732cea0-5e54-418a-b1bd-3e02ed88a564/oauth2/token", HttpMethod.POST, requestEntity, String.class);

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            WindowsLoginToken result = mapper.readValue(response.getBody(), WindowsLoginToken.class);

            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            return result;

        } catch (JsonMappingException e) {
            throw new ExternalCommunicationErrorException("Errore lettura della risposta del servizio di login microsoft", e);
        } catch (JsonParseException e) {
            throw new ExternalCommunicationErrorException("Errore lettura della risposta del servizio di login microsoft", e);
        } catch (IOException e) {
            throw new ExternalCommunicationErrorException("Errore comunicazione della risposta del servizio di login microsoft", e);
        }

    }



}
