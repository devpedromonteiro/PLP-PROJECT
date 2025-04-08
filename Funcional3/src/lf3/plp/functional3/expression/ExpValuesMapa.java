package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpValuesMapa implements Expressao {
    private Expressao mapa;

    public ExpValuesMapa(Expressao mapa) {
        this.mapa = mapa;
    }

    @Override
    public Valor avaliar() {
        // Implementação da obtenção de valores do mapa
        return null;
    }
}