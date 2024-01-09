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
- [ ] Tratamento de erros personalizados
- [ ] ...

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão do jdk-17 ou superiores
- Você tem uma máquina `<Windows / Linux / Mac>`.
- Você tem o algum banco de dados ou o Docker instalado
- (Testar endpoints enquanto não adiciono o swagger) Postman ou ferramenta similar
## 🚀 Como criar a instancia do postgreSQL no docker

Comandos para o docker:

```
<docker run -d --name kyw_project -e POSTGRES_PASSWORD=<yourpassword> -e POSTGRES_DB: KYW_PROJECT -e PGDATA=/var/lib/postgresql/data/pgdata -v /custom/mount:/var/lib/postgresql/data -p 5432:5432 -d postgres>
```
PS: se utilizar a dependencia 'compose' facilita se for usar os bancos como containers do docker.
