package lf3.plp.functional3.expression;

import java.util.HashMap;
import java.util.Map;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorConcreto;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoMapa;

public class ValorMapa extends ValorConcreto<Map<Expressao, Expressao>> {

    private Map<Expressao, Expressao> mapa;

    private ValorMapa(Map<Expressao, Expressao> valor) {
        super(valor);
        this.mapa = valor;
    }

    public static ValorMapa getInstancia() {
        return new ValorMapa(new HashMap<Expressao, Expressao>());
    }

    public void put(Expressao chave, Expressao valor) {
        this.mapa.put(chave, valor);
    }

    public void remove(Expressao chave) {
        this.mapa.remove(chave);
    }

    public Expressao get(Expressao chave) {
        return this.mapa.get(chave);
    }

    public boolean containsKey(Expressao chave) {
        return this.mapa.containsKey(chave);
    }

    public boolean isEmpty() {
        return this.mapa.isEmpty();
    }

    public java.util.Set<Expressao> getChaves() {
        return this.mapa.keySet();
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (isEmpty()) {
            return true;
        }

        Tipo tipoChave = null;
        Tipo tipoValor = null;

        for (Map.Entry<Expressao, Expressao> entry : mapa.entrySet()) {
            if (tipoChave == null) {
                tipoChave = entry.getKey().getTipo(amb);
                tipoValor = entry.getValue().getTipo(amb);
            } else {
                if (!tipoChave.eIgual(entry.getKey().getTipo(amb)) ||
                        !tipoValor.eIgual(entry.getValue().getTipo(amb))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (isEmpty()) {
            return new TipoMapa();
        }

        Expressao primeiraChave = mapa.keySet().iterator().next();
        Expressao primeiroValor = mapa.get(primeiraChave);

        return new TipoMapa(primeiraChave.getTipo(amb), primeiroValor.getTipo(amb));
    }

    @Override
    public ValorMapa clone() {
        ValorMapa novoMapa = ValorMapa.getInstancia();
        for (Map.Entry<Expressao, Expressao> entry : mapa.entrySet()) {
            novoMapa.put(entry.getKey().clone(), entry.getValue().clone());
        }
        return novoMapa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Map.Entry<Expressao, Expressao> entry : mapa.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(entry.getKey()).append(" => ").append(entry.getValue());
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }
}