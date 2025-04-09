package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.functional3.expression.ValorLista;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoLista;
import lf3.plp.functional3.expression.TipoMapa;

public class ExpKeysMapa implements Expressao {
    private Expressao mapa;
    
    public ExpKeysMapa(Expressao mapa) {
        this.mapa = mapa;
    }
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaAvaliado = (ValorMapa) mapa.avaliar(amb);
        
        ValorLista listaChaves = ValorLista.getInstancia(null, null);
        for (Valor chave : mapaAvaliado.valor().keySet()) {
            listaChaves.cons(chave);
        }
        
        return listaChaves.inverter();
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return mapa.checaTipo(amb) && mapa.getTipo(amb) instanceof TipoMapa;
    }
    
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        TipoMapa tipoMapa = (TipoMapa) mapa.getTipo(amb);
        return new TipoLista(tipoMapa.getTipoChave());
    }
    
    @Override
    public ExpKeysMapa clone() {
        return new ExpKeysMapa(mapa.clone());
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        return this;
    }
    
    @Override
    public String toString() {
        return "keys(" + mapa + ")";
    }
}