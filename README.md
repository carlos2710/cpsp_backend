# MS Operaciones

Microservicio backend empresarial desarrollado con **Java 21 + Spring Boot**, utilizando **Arquitectura Hexagonal**, orientado a operaciones transaccionales, integración con **Stored Procedures**, persistencia local para debugging y comunicación asíncrona mediante **Apache Kafka**.

---

## 📌 Descripción

`ms-operaciones` forma parte de una arquitectura de microservicios orientada a sistemas empresariales.

El microservicio está diseñado para:

* Exponer APIs REST.
* Ejecutar operaciones transaccionales mediante Stored Procedures.
* Separar la lógica de negocio de la infraestructura mediante Arquitectura Hexagonal.
* Registrar información técnica de debugging.
* Persistir información de debugging en H2 durante el desarrollo.
* Generar registros en archivos planos.
* Publicar eventos mediante Apache Kafka.
* Consumir eventos desde Kafka.
* Incorporar observabilidad mediante Spring Boot Actuator.
* Mantener una estructura preparada para pruebas automatizadas y despliegue mediante contenedores.

---

# 🏗️ Arquitectura

El proyecto utiliza **Arquitectura Hexagonal (Ports & Adapters)**.

```text
                         ┌──────────────────────┐
                         │      REST Client     │
                         └──────────┬───────────┘
                                    │
                                    ▼
                     ┌───────────────────────────┐
                     │     REST Controller       │
                     │       Adapter IN          │
                     └─────────────┬─────────────┘
                                   │
                                   ▼
                     ┌───────────────────────────┐
                     │       Application         │
                     │                           │
                     │      Use Cases             │
                     │      Input Ports           │
                     └─────────────┬─────────────┘
                                   │
                                   ▼
                     ┌───────────────────────────┐
                     │          Domain           │
                     │                           │
                     │ Entities / Value Objects  │
                     │ Business Rules             │
                     └─────────────┬─────────────┘
                                   │
                       Output Ports│
                                   │
             ┌─────────────────────┼──────────────────────┐
             │                     │                      │
             ▼                     ▼                      ▼
     ┌───────────────┐     ┌───────────────┐     ┌────────────────┐
     │ JDBC / SP     │     │ Debug Adapter │     │ Kafka Adapter  │
     │ Adapter OUT   │     │ H2 + File     │     │ Producer       │
     └───────┬───────┘     └───────────────┘     └───────┬────────┘
             │                                             │
             ▼                                             ▼
     ┌───────────────┐                            ┌────────────────┐
     │ Transaccional │                            │     Kafka      │
     │      DB       │                            │     Topics     │
     └───────────────┘                            └───────┬────────┘
                                                         │
                                                         ▼
                                                ┌────────────────┐
                                                │ Kafka Consumer │
                                                │    Adapter IN  │
                                                └────────────────┘
```

## Principios arquitectónicos

El proyecto busca aplicar los siguientes principios:

* Separation of Concerns.
* Dependency Inversion Principle.
* SOLID.
* Domain-driven design como criterio de organización del dominio.
* Ports & Adapters.
* Bajo acoplamiento entre dominio e infraestructura.
* Alta cohesión.
* Testabilidad.
* Configuración externa mediante perfiles y variables de entorno.

---

# 📁 Estructura del proyecto

La estructura propuesta es:

