package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpGetMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;

    public ExpGetMapa(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }

    @Override
    public Valor avaliar() {
        // Implementação da obtenção de valor do mapa
        return null;
    }
}