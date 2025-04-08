package lf3.plp.functional3.expression;

import lf3.plp.expressions2.expression.Expressao;

public class ExpKeyValue {
    private Expressao chave;
    private Expressao valor;

    public ExpKeyValue(Expressao chave, Expressao valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public Expressao getChave() {
        return chave;
    }

    public Expressao getValor() {
        return valor;
    }
}