package com.training.videoteca.facade;

import com.training.videoteca.pojo.GreetingsPojo;
import org.springframework.stereotype.Component;

@Component
public class GreetingsFacade {

    public GreetingsPojo descrizione (){
        GreetingsPojo gp = new GreetingsPojo();
        gp.setDescrizione("Hello World!");
        return gp;
    }
}
