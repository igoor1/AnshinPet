### Fatec - Centro Paula Souza 
 
##### Estudo de Caso – _Anshin Pet_ 
> O estudo de caso tem como objetivo desenvolver e implantar o Sistema Anshin Pet. O projeto tem como objetivo auxiliar o gerenciamento de abrigo de animais com foco em diminuir os animais em situação de rua. É possível cadastrar animais, cuidadores, doações recebidas .....
 
Time de desenvolvimento - Igor Gonçalves Pereira - Kaiki Kenji Fukumoto Aoto
 
##### Processo de Desenvolvimento de Software - PDS 
> O PDS segue uma abordagem interativa incremental adaptada do Scrum. A definição de pronto estabelece os mecanismos para  
> controle de qualidade da aplicação.  


##### Análise de risco   
> O resultado da análise conclui que será possível implementar o projeto no semestre com grupos de alunos atuando como programadores. O maior risco identificado é a falta de tempo para reunião do time de desenvolvimento e estudo  
do projeto. O grupo deve definir estratégias para mitigar o risco de dificuldades com a linguagem de programação, identificando membros do grupo com problemas na programação, selecionando videoaulas, referência bibliográficas, para 
melhora a produtividade do time.   
 
##### Product Backlog 
> Cada requisito tem um identificador único de maneira que seja possível rastrear a necessidade do cliente com a implementação do software.  

| Identificador | Descrição | Complexidade| 
| ------------ | ------------------------------------------------------------------------ | ------|  
| REQ01 - Animais | O sistema deve permitir manter as informações dos animais como nome, sexo, data de nascimento, tipo, raça, adoção, descrição, cor, porte, castrado e imagem. (CRUD) | Alta | 
| REQ02 - Cuidados Animais - Vacina  | O sistema deve permitir manter as informações dos cuidados médicos dos animais. Como as vacinas que ele tomou (Nome, Lote, DataAplicação, Descrição). (CRUD) | Alta | 
| REQ03 - Cuidados Animais - Doenças | O sistema deve permitir manter as informações dos cuidados médicos dos animais. Doenças que ele possui (Nome, Gravidade, Status, Descrição). (CRUD) | Alta |
| REQ04 - Doações | O sistema deve permitir manter as informações das doações como tipo, data, quantidade, valor e descrição. (CRUD) | Alta |
| REQ05 - Cuidadores | O sistema deve permitir manter as informações dos cuidadores como nome, sobrenome, celular, data de nascimento, sexo, CPF, e-mail, senha. (CRUD) | Alta |
| REQ06 - Login | O sistema deve permitir que usuários sejam autenticados e posteriormente efetuem login no sistema, caso suas informações forem validas. | Alta |
| REQ07 - Visualização de Animais para Adoção | O sistema deve permitir que o usuário adotante visualize uma lista dos animais disponíveis para adoção com as informações de nome, foto, nome, sexo, data de nascimento, idade, tipo, raça, descrição, cor, porte, castrado. | Alta |
| REQ08 - Cadastro de Interesse de Adoção | O sistema deve permitir cadastrar o interesse de adoção com as informações de nome, sobrenome, data de nascimento, CPF, celular, e-mail, cep (Acionado a API de CEP para buscar as demais informações do CEP), número da residência e complemento. | Média |
| REQ09 - Dashboard | O sistema deve exibir por meio de gráficos interativos, informações sobre os animais e as doações permitindo a análise visual. | Baixa |


##### Definição de pronto 
> O sprint será considerado concluido quando: 
> 1) Os casos de teste de aceitação forem executados e obtiverem 100% de satisfatorios. Os casos de teste (CT) são rastreáveis para os requisiitos (REQ). O elo de rastreabilidade  
é estabelecido pelo identificador do caso de teste. 
> 2) Depois de executado os casos de teste com 100% de satisfatorios o código deve ser armazenado no github (commit). 
> 3) O relatório do SonarLint foi gerado e revisado.  
 
##### Proejto da Arquitetura  
A arquitetura segue uma abordagem orientada a serviços. Os serviços foram classificados em três tipos (ERL, 2007): 

 - **1. Serviços utilitários**. Implementam funcionalidades comuns a vários tipos de aplicações, como, por exemplo: log, notificação, transformação de informações. Um exemplo de serviço utilitário seria um serviço para calcular a idade dos animais com base na data de nascimento.  
 
 - **2. Serviços de entidade (serviços de negócios)**. Derivado de uma ou mais entidades de negócio (domínio), possuindo um alto grau de reutilização. Geralmente são serviços que fazem operações CRUD (Create, Read, Update e Delete).  
 - **3. Serviços de tarefa (coordenação de processos-workflow)**. Tipo de serviço mais específico que possui baixo grau de reuso. Consome outros serviços para atender seus consumidores. São serviços que suportam um processo de negócios amplo que geralmente envolve atividades e atores diferentes. Um exemplo de serviço de interesse de adoção, que envolve várias etapas e diferentes atores.
 
>Referencias - [1] KRUCHTEN, Philippe. Reference: Title: Architectural blueprints—the “4+ 1” view model of software architecture. IEEE software, v. 12, n. 6, 1995. - [2] ERL, Thomas. SOA principles of service design (the Prentice Hall service-oriented computing series from Thomas Erl). Prentice Hall PTR, 2007. - [3] LARMAN, Craig. Utilizando UML e padrões. 2aed., Porto Alegre: Bookman Editora, 2006 (pag. 147)