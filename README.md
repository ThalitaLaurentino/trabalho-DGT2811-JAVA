## DGT2811 Desenvolvimento Back-End Corporativo com Java E Cloud
#### Thalita P. Alexandre Laurentino - 202401412341- Terceiro Semestre
#### Campus Estácio São José - SC 

-------


Procedimento 1 - Camadas de persistência e Controle 


1. Organização de um projeto corporativo no NetBeans
O NetBeans organiza o projeto em camadas separadas:
Persistência (JPA) para lidar com o banco.
Negócio (EJB) para regras da aplicação.
Apresentação/Controle (Servlets/JSP) para interação com o usuário.
 Essa divisão deixa o sistema mais limpo e fácil de manter.

2. Papel do JPA e EJB em um aplicativo Web Java
O JPA cuida da comunicação com o banco de dados de forma simples, usando entidades.
 Os EJBs tratam as regras de negócio, garantindo segurança, transações e organização do código.
3. Como o NetBeans melhora a produtividade com JPA e EJB
O NetBeans ajuda gerando código automaticamente (entidades, Beans), configurando o servidor mais facilmente e permitindo testes rápidos. Isso reduz erros e acelera o desenvolvimento.
4. O que são Servlets e como o NetBeans ajuda
Servlets são classes que recebem as requisições Web e enviam respostas. O NetBeans facilita criando a estrutura básica automaticamente e integrando o Servlet com JPA e EJB sem dificuldade.
5. Comunicação entre Servlets e Session Beans
A comunicação é feita por injeção de dependência. O Servlet usa @EJB para acessar um Session Bean direto do pool e chamar seus métodos.

Conclusão
Para realizar essa prática, primeiro fiz um banco de dados no SQL Server Studio, onde coloquei todas as informações necessárias para o sistema. Esse banco foi utilizado diretamente no projeto, integrado ao NetBeans por meio do JPA. A partir daí, os EJBs aplicaram as regras de negócio e os Servlets fizeram a parte de controle. Trabalhar com essas tecnologias juntas deixou tudo mais organizado e ajudou a entender como cada camada se conecta. No final, o ambiente do NetBeans facilitou muito todo o processo e deixou o desenvolvimento mais simples e claro.

