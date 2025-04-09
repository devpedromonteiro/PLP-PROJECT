package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.expression.TipoMapa;

public class ExpCompreensaoMapa implements Expressao {
    
    public ExpCompreensaoMapa() {
    }

    @Override
    public Valor avaliar(AmbienteExecucao amb) {
        return ValorMapa.getInstanciaVazia();
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao amb) {
        return true;
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) {
        return new TipoMapa();
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        return this;
    }

    @Override
    public ExpCompreensaoMapa clone() {
        return new ExpCompreensaoMapa();
    }
}