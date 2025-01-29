# Trabalho de Java

### Tecnologias usadas
- [ ] InteliJ IDEA Community
- [ ] Java JDK 20+
- [ ] Swing UI
- [ ] Hibernate
- [ ] Docker / Docker Compose

### Iniciando

> 💡 Verifique se o git está instalado e funcionando na máquina.

💻 Clone o repositório para sua máquina.

```
$ git clone https://github.com/vinisjs/java_ui.git
```

> 💡 Verifique se o docker está instalado e funcionando na máquina.

```
$ docker ps
$ docker compose up -d
```

> ☕ Clique no botão play da IDE para compilar e executar.

### Requisitos

Utilizando Java Swing, implementar o problema visto anteriormente “Sistema de Gestão de Biblioteca”.
Você foi contratado para desenvolver um “Sistema de Gestão de Biblioteca” com Java Swing, utilizando o padrão de
arquitetura “MVC” com a variação para MVCR (model, view, controller e repository). O sistema deve permitir a
gestão de livros e utilizadores, além de permitir o empréstimo e devolução de livros. Para interagir com o banco
de dados vamos utilizar o ORM Hibernate.

1. Funcionalidades do sistema
* Cadastro de livros: O sistema deve permitir que novos livros sejam cadastrados. Cada livro deve ter:
- Título
- Tema
- Autor
- ISBN
- Data de publicação
- Quantidade disponível (exemplares)
- Número de identificação (id)
  Obs. Utilizar máscaras para o campo data
* Cadastro de usuários: O sistema deve permitir o cadastro de novos usuários. Cada usuário deve ter:
- Nome
- Sexo
- Número do celular
- E-mail
- Número de identificação (id)
  Obs. Utilizar máscaras para os campos celular e e-mail
* Empréstimo de livros: Um usuário deve ser capaz de pegar livros emprestados, contanto que haja exemplares
  disponíveis. O sistema deve registrar:
- Usuário que pegou o livro emprestado
- Data do empréstimo
- Data de devolução prevista
- Data de devolução
- Número de identificação (id)
  Obs. Utilizar máscaras para os campos de datas
* Devolução de livros: O sistema deve permitir que o usuário devolva os livros que foram emprestados.

2. Camadas do sistema:
   Model: Representará os dados do sistema, como informações sobre os livros, usuários e empréstimos.
   Repository: Controlará a interação com o banco de dados. Todos os métodos que interagem com o banco de
   dados serão apresentados nessa camada.
   View: Responsável pela interface do usuário. Neste caso, pode ser uma interface de linha de comando simples
   que exibe menus e aceita a entrada do usuário.
   Controller: Controlará a interação entre a View e o Model, gerenciando as operações de cadastro, empréstimo e
   devolução de livros.
3. Regras de Negócio
- Um usuário pode pegar até 5 livros emprestados ao mesmo tempo.
- Um livro só pode ser emprestado se houver exemplares disponíveis.
- O prazo máximo de empréstimo é de 14 dias. Após esse período, o sistema deve sinalizar que o livro está
  atrasado.
## Tarefas:
1. Implementar a camada Model:
- Crie classes para representar Livro, Usuário e Empréstimo.

2. Implementar a camada View:
- Crie uma interface simples em Java que permita ao usuário escolher entre as opções de:
- CRUD livro
- CRUD usuário
- Fazer empréstimo
- Registrar devolução
- Listar livros disponíveis

3. Implementar a camada Controller:
- Crie uma classe controladora que gerencie a interação entre a interface de usuário (View) e a classe de dado
  (Repository).
- Implemente a lógica de negócio para verificar a disponibilidade dos livros, validar a quantidade de livros que um
  usuário pode pegar emprestado e calcular as datas de devolução.
4. Implementar a camada Repository:
- Crie os métodos para tornar possível as funcionalidades disponíveis no projeto.
5. Funcionalidades do sistema:
- CRUD livro
- CRUD usuário
- Fazer empréstimo
- Registrar devolução
- Listar livros disponíveis
- Recurso para calcular multas em caso de devolução atrasada.

### Implementação Atual

#### Usuário
- [x] Criar Usuário 
- [ ] Visualizar Usuário 
- [ ] Visualizar Usuário (Todos) 
- [ ] Editar Usuário 
- [ ] Deletar Usuário 

#### Livro
- [x] Criar Livro 
- [x] Visualizar Livro 
- [x] Visualizar Livro (Todos)
- [x] Editar Livro 
- [x] Deletar Livro 

#### Empréstimo
- [ ] Criar Empréstimo 
- [ ] Visualizar Empréstimo 
- [ ] Visualizar Empréstimo (Todos)
- [ ] Editar Empréstimo 
- [ ] Deletar Empréstimo 