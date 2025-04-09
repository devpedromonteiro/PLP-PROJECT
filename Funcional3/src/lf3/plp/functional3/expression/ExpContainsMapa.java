package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.expression.TipoMapa;

public class ExpContainsMapa implements Expressao {
    private Expressao mapa;
    private Expressao chave;
    
    public ExpContainsMapa(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaAvaliado = (ValorMapa) mapa.avaliar(amb);
        Valor chaveAvaliada = chave.avaliar(amb);
        
        return new ValorBooleano(mapaAvaliado.contem(chaveAvaliada));
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        boolean tipoMapaOk = mapa.checaTipo(amb) && mapa.getTipo(amb) instanceof TipoMapa;
        boolean tipoChaveOk = chave.checaTipo(amb);
        
        if (!tipoMapaOk || !tipoChaveOk) {
            return false;
        }
        
        TipoMapa tipoMapa = (TipoMapa) mapa.getTipo(amb);
        return tipoMapa.getTipoChave().eIgual(chave.getTipo(amb));
    }
    
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return TipoPrimitivo.BOOLEANO;
    }
    
    @Override
    public ExpContainsMapa clone() {
        return new ExpContainsMapa(mapa.clone(), chave.clone());
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        this.chave = this.chave.reduzir(ambiente);
        return this;
    }
    
    @Override
    public String toString() {
        return "contains(" + mapa + ", " + chave + ")";
    }
}