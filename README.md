# Calculadora

Projeto de estudo em Java que evoluiu de uma calculadora simples via terminal para uma API REST com Spring Boot, containerizada com Docker e com um pipeline de CI/CD via GitHub Actions.

## Sobre o projeto

O projeto oferece duas formas de usar a mesma lógica de cálculo (soma, subtração, multiplicação e divisão):

1. **CLI (linha de comando)** — uma calculadora interativa via terminal.
2. **API REST** — endpoints HTTP que expõem as mesmas operações, construídos com Spring Boot.

Ambas compartilham a mesma classe de lógica de negócio (`Calculadora`), garantindo que o comportamento seja idêntico nos dois modos.

## Tecnologias utilizadas

- **Java 21**
- **Gradle** (com Kotlin DSL)
- **Spring Boot 3.3.4** (Web + Test)
- **JUnit 5** — testes unitários e de componente
- **Log4j2** — logging estruturado
- **Docker** — containerização (build multi-stage)
- **GitHub Actions** — pipeline de CI/CD

## Estrutura do projeto

```
myFirstProject/
├── app/
│   └── src/
│       ├── main/java/org/example/
│       │   ├── App.java                    # Calculadora via terminal (CLI)
│       │   ├── CalculatorApplication.java  # Ponto de entrada da API (Spring Boot)
│       │   ├── controller/
│       │   │   └── CalculatorController.java
│       │   ├── dto/
│       │   │   ├── CalculationRequest.java
│       │   │   └── CalculationResponse.java
│       │   └── service/
│       │       └── Calculadora.java        # Lógica de negócio (compartilhada)
│       └── test/java/org/example/
│           ├── controller/CalculatorControllerTest.java
│           ├── service/CalculadoraTest.java
│           └── AppTest.java
├── doc/insomnia/
│   └── insomnia_collection.json            # Coleção de requisições para testar a API
├── .github/workflows/
│   └── ci.yml                              # Pipeline de CI/CD
├── Dockerfile                              # Build multi-stage da imagem
└── build.gradle.kts
```

## Como rodar localmente

### Pré-requisitos

- Java 21
- Docker (opcional, para rodar via container)

### Opção 1 — Calculadora via terminal (CLI)

```bash
./gradlew run
```

### Opção 2 — API REST (Spring Boot)

```bash
./gradlew bootRun
```

A aplicação sobe em `http://localhost:8080`.

### Rodando os testes

```bash
./gradlew test
```

Isso executa os testes unitários (`CalculadoraTest`) e os testes de componente da API (`CalculatorControllerTest`), que simulam requisições HTTP reais usando `MockMvc`.

## Usando a API

### Base path

```
/v1/calculator
```

### Endpoints disponíveis

| Método | Rota                    | Operação        |
|--------|--------------------------|------------------|
| POST   | `/v1/calculator/sum`      | Soma             |
| POST   | `/v1/calculator/subtract` | Subtração        |
| POST   | `/v1/calculator/multiply` | Multiplicação    |
| POST   | `/v1/calculator/divide`   | Divisão          |

### Formato da requisição

```json
{
  "a": "10",
  "b": "5"
}
```

Os valores `a` e `b` são recebidos como `String` e validados internamente. Se algum não for um número válido, a API responde com status **400**.

### Formato da resposta

**Sucesso (200):**
```json
{
  "success": true,
  "result": 15.0
}
```

**Erro (400)** — número inválido ou divisão por zero:
```json
{
  "success": false,
  "message": "Erro: Não é possível dividir por zero!"
}
```

### Exemplo com curl

```bash
curl -X POST http://localhost:8080/v1/calculator/sum \
  -H "Content-Type: application/json" \
  -d '{"a": "10", "b": "5"}'
```

### Coleção do Insomnia

Uma coleção pronta com todos os endpoints está disponível em [`doc/insomnia/insomnia_collection.json`](doc/insomnia/insomnia_collection.json). Basta importar no Insomnia (**Import → From File**).

## Docker

### Build da imagem

```bash
docker build -t calculator-app .
```

A imagem é construída em duas etapas (multi-stage build): a primeira compila a aplicação com o JDK completo, e a segunda copia apenas o `.jar` final para uma imagem baseada apenas no JRE, mantendo a imagem final enxuta.

### Rodando o container

```bash
docker run -p 8080:8080 calculator-app
```

A aplicação fica disponível em `http://localhost:8080`, do mesmo jeito que rodando via `./gradlew bootRun`.

## CI/CD

O projeto conta com um pipeline de Integração Contínua configurado em [`.github/workflows/ci.yml`](.github/workflows/ci.yml), que roda automaticamente a cada `push` e a cada Pull Request aberto contra a `main`.

O pipeline executa dois jobs, em sequência:

1. **`test`** — configura o Java 21 e roda `./gradlew test`, garantindo que toda a suíte de testes (unitários e de componente) passa antes de qualquer outra etapa.
2. **`docker-build`** — depende do sucesso do job anterior; constrói a imagem Docker a partir do `Dockerfile`, validando que a aplicação também pode ser empacotada corretamente.

Se qualquer um dos jobs falhar, isso fica visível diretamente no Pull Request, antes de qualquer revisão manual.

## Histórico do projeto

Este projeto começou como uma calculadora simples via terminal e foi evoluindo em etapas:

1. Calculadora CLI com validação de entrada e logging (Log4j2).
2. Testes unitários com JUnit 5.
3. API REST com Spring Boot, DTOs, validação de entrada e testes de componente.
4. Containerização com Docker e pipeline de CI/CD com GitHub Actions.
