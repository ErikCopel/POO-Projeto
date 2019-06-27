# POO-Projeto


### Integrantes da equipe:

- Patrick Oliveira de Paula
- Luisa Salles de Oliveira
- Erik Copel Rothman

#### Tema do Projeto
Gerenciador de atividades baseado na técnica pomodoro.

#### Motivações
A técnica pomodoro é uma técnica de produtividade e gerenciamento de tempo em que são especificados intervalos de 25 minutos para trabalho focado, seguidos de intervalos de descansos de 5 minutos, com um período maior de descanso após um número determinado de ciclos (ou pomodoros) – usualmente 15 minutos de descanso após 4 pomodoros. O tempo se dá pela constatação de que, em média, uma pessoa não treinada consegue manter sua concentração em uma atividade por aproximadamente 30 minutos, e pequenas pausas para descanso evita que se fique fadigado rapidamente. Outros parâmetros são comuns, como ciclos de trabalho/descanso com 45/15 minutos, ou 90/30 minutos.
A técnica é popular entre estudantes, músicos e profissionais e muitos aplicativos inspirados nela já foram produzidos, dos mais simples, como um simples Timer para pomodoros de 25 minutos, aos mais complexos. Entretanto, nenhum aplicativo se adequa plenamente às necessidades individuais, de modo que podem faltar funcionalidades ou a complexidade demasiada afasta usuários. Neste sentido, conhecimentos em programação dão ao indivíduo a oportunidade de construir para si, e porventura para outros, ferramentas tais que o satisfaça plenamente.


#### Objetivos
Objetiva-se construir uma ferramenta simples de gerenciamento de tempo, tendo a técnica pomodoro como base, ou padrão de parâmetros, mas possibilitando customização suficiente para que o usuário possa especificar o tempo que pretende trabalhar em um projeto, junto com seus períodos de descansos e o número de ciclos que pretende executar, gerenciados automaticamente pelo aplicativo, que o avisará quando um ciclo acaba ou termina. Dessa forma, o aplicativo pode ser utilizado para projetos distintos, com suas particularidades de gerenciamento de tempo.

#### Descrição Geral
O projeto, no seu escopo básico, possuirá:
- Possibilidade de criação de atividades com título, descrição, tempo de duração, tempo de descanso, alarme (áudio e mensagem de texto), a serem especificados pelo usuário ou inicializados com parâmetros padrões.
- Criação de perfil de usuário.
- Exportação de atividades indexados por usuários, como backup, carregado quando um usuário inicia a sessão.
- Edição, remoção e visualização de atividades do arquivo de cada usuário.
- Ativação de atividades, com timer, com opção de pausar, encerrar e recomeçar.
- Inclusão de atividades em uma lista de ativação automática.

Sujeito à disponibilidade de tempo, objetiva-se ainda: 
- Criação de um arquivo executável com uma interface de usuário básica utilizando JavaFX.
- Adicionar estatísticas de usuário. 

O programa, tendo cumprido suas especificações básicas, pode ser usado em um amplo leque de atividades que se beneficiam de um gerenciador de tempo, como estudos e prática esportiva, pois possibilidade a organização de todo um período de trabalho. O código, permanecendo aberto, pode servir de base para outros projetos, adição de novas funcionalidades e adaptação para dispositivos Android.


### Sobre os arquivos de exemplo

Cada bloco tem a mesma estrutura, e o mesmo tamanho, nos dois arquivos. Estou presumindo que java lida com arquivos de modo análogo a C, em que posso percorrer as linhas. Se for assim, saltar de um perfil a outro consiste em saltar um número determinado de linhas. Se um ponteiro está posicionado no título da atividade, encontrar uma informação específica também consiste em saltar um número especificado de linhas.

Os dados são distinguidos por pontuação. A informação sempre estará dentro de <...>. Daí basta obter a string e fazer a conversão adequada no programa.

A parte de ciclos completos será melhor trabalhada depois. Em resumo, é uma linha só, em que a primeira informação é o número total de ciclos completos, depois blocos identificando um dia, seguido do número e dos segundos totais de ciclos executados naquele dia. Tudo entre chaves.