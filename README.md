# TechConference — Gerenciamento de Propostas de Palestras

Projeto desenvolvido como laboratório avaliativo de **Spring Boot MVC**, com o objetivo de implementar uma aplicação web para cadastro, consulta e gerenciamento de propostas de palestras para a TechConference.

O projeto utiliza **Spring MVC, Thymeleaf, Jakarta Bean Validation, BindingResult e regras de negócio em classes de domínio**, com armazenamento dos dados em memória e sem utilização de banco de dados.

## 📋 Sobre o projeto

A **TechConference** recebe propostas de palestras relacionadas às áreas de:

* Desenvolvimento
* Cloud
* Segurança
* Dados
* Inteligência Artificial

A aplicação permite cadastrar novas propostas, consultar as propostas cadastradas, visualizar seus detalhes e realizar o gerenciamento do status das propostas.

Cada nova proposta é criada inicialmente com o status **EM_ANALISE**.

## 🚀 Tecnologias utilizadas

* Java
* Spring Boot
* Spring MVC
* Thymeleaf
* Jakarta Bean Validation
* Lombok
* Maven
* HTML

## 🏗️ Estrutura do projeto

```text
br.edu.techconference
├── controller
│   └── PropostaController.java
│
├── model
│   ├── Proposta.java
│   ├── Categoria.java
│   ├── Nivel.java
│   └── StatusProposta.java
│
└── repository
    └── PropostaRepository.java

templates
└── propostas
    ├── lista.html
    ├── formulario.html
    └── detalhes.html
```

## 📦 Modelo Proposta

A entidade `Proposta` possui os seguintes atributos:

| Campo              | Descrição                 |
| ------------------ | ------------------------- |
| `id`               | Identificador da proposta |
| `titulo`           | Título da palestra        |
| `descricao`        | Descrição da palestra     |
| `palestrante`      | Nome do palestrante       |
| `emailPalestrante` | E-mail do palestrante     |
| `categoria`        | Categoria da palestra     |
| `duracaoMinutos`   | Duração da palestra       |
| `nivel`            | Nível da palestra         |
| `status`           | Status atual da proposta  |

### Categorias

```text
BACKEND
FRONTEND
CLOUD
DEVOPS
SEGURANCA
DADOS
INTELIGENCIA_ARTIFICIAL
```

### Níveis

```text
INICIANTE
INTERMEDIARIO
AVANCADO
```

### Status

```text
EM_ANALISE
APROVADA
REJEITADA
```

Toda nova proposta começa com o status `EM_ANALISE`.

## ✅ Validações

A aplicação utiliza **Jakarta Bean Validation** para validar os dados informados no formulário.

As principais regras são:

* **Descrição:** obrigatória, entre 20 e 500 caracteres.
* **Palestrante:** obrigatório, com no mínimo 3 caracteres.
* **E-mail:** obrigatório e deve possuir formato válido.
* **Categoria:** obrigatória.
* **Duração:** obrigatória, entre 20 e 120 minutos.
* **Nível:** obrigatório.

Quando existem erros de validação, o formulário é apresentado novamente e os erros são exibidos abaixo dos respectivos campos.

## 🌐 Funcionalidades

### Cadastro de proposta

A aplicação possui um formulário para cadastrar uma nova proposta.

No formulário são utilizados recursos do Thymeleaf como:

```html
th:action
th:object
th:field
th:each
```

Categoria e nível são selecionados através de campos `select`.

O status da proposta não pode ser definido diretamente pelo usuário.

### Listagem

A tela de listagem apresenta as propostas cadastradas contendo:

* ID
* Título
* Palestrante
* Categoria
* Nível
* Status
* Link para visualizar os detalhes

A listagem utiliza `th:each` para percorrer as propostas.

### Detalhes

A tela de detalhes apresenta todas as informações de uma proposta específica.

Também são disponibilizadas as ações de aprovação e rejeição quando a proposta estiver com status:

```text
EM_ANALISE
```

### Aprovação e rejeição

