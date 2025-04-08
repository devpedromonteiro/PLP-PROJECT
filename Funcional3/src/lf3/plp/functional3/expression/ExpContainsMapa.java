package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.ValorBooleano;

public class ExpContainsMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;

    public ExpContainsMapa(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }

    @Override
    public Valor avaliar() {
        // Implementação da verificação de chave no mapa
        return new ValorBooleano(false); // Temporário
    }
}