package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoMapa;

public class ExpContains implements Expressao {

    private Expressao mapa;
    private Expressao chave;

    public ExpContains(Expressao mapa, Expressao chave) {
        this.mapa = mapa;
        this.chave = chave;
    }

    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaValor = (ValorMapa) mapa.avaliar(amb);
        boolean contem = mapaValor.containsKey(chave.avaliar(amb));
        return new ValorBooleano(contem);
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
        return TipoPrimitivo.BOOLEANO;
    }

    @Override
    public Expressao clone() {
        return new ExpContains(mapa.clone(), chave.clone());
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        this.mapa = this.mapa.reduzir(ambiente);
        this.chave = this.chave.reduzir(ambiente);
        return this;
    }
}