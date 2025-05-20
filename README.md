# PLP-PROJECT
Projeto da disciplina de Paradigmas de Linguagem(IN-1007) de Programação do CIn - UFPE

# EQUIPE:
* João Pedro Monteiro Pereira jpmp2@cin.ufpe.br
* André Souza alssg@cin.ufpe.br

# Escopo do Projeto: Implementação de ValorMapa na Linguagem Funcional 3

## 1. Objetivo
Implementar um novo tipo de valor `ValorMapa` que permita armazenar e manipular pares chave-valor de forma funcional, mantendo a imutabilidade e transparência referencial da linguagem.

## 2. Gramática - Extensões Necessárias

```bnf
// Adição ao ValorConcreto existente
ValorConcreto ::= ValorInteiro 
                | ValorBooleano 
                | ValorString 
                | ValorLista 
                | ValorMapa

// Novas expressões para manipulação de mapas
ExpMapa ::= "{" Expressao "=>" Expressao "}" 
          | "insert" "(" Expressao "," Expressao "," Expressao ")"
          | "remove" "(" Expressao "," Expressao ")"
          | "get" "(" Expressao "," Expressao ")"
          | "contains" "(" Expressao "," Expressao ")"
          | "keys" "(" Expressao ")"
          | "values" "(" Expressao ")"
          | ExpCompreensaoMapa

// Nova expressão para compreensão de mapa
ExpCompreensaoMapa ::= "{" Expressao "=>" Expressao Gerador+ [Filtro] "}"

Gerador ::= "for" Id "in" Expressao [","]

Filtro ::= "if" Expressao
```

## 3. Operações Suportadas

### 3.1. Criação
```
let mapa = { 1 => 2, 3 => 4 } in ...
```

### 3.2. Inserção
```
let novoMapa = insert(mapa, 4, 5) in ...
```

### 3.3. Remoção
```
let semB = remove(mapa, 1) in ...
```

### 3.4. Consulta
```
let valor = get(mapa, 3) in ...
let existe = contains(mapa, 3) in ...
```

### 3.5. Coleções
```
let chaves = keys(mapa) in ...
let valores = values(mapa) in ...
```

## 4. Exemplos de Uso

```
// Criação e manipulação de mapa
let mapa = { 1 => 2, 3 => 4 } in
let comTres = insert(mapa, 4, 5) in
let semDois = remove(comTres, 1) in
let temUm = contains(semDois, 3) in
let valor = get(semDois, 4) in
let todasChaves = keys(semDois) in
valor

// Exemplo com mapas aninhados
let configuracoes = { 
    "cores" => { "primaria" => "azul", "secundaria" => "vermelho" },
    "tamanhos" => { "pequeno" => "10px", "grande" => "20px" }
} in
get(get(configuracoes, "cores"), "primaria")
```

### 4.1 ExpCompreensaoMapa (Compreensão de Mapa)
```
// Sem filtro
let quadrados = { x => x * x for x in [1,2,3] } in
// Resultado: {1 => 1, 2 => 4, 3 => 9}

// Com filtro
let pares = { x => x * 2 for x in [1,2,3,4,5] if x > 2 } in
// Resultado: {3 => 6, 4 => 8, 5 => 10}
```

### 4.2 Exemplos Complexos Combinando os Conceitos
```
// Criando mapa com compreensão e filtro
let dados = [1, 2, 3, 4, 5] in
let mapa = { 
    x => x * x 
    for x in dados 
    if x > 2 
} in

// Mapa aninhado com múltiplos pares
let configuracoes = {
    "cores" => {
        "primaria" => "azul",
        "secundaria" => "vermelho"
    },
    "tamanhos" => {
        "pequeno" => 10,
        "grande" => 20
    }
} in
get(get(configuracoes, "cores"), "primaria")
```

