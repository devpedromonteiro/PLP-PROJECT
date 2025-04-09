package lf3.plp.functional3.expression;

import java.util.HashMap;
import java.util.Map;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.expression.ValorBooleano;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional1.util.TipoPolimorfico;
import lf3.plp.functional2.expression.ValorIrredutivel;
import lf3.plp.functional3.util.TipoLista;

public class Gerador {

    private Id id;
    private Expressao expressao;
    private Gerador proximo;

    public Gerador(Id id, Expressao expressao) {
        this.id = id;
        this.expressao = expressao;
    }

    public Id getId() {
        return id;
    }

    public Expressao getExpressao() {
        return expressao;
    }

    public Gerador getProximoGerador() {
        return proximo;
    }

    public void addProximoGerador(Gerador gerador) {
        if (this.proximo == null) {
            this.proximo = gerador;
        } else {
            this.proximo.addProximoGerador(gerador);
        }
    }

    public void gerarValores(AmbienteExecucao amb, ValorLista resultado,
            Expressao expressao, Expressao filtro)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Valor valorExpressao = this.expressao.avaliar(amb);
        
        if (valorExpressao instanceof ValorLista) {
            ValorLista temp = (ValorLista) valorExpressao;

            while (temp != null && !temp.isEmpty()) {
                amb.incrementa();

                Valor valor = temp.getHead().avaliar(amb);
                amb.map(this.id, valor);
                temp = temp.getTail();

                if (getProximoGerador() == null) {
                    if (filtro == null || ((ValorBooleano) filtro.avaliar(amb)).valor()) {
                        resultado.cons(expressao.avaliar(amb));
                    }
                } else {
                    getProximoGerador().gerarValores(amb, resultado, expressao, filtro);
                }

                amb.restaura();
            }
        }
    }

    public void gerarParesMapa(AmbienteExecucao amb, Map<Valor, Valor> resultado,
            Expressao chaveExpr, Expressao valorExpr, Expressao filtro)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        Valor valorExpressao = this.expressao.avaliar(amb);
        
        if (valorExpressao instanceof ValorLista) {
            ValorLista temp = (ValorLista) valorExpressao;

            while (temp != null && !temp.isEmpty()) {
                amb.incrementa();

                Valor valor = temp.getHead().avaliar(amb);
                amb.map(this.id, valor);
                temp = temp.getTail();

                if (getProximoGerador() == null) {
                    if (filtro == null || ((ValorBooleano) filtro.avaliar(amb)).valor()) {
                        Valor chave = chaveExpr.avaliar(amb);
                        Valor valorMapa = valorExpr.avaliar(amb);
                        resultado.put(chave, valorMapa);
                    }
                } else {
                    getProximoGerador().gerarParesMapa(amb, resultado, chaveExpr, valorExpr, filtro);
                }

                amb.restaura();
            }
        }
    }

    public boolean temProximoGerador() {
        return proximo != null;
    }

    public Map<Id, Tipo> checkTypeBindings(AmbienteCompilacao amb) {
        HashMap<Id, Tipo> tipos = new HashMap<Id, Tipo>();

        Tipo tipo = expressao.getTipo(amb);
        if (tipo instanceof TipoPolimorfico) {
            TipoPolimorfico tp = (TipoPolimorfico) tipo;
            tipo = tp.getTipoInstanciado();
        }
        
        if (tipo instanceof TipoLista) {
            TipoLista tipoLista = (TipoLista) tipo;
            tipos.put(id, tipoLista.getSubTipo());
        }
        
        if (temProximoGerador()) {
            tipos.putAll(proximo.checkTypeBindings(amb));
        }

        return tipos;
    }

    public void mapearTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        Tipo tipo = expressao.getTipo(amb);
        if (tipo instanceof TipoPolimorfico) {
            tipo = ((TipoPolimorfico) tipo).getTipoInstanciado();
        }
        
        if (tipo instanceof TipoLista) {
            amb.map(id, ((TipoLista) tipo).getSubTipo());
        }
        
        if (temProximoGerador()) {
            proximo.mapearTipo(amb);
        }
    }

    public boolean checaTipo(AmbienteCompilacao amb) throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        boolean tipoOk = expressao.checaTipo(amb);
        Tipo tipo = expressao.getTipo(amb);
        
        if (tipo instanceof TipoPolimorfico) {
            tipo = ((TipoPolimorfico) tipo).getTipoInstanciado();
        }
        
        tipoOk &= tipo instanceof TipoLista;
        
        if (temProximoGerador()) {
            tipoOk &= proximo.checaTipo(amb);
        }
        
        return tipoOk;
    }

    public Gerador reduzir(AmbienteExecucao ambiente) {
        this.expressao = this.expressao.reduzir(ambiente);
        if (this.proximo != null) {
            this.proximo = this.proximo.reduzir(ambiente);
        }
        return this;
    }
    
    public Gerador clone() {
        Gerador novoProximo = proximo != null ? proximo.clone() : null;
        return new Gerador(id.clone(), expressao.clone());
    }
    
    public String toString() {
        return " for " + this.id + " in " + this.expressao;
    }
}