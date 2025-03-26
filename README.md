# PLP-PROJECT
# Escopo do Projeto: Implementação de ValorMapa na Linguagem Funcional 3

## 1. Objetivo
Implementar um novo tipo de valor `ValorMapa` que permita armazenar e manipular pares chave-valor de forma funcional, mantendo a imutabilidade e transparência referencial da linguagem.

## 2. Gramática - Extensões Necessárias

```bnf
// Adição ao ValorConcreto existente
ValorConcreto ::= ValorInteiro | ValorBooleano | ValorString | ValorLista | ValorMapa

// Novas expressões para manipulação de mapas
ExpMapa ::= "map" "{" ListaKeyValue "}" 
          | "insert" "(" Expressao "," Expressao "," Expressao ")"
          | "remove" "(" Expressao "," Expressao ")"
          | "get" "(" Expressao "," Expressao ")"
          | "contains" "(" Expressao "," Expressao ")"
          | "keys" "(" Expressao ")"
          | "values" "(" Expressao ")"

ListaKeyValue ::= KeyValue 
                | KeyValue "," ListaKeyValue

KeyValue ::= Expressao "=>" Expressao
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

Este escopo fornece uma base para implementação do `ValorMapa`, mantendo a natureza funcional da linguagem e adicionando funcionalidade útil para manipulação de dados estruturados.
