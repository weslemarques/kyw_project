# KYW (BACKEND)

![GitHub repo size](https://img.shields.io/github/repo-size/weslemarques/kyw_project?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/weslemarques/kyw_project?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/weslemarques/kyw_project?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/github/issues/weslemarques/kyw_project?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/github/issues-pr/weslemarques/kyw_project?style=for-the-badge)


> Backend do software de gerenciamento de projetos

### Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

- [x] Criação dos modelos de negócio conforme o levantamento de requisitos
- [x] Mapeamento para banco de dados
- [x] Conexão do banco de dados (Docker)
- [x] Criação dos relacionamentos necessários
- [x] Configuração de segurança com spring security
- [x] Registro do usuario
- [x] Verificação do usuário
- [x] Implementação do token JWT
- [x] Refresh Token
- [x] Tratamento de erros personalizados
- [ ] ...

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão do jdk-17 ou superiores
- Você tem uma máquina `<Windows / Linux / Mac>`.
- Você tem o algum banco de dados ou o Docker instalado
- (Testar endpoints enquanto não adiciono o swagger) Postman ou ferramenta similar
## 🚀 Como criar a instancia do postgreSQL no docker

# Desenvolvimento do Projeto Integrador

## 1. Introdução
- Breve contextualização do projeto integrador e importância das diferentes partes (backend, mobile e web).

## 2. Desenvolvimento do Backend com Spring Boot, PostgreSQL e Docker

### Planejamento e Definição dos Requisitos
- Identificação dos requisitos gerais do projeto.
- Especificação das funcionalidades necessárias para o backend.

### Configuração do Ambiente de Desenvolvimento
- **Setup do Projeto Spring Boot**:
  - Utilização do Spring Initializr para criar o projeto.
  - Inclusão das dependências: Spring Web, Spring Data JPA, PostgreSQL Driver.
- **Configuração do PostgreSQL com Docker**:
  - Uso de Docker para configurar e executar o PostgreSQL localmente.
  - Definição das credenciais no arquivo `application.properties`.

### Desenvolvimento das Funcionalidades
- **Modelagem do Banco de Dados**:
  - Criação das entidades JPA para representar as tabelas.
- **Implementação de Repositórios**:
  - Desenvolvimento de interfaces de repositório para interação com o banco de dados.
- **Implementação dos Serviços**:
  - Desenvolvimento da lógica de negócio nos serviços do backend.
- **Desenvolvimento dos Controladores**:
  - Criação de controladores REST para expor os endpoints da API.

### Testes e Validação
- **Testes Unitários e de Integração**:
  - Implementação de testes para validar a lógica de negócios e a interação com o banco de dados.
- **Documentação dos Endpoints**:
  - Utilização de Swagger para documentar os endpoints da API.

### Segurança e Manutenção
- **Implementação de Segurança**:
  - Configuração de mecanismos de autenticação e autorização.
- **Dockerização da Aplicação**:
  - Dockerização do backend para facilitar o deploy e a escalabilidade.

### Deploy e Manutenção
- **Deploy em Ambiente de Produção**:
  - Configuração de scripts de deploy e publicação da aplicação.
- **Monitoramento e Manutenção**:
  - Estabelecimento de práticas de monitoramento e manutenção em produção.

## 3. Desenvolvimento do Cliente Mobile

### Planejamento e Implementação
- Definição dos requisitos específicos para o cliente mobile.
- Descrição das tecnologias utilizadas (por exemplo, Flutter, React Native).
- Implementação das funcionalidades no cliente mobile.

### Integração com o Backend
- Consumo dos serviços do backend através de chamadas API.

### Testes e Validação
- Testes realizados no cliente mobile para validar as funcionalidades implementadas.

## 4. Desenvolvimento do Cliente Web

### Planejamento e Implementação
- Definição dos requisitos específicos para o cliente web.
- Descrição das tecnologias utilizadas (por exemplo, React, Angular).
- Implementação das funcionalidades no cliente web.

### Integração com o Backend
- Consumo dos serviços do backend através de chamadas API.

### Testes e Validação
- Testes realizados no cliente web para validar as funcionalidades implementadas.

## 5. Conclusão
- Discussão sobre os resultados obtidos com a implementação do projeto.
- Lições aprendidas durante o desenvolvimento das diferentes partes da aplicação.

## 6. Considerações Finais
- Perspectivas futuras para o projeto.

