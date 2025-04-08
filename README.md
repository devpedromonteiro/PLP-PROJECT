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
ExpMapa ::= "map" "{" ListaKeyValue "}" 
          | "insert" "(" Expressao "," Expressao "," Expressao ")"
          | "remove" "(" Expressao "," Expressao ")"
          | "get" "(" Expressao "," Expressao ")"
          | "contains" "(" Expressao "," Expressao ")"
          | "keys" "(" Expressao ")"
          | "values" "(" Expressao ")"
          | ExpCompreensaoMapa

ListaKeyValue ::= KeyValue 
                | KeyValue "," ListaKeyValue

KeyValue ::= Expressao "=>" Expressao

// Nova expressão para compreensão de mapa
ExpCompreensaoMapa ::= "map" "{" Expressao "=>" Expressao Gerador "}"
                     | "map" "{" Expressao "=>" Expressao Gerador Filtro "}"
```

## 3. Operações Suportadas

### 3.1. Criação
```
let mapa = map { "a" => 1, "b" => 2 } in ...
```

### 3.2. Inserção
```
let novoMapa = insert(mapa, "c", 3) in ...
```

### 3.3. Remoção
```
let semB = remove(mapa, "b") in ...
```

### 3.4. Consulta
```
let valor = get(mapa, "a") in ...
let existe = contains(mapa, "b") in ...
```

### 3.5. Coleções
```
let chaves = keys(mapa) in ...
let valores = values(mapa) in ...
```

## 4. Exemplos de Uso

```
// Criação e manipulação de mapa
let mapa = map { "um" => 1, "dois" => 2 } in
let comTres = insert(mapa, "tres", 3) in
let semDois = remove(comTres, "dois") in
let temUm = contains(semDois, "um") in
let valor = get(semDois, "tres") in
let todasChaves = keys(semDois) in
valor

// Exemplo com funções
let usuarios = map { 
    "joao" => map { "idade" => 25, "email" => "joao@email.com" },
    "maria" => map { "idade" => 30, "email" => "maria@email.com" }
} in
get(get(usuarios, "joao"), "idade")
```
### 4.1 ExpCompreensaoMapa (Compreensão de Mapa)
```
// Sem filtro
let quadrados = map { x => x * x for x in [1,2,3] } in
// Resultado: {1 => 1, 2 => 4, 3 => 9}

// Com filtro
let pares = map { x => x * 2 for x in [1,2,3,4,5] if x % 2 == 0 } in
// Resultado: {2 => 4, 4 => 8}
```

### 4.1.2 Exemplos Complexos Combinando os Conceitos
```
// Criando mapa com compreensão e filtro
let dados = [1, 2, 3, 4, 5] in
let mapaPares = map { 
    x => x * x 
    for x in dados 
    if x % 2 == 0 
} in

// Mapa aninhado com múltiplos pares
let usuarios = map {
    "admin" => map {
        "nome" => "João",
        "permissoes" => ["ler", "escrever"]
    },
    "usuario" => map {
        "nome" => "Maria",
        "permissoes" => ["ler"]
    }
} in
get(get(usuarios, "admin"), "permissoes")
```

## 5 BNF Completa:
```
Programa ::= Expressao

Expressao ::= Valor
            | ExpUnaria
            | ExpBinaria
            | ExpDeclaracao
            | Id
            | Aplicacao
            | IfThenElse
            | ExpMapa            // Nova expressão

Valor ::= ValorConcreto | ValorAbstrato

ValorAbstrato ::= ValorFuncao

ValorConcreto ::= ValorInteiro 
                | ValorBooleano 
                | ValorString 
                | ValorLista
                | ValorMapa     // Novo tipo concreto

ValorFuncao ::= "fn" Id Id "." Expressao

ExpUnaria ::= "-" Expressao 
            | "not" Expressao 
            | "length" Expressao
            | head(Expressao) 
            | tail(Expressao)
            | ExpCompreensaoLista
            | ExpCompreensaoMapa    // Nova expressão unária

ExpCompreensaoLista ::= Expressao Gerador 
                      | Expressao Gerador Filtro

ExpCompreensaoMapa ::= "map" "{" Expressao "=>" Expressao Gerador "}"
                     | "map" "{" Expressao "=>" Expressao Gerador Filtro "}"

Gerador ::= "for" Id "in" Expressao
          | "for" Id "in" Expressao [","] Gerador

Filtro ::= "if" Expressao

ExpBinaria ::= Expressao "+" Expressao
             | Expressao "-" Expressao
             | Expressao "*" Expressao
             | Expressao ">" Expressao
             | Expressao "<" Expressao
             | Expressao "and" Expressao
             | Expressao "or" Expressao
             | Expressao "==" Expressao
             | Expressao "++" Expressao
             | Expressao ".." Expressao
             | Expressao ":" Expressao
             | Expressao "^^" Expressao

ExpMapa ::= "map" "{" ListaKeyValue "}"          // Criação de mapa
          | "insert" "(" Expressao "," Expressao "," Expressao ")"  // Inserção
          | "remove" "(" Expressao "," Expressao ")"                // Remoção
          | "get" "(" Expressao "," Expressao ")"                   // Consulta
          | "contains" "(" Expressao "," Expressao ")"              // Verificação
          | "keys" "(" Expressao ")"                                // Lista de chaves
          | "values" "(" Expressao ")"                              // Lista de valores
          | ExpCompreensaoMapa

ListaKeyValue ::= KeyValue 
                | KeyValue "," ListaKeyValue

KeyValue ::= Expressao "=>" Expressao

ExpDeclaracao ::= "let" DeclaracaoFuncional "in" Expressao

DeclaracaoFuncional ::= DecVariavel
                      | DecFuncao
                      | DecComposta

DecVariavel ::= "var" Id "=" Expressao

DecFuncao ::= "fun" ListId "=" Expressao

DecComposta ::= DeclaracaoFuncional "," DeclaracaoFuncional

ListId ::= Id | Id "," ListId

Aplicacao ::= Expressao "(" ListExp ")"

ListExp ::= Expressao | Expressao "," ListExp
```

Este escopo fornece uma base para implementação do `ValorMapa`, mantendo a natureza funcional da linguagem e adicionando funcionalidade útil para manipulação de dados estruturados além da adição da nova expressão ExpCompreensaoMapa.
