package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.ExpUnaria;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoLista;
import lf3.plp.functional3.util.TipoMapa;

public class ExpValues extends ExpUnaria {

    public ExpValues(Expressao exp) {
        super(exp, "values");
    }

    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa mapaValor = (ValorMapa) this.getExp().avaliar(amb);
        ValorLista listaValores = ValorLista.getInstancia(null, null);

        for (Expressao chave : mapaValor.getChaves()) {
            Expressao valor = mapaValor.get(chave);
            listaValores.cons(valor);
        }

        return listaValores.inverter();
    }

    @Override
    protected boolean checaTipoElementoTerminal(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        Tipo tipoExp = getExp().getTipo(amb);
        return tipoExp instanceof TipoMapa;
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        TipoMapa tipoMapa = (TipoMapa) getExp().getTipo(amb);
        return new TipoLista(tipoMapa.getTipoValor());
    }

    @Override
    public ExpValues clone() {
        return new ExpValues(this.exp.clone());
    }
}