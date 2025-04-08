package lf3.plp.functional3.expression;

import java.util.List;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;

public class ExpMapa implements Expressao {
    private List<ExpKeyValue> pares;

    public ExpMapa(List<ExpKeyValue> pares) {
        this.pares = pares;
    }

    @Override
    public Valor avaliar() {
        // Implementar a criação do mapa
        return null;
    }

    
}