```text
ms-operaciones/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── pe/com/empresa/operaciones/
│   │   │       │
│   │   │       ├── OperacionesApplication.java
│   │   │       │
│   │   │       ├── domain/
│   │   │       │   ├── model/
│   │   │       │   └── service/
│   │   │       │
│   │   │       ├── application/
│   │   │       │   ├── port/
│   │   │       │   │   ├── in/
│   │   │       │   │   └── out/
│   │   │       │   │
│   │   │       │   └── service/
│   │   │       │
│   │   │       └── infrastructure/
│   │   │           ├── adapter/
│   │   │           │   ├── in/
│   │   │           │   │   ├── rest/
│   │   │           │   │   └── kafka/
│   │   │           │   │
│   │   │           │   └── out/
│   │   │           │       ├── persistence/
│   │   │           │       │   ├── jdbc/
│   │   │           │       │   └── h2/
│   │   │           │       │
│   │   │           │       ├── debug/
│   │   │           │       └── messaging/
│   │   │           │           └── kafka/
│   │   │           │
│   │   │           └── config/
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-local.yml
│   │       └── logback-spring.xml
│   │
│   └── test/
│
├── docs/
│   ├── architecture/
│   ├── database/
│   ├── kafka/
│   └── api/
│
├── .gitignore
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# ⚙️ Stack tecnológico

| Tecnología        | Uso                             |
| ----------------- | ------------------------------- |
| Java 21           | Lenguaje principal              |
| Spring Boot       | Framework backend               |
| Spring Web        | APIs REST                       |
| Spring Validation | Validación de entradas          |
| Spring JDBC       | Acceso a BD y Stored Procedures |
| H2 Database       | Persistencia local de debugging |
| Apache Kafka      | Mensajería y eventos            |
| Spring Kafka      | Integración con Kafka           |
| Spring Actuator   | Health checks y métricas        |
| Maven             | Gestión y construcción          |
| JUnit             | Pruebas                         |
| Mockito           | Mocking                         |
| Docker            | Contenerización                 |
| Docker Compose    | Entorno local                   |

---

# 🔌 Integración con base de datos transaccional

El acceso a la base de datos transaccional se realizará mediante un **adaptador de infraestructura basado en JDBC**.

La aplicación no deberá acceder directamente a JDBC desde los casos de uso.

El flujo será:

```text
Use Case
   │
   ▼
Output Port
   │
   ▼
JDBC Adapter
   │
   ▼
Stored Procedure
   │
   ▼
Transactional Database
```

Esto permite que la lógica de aplicación permanezca independiente del motor de base de datos.

## Stored Procedures

El adaptador será responsable de:

* Configurar parámetros `IN`.
* Configurar parámetros `OUT`.
* Ejecutar Stored Procedures.
* Mapear resultados.
* Manejar errores de infraestructura.
* Controlar timeouts.
* Registrar información técnica.
* Convertir respuestas de infraestructura a modelos de aplicación.

La firma real de los Stored Procedures dependerá del motor de base de datos utilizado por el proyecto.

---

# 🐞 Sistema de Debug

El proyecto contempla un mecanismo independiente para registrar información técnica durante la ejecución.

```text
                     ┌────────────────┐
                     │   Application  │
                     └───────┬────────┘
                             │
                             ▼
                     ┌────────────────┐
                     │ Debug Port     │
                     └───────┬────────┘
                             │
                  ┌──────────┴──────────┐
                  │                     │
                  ▼                     ▼
           ┌─────────────┐       ┌─────────────┐
           │ H2 Database │       │ Flat Files  │
           └─────────────┘       └─────────────┘
```

## Información que puede registrarse

* `correlationId`
* Servicio
* Operación
* Evento
* Fecha y hora
* Duración
* Estado
* Mensaje técnico
* Identificador de transacción
* Información de error

No se deberán almacenar:

* Contraseñas.
* Tokens.
* Secretos.
* Credenciales.
* Datos sensibles innecesarios.

---

# 📨 Apache Kafka

Kafka será utilizado como mecanismo de comunicación asíncrona.

## Productor

El microservicio podrá publicar eventos después de determinadas operaciones.

```text
Business Operation
       │
       ▼
Use Case
       │
       ▼
Event Publisher Port
       │
       ▼
Kafka Adapter
       │
       ▼
Kafka Topic
```

## Consumidor

Los mensajes recibidos serán procesados mediante un adaptador de entrada.

```text
Kafka Topic
    │
    ▼
