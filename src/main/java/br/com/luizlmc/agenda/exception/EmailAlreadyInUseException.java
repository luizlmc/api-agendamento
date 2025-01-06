package br.com.luizlmc.agenda.exception;

public class EmailAlreadyInUseException extends RuntimeException{

    public EmailAlreadyInUseException(String message) { super(message); }
}