Uma proposta somente pode ser aprovada ou rejeitada enquanto estiver em análise.

Foram implementados os endpoints:

```text
POST /propostas/{id}/aprovar
POST /propostas/{id}/rejeitar
```

A alteração do status é realizada através dos métodos da própria classe de domínio, evitando que o Controller altere o status diretamente com `setStatus(...)`.

## 🔄 Fluxo da aplicação

O fluxo principal do cadastro funciona da seguinte maneira:

```text
Usuário
   ↓
Formulário Thymeleaf
   ↓
PropostaController
   ↓
@Valid + BindingResult
   ↓
┌─────────────────────┐
│ Existem erros?      │
└─────────────────────┘
       ↓ Sim
Formulário novamente
       │
       └───────────────┐
                       ↓ Não
                PropostaRepository
                       ↓
                    Salvar
                       ↓
              /propostas
```

## 🧩 Spring MVC

O projeto utiliza o padrão MVC:

### Model

Representa os dados e as regras de negócio da aplicação.

Exemplo:

```text
Proposta
Categoria
Nivel
StatusProposta
```

### Controller

Responsável por receber as requisições HTTP, preparar os dados necessários e direcionar o fluxo para as Views.

Exemplo:

```text
PropostaController
```

### View

Responsável pela apresentação das informações ao usuário.

Neste projeto, as Views são páginas HTML utilizando Thymeleaf:

```text
lista.html
formulario.html
detalhes.html
```

## 📝 Validação e BindingResult

O cadastro utiliza:

```java
@Valid
@ModelAttribute("proposta") Proposta proposta,
BindingResult result
```

O `@Valid` executa as validações definidas na classe `Proposta`.

O `BindingResult` recebe os possíveis erros encontrados durante a validação.

A aplicação verifica:

```java
if (result.hasErrors()) {
    // retorna para o formulário
}
```

Dessa forma, uma proposta com dados inválidos não é salva no repositório.

## 🎨 Thymeleaf

O projeto utiliza diferentes atributos do Thymeleaf.

### `th:object`

Define o objeto utilizado pelo formulário:

```html
<form th:object="${proposta}">
```

### `th:field`

Relaciona um campo HTML com um atributo do objeto:

```html
<input th:field="*{titulo}">
```

### `th:each`

Percorre uma lista:

```html
<tr th:each="proposta : ${propostas}">
```

### `th:if`

Exibe um elemento somente quando uma condição é verdadeira:

```html
<div th:if="${proposta.status == T(br.edu.techconference.model.StatusProposta).EM_ANALISE}">
```

### `th:errors`

Exibe uma mensagem de erro de validação:

```html
<span th:errors="*{titulo}"></span>
```

## ▶️ Como executar o projeto

### 1. Clonar o repositório

```bash
git clone <URL_DO_REPOSITORIO>
```

### 2. Entrar na pasta do projeto

```bash
cd <NOME_DO_PROJETO>
```

### 3. Executar com Maven

No Windows:

```bash
.\mvnw.cmd spring-boot:run
```

Ou, caso o Maven esteja instalado:

```bash
mvn spring-boot:run
```

### 4. Acessar a aplicação

Depois que a aplicação iniciar, acesse:

```text
http://localhost:8080/propostas
```

## 💾 Armazenamento

O projeto utiliza **armazenamento em memória**, portanto não é necessário configurar banco de dados para executar a aplicação.

Os dados cadastrados são perdidos quando a aplicação é encerrada.

## 🎯 Objetivos avaliados

O laboratório aborda os seguintes conhecimentos:

* Modelagem de classes
* Jakarta Bean Validation
* Spring MVC
* Controllers
* Model
* Thymeleaf
* Formulários HTML
* `@Valid`
* `@ModelAttribute`
* `BindingResult`
* Exibição de mensagens de erro
* Listagem de dados
* Página de detalhes
* Regras de negócio
* Armazenamento em memória

## 👨‍💻 Autor

**Ricardo Ongari Rodrigues**

Projeto desenvolvido para fins acadêmicos.
