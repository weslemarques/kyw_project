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

- 
# Desenvolvimento do Backend com Spring Boot, PostgreSQL e Docker

## 1. Introdução
Neste documento, serão detalhadas as etapas de desenvolvimento do backend utilizando Spring Boot, PostgreSQL e Docker para o projeto integrador.

## 2. Planejamento e Definição dos Requisitos
- Identificação dos requisitos específicos do backend.
- Especificação das funcionalidades necessárias para suportar os clientes mobile e web.

## 3. Configuração do Ambiente de Desenvolvimento
- **Setup do Projeto Spring Boot**:
  - Utilização do Spring Initializr para criar o projeto.
  - Inclusão das dependências: Spring Web, Spring Data JPA, PostgreSQL Driver.
  
- **Configuração do PostgreSQL com Docker**:
  - Utilização de Docker para configurar e executar o PostgreSQL localmente.
  - Definição das credenciais de acesso no arquivo `application.properties`.

## 4. Desenvolvimento das Funcionalidades
- **Modelagem do Banco de Dados**:
  - Criação das entidades JPA para representar as tabelas do banco de dados.
  
- **Implementação de Repositórios**:
  - Desenvolvimento das interfaces de repositório para interação com o banco de dados PostgreSQL.
  
- **Implementação dos Serviços**:
  - Desenvolvimento da lógica de negócio nos serviços do backend utilizando Spring Boot.

- **Desenvolvimento dos Controladores**:
  - Criação de controladores REST para expor os endpoints da API para os clientes mobile e web.

## 5. Testes e Validação
- **Testes Unitários e de Integração**:
  - Implementação de testes para validar a lógica de negócio e a integração com o banco de dados.
  
- **Documentação dos Endpoints**:
  - Utilização de ferramentas como Swagger para documentar os endpoints da API.

## 6. Segurança e Dockerização da Aplicação
- **Implementação de Segurança**:
  - Configuração de autenticação e autorização para proteger os endpoints da API.
  
- **Dockerização da Aplicação**:
  - Dockerização do backend Spring Boot para facilitar o deploy e a escalabilidade da aplicação.
  - Descrição do Dockerfile utilizado para empacotar a aplicação Spring Boot.
  - Configuração de um ambiente de desenvolvimento e produção com Docker Compose, se aplicável.

## 7. Deploy e Manutenção
- **Deploy em Ambiente de Produção**:
  - Configuração de scripts de deploy e publicação da aplicação em um servidor de produção.
  
- **Monitoramento e Manutenção**:
  - Estabelecimento de práticas de monitoramento da aplicação em produção.
  - Realização de manutenções periódicas conforme necessário.