Kafka Consumer
    │
    ▼
Input Port
    │
    ▼
Use Case
    │
    ▼
Domain
```

## Topics iniciales

Los siguientes nombres son referenciales:

```text
operaciones.events
operaciones.debug
operaciones.errors
operaciones.audit
```

Los topics definitivos serán definidos según los contratos de eventos del sistema.

---

# 🔄 Flujo transaccional

Una operación típica tendrá el siguiente flujo:

```text
HTTP Request
     │
     ▼
REST Controller
     │
     ▼
Input Port
     │
     ▼
Use Case
     │
     ▼
Domain Rules
     │
     ▼
Output Port
     │
     ▼
JDBC Adapter
     │
     ▼
Stored Procedure
     │
     ▼
Transactional DB
     │
     ├──────────────► Debug
     │
     └──────────────► Event
                           │
                           ▼
                         Kafka
```

---

# 🔐 Configuración

Las configuraciones sensibles no deben almacenarse directamente en el repositorio.

Se utilizarán variables de entorno.

Ejemplo:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
```

Variables esperadas:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
KAFKA_BOOTSTRAP_SERVERS
APIPERU_DNI_TOKEN
```

El endpoint `GET /api/v1/solicitud/BuscarDNI-Reniec/{nroDNI}` consume el servicio DNI de API Perú. Configura `APIPERU_DNI_TOKEN` en el entorno del backend (por ejemplo, en la configuración de variables de la aplicación de Azure). Opcionalmente, puedes cambiar la URL con `APIPERU_DNI_URL`.

---

# 🌎 Perfiles

Se recomienda trabajar inicialmente con los siguientes perfiles:

```text
local
dev
qa
prod
```

Ejemplo:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

En Windows:

```cmd
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

---

# 🚀 Ejecución local

## Requisitos

Instalar:

* JDK 21
* Maven 3.9+
* Docker
* Docker Compose
* Git

Verificar Java:

```bash
java -version
```

Verificar Maven:

```bash
mvn -version
```

---

## Ejecutar la aplicación

Linux / macOS:

```bash
./mvnw spring-boot:run
```

Windows:

```cmd
mvnw.cmd spring-boot:run
```

---

# 🧪 Testing

El proyecto utilizará diferentes niveles de pruebas.

## Unit Tests

Para:

* Domain.
* Use Cases.
* Business rules.

```bash
./mvnw test
```

## Integration Tests

Para validar:

* JDBC.
* Stored Procedures.
* H2.
* Kafka.
* Integraciones entre componentes.

---

# ❤️ Health Check

Spring Boot Actuator permitirá verificar el estado del servicio.

Endpoint:

```text
GET /actuator/health
```

Ejemplo de respuesta:

```json
{
  "status": "UP"
}
```

Los endpoints expuestos deberán limitarse según el entorno.

---

# 📊 Observabilidad

La arquitectura contempla:

### Logs

Registro de eventos técnicos.

### Métricas

Indicadores de rendimiento mediante Actuator.

### Correlation ID

Identificación de una operación a través de diferentes componentes.

### Distributed Tracing

Se podrá incorporar posteriormente OpenTelemetry para trazabilidad distribuida.

---

# 🛡️ Manejo de errores

Las excepciones deberán clasificarse por responsabilidad.

```text
Domain Exception
        │
        ▼
Application Exception
        │
        ▼
Infrastructure Exception
```

Las APIs REST deberán proporcionar respuestas consistentes.

Ejemplo:

```json
{
  "timestamp": "2026-09-24T22:30:00",
  "status": 400,
  "code": "INVALID_REQUEST",
  "message": "La solicitud no es válida",
  "correlationId": "8c1a7c..."
}
```

Los detalles internos de infraestructura no deberán exponerse directamente al consumidor.

---

# 📐 Principios de desarrollo

El proyecto seguirá principalmente:

