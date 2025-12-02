
## DGT2811 Desenvolvimento Back-End Corporativo com Java E Cloud
#### Thalita P. Alexandre Laurentino - 202401412341- Terceiro Semestre
#### Campus Estácio São José - SC 

-------


#### Procedimento 2 | Interface Cadastral com Servlet e JSPs


1. Como eu entendi o Front Controller
O Front Controller virou quase um “porteiro” do meu sistema. Tudo que o usuário faz passa primeiro por ele. No meu caso, o ServletProdutoFC recebe a ação, vê o que a pessoa quer (listar, incluir, alterar, excluir…) e encaminha certinho.  Isso deixou o fluxo muito mais organizado e evitou aquela bagunça de vários servlets espalhados.

2. O que eu percebi sobre Servlets e JSP
Os dois trabalham juntos, mas cada um tem sua função. O Servlet é onde a lógica acontece é a parte que realmente “pensa”. A JSP é só a parte visual, onde as coisas aparecem para o usuário. Quando entendi isso, o MVC fez muito mais sentido para mim.

3. A diferença que faz usar redirect ou forward
Descobri que o redirect manda o navegador abrir outra página; muda a URL, perde atributos e ainda é mais lento.  O forward, por outro lado, acontece dentro do servidor, sem perder dados e sem mudar a URL. Por isso uso forward quando quero carregar uma JSP já com as listas e objetos prontinhos.

4. Como eu entendi parâmetros e atributos
Parâmetro é o que vem do usuário: coisas que chegam pela URL ou pelo formulário (tipo id, nome, ação). Atributo é o que eu mando da lógica para a tela por exemplo, a lista de produtos ou o produto que vou editar. Um jeito simples de lembrar:
Parâmetro vem do usuário. Atributo vai do sistema para a tela.


#### Conclusão
No começo, o sistema só listava produtos e não tinha fluxo, formulários funcionando ou um CRUD completo. Depois que implementei o Front Controller, tudo mudou. A lógica ficou centralizada, as telas passaram a conversar corretamente com o banco e as ações de incluir, editar e excluir começaram a funcionar de verdade. O projeto também ficou muito mais organizado dentro do padrão MVC, onde cada parte assumiu seu papel: o Servlet controlando o fluxo, o JSP exibindo as informações e o EJB cuidando do acesso aos dados. Com essas melhorias, o sistema ganhou cara de aplicação profissional e ficou muito mais claro, estruturado e fácil de manter.
