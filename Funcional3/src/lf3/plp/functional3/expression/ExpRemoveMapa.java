package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpRemoveMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;

    public ExpRemoveMapa(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }

    @Override
    public Valor avaliar() {
        // Implementação da remoção do mapa
        return null;
    }
}