package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpKeysMapa implements Expressao {
    private Expressao mapa;

    public ExpKeysMapa(Expressao mapa) {
        this.mapa = mapa;
    }

    @Override
    public Valor avaliar() {
        // Implementação da obtenção de chaves do mapa
        return null;
    }
}