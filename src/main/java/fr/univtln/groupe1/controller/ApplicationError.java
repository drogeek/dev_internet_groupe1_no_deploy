package fr.univtln.groupe1.controller;

import javax.ejb.EJBException;

public class ApplicationError extends NullPointerException {

    private ApplicationError(String message){super(message);}
}
