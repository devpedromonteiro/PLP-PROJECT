package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoMapa;

public class ExpGet implements Expressao {

    private Expressao mapa;
    private Expressao chave;

    public ExpGet(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }

    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaValor = (ValorMapa) mapa.avaliar(amb);
        Expressao valor = mapaValor.get(chave.avaliar(amb));
        if (valor == null) {
            throw new RuntimeException("Chave não encontrada no mapa");
        }
        return valor.avaliar(amb);
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

        return tipoChave.eIgual(tipoMapaConcreto.getTipoChave());
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        TipoMapa tipoMapa = (TipoMapa) mapa.getTipo(amb);
        return tipoMapa.getTipoValor();
    }

    @Override
    public Expressao clone() {
        return new ExpGet(mapa.clone(), chave.clone());
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        this.chave = this.chave.reduzir(ambiente);
        return this;
    }
}