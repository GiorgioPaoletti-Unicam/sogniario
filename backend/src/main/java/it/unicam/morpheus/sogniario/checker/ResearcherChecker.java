package it.unicam.morpheus.sogniario.checker;

import it.unicam.morpheus.sogniario.exception.EntityNotFoundException;
import it.unicam.morpheus.sogniario.model.Researcher;
import org.springframework.stereotype.Component;

@Component
public class ResearcherChecker implements EntityChecker <Researcher>{
    @Override
    public boolean check(Researcher object) throws EntityNotFoundException {

        if(object.getUsername().isBlank())
            throw new IllegalStateException("L'email del Researcher è vuota");

        if(object.getName().isBlank())
            throw new IllegalStateException("Il name del Researcher è vuoto");

        if(object.getPassword().isBlank())
            throw new IllegalStateException("La password del Researcher è vuota");

        return false;
    }
}