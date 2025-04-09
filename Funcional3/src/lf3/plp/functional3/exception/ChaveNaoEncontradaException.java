package lf3.plp.functional3.exception;

public class ChaveNaoEncontradaException extends RuntimeException {
    public ChaveNaoEncontradaException(String mensagem) {
        super("Chave não encontrada no mapa: " + mensagem);
    }
}