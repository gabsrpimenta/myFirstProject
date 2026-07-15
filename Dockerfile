# ---------- ETAPA 1: build ----------
FROM eclipse-temurin:21-jdk AS build

WORKDIR /workspace

# Copia primeiro os arquivos de configuração do Gradle (para aproveitar cache de camadas)
COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY app/build.gradle.kts app/build.gradle.kts

# Baixa as dependências antes de copiar o código-fonte
RUN chmod +x gradlew
RUN ./gradlew --no-daemon dependencies || true

# Agora copia o restante do código-fonte
COPY app app

# Compila e empacota a aplicação, pulando os testes (eles já rodam no CI, em outra etapa)
RUN ./gradlew --no-daemon :app:bootJar -x test

# ---------- ETAPA 2: runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia só o .jar final, gerado na etapa anterior
COPY --from=build /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]