# Projeto Consulta de Clima e Tempo para Agropecuária

## Descrição

O projeto é um web service desenvolvido em Java Spring Boot que realiza o consumo de uma API open source trazendo dados referentes ao clima e o tempo, favorecendo os produtores rurais que exercem atividades de agricultura e pecuária. 

## Endpoints
- `POST /clima` - Retorna dados referente ao clima e o tempo.
- `POST /avaliar` - Retorna dados referente ao clima da cidade correspondente e traz sugestões da atividade favorável com base no clima atual.
- `GET /sobre` - Informações sobre os autores do projeto.
- `GET /histórico` - Histórico de todas as consultas e requisições realizadas.

## Instruções de como executar o projeto

### Pré requisitos:

- IDE que seja compatível com Spring, como por exemplo o InteliJJ IDEA, Spring Tool Suite, Eclipse ou a versão gratuita do IntelliJ Comumunity Edition.
- JDK (Java Development Kit) com versão 8 ou superior instalado.
- Maven versão 3.3.2+ para realizar a gestão das dependências.
- Ferramenta para executar **requisições HTTP** (GET, POST, PUT, DELETE). Recomenda-se o `Postman` ou o `Insomnia`.

### Instruções de uso:
1. Abra a IDEA (neste exemplo, utiliza-se o `InteliJJ Community Edition`)
2. Siga o caminho conforme imagem abaixo:

![img.png](img.png)

3. Clonar repositório e preencher a seguinte URL:

`https://github.com/MaicouHahn/backend-projeto-Maicou_Hahn_Fortuna-Cristhian_Cardoso_Berthan.git`

![img_1.png](img_1.png)
4. Abrir a camada da aplicação chamada `AgriPecuApplica`
5. Inicializar a aplicação através do botão `Run`

![img_2.png](img_2.png)

6. Aplicação inicializada com sucesso.
![img_3.png](img_3.png)


7. Abra a ferramenta para executar as requisições HTTP (neste exemplo, utiliza-se o `Postman`)


## 1º Exemplo de uso
- **POST ``/clima``**
```json
{
  "cidade": "Criciúma",
  "tipo": "agricultura",
  "atividade": "colheita"
}
```
- **Retorno**
```json
{
  "cidade": "Criciúma",
  "condicoesAtuais": "nublado, 23.75°C",
  "recomendacao": "Clima ideal para colheita. Aproveite o tempo seco."
}
```

## 2º Exemplo de uso
- **POST ``/avaliar``**
```json
{
  "cidade": "Criciúma"
}
```
- **Retorno**
```json
{
  "cidade": "Criciúma",
  "clima": "nublado, 24.31°C",
  "avaliacao": "Clima favorável para atividades no campo."
}
```

## 3º Exemplo de uso
- **GET ``/sobre``**
```json
{}
```
- **Retorno**
```json
{
  "integrantes": ["Maicou Hahn Fortuna","Cristhian Cardoso Bertan"],
  "nome_projeto": "AgriPecu"
}
```

## 4º Exemplo de uso
- **GET ``/histórico``**
```json
{}
```
- **Retorno**
```json
[
  {
    "cidade": "Criciúma",
    "tipo": null,
    "atividade": null,
    "resposta": null,
    "clima": "nublado, 24.31°C",
    "avaliacao": "Clima favorável para atividades no campo."
  },
  {
    "cidade": "Criciúma",
    "tipo": "agricultura",
    "atividade": "colheita",
    "resposta": "Clima ideal para colheita. Aproveite o tempo seco.",
    "clima": null,
    "avaliacao": null
  }
]
```

# Serviço de Meteorologia - API REST

## Descrição

Esta API REST fornece dados meteorológicos para cidades cadastradas, incluindo informações do clima atual e previsões futuras. Ideal para aplicativos que desejam consultar o tempo de forma simples e eficiente.

---

## Casos de Uso

1. Consultar condições meteorológicas atuais de uma cidade.
2. Gerenciar previsões meteorológicas (criar, consultar, atualizar e deletar).

---

## Recursos

- **Cidades**: cadastro e consulta de cidades disponíveis para consulta do clima.
- **Previsões**: consulta e gerenciamento das previsões meteorológicas de cada cidade.

---

## Endpoints

### Cidades

| Método | URI                 | Descrição                             |
|--------|---------------------|-------------------------------------|
| GET    | `/cidades`          | Listar todas as cidades cadastradas |
| GET    | `/cidades/{id}` | Lista uma cidade em específico|
| GET    | `/cidades/{id}/clima` | Obter clima atual da cidade          |
| POST   | `/cidades`          | Cadastrar uma nova cidade            |
| PUT    | `/cidades/{id}`     | Atualizar dados da cidade            |
| DELETE | `/cidades/{id}`     | Remover cidade                       |

### Previsões

| Método | URI                                    | Descrição                               |
|--------|----------------------------------------|---------------------------------------|
| GET    | `/cidades/{id}/previsoes`              | Listar previsões para uma cidade      |
| GET    | `/cidades/{id}/previsoes/{data}`       | Consultar previsão para data específica |
| POST   | `/cidades/{id}/previsoes`              | Inserir nova previsão meteorológica   |
| PUT    | `/cidades/{id}/previsoes/{data}`       | Atualizar previsão para uma data      |
| DELETE | `/cidades/{id}/previsoes/{data}`       | Deletar previsão                      |

---

## Exemplo de Requisição

### Consultar clima atual da cidade

```http
GET /cidades/123/clima HTTP/1.1
Host: api.meteorologia.com
Accept: application/json
```

### Resposta

```json
{
  "cidade": "São Paulo",
  "data": "2025-05-23T14:00:00Z",
  "temperatura": 22,
  "umidade": 60,
  "descricao": "Parcialmente nublado"
}
```

---

## Códigos de Status HTTP

- **200 OK** – Requisição bem sucedida
- **201 Created** – Recurso criado com sucesso
- **204 No Content** – Nenhum dado encontrado (para listagens vazias)
- **400 Bad Request** – Dados inválidos na requisição
- **404 Not Found** – Recurso não encontrado (cidade ou previsão)

---

## Como Usar

1. **Cadastrar cidades** via `POST /cidades` para que estejam disponíveis para consulta.
2. **Consultar clima atual** usando `GET /cidades/{id}/clima`.
3. **Gerenciar previsões** com os métodos POST, PUT, DELETE no recurso `/previsoes`.

---

## Tecnologias Utilizadas

- API RESTful
- JSON para troca de dados
- HTTP para comunicação  
