# PLP-PROJECT
Projeto da disciplina de Paradigmas de Linguagem(IN-1007) de Programação do CIn - UFPE

# EQUIPE:
* João Pedro Monteiro Pereira jpmp2@cin.ufpe.br
* André Souza alssg@cin.ufpe.br

# Escopo do Projeto: Implementação de ValorMapa na Linguagem Funcional 3

## 1. Objetivo
Implementar um novo tipo de valor `ValorMapa` que permita armazenar e manipular pares chave-valor de forma funcional, mantendo a imutabilidade e transparência referencial da linguagem.

## 2. Gramática - Extensões Necessárias

// Adição ao ValorConcreto existente

[ValorConcreto](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorConcreto.java) ::= [ValorInteiro](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorInteiro.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorBooleano](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorBooleano.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorString](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorString.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorLista.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorMapa.java)

// Novas expressões para manipulação de mapas

// Compreensão de mapas

[ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)+ [[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)] "}"

[Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) ::= "for" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
                | "for" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [","] [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)

[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java) ::= "if" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)


// Definições das demais expressões

[ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "}"

[ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java) ::= "insert" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java) ::= "remove" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java) ::= "get" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java) ::= "contains" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java) ::= "keys" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java) ::= "values" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

// Uso das expressões

[ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java) ::= [ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)

## 3. Operações Suportadas

### 3.1. Criação
```
let var mapa = { 1 => 2, 3 => 4 } in ...
```

### 3.2. Inserção
```
let var novoMapa = insert(mapa, 4, 5) in ...
```

### 3.3. Remoção
```
let var semB = remove(mapa, 1) in ...
```

### 3.4. Consulta
```
let var valor = get(mapa, 3) in ...
let var existe = contains(mapa, 3) in ...
```

### 3.5. Coleções
```
let var chaves = keys(mapa) in ...
let var valores = values(mapa) in ...
```

## 4. Exemplos de Uso

```
// Criação e manipulação de mapa
let var mapa = { 1 => 2, 3 => 4 } in
let var comTres = insert(mapa, 4, 5) in
let var semDois = remove(comTres, 1) in
let var temUm = contains(semDois, 3) in
let var valor = get(semDois, 4) in
let var todasChaves = keys(semDois) in
valor

// Exemplo com mapas aninhados
let var configuracoes = {
    "cores" => {
        "primaria" => "azul",
        "secundaria" => "vermelho"
    },
    "tamanhos" => {
        "pequeno" => "10px",
        "grande" => "20px"
    }
} in
get(get(configuracoes, "cores"), "primaria")
```

### 4.1 ExpCompreensaoMapa (Compreensão de Mapa)
```
// Sem filtro
let var quadrados = { x => x * x for x in [1,2,3] } in
// Resultado: {1 => 1, 2 => 4, 3 => 9}

// Com filtro
let var pares = { x => x * 2 for x in [1,2,3,4,5] if x > 2 } in
// Resultado: {3 => 6, 4 => 8, 5 => 10}
```

### 4.2 Exemplos Complexos Combinando os Conceitos
```
// Criando mapa com compreensão e filtro
let var dados = [1, 2, 3, 4, 5] in
let var mapa = { 
    x => x * x 
    for x in dados 
    if x > 2 
} in
```

