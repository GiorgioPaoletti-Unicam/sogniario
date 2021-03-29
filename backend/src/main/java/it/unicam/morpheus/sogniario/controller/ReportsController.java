package it.unicam.morpheus.sogniario.controller;

import it.unicam.morpheus.sogniario.exception.EntityNotFoundException;
import it.unicam.morpheus.sogniario.model.Report;
import org.springframework.data.domain.Page;

public interface ReportsController extends EntityController<Report, String> {

    Page<Report> getPageByDreamerId(int page, int size, String dreamerID) throws EntityNotFoundException;

    int getDreamNumberOfWord(String reportID) throws EntityNotFoundException;

}