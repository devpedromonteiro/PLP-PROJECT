package lf3.plp.functional3.expression;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.ArrayList;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions2.expression.Expressao;
import lf3.plp.expressions2.expression.Id;
import lf3.plp.expressions2.expression.Valor;
import lf3.plp.expressions2.memory.AmbienteCompilacao;
import lf3.plp.expressions2.memory.AmbienteExecucao;
import lf3.plp.expressions2.memory.VariavelJaDeclaradaException;
import lf3.plp.expressions2.memory.VariavelNaoDeclaradaException;
import lf3.plp.functional3.util.TipoMapa;

public class ExpCompreensaoMapa implements Expressao {

    private Expressao chave;
    private Expressao valor;
    private Expressao filtro;
    private Gerador gerador;

    public ExpCompreensaoMapa(Expressao chave, Expressao valor) {
        this.chave = chave;
        this.valor = valor;
    }

    public void setFiltro(Expressao filtro) {
        this.filtro = filtro;
    }

    public void add(Gerador gerador) {
        if (this.gerador == null) {
            this.gerador = gerador;
        } else {
            this.gerador.addProximoGerador(gerador);
        }
    }

    public void setGeradores(List<Gerador> geradores) {
        for (Gerador geradorTemp : geradores) {
            this.add(geradorTemp);
        }
    }


    @Override
    public Valor avaliar(AmbienteExecucao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        ValorMapa resultado = ValorMapa.getInstancia();

        gerador.gerarValoresMapa(amb, resultado, chave, valor, filtro);

        return resultado;
    }

    @Override
    public boolean checaTipo(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {
        if (!temGerador())
            return false;

        boolean retorno = gerador.checaTipo(amb);

        amb.incrementa();

        mapTypeBindings(amb);

        retorno &= chave.checaTipo(amb);
        retorno &= valor.checaTipo(amb);
        retorno &= filtroChecaTipo(amb);

        amb.restaura();

        return retorno;
    }

    private void mapTypeBindings(AmbienteCompilacao amb) {
        Map<Id, Tipo> typeBindings = gerador.checkTypeBindings(amb);
        Set<Entry<Id, Tipo>> entrySet = typeBindings.entrySet();
        for (Entry<Id, Tipo> entry : entrySet) {
            amb.map(entry.getKey(), entry.getValue());
        }
    }

    private boolean temGerador() {
        return gerador != null;
    }

    private boolean filtroChecaTipo(AmbienteCompilacao amb) {
        return filtro == null || filtro.checaTipo(amb)
                && filtro.getTipo(amb).eBooleano();
    }

    @Override
    public Tipo getTipo(AmbienteCompilacao amb)
            throws VariavelNaoDeclaradaException, VariavelJaDeclaradaException {

        amb.incrementa();

        mapTypeBindings(amb);

        TipoMapa retorno = new TipoMapa(chave.getTipo(amb), valor.getTipo(amb));

        amb.restaura();

        return retorno;
    }

    @Override
    public ExpCompreensaoMapa clone() {
        ExpCompreensaoMapa retorno = new ExpCompreensaoMapa(this.chave.clone(), this.valor.clone());
        retorno.setFiltro(this.filtro.clone());

        List<Gerador> listaGer = new ArrayList<Gerador>();

        Gerador ger = this.gerador;
        if (ger != null) {
            listaGer.add(ger);
            while (ger.temProximoGerador()) {
                ger = ger.getProximoGerador();
                listaGer.add(ger);
            }
        }

        retorno.setGeradores(listaGer);

        return retorno;
    }

    @Override
    public Expressao reduzir(AmbienteExecucao ambiente) {
        ambiente.incrementa();

        Gerador ger = this.gerador;
        while (ger.temProximoGerador()) {
            ger.reduzir(ambiente);

            ger = ger.getProximoGerador();
        }

        this.chave = this.chave.reduzir(ambiente);
        this.valor = this.valor.reduzir(ambiente);
        if (this.filtro != null) {
            this.filtro = this.filtro.reduzir(ambiente);
        }

        ambiente.restaura();

        return this;
    }

    @Override
    public String toString() {
        String aux = this.chave + " => " + this.valor;

        Gerador ger = this.gerador;
        aux += ger.toString();

        while (ger.temProximoGerador()) {
            ger = ger.getProximoGerador();

            aux += ", " + ger.toString();
        }

        if (this.filtro != null) {
            aux += " if " + this.filtro;
        }

        return aux;
    }
}