package lf3.plp.functional3.expression;

import java.util.List;
import lf3.plp.expressions2.expression.Expressao;

public class ExpCompreensaoMapa implements Expressao {
    private Expressao chaveExpr;
    private Expressao valorExpr;
    private List<Gerador> geradores;
    private Expressao filtro;

    public ExpCompreensaoMapa(Expressao chaveExpr, Expressao valorExpr, 
                             List<Gerador> geradores, Expressao filtro) {
        this.chaveExpr = chaveExpr;
        this.valorExpr = valorExpr;
        this.geradores = geradores;
        this.filtro = filtro;
    }

    @Override
    public Valor avaliar() {
        // Implementação da avaliação da compreensão de mapa
        return null;
    }
}