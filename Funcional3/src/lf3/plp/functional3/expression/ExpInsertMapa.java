package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpInsertMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;
    private Expressao valor;

    public ExpInsertMapa(Expressao mapa, Expressao chave, Expressao valor) {
        this.mapa = mapa;
        this.chave = chave;
        this.valor = valor;
    }

    @Override
    public Valor avaliar() {
        // Implementação da inserção no mapa
        return null;
    }
}