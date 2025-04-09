package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.expression.TipoMapa;

public class ExpMapa implements Expressao {
    private List<ExpKeyValue> pares;
    
    public ExpMapa(List<ExpKeyValue> pares) {
        this.pares = pares;
    }
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        Map<Valor, Valor> mapa = new HashMap<>();
        
        for (ExpKeyValue par : pares) {
            Valor chave = par.getChave().avaliar(amb);
            Valor valor = par.getValor().avaliar(amb);
            mapa.put(chave, valor);
        }
        
        return ValorMapa.getInstancia(mapa);
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (pares.isEmpty()) {
            return true;
        }
        
        boolean tiposValidos = true;
        Tipo tipoChave = null;
        Tipo tipoValor = null;
        
        for (ExpKeyValue par : pares) {
            if (!par.checaTipo(amb)) {
                tiposValidos = false;
                break;
            }
            
            if (tipoChave == null) {
                tipoChave = par.getChave().getTipo(amb);
                tipoValor = par.getValor().getTipo(amb);
            } else {
                if (!tipoChave.eIgual(par.getChave().getTipo(amb)) ||
                    !tipoValor.eIgual(par.getValor().getTipo(amb))) {
                    tiposValidos = false;
                    break;
                }
            }
        }
        
        return tiposValidos;
    }
    
    @Override
    public Tipo getTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (pares.isEmpty()) {
            return new TipoMapa();
        }
        
        ExpKeyValue primeiroPar = pares.get(0);
        return new TipoMapa(primeiroPar.getChave().getTipo(amb), primeiroPar.getValor().getTipo(amb));
    }
    
    @Override
    public ExpMapa clone() {
        List<ExpKeyValue> novosPares = new ArrayList<>();
        for (ExpKeyValue par : pares) {
            novosPares.add(par.clone());
        }
        return new ExpMapa(novosPares);
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        List<ExpKeyValue> novosPares = new ArrayList<>();
        for (ExpKeyValue par : this.pares) {
            novosPares.add((ExpKeyValue) par.reduzir(ambiente));
        }
        this.pares = novosPares;
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("map {");
        for (int i = 0; i < pares.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(pares.get(i).toString());
        }
        sb.append("}");
        return sb.toString();
    }
}