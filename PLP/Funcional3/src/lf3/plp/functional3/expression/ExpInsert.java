package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoMapa;

public class ExpInsert implements Expressao {

    private Expressao mapa;
    private Expressao chave;
    private Expressao valor;

    public ExpInsert(Expressao mapa, Expressao chave, Expressao valor) {
        this.mapa = mapa;
        this.chave = chave;
        this.valor = valor;
    }

    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaValor = (ValorMapa) mapa.avaliar(amb);
        ValorMapa novoMapa = mapaValor.clone();
        novoMapa.put(chave.avaliar(amb), valor.avaliar(amb));
        return novoMapa;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        Tipo tipoMapa = mapa.getTipo(amb);
        if (!(tipoMapa instanceof TipoMapa)) {
            return false;
        }

        TipoMapa tipoMapaConcreto = (TipoMapa) tipoMapa;
        Tipo tipoChave = chave.getTipo(amb);
        Tipo tipoValor = valor.getTipo(amb);

        return tipoChave.eIgual(tipoMapaConcreto.getTipoChave()) &&
                tipoValor.eIgual(tipoMapaConcreto.getTipoValor());
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        return mapa.getTipo(amb);
    }

    @Override
    public Expressao clone() {
        return new ExpInsert(mapa.clone(), chave.clone(), valor.clone());
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        this.chave = this.chave.reduzir(ambiente);
        this.valor = this.valor.reduzir(ambiente);

        return this;
    }
}