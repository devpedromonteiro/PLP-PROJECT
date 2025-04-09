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

public class ExpInsertMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;
    private Expressao valor;
    
    public ExpInsertMapa(Expressao mapa, Expressao chave, Expressao valor) {
        this.mapa = mapa;
        this.chave = chave;
        this.valor = valor;
    }
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaAvaliado = (ValorMapa) mapa.avaliar(amb);
        Valor chaveAvaliada = chave.avaliar(amb);
        Valor valorAvaliado = valor.avaliar(amb);
        
        return mapaAvaliado.inserir(chaveAvaliada, valorAvaliado);
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        boolean tipoMapaOk = mapa.checaTipo(amb) && mapa.getTipo(amb) instanceof TipoMapa;
        boolean tiposChaveValorOk = chave.checaTipo(amb) && valor.checaTipo(amb);
        
        if (!tipoMapaOk || !tiposChaveValorOk) {
            return false;
        }
        
        TipoMapa tipoMapa = (TipoMapa) mapa.getTipo(amb);
        return tipoMapa.getTipoChave().eIgual(chave.getTipo(amb)) && 
               tipoMapa.getTipoValor().eIgual(valor.getTipo(amb));
    }
    
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return mapa.getTipo(amb);
    }
    
    @Override
    public ExpInsertMapa clone() {
        return new ExpInsertMapa(mapa.clone(), chave.clone(), valor.clone());
    }
    
    @Override
    public String toString() {
        return "insert(" + mapa + ", " + chave + ", " + valor + ")";
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        this.chave = this.chave.reduzir(ambiente);
        this.valor = this.valor.reduzir(ambiente);
        return this;
    }
}