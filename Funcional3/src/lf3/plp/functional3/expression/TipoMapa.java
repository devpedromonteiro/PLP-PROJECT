package lf3.plp.functional3.expression;

import lf3.plp.expressions1.util.Tipo;
import lf3.plp.expressions1.util.TipoPrimitivo;

public class TipoMapa implements Tipo {
    private Tipo tipoChave;
    private Tipo tipoValor;
    
    public TipoMapa() {
        // Tipo vazio (para mapas vazios)
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
    public String getNome() {
        return "Mapa";
    }
    
    @Override
    public boolean eInteiro() {
        return false;
    }
    
    @Override
    public boolean eBooleano() {
        return false;
    }
    
    @Override
    public boolean eString() {
        return false;
    }
    
    @Override
    public boolean eValido() {
        return true;
    }
    
    @Override
    public boolean eIgual(Tipo tipo) {
        if (!(tipo instanceof TipoMapa)) {
            return false;
        }
        
        TipoMapa outroMapa = (TipoMapa) tipo;
        if (tipoChave == null || tipoValor == null || 
            outroMapa.tipoChave == null || outroMapa.tipoValor == null) {
            return true; // Mapas vazios são compatíveis com qualquer mapa
        }
        
        return tipoChave.eIgual(outroMapa.tipoChave) && 
               tipoValor.eIgual(outroMapa.tipoValor);
    }
    
    @Override
    public Tipo intersecao(Tipo outroTipo) {
        if (outroTipo instanceof TipoMapa) {
            TipoMapa outroMapa = (TipoMapa) outroTipo;
            
            Tipo intersecaoChave = tipoChave != null ? 
                tipoChave.intersecao(outroMapa.tipoChave) : outroMapa.tipoChave;
            Tipo intersecaoValor = tipoValor != null ? 
                tipoValor.intersecao(outroMapa.tipoValor) : outroMapa.tipoValor;
            
            if (intersecaoChave != null && intersecaoValor != null) {
                return new TipoMapa(intersecaoChave, intersecaoValor);
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        if (tipoChave == null || tipoValor == null) {
            return "Mapa";
        }
        return "Mapa[" + tipoChave + " => " + tipoValor + "]";
    }
}