## 5 BNF Completa:
```
[Programa](PLP/Funcional3/src/lf3/plp/functional3/Programa.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ::= [Valor](PLP/Funcional3/src/lf3/plp/expressions2/expression/Valor.java)
            | [ExpUnaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpUnaria.java)
            | [ExpBinaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpBinaria.java)
            | [ExpDeclaracao](PLP/Funcional3/src/lf3/plp/functional2/expression/ExpDeclaracao.java)
            | [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java)
            | [Aplicacao](PLP/Funcional3/src/lf3/plp/functional2/expression/Aplicacao.java)
            | [IfThenElse](PLP/Funcional3/src/lf3/plp/functional1/expression/IfThenElse.java)
            | [ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java)

[Valor](PLP/Funcional3/src/lf3/plp/expressions2/expression/Valor.java) ::= [ValorConcreto](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorConcreto.java) | [ValorAbstrato](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorAbstrato.java)

[ValorAbstrato](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorAbstrato.java) ::= [ValorFuncao](PLP/Funcional3/src/lf3/plp/functional2/expression/ValorFuncao.java)

[ValorConcreto](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorConcreto.java) ::= [ValorInteiro](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorInteiro.java) 
                | [ValorBooleano](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorBooleano.java) 
                | [ValorString](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorString.java) 
                | [ValorLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorLista.java)
                | [ValorMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorMapa.java)

[ValorFuncao](PLP/Funcional3/src/lf3/plp/functional2/expression/ValorFuncao.java) ::= "fn" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "." [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[ExpUnaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpUnaria.java) ::= [ExpMenos](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpMenos.java) "-" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 
            | [ExpNot](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpNot.java) "not" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 
            | [ExpLength](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpLength.java) "length" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
            | [ExpHead](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpHead.java) "head" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 
            | [ExpTail](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpTail.java) "tail" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
            | [ExpCompreensaoLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoLista.java)
            | [ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)

[ExpCompreensaoLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoLista.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) 
                      | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) [Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)

[ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)+ [[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)] "}"

[Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) ::= "for" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [","]

[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java) ::= "if" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[ExpBinaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpBinaria.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "+" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "-" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "*" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ">" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "<" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "and" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "or" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "==" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "++" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ".." [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ":" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
             | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "^^" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "}"          // Criação de mapa
          | [ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java) "insert" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"  // Inserção
          | [ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java) "remove" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"                // Remoção
          | [ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java) "get" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"                   // Consulta
          | [ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java) "contains" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"              // Verificação
          | [ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java) "keys" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"                                // Lista de chaves
          | [ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java) "values" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"                              // Lista de valores
          | [ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)

[ExpDeclaracao](PLP/Funcional3/src/lf3/plp/functional2/expression/ExpDeclaracao.java) ::= "let" [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) ::= [DecVariavel](PLP/Funcional3/src/lf3/plp/functional1/declaration/DecVariavel.java)
                      | [DecFuncao](PLP/Funcional3/src/lf3/plp/functional2/declaration/DecFuncao.java)
                      | [DecComposta](PLP/Funcional3/src/lf3/plp/functional3/declaration/DecComposta.java)

[DecVariavel](PLP/Funcional3/src/lf3/plp/functional1/declaration/DecVariavel.java) ::= "var" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "=" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DecFuncao](PLP/Funcional3/src/lf3/plp/functional2/declaration/DecFuncao.java) ::= "fun" [ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java) "=" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DecComposta](PLP/Funcional3/src/lf3/plp/functional3/declaration/DecComposta.java) ::= [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) "," [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java)

[ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java) ::= [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) | [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "," [ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java)

[Aplicacao](PLP/Funcional3/src/lf3/plp/functional2/expression/Aplicacao.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "(" [ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java) ")"

[ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java)
```

### Classes Principais
[ValorMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorMapa.java)  
[ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)  
[ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java)  
[ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java)  
[ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java)  
[ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java)  
[ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java)  
[ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java)  

### Parser
[Funcional3](PLP/Funcional3/src/lf3/plp/functional3/parser/Funcional3.jj)
