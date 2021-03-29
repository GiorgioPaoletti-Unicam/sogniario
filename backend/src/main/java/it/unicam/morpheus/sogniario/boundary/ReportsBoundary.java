package it.unicam.morpheus.sogniario.boundary;

import it.unicam.morpheus.sogniario.exception.EntityNotFoundException;
import it.unicam.morpheus.sogniario.model.Report;
import org.springframework.data.domain.Page;

public interface ReportsBoundary extends EntityBoundary<Report, String> {

    Page<Report> getPageByDreamerId(int page, int size, String dreamerID) throws EntityNotFoundException;

    int getDreamNumberOfWord(String reportID) throws EntityNotFoundException;

    // TODO: 17/03/2021 getDreamGraph

}