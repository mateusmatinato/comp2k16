import java.util.Scanner;

public class TrabalhoPOO {

    public static void main(String[] args) {
        int contBus = 0, contPas = 0, contMot = 0, contRota = 0, altPas, altMot, altBus, altRota, ver = 0, verCod, useroradm, senhaAdm, numRotas = 1, qntPar, compra, lugar;
        Motorista motorista[] = new Motorista[20];
        Onibus onibus[] = new Onibus[20];
        Rota rota[] = new Rota[20];
        Hora horaVer = new Hora();
        Data dataVer = new Data();
        Passageiro passageiro[] = new Passageiro[500];
        int hora, min, dia, mes, ano, opcao, adm;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Informe o tipo de usuário:\n(1)-Administrador\n(2)-Passageiro\n(0)-Finaliza Programa");
            useroradm = sc.nextInt();
            if (useroradm == 1) { //adm
                System.out.print("Informe a senha de administrador: ");
                senhaAdm = sc.nextInt();
                if (senhaAdm == 123) {
                    do { //Looping no menu de adm
                        System.out.println("O que deseja fazer?");
                        System.out.println("(1)-Cadastrar\n(2)-Alterar\n(3)Excluir\n(0)-Voltar");
                        adm = sc.nextInt();
                        if (adm == 1) { // if para realizar cadastro
                            do {
                                System.out.println("Informe o tipo de cadastro que deseja realizar:\n(1)-Passageiro\n(2)-Ônibus\n(3)-Motorista\n(4)-Rota\n(0)-Voltar");
                                opcao = sc.nextInt();
                                if (opcao == 1) { // cadastro passageiro
                                    sc.nextLine();
                                    passageiro[contPas] = new Passageiro();
                                    System.out.print("Digite o nome do passageiro: ");
                                    passageiro[contPas].setNome(sc.nextLine());
                                    System.out.print("Digite o rg do passageiro (Somente números): ");
                                    passageiro[contPas].setRg(sc.nextInt());
                                    while (ver != 1) {
                                        System.out.print("Digite a data de nascimento na forma DD MM AAAA: ");
                                        dia = sc.nextInt();
                                        mes = sc.nextInt();
                                        ano = sc.nextInt();
                                        if (dataVer.valida(dia, mes) == 1) { // Chama a função de verificar se a data inserida é valida.
                                            passageiro[contPas].dataNasc.setDia(dia);
                                            passageiro[contPas].dataNasc.setMes(mes);
                                            passageiro[contPas].dataNasc.setAno(ano);
                                            ver = 1; // valida a verificação
                                        }
                                    }
                                    ver = 0; // zerar a verificação para outras checagens
                                    System.out.print("Digite a rua de endereço do passageiro: ");
                                    sc.nextLine();
                                    passageiro[contPas].setRua(sc.nextLine());
                                    System.out.print("Digite o bairro de endereço do passageiro: ");
                                    passageiro[contPas].setBairro(sc.nextLine());
                                    System.out.print("Digite o número de endereço do passageiro: ");
                                    passageiro[contPas].setNumEnd(sc.nextInt());
                                    System.out.print("Digite o CEP de endereço do passageiro: ");
                                    sc.nextLine();
                                    passageiro[contPas].setCep(sc.nextLine());
                                    System.out.print("Digite a cidade do passageiro: ");
                                    passageiro[contPas].setCidade(sc.nextLine());
                                    System.out.print("Digite a profissão do passageiro: ");
                                    passageiro[contPas].setProfissao(sc.nextLine());
                                    System.out.println("Passageiro cadastrado com sucesso! O código dele é " + contPas);
                                    passageiro[contPas].setExiste(true); // torna o passageiro existente, para que depois possa realizar uma compra no nome dele
                                    contPas++;
                                } else if (opcao == 2) { // cadastro ônibus
                                    sc.nextLine();
                                    onibus[contBus] = new Onibus();
                                    System.out.print("Digite o modelo do ônibus: ");
                                    onibus[contBus].setModelo(sc.nextLine());
                                    System.out.print("Digite o ano de fabricação do ônibus: ");
                                    onibus[contBus].anoFab.setAno(sc.nextInt());
                                    System.out.print("Digita a marca do ônibus: ");
                                    sc.nextLine();
                                    onibus[contBus].setMarca(sc.nextLine());
                                    System.out.print("Digite a km do ônibus: ");
                                    onibus[contBus].setKm(sc.nextInt());
                                    sc.nextLine();
                                    System.out.println("Ônibus cadastrado com sucesso! O código dele é " + contBus);
                                    onibus[contBus].setDisp(true); // motorista acabou de ser cadastrado, logo está disponível
                                    onibus[contBus].colocarAssentos();
                                    contBus++;
                                } else if (opcao == 3) { // cadastro motorista
                                    sc.nextLine();
                                    motorista[contMot] = new Motorista();
                                    System.out.print("Digite o nome do motorista: ");
                                    motorista[contMot].setNome(sc.nextLine());
                                    System.out.print("Digite a CNH do motorista: ");
                                    motorista[contMot].setCnh(sc.nextLine());
                                    while (ver != 1) {
                                        System.out.print("Digite a data de admissão na forma DD MM AAAA: ");
                                        dia = sc.nextInt();
                                        mes = sc.nextInt();
                                        ano = sc.nextInt();
                                        if (dataVer.valida(dia, mes) == 1) { // Chama a função de verificar se a data inserida é valida.
                                            motorista[contMot].dataAdm.setDia(dia);
                                            motorista[contMot].dataAdm.setMes(mes);
                                            motorista[contMot].dataAdm.setAno(ano);
                                            ver = 1; // valida a verificação
                                        }
                                    }
                                    ver = 0;
                                    System.out.println("Motorista cadastrado com sucesso! O código dele é " + contMot);
                                    motorista[contMot].setDisp(true); // motorista acabou de ser cadastrado, logo está disponível
                                    contMot++;
                                } else if (opcao == 4) { // cadastro rota
                                    if ((contMot >= numRotas && contBus >= numRotas)) { // verificação para ver se é possível criar uma rota, para isso é necessário que o num de motoristas e num de ônibus seja maior que o de rotas
                                        sc.nextLine();
                                        rota[contRota] = new Rota();
                                        System.out.print("Digite a cidade de origem: ");
                                        rota[contRota].setOri(sc.nextLine());
                                        System.out.print("Digite a cidade de destino: ");
                                        rota[contRota].setDest(sc.nextLine());
                                        do {
                                            System.out.println("Haverá paradas durante o percurso? (1)-Sim, (2)-Não");
                                            opcao = sc.nextInt();
                                            if (opcao == 1) {
                                                do {
                                                    System.out.println("Quantas paradas haverão? (1)-Uma\n(2)-Duas\n(3)-Três");
                                                    qntPar = sc.nextInt();
                                                    sc.nextLine();
                                                    if (qntPar == 1) {
                                                        System.out.print("Informe a cidade: ");
                                                        rota[contRota].setCity1(sc.nextLine());
                                                        rota[contRota].setCity2("-");
                                                        rota[contRota].setCity3("-");
                                                    } else if (qntPar == 2) {
                                                        System.out.print("Informe a 1ª cidade: ");
                                                        rota[contRota].setCity1(sc.nextLine());
                                                        System.out.print("Informe a 2ª cidade: ");
                                                        rota[contRota].setCity2(sc.nextLine());
                                                        rota[contRota].setCity3("-");
                                                    } else if (qntPar == 3) {
                                                        System.out.print("Informe a 1ª cidade: ");
                                                        rota[contRota].setCity1(sc.nextLine());
                                                        System.out.print("Informe a 2ª cidade: ");
                                                        rota[contRota].setCity2(sc.nextLine());
                                                        System.out.print("Informe a 3ª cidade: ");
                                                        rota[contRota].setCity3(sc.nextLine());
                                                    }
                                                } while (qntPar != 1 && qntPar != 2 && qntPar != 3); // para receber quantas paradas haverão
                                            }
                                        } while (opcao != 2 && opcao != 1); // para receber uma resposta sobre possíveis paradas
                                        System.out.print("Digite o valor da passagem: ");
                                        rota[contRota].setValor(sc.nextDouble());
                                        while (ver != 1) {
                                            System.out.print("Digite o horário de saída previsto no formato HH MM: ");
                                            hora = sc.nextInt();
                                            min = sc.nextInt();
                                            if (horaVer.valida(hora, min) == 1) { // Chama a função de verificar se a hora inserida é valida.
                                                rota[contRota].saida.setHora(hora);
                                                rota[contRota].saida.setMin(min);
                                                ver = 1;
                                            }
                                        } // fim do while de verificação de horário de saída
                                        ver = 0; // zerado para realizar nova verificação
                                        while (ver != 1) {
                                            System.out.print("Digite o horário de chegada previsto no formato HH MM: ");
                                            hora = sc.nextInt();
                                            min = sc.nextInt();
                                            if (horaVer.valida(hora, min) == 1) {
                                                rota[contRota].chegada.setHora(hora);
                                                rota[contRota].chegada.setMin(min);
                                                ver = 1;
                                            }
                                        } // fim do while de verificação do horário de chegada
                                        ver = 0; // zerado para realizar nova verificação
                                        while (ver != 1) { // while para receber um motorista cadastrado e que esteja disponível
                                            System.out.println("----------------------------------------");
                                            System.out.println("Motoristas cadastrados possíveis para a rota");
                                            for (int i = 0; i < contMot; i++) { // printa todos os motoristas disponíveis
                                                if (motorista[i].getDisp() == true) { // se ele estiver disponível, será printado
                                                    System.out.println("Motorista " + i);
                                                }
                                            }
                                            System.out.println("----------------------------------------");
                                            System.out.print("Digite o código do motorista para ser designado a esta rota e que esteja cadastrado[Entre 0 e 19]: ");
                                            verCod = sc.nextInt();
                                            if (verCod < contMot) { // verificar se o código digitado existe dentre os instanciados
                                                if (motorista[verCod].getDisp() == true) {
                                                    rota[contRota].setCodMot(motorista[verCod]); // atribui à rota o motorista selecionado caso esteja disponível
                                                    motorista[verCod].setDisp(false); // após o motorista ser cadastrado, torna-o indisponível para outras rotas
                                                    ver = 1; // verificação recebe 1 quando é digitado um motorista que pode ser cadastrado na rota, e possibilita sair do while
                                                } else {
                                                    System.out.println("Motorista indisponível");
                                                }
                                            } else {
                                                System.out.println("Código inválido");
                                            }
                                        }
                                        ver = 0;
                                        while (ver != 1) { // while para receber um ônibus cadastrado e que esteja desponível
                                            System.out.println("----------------------------------------");
                                            System.out.println("Ônibus cadastrados possíveis para a rota");
                                            for (int i = 0; i < contBus; i++) { // printa todos os ônibus disponíveis
                                                if (onibus[i].getDisp() == true) { // se ele estiver disponível, será printado
                                                    System.out.println("Onibus " + i);
                                                }
                                            }
                                            System.out.println("----------------------------------------");
                                            System.out.print("Digite o código do ônibus designado a esta rota [Entre 0 e 19]: ");
                                            verCod = sc.nextInt();
                                            if (verCod < contBus) {
                                                if (onibus[verCod].getDisp() == true) {
                                                    rota[contRota].setCodBus(onibus[verCod]); // atribui à rota o ônibus selecionado caso esteja disponível
                                                    onibus[verCod].setDisp(false); // após o ônibus ser cadastrado, torna-o indisponível para outras rotas
                                                    ver = 1; // verificação recebe 1 quando é digitado um ônibus que pode ser cadastrado na rota, e possibilita sair do while
                                                } else {
                                                    System.out.println("Ônibus indisponível");
                                                }
                                            } else {
                                                System.out.println("Código inválido");
                                            }
                                        }
                                        ver = 0;
                                        System.out.println("Rota cadastrada com sucesso! O código dela é " + contRota);
                                        rota[contRota].setExiste(true); // torna a rota existente, para que depois possa realizar uma compra desta rota
                                        contRota++;
                                        numRotas++; // para verificar se há ou não mais motoristas e ônibus que rotas
                                    } else {
                                        System.out.println("Não é possível cadastrar rotas ainda pois não há número de motorista ou ônibus maior que de rotas");
                                    }
                                } // fim do if (Cadastro de rota)
                            } while (opcao != 0); // do while para realização de cadastros
                        } else if (adm == 2) { // if para alterar cadastro
                            do {
                                System.out.println("Informe o que deseja alterar:\n(1)-Passageiro\n(2)-Ônibus\n(3)-Motorista\n(4)-Rota\n(0)-Voltar");
                                opcao = sc.nextInt();
                                if (opcao == 1) {  // if para opção de alterar passageiro
                                    if (contPas > 0) { // para que seja possível alterar é necessário que haja pelo menos um passageiro cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Passageiros cadastrados:");
                                        for (int i = 0; i < contPas; i++) {
                                            System.out.println("Passageiro: " + passageiro[i].getNome() + ", Código: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do passageiro que você deseja alterar?");
                                            altPas = sc.nextInt();
                                            if (altPas >= contPas) { //o código informado é maior que os possíveis, logo, não existe
                                                System.out.println("Código do passageiro inválido");
                                            }
                                        } while (altPas >= contPas); // do while para receber apenas um código de passageiro válido, ou seja menor que o contPas
                                        do {
                                            System.out.println("O que você deseja alterar sobre o passageiro?");
                                            System.out.println("(1)-Nome\n(2)-RG\n(3)-Data de Nascimento\n(4)-Endereço\n(5)-Profissão");
                                            opcao = sc.nextInt();
                                            if (opcao == 1) { // if para alterar o nome
                                                sc.nextLine();
                                                System.out.print("Digite o nome: ");
                                                passageiro[altPas].setNome(sc.nextLine());
                                            } else if (opcao == 2) { // if para alterar o RG
                                                System.out.print("Digite o RG: ");
                                                passageiro[altPas].setRg(sc.nextInt());
                                            } else if (opcao == 3) { // if parar alterar a Data de nascimento
                                                while (ver != 1) {
                                                    System.out.print("Digite a data de nascimento na forma DD MM AAAA: ");
                                                    dia = sc.nextInt();
                                                    mes = sc.nextInt();
                                                    ano = sc.nextInt();
                                                    if (dataVer.valida(dia, mes) == 1) {
                                                        passageiro[altPas].dataNasc.setDia(dia);
                                                        passageiro[altPas].dataNasc.setMes(mes);
                                                        passageiro[altPas].dataNasc.setAno(ano);
                                                        ver = 1; // valida a verificação
                                                    }
                                                }
                                                ver = 0; // zerar a verificação para outras checagens
                                            } else if (opcao == 4) { // if para alterar endereço do passageiro
                                                sc.nextLine();
                                                System.out.print("Digite a rua de endereço do passageiro: ");
                                                passageiro[altPas].setRua(sc.nextLine());
                                                System.out.print("Digite o bairro de endereço do passageiro: ");
                                                passageiro[altPas].setBairro(sc.nextLine());
                                                System.out.print("Digite o número de endereço do passageiro: ");
                                                passageiro[altPas].setNumEnd(sc.nextInt());
                                                sc.nextLine();
                                                System.out.print("Digite o CEP de endereço do passageiro: ");
                                                passageiro[altPas].setCep(sc.nextLine());
                                                System.out.print("Digite a cidade do passageiro: ");
                                                passageiro[altPas].setCidade(sc.nextLine());
                                            } else if (opcao == 5) { // if para alterar a profissão do passageiro
                                                sc.nextLine();
                                                System.out.print("Digite a profissão do passageiro: ");
                                                passageiro[altPas].setProfissao(sc.nextLine());
                                            }
                                        } while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5); // do while para checar se o número inserido está correto
                                        passageiro[altPas].setExiste(true); // após alterar um passageiro, torna ele existente novamente, caso ele seja excluido (na exclusão tornamos falso a existência)
                                    } else {
                                        System.out.println("Não há passageiros cadastrados!");
                                    }
                                } else if (opcao == 2) { // if para opção de alterar ônibus
                                    if (contBus > 0) { // para que seja possível alterar é necessário que haja pelo menos um ônibus cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Ônibus cadastrados:");
                                        for (int i = 0; i < contBus; i++) {
                                            System.out.println("Código do Ônibus: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do ônibus que você deseja alterar?");
                                            altBus = sc.nextInt();
                                            if (altBus >= contBus) {
                                                System.out.println("Código de ônibus inválido");
                                            }
                                        } while (altBus >= contBus); // do while para receber apenas um código de ônibus válido, ou seja menor que o contBus
                                        do {
                                            System.out.println("O que você deseja alterar sobre o ônibus?");
                                            System.out.println("(1)-Modelo\n(2)-Ano de fabricação\n(3)-Marca\n(4)Quilometragem");
                                            opcao = sc.nextInt();
                                            if (opcao == 1) { // if para alterar o modelo do ônibus
                                                sc.nextLine();
                                                System.out.print("Digite o modelo do ônibus: ");
                                                onibus[altBus].setModelo(sc.nextLine());
                                            } else if (opcao == 2) { // if para alterar o ano de fabricação do ônibus
                                                System.out.print("Digite o ano de fabricação do ônibus: ");
                                                onibus[altBus].anoFab.setAno(sc.nextInt());
                                            } else if (opcao == 3) { // if para alterar a marca do ônibus
                                                sc.nextLine();
                                                System.out.print("Digita a marca do ônibus: ");
                                                onibus[altBus].setMarca(sc.nextLine());
                                            } else if (opcao == 4) { // if para alterar a quilometragem do ônibus
                                                System.out.print("Digite a km do ônibus: ");
                                                onibus[altBus].setKm(sc.nextInt());
                                            }
                                        } while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4); // do while para selecionar uma das 4 opções válidas
                                    } else {
                                        System.out.println("Não há ônibus cadastrados!");
                                    }
                                } else if (opcao == 3) { // if para opção de alterar motorista
                                    if (contMot > 0) { // para que seja possível alterar é necessário que haja pelo menos um motorista cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Motoristas cadastrados:");
                                        for (int i = 0; i < contMot; i++) {
                                            System.out.println("Motorista: " + motorista[i].getNome() + ", Código: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do motorista que você deseja alterar?");
                                            altMot = sc.nextInt();
                                            if (altMot >= contMot) {
                                                System.out.println("Código do motorista inválido");
                                            }
                                        } while (altMot >= contMot); // do while para receber apenas um código de motorista válido, ou seja menor que o contMot
                                        do {
                                            System.out.println("O que você deseja alterar sobre o motorista?");
                                            System.out.println("(1)-Nome\n(2)-CNH\n(3)-Data de Admissão");
                                            opcao = sc.nextInt();
                                            if (opcao == 1) {
                                                sc.nextLine();
                                                System.out.print("Digite o nome do motorista: ");
                                                motorista[altMot].setNome(sc.nextLine());
                                            } else if (opcao == 2) {
                                                sc.nextLine();
                                                System.out.print("Digite a CNH do motorista: ");
                                                motorista[altMot].setCnh(sc.nextLine());
                                            } else if (opcao == 3) {
                                                while (ver != 1) {
                                                    System.out.print("Digite a data de admissão na forma DD MM AAAA: ");
                                                    dia = sc.nextInt();
                                                    mes = sc.nextInt();
                                                    ano = sc.nextInt();
                                                    if (dataVer.valida(dia, mes) == 1) {
                                                        motorista[altMot].dataAdm.setDia(dia);
                                                        motorista[altMot].dataAdm.setMes(mes);
                                                        motorista[altMot].dataAdm.setAno(ano);
                                                        ver = 1; // valida a verificação
                                                    }
                                                }
                                                ver = 0;
                                            }
                                        } while (opcao != 1 && opcao != 2 && opcao != 3);
                                    } else {
                                        System.out.println("Não há motoristas cadastrados!");
                                    }
                                } else if (opcao == 4) { // if para opção de alterar rota
                                    if (contRota > 0) { // para que seja possível alterar é necessário que haja pelo menos uma rota cadastrada
                                        System.out.println("----------------------------------------");
                                        System.out.println("Rotas cadastradas:");
                                        for (int i = 0; i < contRota; i++) {
                                            System.out.println("Código da Rota: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código da Rota que você deseja alterar?");
                                            altRota = sc.nextInt();
                                            if (altRota >= contRota) {
                                                System.out.println("Código de rota inválido");
                                            }
                                        } while (altRota >= contRota); // do while para receber apenas um código de passageiro válido, ou seja menor que o contRota
                                        do {
                                            System.out.println("O que você deseja alterar sobre a rota?");
                                            System.out.println("(1)-Cidade de Origem\n(2)-Cidade de Destino\n(3)-Paradas durante o percurso\n(4)-Valor da passagemn\n(5)-Horário de saída e chegada\n(6)-Motorista da Rota\n(7)-Ônibus da Rota");
                                            opcao = sc.nextInt();
                                            if (opcao == 1) { // if para alterar a cidade de origem de uma rota
                                                sc.nextLine();
                                                System.out.print("Digite a cidade de origem: ");
                                                rota[altRota].setOri(sc.nextLine());
                                            } else if (opcao == 2) { // if para alterar a cidade de destino de uma rota
                                                sc.nextLine();
                                                System.out.print("Digite a cidade de destino: ");
                                                rota[altRota].setDest(sc.nextLine());
                                            } else if (opcao == 3) { // if para alterar as paradas de uma rota
                                                do {
                                                    System.out.println("Haverá paradas durante o percurso? (1)-Sim\n(2)-Não");
                                                    opcao = sc.nextInt();
                                                    if (opcao == 1) {
                                                        do {
                                                            System.out.println("Quantas paradas haverão? (1)-Uma\n(2)-Duas\n(3)-Três");
                                                            qntPar = sc.nextInt();
                                                            sc.nextLine();
                                                            if (qntPar == 1) {
                                                                System.out.print("Informe a cidade: ");
                                                                rota[altRota].setCity1(sc.nextLine());
                                                                rota[altRota].setCity2("-");
                                                                rota[altRota].setCity3("-");
                                                            } else if (qntPar == 2) {
                                                                System.out.print("Informe a 1ª cidade: ");
                                                                rota[altRota].setCity1(sc.nextLine());
                                                                System.out.print("Informe a 2ª cidade: ");
                                                                rota[altRota].setCity2(sc.nextLine());
                                                                rota[altRota].setCity3("-");
                                                            } else if (qntPar == 3) {
                                                                System.out.print("Informe a 1ª cidade: ");
                                                                rota[altRota].setCity1(sc.nextLine());
                                                                System.out.print("Informe a 2ª cidade: ");
                                                                rota[altRota].setCity2(sc.nextLine());
                                                                System.out.print("Informe a 3ª cidade: ");
                                                                rota[altRota].setCity3(sc.nextLine());
                                                            }
                                                        } while (qntPar != 1 && qntPar != 2 && qntPar != 3);
                                                    } else if (opcao == 2) {
                                                        rota[altRota].setCity1("-");
                                                        rota[altRota].setCity2("-");
                                                        rota[altRota].setCity3("-");
                                                    }
                                                } while (opcao != 1 && opcao != 2);
                                            } else if (opcao == 4) { // if para alterar o valor de uma rota
                                                System.out.print("Digite o valor da passagem: ");
                                                rota[altRota].setValor(sc.nextDouble());
                                            } else if (opcao == 5) { // if para alterar o horário de saída e chegada de uma rota
                                                while (ver != 1) {
                                                    System.out.print("Digite o horário de saída previsto no formato HH MM: ");
                                                    hora = sc.nextInt();
                                                    min = sc.nextInt();
                                                    if (horaVer.valida(hora, min) == 1) {
                                                        rota[altRota].saida.setHora(hora);
                                                        rota[altRota].saida.setMin(min);
                                                        ver = 1;
                                                    }
                                                } // fim do while de verificação de horário de saída
                                                ver = 0; // zerado para realizar nova verificação

                                                while (ver != 1) {
                                                    System.out.print("Digite o horário de chegada previsto no formato HH MM: ");
                                                    hora = sc.nextInt();
                                                    min = sc.nextInt();
                                                    if (horaVer.valida(hora, min) == 1) {
                                                        rota[altRota].chegada.setHora(hora);
                                                        rota[altRota].chegada.setMin(min);
                                                        ver = 1;
                                                    }
                                                } // fim do while de verificação do horário de chegada
                                                ver = 0; // zerado para realizar nova verificação
                                            } else if (opcao == 6) { // if para alterar motorista da rota
                                                if (contMot > contRota) { // condição para que haja mais motoristas que rotas, possibilitando assim, a troca de motorista
                                                    while (ver != 1) {
                                                        System.out.println("----------------------------------------");
                                                        System.out.println("Motoristas cadastrados possíveis para a rota");
                                                        for (int i = 0; i < contMot; i++) {
                                                            if (motorista[i].getDisp() == true) {
                                                                System.out.println("Motorista " + i);
                                                            }
                                                        }
                                                        System.out.println("----------------------------------------");
                                                        System.out.print("Digite o código do motorista para ser designado a esta rota e que esteja cadastrado[Entre 0 e 19]: ");
                                                        verCod = sc.nextInt();
                                                        if (verCod >= 0 && verCod < contMot) {
                                                            if (motorista[verCod].getDisp() == true) {
                                                                rota[altRota].codMot.setDisp(true); // liberar antigo motorista da rota
                                                                rota[altRota].setCodMot(motorista[verCod]);
                                                                motorista[verCod].setDisp(false);
                                                                ver = 1;
                                                            } else {
                                                                System.out.println("Motorista indisponível");
                                                            }
                                                        } else {
                                                            System.out.println("Código inválido");
                                                        }
                                                    }
                                                    ver = 0;
                                                } else {
                                                    System.out.println("Não há número de motoristas suficientes e disponíveis!");
                                                }
                                            } else if (opcao == 7) { // if para alterar ônibus da rota
                                                if (contBus > contRota) { // condição para que haja mais ônibus que rotas, possibilitando assim, a troca de ônibus
                                                    while (ver != 1) {
                                                        System.out.println("----------------------------------------");
                                                        System.out.println("Ônibus cadastrados possíveis para a rota");
                                                        for (int i = 0; i < contBus; i++) {
                                                            if (onibus[i].getDisp() == true) {
                                                                System.out.println("Onibus " + i);
                                                            }
                                                        }
                                                        System.out.println("----------------------------------------");
                                                        System.out.print("Digite o código do ônibus designado a esta rota [Entre 0 e 19]: ");
                                                        verCod = sc.nextInt();
                                                        if (verCod >= 0 && verCod < contBus) {
                                                            if (onibus[verCod].getDisp() == true) {
                                                                rota[altRota].codBus.setDisp(true); // liberar antigo ônibus da rota
                                                                rota[altRota].setCodBus(onibus[verCod]);
                                                                onibus[verCod].setDisp(false);
                                                                ver = 1;
                                                            } else {
                                                                System.out.println("Ônibus indisponível");
                                                            }
                                                        } else {
                                                            System.out.println("Código inválido");
                                                        }
                                                    }
                                                    ver = 0;
                                                } else {
                                                    System.out.println("Não há número de ônibus suficientes e disponíveis!");
                                                }
                                            }
                                        } while (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 4 && opcao != 5 && opcao != 6 && opcao != 7); // do while para checar se o número inserido está correto
                                        rota[altRota].setExiste(true); // após alterar uma rota, torna ela existente novamente, caso ela seja excluido (na exclusão tornamos falso a existência)
                                    } else {
                                        System.out.println("Não há rotas cadastradas!");
                                    }
                                }
                            } while (opcao != 0); // repetição para alterar cadastros enquanto for diferente de 0
                        } else if (adm == 3) { // exclusão de passageiros, ônibus, motoristas e rota
                            do {
                                System.out.println("Informe o que deseja remover:\n(1)-Passageiro\n(2)-Ônibus\n(3)-Motorista\n(4)-Rota\n(0)-Voltar");
                                opcao = sc.nextInt();
                                if (opcao == 1) { // excluir passageiro
                                    if (contPas > 0) { // para que seja possível excluir é necessário que haja pelo menos um passageiro cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Passageiros cadastrados:");
                                        for (int i = 0; i < contPas; i++) {
                                            System.out.println("Passageiro: " + passageiro[i].getNome() + ", Código: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do passageiro que você deseja excluir?");
                                            altPas = sc.nextInt();
                                            if (altPas >= contPas) {
                                                System.out.println("Código do passageiro inválido");
                                            }
                                        } while (altPas >= contPas);
                                        passageiro[altPas].setNome("");
                                        passageiro[altPas].setRg(0);
                                        passageiro[altPas].dataNasc.setDia(00);
                                        passageiro[altPas].dataNasc.setMes(00);
                                        passageiro[altPas].dataNasc.setAno(0000);
                                        passageiro[altPas].setRua("");
                                        passageiro[altPas].setBairro("");
                                        passageiro[altPas].setNumEnd(0);
                                        passageiro[altPas].setCep("");
                                        passageiro[altPas].setCidade("");
                                        passageiro[altPas].setProfissao("");
                                        passageiro[altPas].setExiste(false); // torna falsa a existência do passageiro, para que não seja utilizado na compra
                                    } else {
                                        System.out.println("Não há passageiros cadastrados!");
                                    }
                                } else if (opcao == 2) { // exclusão de ônibus
                                    if (contBus > 0) { // para que seja possível excluir é necessário que haja pelo menos um ônibus cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Ônibus cadastrados:");
                                        for (int i = 0; i < contBus; i++) {
                                            System.out.println("Código do Ônibus: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do ônibus que você deseja excluir?");
                                            altBus = sc.nextInt();
                                            if (altBus >= contBus) {
                                                System.out.println("Código de ônibus inválido");
                                            }
                                        } while (altBus >= contBus);
                                        onibus[altBus].setModelo("");
                                        onibus[altBus].anoFab.setAno(0000);
                                        onibus[altBus].setMarca("");
                                        onibus[altBus].setKm(0);
                                        onibus[altBus].setDisp(false); // torna o ônibus indisponível pois foi excluído
                                    } else {
                                        System.out.println("Não há ônibus cadastrados!");
                                    }
                                } else if (opcao == 3) { // exclusão motorista
                                    if (contMot > 0) { // para que seja possível excluir é necessário que haja pelo menos um motorista cadastrado
                                        System.out.println("----------------------------------------");
                                        System.out.println("Motoristas cadastrados:");
                                        for (int i = 0; i < contMot; i++) {
                                            System.out.println("Motorista: " + motorista[i].getNome() + ", Código: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código do motorista que você deseja excluir?");
                                            altMot = sc.nextInt();
                                            if (altMot >= contMot) {
                                                System.out.println("Código do motorista inválido");
                                            }
                                        } while (altMot >= contMot);
                                        motorista[altMot].setNome("");
                                        motorista[altMot].setCnh("");
                                        motorista[altMot].dataAdm.setDia(00);
                                        motorista[altMot].dataAdm.setMes(00);
                                        motorista[altMot].dataAdm.setAno(0000);
                                        motorista[altMot].setDisp(false); // torna o motorista indisponível pois foi excluído
                                    } else {
                                        System.out.println("Não há motoristas cadastrados!");
                                    }
                                } else if (opcao == 4) {
                                    if (contRota > 0) { // para que seja possível excluir é necessário que haja pelo menos uma rota cadastrada
                                        System.out.println("----------------------------------------");
                                        System.out.println("Rotas cadastradas:");
                                        for (int i = 0; i < contRota; i++) {
                                            System.out.println("Código da Rota: " + i);
                                        }
                                        System.out.println("----------------------------------------");
                                        do {
                                            System.out.println("Qual o código da Rota que você deseja excluir?");
                                            altRota = sc.nextInt();
                                            if (altRota >= contRota) {
                                                System.out.println("Código de rota inválido");
                                            }
                                        } while (altRota >= contRota);
                                        rota[altRota].setOri("");
                                        rota[altRota].setDest("");
                                        rota[altRota].setCity1("");
                                        rota[altRota].setCity2("");
                                        rota[altRota].setCity3("");
                                        rota[altRota].setValor(0);
                                        rota[altRota].saida.setHora(00);
                                        rota[altRota].saida.setMin(00);
                                        rota[altRota].chegada.setHora(00);
                                        rota[altRota].chegada.setMin(00);
                                        rota[altRota].codMot.setDisp(true); // libera o motorista que pertencia a rota
                                        rota[altRota].codBus.setDisp(true); // libera o ônibus que pertencia a rota
                                        rota[altRota].setExiste(false); // torna a rota falsa, pois foi excluída
                                    } else {
                                        System.out.println("Não há rotas cadastradas!");
                                    }
                                }
                            } while (opcao != 0); // do while para ficar no menu de remoção
                        }

                    } while (adm != 0); // do while para permanecer no menu do adm enquanto não digitar 0
                } else {
                    System.out.println("Senha incorreta!");
                }
            } else if (useroradm == 2) { // compra usuário
                do {
                    Compra passagem = new Compra();
                    System.out.println("O que deseja fazer?\n(1)-Compra de passagem\n(0)-Voltar");
                    compra = sc.nextInt();
                    if (compra == 1) {
                        if (contRota > 0 && contPas > 0) { // é necessário que haja ao menos uma rota e um pasasgeiro cadastrado para comprar a passagem
                            while (ver != 1) {
                                System.out.println("----------------------------------------");
                                System.out.println("Passageiros cadastrados:");
                                for (int i = 0; i < contPas; i++) {
                                    if (passageiro[i].getExiste() == true) {
                                        System.out.println("Passageiro: " + passageiro[i].getNome() + ", Código: " + i);
                                    }
                                }
                                System.out.println("----------------------------------------");
                                System.out.println("Qual o código do passageiro que está realizando a compra?");
                                altPas = sc.nextInt();
                                if (altPas < contPas) {
                                    if (passageiro[altPas].getExiste() == true) { // só possibilita comprar a passagem caso o passageiro esteja cadastrado
                                        passagem.setPas(passageiro[altPas]);
                                        ver = 1;
                                    } else {
                                        System.out.println("Passageiro inválido");
                                    }
                                } else {
                                    System.out.println("Código do passageiro inválido");
                                }
                            } // while que executa até receber um código de passageiro que exista
                            ver = 0;
                            while (ver != 1) {
                                System.out.println("----------------------------------------");
                                System.out.println("Rotas disponíveis para a compra");
                                for (int i = 0; i < contRota; i++) {
                                    if (rota[i].getExiste() == true) { // para não mostrar rotas excluidas
                                        System.out.println("***********************************");
                                        System.out.println("Rota " + i);
                                        System.out.println("Cidade de origem: " + rota[i].getOri());
                                        System.out.println("Cidade de destino: " + rota[i].getDest());
                                        System.out.println("Cidade de parada 1: " + rota[i].getCity1());
                                        System.out.println("Cidade de parada 2: " + rota[i].getCity2());
                                        System.out.println("Cidade de parada 3: " + rota[i].getCity3());
                                        System.out.println("Nome do motorista: " + rota[i].codMot.getNome());
                                        System.out.println("Horário de saída previsto: " + rota[i].saida.getHora() + ":" + rota[i].saida.getMin());
                                        System.out.println("Horário de chegada previsto: " + rota[i].chegada.getHora() + ":" + rota[i].chegada.getMin());
                                        System.out.println("Valor da passagem: " + rota[i].getValor());
                                        System.out.println("***********************************");
                                    }
                                }
                                System.out.println("----------------------------------------");
                                System.out.println("Qual o código da Rota que você deseja comprar?");
                                altRota = sc.nextInt();
                                if (altRota < contRota) {
                                    if (rota[altRota].getExiste() == true) {
                                        passagem.setRota(rota[altRota]);
                                        System.out.println("***********************************");
                                        System.out.println("Assentos disponíveis:");
                                        rota[altRota].codBus.assentosDisponiveis(); // chama a função que printa todos os assentos que estão disponíveis
                                        System.out.println("***********************************");
                                        do {
                                            System.out.println("Qual o assento que você deseja comprar?");
                                            lugar = sc.nextInt();
                                            if (lugar < 42 && lugar >= 0) { // condição para que seja selecionado um assento de código 0 a 41;
                                                passagem.rota.codBus.compraAssento(lugar);
                                                ver = 1; // caso o assento selecionado seja entre 0 e 41, permite sair do while e valida a verificação
                                            } else {
                                                System.out.println("Número do assento inválido!");
                                            }
                                        } while (ver != 1);
                                    } else {
                                        System.out.println("Rota inválida");
                                    }
                                } else {
                                    System.out.println("Código da rota inválida");
                                }
                            } // while que executa até receber um código de rota que exista, e seja escolhido um assento disponível
                            ver = 0; // zera verificação para ser utilizado em outros casos.
                            System.out.println("----------------------------------------");
                        } else {
                            System.out.println("Não há rotas ou passageiros cadastrados para realização da compra!");
                        }
                    }
                } while (compra != 0);
            }
        } while (useroradm != 0); // para alternar entre adm e usuário
    }

}

/* Há alguns passos que são análogos entre cadastro, alteração e exclusão de passageiros, logo para maior clareza do código 
comentamos apenas na primeira ocorrência */
