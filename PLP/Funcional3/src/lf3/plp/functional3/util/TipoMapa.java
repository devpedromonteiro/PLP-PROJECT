package lf3.plp.functional3.util;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.functional1.util.TipoPolimorfico;

public class TipoMapa implements Tipo {

    private Tipo tipoChave;
    private Tipo tipoValor;

    public TipoMapa() {
        this.tipoChave = new TipoPolimorfico();
        this.tipoValor = new TipoPolimorfico();
    }

    public TipoMapa(Tipo tipoChave, Tipo tipoValor) {
        this.tipoChave = tipoChave;
        this.tipoValor = tipoValor;
    }

    public Tipo getTipoChave() {
        return tipoChave;
    }

    public Tipo getTipoValor() {
        return tipoValor;
    }

    @Override
    public boolean eBooleano() {
        return false;
    }

    @Override
    public boolean eInteiro() {
        return false;
    }

    @Override
    public boolean eString() {
        return false;
    }

    @Override
    public boolean eValido() {
        return tipoChave.eValido() && tipoValor.eValido();
    }

    @Override
    public boolean eIgual(Tipo tipo) {
        if (tipo instanceof TipoMapa) {
            TipoMapa outroMapa = (TipoMapa) tipo;
            return tipoChave.eIgual(outroMapa.tipoChave) &&
                    tipoValor.eIgual(outroMapa.tipoValor);
        }
        return false;
    }

    @Override
    public String getNome() {
        return "{" + tipoChave.getNome() + " => " + tipoValor.getNome() + "}";
    }

    @Override
    public Tipo intersecao(Tipo outroTipo) {
        if (outroTipo instanceof TipoMapa) {
            TipoMapa outroMapa = (TipoMapa) outroTipo;
            Tipo intersecaoChave = tipoChave.intersecao(outroMapa.tipoChave);
            Tipo intersecaoValor = tipoValor.intersecao(outroMapa.tipoValor);

            if (intersecaoChave != null && intersecaoValor != null) {
                return new TipoMapa(intersecaoChave, intersecaoValor);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getNome();
    }
}