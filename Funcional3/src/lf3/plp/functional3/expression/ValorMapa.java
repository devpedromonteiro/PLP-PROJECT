package lf3.plp.functional3.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorConcreto;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.expression.TipoMapa;

public class ValorMapa extends ValorConcreto<Map<Valor, Valor>> {
    private static ValorMapa instanciaVazia;
    
    static {
        instanciaVazia = new ValorMapa(new HashMap<>());
    }
    
    public ValorMapa(Map<Valor, Valor> valor) {
        super(valor);
    }
    
    public static ValorMapa getInstancia(Map<Valor, Valor> mapa) {
        if (mapa == null || mapa.isEmpty()) {
            return getInstanciaVazia();
        }
        return new ValorMapa(new HashMap<>(mapa));
    }
    
    public static ValorMapa getInstanciaVazia() {
        return instanciaVazia;
    }
    
    public Valor obter(Valor chave) {
        return valor().get(chave);
    }
    
    public boolean contem(Valor chave) {
        return valor().containsKey(chave);
    }
    
    public ValorMapa inserir(Valor chave, Valor valor) {
        Map<Valor, Valor> novoMapa = new HashMap<>(valor());
        novoMapa.put(chave, valor);
        return getInstancia(novoMapa);
    }
    
    public ValorMapa remover(Valor chave) {
        Map<Valor, Valor> novoMapa = new HashMap<>(valor());
        novoMapa.remove(chave);
        return getInstancia(novoMapa);
    }
    
    @Override
    public Valor avaliar(AmbienteExecucao amb) {
        return this;
    }
    
    @Override
    public boolean checaTipo(AmbienteCompilacao amb) {
        if (valor().isEmpty()) {
            return true;
        }
        
        Tipo tipoChave = null;
        Tipo tipoValor = null;
        
        for (Map.Entry<Valor, Valor> entry : valor().entrySet()) {
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
        if (valor().isEmpty()) {
            return new TipoMapa();
        }
        
        Map.Entry<Valor, Valor> firstEntry = valor().entrySet().iterator().next();
        return new TipoMapa(firstEntry.getKey().getTipo(amb), firstEntry.getValue().getTipo(amb));
    }
    
    @Override
    public ValorMapa clone() {
        return getInstancia(valor());
    }
    
    @Override
    public boolean isEquals(ValorConcreto<Map<Valor, Valor>> obj) {
        if (!(obj instanceof ValorMapa)) {
            return false;
        }
        return valor().equals(((ValorMapa)obj).valor());
    }
}