# Employee Data Component

## Responsabilidades
- Oferecer a API `POST /api/v1/employees/search` usada pelo orquestrador de folha para buscar informações detalhadas dos colaboradores de um tenant.
- Validar o payload (obrigatórios `tenantId` e `correlationId`) e traduzir o DTO para critérios de busca, delegando para o caso de uso `SearchEmployeeDataUseCase`.
- Mapear requisitos de negócio (filtros por `employeeId`, `registrationNumber`, `documentNumber`, `EmploymentStatus`) em consultas JPA que filtram contra `EmployeeEntity` e os relacionamentos relevantes.
- Fornecer os dados persistidos (empresas, funcionários, departamentos etc.) conforme o script `src/main/resources/data.sql` durante ambientes de desenvolvimento em memória.

## Tecnologias
- `Java 21`, `Spring Boot 3` e `Jakarta` para composição da API REST, DTOs e validação com `jakarta.validation`.
- `Spring Data JPA` com `Hibernate` e `H2` (memória) para persistência.
- `Lombok` para reduzir verbosidade nos DTOs/entidades e `SLF4J` via `@Slf4j` no controller.
- Arquivo OpenAPI (`src/main/resources/openapi.yaml`) descrevendo contrato e cenários de erro padrão (`ErrorResponse`).

## Como usar localmente
1. Execute `mvn spring-boot:run` (ou gere o JAR `target/employeeservice-0.0.1-SNAPSHOT.jar`).
2. O banco H2 em memória é populado por `data.sql`; as empresas já cadastradas (registration_number `46634044000174`, `46634077000114`, `12345678000199`) permitem testar cenários reais.
3. Envie um POST contra a rota `http://localhost:8080/api/v1/employees/search` com ao menos `tenantId` e `correlationId`. Exemplo válido com dados do SQL:

```bash
curl -X POST "http://localhost:8080/api/v1/employees/search" \
  -H "Content-Type: application/json" \
  -d '{
        "tenantId": "46634044000174",
        "correlationId": "req-visit-001",
        "employeeId": "1",
        "registrationNumber": "REG-001",
        "documentNumber": null,
        "status": null
      }'
```
4. O JSON de resposta segue o esquema `EmployeeSearchResponse` que inclui `employees` e quantidade de registros.

## Integração no fluxo SaaS e flag de entrega
Este componente representa a camada de dados do SaaS (primeira funcionalidade: geração de holerite). Para garantir que ele seja incluído no produto final, mantenha o arquivo `features/holerite.flag` presente na raiz do repositório. O pipeline de entrega deve:
- Reconhecer `features/holerite.flag` como sinal para compor o módulo de dados/consulta (`EmployeeQueryController`) junto ao restante do SaaS.
- Sincronizar esse flag com a branch ou tag de release que promove a entrega do holerite como primeira funcionalidade, garantindo que commits de preparação contenham o flag ativo antes do `git push` final.
- Atualizar documentação de release (por exemplo, no `RELEASE_NOTES.md` ou no tracker de funcionalidades) listando o holerite e qualquer dependência de banco de dados/habilitação `employee-service.api.base-path`.

Para desativar o componente em ambientes onde o holerite ainda não é requisito, remova o arquivo `features/holerite.flag` e aplique a configuração `EMPLOYEE_SERVICE_ENABLED=false` no profile ativo (ou remova a propriedade customizada `employee-service.api.base-path`).
