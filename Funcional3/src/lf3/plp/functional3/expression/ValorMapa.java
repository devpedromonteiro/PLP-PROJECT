package lf3.plp.functional3.expression;

import java.util.HashMap;
import java.util.Map;
import lf3.plp.expressions2.expression.Valor;

public class ValorMapa extends Valor {
    private Map<Valor, Valor> mapa;

    public ValorMapa(Map<Valor, Valor> mapa) {
        this.mapa = new HashMap<>(mapa);
    }

    public Valor obter(Valor chave) {
        return mapa.get(chave);
    }

    public boolean contem(Valor chave) {
        return mapa.containsKey(chave);
    }

}