## 5 BNF Completa:
[Programa](PLP/Funcional3/src/lf3/plp/functional3/Programa.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ::= [Valor](PLP/Funcional3/src/lf3/plp/expressions2/expression/Valor.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpUnaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpUnaria.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpBinaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpBinaria.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpDeclaracao](PLP/Funcional3/src/lf3/plp/functional2/expression/ExpDeclaracao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Aplicacao](PLP/Funcional3/src/lf3/plp/functional2/expression/Aplicacao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [IfThenElse](PLP/Funcional3/src/lf3/plp/functional1/expression/IfThenElse.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java)

[Valor](PLP/Funcional3/src/lf3/plp/expressions2/expression/Valor.java) ::= [ValorConcreto](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorConcreto.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorAbstrato](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorAbstrato.java)

[ValorAbstrato](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorAbstrato.java) ::= [ValorFuncao](PLP/Funcional3/src/lf3/plp/functional2/expression/ValorFuncao.java)

[ValorConcreto](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorConcreto.java) ::= [ValorInteiro](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorInteiro.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorBooleano](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorBooleano.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorString](PLP/Funcional3/src/lf3/plp/expressions2/expression/ValorString.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorLista.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ValorMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ValorMapa.java)

[ValorFuncao](PLP/Funcional3/src/lf3/plp/functional2/expression/ValorFuncao.java) ::= "fn" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "." [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[ExpUnaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpUnaria.java) ::= [ExpMenos](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpMenos.java) "-" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpNot](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpNot.java) "not" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpLength](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpLength.java) "length" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpHead](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpHead.java) "head" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpTail](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpTail.java) "tail" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpCompreensaoLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoLista.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)

[ExpCompreensaoLista](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoLista.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) 

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) [Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)

[ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)+ [[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)] "}"

[Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java) ::= "for" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)
                | "for" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [","] [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)

[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java) ::= "if" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[ExpBinaria](PLP/Funcional3/src/lf3/plp/expressions2/expression/ExpBinaria.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "+" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "-" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "*" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ">" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "<" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "and" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "or" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "==" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "++" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ".." [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ":" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "^^" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

// Definições das expressões

[ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "}"

[ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java) ::= "insert" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java) ::= "remove" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java) ::= "get" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java) ::= "contains" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java) ::= "keys" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java) ::= "values" "(" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) ")"

[ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java) ::= "{" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "=>" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) [Gerador](PLP/Funcional3/src/lf3/plp/functional3/expression/Gerador.java)+ [[Filtro](PLP/Funcional3/src/lf3/plp/functional3/expression/Filtro.java)] "}"

// Uso das expressões

[ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java) ::= [ExpMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpMapa.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpInsert](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpInsert.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpRemove](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpRemove.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpGet](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpGet.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpContains](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpContains.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpKeys](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpKeys.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpValues](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpValues.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [ExpCompreensaoMapa](PLP/Funcional3/src/lf3/plp/functional3/expression/ExpCompreensaoMapa.java)

[ExpDeclaracao](PLP/Funcional3/src/lf3/plp/functional2/expression/ExpDeclaracao.java) ::= "let" [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) "in" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) ::= [DecVariavel](PLP/Funcional3/src/lf3/plp/functional1/declaration/DecVariavel.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [DecFuncao](PLP/Funcional3/src/lf3/plp/functional2/declaration/DecFuncao.java)

&emsp; &emsp; &emsp; &emsp;  &ensp;| [DecComposta](PLP/Funcional3/src/lf3/plp/functional3/declaration/DecComposta.java)

[DecVariavel](PLP/Funcional3/src/lf3/plp/functional1/declaration/DecVariavel.java) ::= "var" [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "=" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DecFuncao](PLP/Funcional3/src/lf3/plp/functional2/declaration/DecFuncao.java) ::= "fun" [ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java) "=" [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java)

[DecComposta](PLP/Funcional3/src/lf3/plp/functional3/declaration/DecComposta.java) ::= [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java) "," [DeclaracaoFuncional](PLP/Funcional3/src/lf3/plp/functional1/declaration/DeclaracaoFuncional.java)

[ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java) ::= [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) | [Id](PLP/Funcional3/src/lf3/plp/expressions2/expression/Id.java) "," [ListId](PLP/Funcional3/src/lf3/plp/functional2/declaration/ListId.java)

[Aplicacao](PLP/Funcional3/src/lf3/plp/functional2/expression/Aplicacao.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "(" [ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java) ")"

[ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java) ::= [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) | [Expressao](PLP/Funcional3/src/lf3/plp/expressions2/expression/Expressao.java) "," [ListExp](PLP/Funcional3/src/lf3/plp/functional2/expression/ListExp.java)

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