* SOLID
* Clean Code
* DRY
* KISS
* Separation of Concerns
* Dependency Inversion
* Fail Fast
* Explicit Error Handling
* Idempotencia cuando corresponda
* Configuración externa
* Security by Design

---

# 🔁 Transactional Outbox

Para eventos de negocio que requieran consistencia entre la transacción de base de datos y Kafka se evaluará el patrón **Transactional Outbox**.

```text
                 ┌─────────────────────┐
                 │ Transactional DB    │
                 │                     │
                 │ Business Data       │
                 │        +            │
                 │ Outbox Event        │
                 └──────────┬──────────┘
                            │
                            ▼
                    Outbox Publisher
                            │
                            ▼
                          Kafka
```

Este patrón será incorporado únicamente cuando el requisito funcional lo justifique.

---

# 🗺️ Roadmap

## Fase 1 — Bootstrap

* [x] Crear proyecto Spring Initializr
* [ ] Configurar Java 21
* [ ] Configurar Maven
* [ ] Configurar `application.yml`
* [ ] Configurar perfiles

## Fase 2 — Arquitectura Hexagonal

* [ ] Crear Domain
* [ ] Crear Application
* [ ] Crear Input Ports
* [ ] Crear Output Ports
* [ ] Crear adapters
* [ ] Definir convenciones de paquetes

## Fase 3 — Persistencia

* [ ] Configurar DataSource
* [ ] Configurar JDBC
* [ ] Implementar Stored Procedure
* [ ] Implementar mapeo de resultados
* [ ] Manejo de errores de BD
* [ ] Pruebas de integración

## Fase 4 — Debug

* [ ] Crear Debug Port
* [ ] Implementar H2
* [ ] Implementar File Adapter
* [ ] Definir modelo `DebugEvent`
* [ ] Configurar activación/desactivación

## Fase 5 — Kafka

* [ ] Configurar Kafka
* [ ] Crear Producer
* [ ] Crear Consumer
* [ ] Definir contratos de eventos
* [ ] Configurar reintentos
* [ ] Manejar mensajes fallidos
* [ ] Evaluar DLQ

## Fase 6 — Observabilidad

* [ ] Actuator
* [ ] Logs estructurados
* [ ] Correlation ID
* [ ] Métricas
* [ ] OpenTelemetry
* [ ] Distributed tracing

## Fase 7 — Calidad

* [ ] Unit Tests
* [ ] Integration Tests
* [ ] Contract Tests
* [ ] Code Quality
* [ ] SonarQube

## Fase 8 — Containerización

* [ ] Dockerfile
* [ ] Docker Compose
* [ ] Kafka
* [ ] Base de datos
* [ ] Configuración por entorno

## Fase 9 — CI/CD

* [ ] GitHub Actions / Jenkins
* [ ] Build
* [ ] Tests
* [ ] Quality Gate
* [ ] Docker Image
* [ ] Deployment

---

# 📚 Documentación

La documentación técnica estará organizada en:

```text
docs/
├── architecture/
│   ├── architecture.md
│   └── diagrams/
│
├── database/
│   ├── stored-procedures.md
│   └── data-model.md
│
├── kafka/
│   ├── topics.md
│   └── events.md
│
└── api/
    └── api-contract.md
```

---

# 📌 Estado actual

**Proyecto:** `ms-operaciones`

**Estado:** Bootstrap inicial

**Arquitectura:** Hexagonal

**Java:** 21

**Framework:** Spring Boot

**Build:** Maven

**Persistencia transaccional:** Stored Procedures + JDBC

**Debug:** H2 + archivos planos

**Mensajería:** Apache Kafka

**Observabilidad:** Spring Boot Actuator

---

# 👨‍💻 Desarrollo

Proyecto orientado a la construcción de un backend empresarial basado en principios de arquitectura limpia, microservicios, integración transaccional y comunicación basada en eventos.

---

## 📄 Licencia

Definir licencia según las condiciones de distribución del proyecto.
