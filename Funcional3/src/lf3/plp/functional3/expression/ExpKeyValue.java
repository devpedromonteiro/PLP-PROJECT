package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;

public class ExpKeyValue implements Expressao {
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
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        throw new UnsupportedOperationException("ExpKeyValue não pode ser avaliada diretamente");
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return chave.checaTipo(amb) && valor.checaTipo(amb);
    }
    
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        throw new UnsupportedOperationException("ExpKeyValue não tem tipo diretamente");
    }
    
    @Override
    public ExpKeyValue clone() {
        return new ExpKeyValue(chave.clone(), valor.clone());
    }

   @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.chave = this.chave.reduzir(ambiente);
        this.valor = this.valor.reduzir(ambiente);
        return this;
    }
    
    @Override
    public String toString() {
        return chave.toString() + " => " + valor.toString();
    }
}