# **Sistema de Folha de Pagamento**

O objetivo do projeto é construir um sistema de folha de pagamento. O sistema consiste do
gerenciamento de pagamentos dos empregados de uma empresa. Além disso, o sistema deve
gerenciar os dados destes empregados, a exemplo os cartões de pontos. Empregados devem receber
o salário no momento correto, usando o método que eles preferem, obedecendo várias taxas e
impostos deduzidos do salário.

* Alguns empregados são horistas. Eles recebem um salário por hora trabalhada. Eles
submetem "cartões de ponto" todo dia para informar o número de horas trabalhadas naquele
dia. Se um empregado trabalhar mais do que 8 horas, deve receber 1.5 a taxa normal
durante as horas extras. Eles são pagos toda sexta-feira. 

* Alguns empregados recebem um salário fixo mensal. São pagos no último dia útil do mês
(desconsidere feriados). Tais empregados são chamados "assalariados". 

* Alguns empregados assalariados são comissionados e portanto recebem uma comissão, um
percentual das vendas que realizam. Eles submetem resultados de vendas que informam a
data e valor da venda. O percentual de comissão varia de empregado para empregado. Eles
são pagos a cada 2 sextas-feiras; neste momento, devem receber o equivalente de 2 semanas
de salário fixo mais as comissões do período. 
   - Empregados podem escolher o método de pagamento.
   - Podem receber um cheque pelos correios 
   - Podem receber um cheque em mãos 
   - Podem pedir depósito em conta bancária 

* Alguns empregados pehttps://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/MonthlyLastDaySalariedStrategy.javartencem ao sindicato (para simplificar, só há um possível sindicato).
O sindicato cobra uma taxa mensal do empregado e essa taxa pode variar entre
empregados. A taxa sindical é deduzida do salário. Além do mais, o sindicato pode
ocasionalmente cobrar taxas de serviços adicionais a um empregado. Tais taxas de serviço
são submetidas pelo sindicato mensalmente e devem ser deduzidas do próximo
contracheque do empregado. A identificação do empregado no sindicato não é a mesma da
identificação no sistema de folha de pagamento.

* A folha de pagamento é rodada todo dia e deve pagar os empregados cujos salários vencem
naquele dia. O sistema receberá a data até a qual o pagamento deve ser feito e calculará o
pagamento para cada empregado desde a última vez em que este foi pago.

| **Func.** | **Título** | **Breve Descrição** |
|-----------|------------|---------------------|
| 1 | Adição de um empregado | Um novo empregado é adicionado ao sistema. Os seguintes atributos são fornecidos: nome, endereço, tipo (hourly, salaried, commissioned) e os atributos associados (salário horário, salário mensal, comissão). Um número de empregado (único) deve ser escolhido automaticamente pelo sistema |
| 2 | Remoção de um empregado | Um empregado é removido do sistema. |
| 3 | Lançar um Cartão de Ponto | O sistema anotará a informação do cartão de ponto e a associará ao empregado correto.|
| 4 | Lançar um Resultado Venda | O sistema anotará a informação do resultado da venda e a associará ao empregado correto |
| 5 | Lançar uma taxa de serviço | O sistema anotará a informação da taxa de serviço e a associará ao empregado correto. |
| 6 | Alterar detalhes de um empregado | Os seguintes atributos de um empregado podem ser alterados: nome, endereço, tipo (hourly,salaried,commisioned), método de pagamento, se pertence ao sindicato ou não, identificação no sindicato, taxa sindical|
| 7 | Rodar a folha de pagamento para hoje | O sistema deve achar todos os empregados que devem ser pagos no dia indicado, deve calcular o valor do salário e deve providenciar o pagamento de acordo com o método escolhido pelo empregado. |
| 8 | Undo/redo | Qualquer transação associada as funcionalidades 1 a 7 deve ser desfeita (undo) ou refeita (redo). |
| 9 | Agenda de Pagamento | Cada empregado é pago de acordo com uma "agenda de pagamento". Empregados podem selecionar a agenda de pagamento que desejam. Por default, as agendas "semanalmente", "mensalmente" e "bi- semanalmente" são usadas, como explicado na descricao geral. Posteriormente, um empregado pode pedir para ser pago de acordo com qualquer uma dessas agendas.|
| 10 | Criação de Novas Agendas de Pagamento | A direção da empresa pode criar uma nova agenda de pagamento e disponibilizá-la para os empregados escolherem, se assim desejarem. Uma agenda é especificada através de um string. Alguns exemplos mostram as possibilidades: "mensal 1": dia 1 de todo mês "mensal 7": dia 7 de todo mês "mensal $": último dia útil de todo mês "semanal 1 segunda": toda semana às segundas-feiras "semanal 1 sexta": toda semana às sextas-feiras "semanal 2 segunda": a cada 2 semanas às segundas-feiras |

# **Bad Smels**

## Duplicated Code

* Na classe EmployessFunction os métodos: addEmployee, removeEmployee e editEmployees;
* Na classe Employee os métodos: printEmployeeInfo, printHourlyInfo, printSalariedInfo e printComissionedInfo;

## Long Parameter List

* Na classe Paycheck no método: calcSalary;
* Na classe Payrool no método: PaymentList;

## Long Method

* Na classe Main no método: main;
* Na classe Employess Function os métodos: addEmployee, editEmployee, removeEmployee;
* Na classe Payroll o método: PaymentList;
* Na classe Paycheck o método: calcSalary;
* Na classe Day nos métodos: getWeekDayString e getWeekDayInt;

## Large Class

* Classe Employee;

## Lazy Class

* Classe Syndicate;

## Speculative Generality

* Classe Taxes;

## Indecent Exposure

* Na classe EmployessFunction nos métodos: addEmployee e editEmployee;
Na classe Payroll no método: PaymentList;

## Data Class

* Classe Employee e  Paycheck;

# **Refatoração**

## Extract Method
O Design Pattern Extract Method foi aplicado para solucionar um code smell da Classe EmployeesFunction . Na 1ª implementação no método addEmployee disponível [aqui](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/EmployessFunction.java#L22-L173) sempre que foi necessário ler a entrada do usuário foi criada uma variável do tipo Scanner para receber as informações digitadas no terminal. Dessa forma, houve uma grande cópia de código para receber as entradas do usuário. 

O padrão Extract Method foi utilizado para pegar a parte de código que era copiada e colocá-la dentro métodos de uma classe SystemInput [disponível aqui](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/5dc84c829d16f03ed6dbee0350bb5c29e81e0044/src/app/SystemInput.java#L6-L73). Dessa forma, a cópia de código foi evitada. [Aqui](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/5dc84c829d16f03ed6dbee0350bb5c29e81e0044/src/app/EmployessFunction.java#L20-L168) está o método addEmployee refatorado.

A mesma situação demonstrada acima se repetiu nos métodos removeEmployee e editEmployee dessa mesma classe. A seguir, é demonstrada a implementação antiga dos métodos juntamente com a refatoração dos métodos citados.
* removeEmployee [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/EmployessFunction.java#L253-L282), removeEmployee [refatorado](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/5dc84c829d16f03ed6dbee0350bb5c29e81e0044/src/app/EmployessFunction.java#L242-L269);
* editEmployee [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/EmployessFunction.java#L284-L548), editEmployee [refatorado](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/5dc84c829d16f03ed6dbee0350bb5c29e81e0044/src/app/EmployessFunction.java#L271-L528);

Apresentam o mesmo problema os seguintes métodos: 
* O método setServiceFee da Classe Taxes Launch;
* O método main da Classe Main;
* O método addSale da Classe SalesResult;
* O método setTimecard da Classe TimecardLaunch;
* O método PaymentList da Classe Payrool;

A seguir é demonstrada a implementação antiga dos métodos acima e a implementação refatorada.
* setServiceFee [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/TaxesLaunch.java#L13-L22), setServiceFee [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b412d276a6493c278c5df04cc8b3e5cb6047d81b/src/app/TaxesLaunch.java#L12-L20);
* main [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/Main.java#L12-L38), main [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b412d276a6493c278c5df04cc8b3e5cb6047d81b/src/app/Main.java#L10-L32);
* addSale [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/SalesResult.java#L13-L22), addSale [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b412d276a6493c278c5df04cc8b3e5cb6047d81b/src/app/SalesResult.java#L13-L21);
* setTimecard [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/app/TimecardLaunch.java#L16-L32), setTimecard [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b412d276a6493c278c5df04cc8b3e5cb6047d81b/src/app/TimecardLaunch.java#L15-L30);
* PaymentList [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/payment/Payroll.java#L22-L32), PaymentList [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b412d276a6493c278c5df04cc8b3e5cb6047d81b/src/payment/Payroll.java#L16-L25).

Apresentam o mesmo problema os seguintes métodos da Classe Employee:
* O método printHourlyInfo;
* O método printSalariedInfo;
* O método printComissionedInfo;

A seguir é demonstrada a implementação antiga dos métodos acima e a implementação refatorada:
* printHourlyInfo [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/employees/Employee.java#L94-L99), printHourlyInfo [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b1d130a0c4fa84e630309fd54711e961264218b4/src/employees/Employee.java#L94);
* printSalariedInfo [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/employees/Employee.java#L108-L113), printSalariedInfo [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b1d130a0c4fa84e630309fd54711e961264218b4/src/employees/Employee.java#L103);
* printComissionedInfo [antigo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/employees/Employee.java#L119-L124), printComissionedInfo [novo](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/b1d130a0c4fa84e630309fd54711e961264218b4/src/employees/Employee.java#L109);

## Strategy
Na implementação antiga do método PaymentList da Classe Payroll haviam 9 métodos de pagamentos diferentes. A implementação desse método está disponível disponível [aqui](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_2020.1/blob/bb1ee1fe8f913723b47df94968dfec3bb7cf90f4/src/payment/Payroll.java#L50-L213). Para cada método de pagamento, havia uma variante do algoritmo. Foi aplicado o padrão de projeto Strategy em cada opção de pagamento. Foi criada uma interface PaymentStrategy e para cada tipo de pagamento, uma estratégia foi implementada. Dessa forma, foi evitada a exposição de dados específicos do algoritmo.

* [Estratégia 1](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/WeeklyFridayComissionedStrategy.java)
* [Estrategia 2](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/WeeklyFridayHourlyStrategy.java) 
* [Estratégia 3.](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/WeeklyFridaySalariedStrategy.java) 
* [Estratégia 4.](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/TwoWeeklyOrFridayComissionedStrategy.java) 
* [Estratégia 5.](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/TwoWeeklyOrFridayHourlyStrategy.java) 
* [Estratégia 6.](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/TwoWeeklyOrFridaySalariedStrategy.java) 
* [Estratégia 7](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/MonthlyLastDayComissionedStrategy.java) 
* [Estratégia 8](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/MonthlyLastDayHourlyStrategy.java) 
* [Estratégia 9](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/MonthlyLastDaySalariedStrategy.java) 

Essas estratégias são usadas de acordo com a agenda de pagamento, o tipo de empregado e o dia ou semana do mês.
A seguir, é demonstrada a implementação da Interface PaymentStrategy e da Classe Context.
* [Interface](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/PaymentStrategy.java)
* [Context](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/main/src/payment/Context.java)
* [Aqui](https://github.com/tarcisiolf0/folha_de_pagamento_projeto_de_software_refatorado_2020.1/blob/850c1cb6fcb0d6965b187337fc7723a1a55a6f5f/src/payment/Payroll.java#L39-L82) é mostrado a implementação do método PaymentList refatorado.
