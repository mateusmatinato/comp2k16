/**********************************************
*                                             * 
* Trabalho de Programação Orientada a Objetos *
* Integrantes: João Marcos Rosa               *
*              Jonatan Rodrigues              *
*              Mateus Matinato                *
* Data de Entrega: 10/05/2017                 *
* Tema: Viação Asteróide                      *
*                                             *
**********************************************/


package viacaoasteroide;
import java.util.*;

public class ViacaoAsteroide {

    public static void main(String[] args) {
        int opc = 1;
        int opc2 = 1;
        int p,o,m,r;
        int senha = 1234;  //Define a senha para logar como admin 
        String usuario = "asteroide";  //Define o usuário para logar como admin
        Passageiro pas[] = new Passageiro[400];  //Cria vetor de passageiros
        Onibus oni[] = new Onibus[40]; //Cria vetor de onibus
        Motorista mot[] = new Motorista[20]; //Cria vetor de motoristas
        Rotas rota[] = new Rotas[20]; //Cria vetor de rotas
        String usuarioinserido; 
        int senhainserida, admin = 1;
        Scanner input = new Scanner(System.in);
        while(admin != 0){ 
            System.out.println();
            System.out.println("|===================================|");
            System.out.println("|    BEM VINDO A VIAÇÃO ASTERÓIDE   |");
            System.out.println("|-----------------------------------|");
            System.out.println("|     Menu Admin      (1)           | ");
            System.out.println("|     Menu Cliente    (2)           |");
            System.out.println("|     Sair do Sistema (0)           |");
            System.out.println("|===================================|");
            System.out.print("Digite a opção-> ");
            admin = input.nextInt();     
            input.nextLine();
            if(admin == 1){
                //Caso inserir 1: entra no menu admin
                System.out.println("Digite o usuário: ");
                usuarioinserido = input.next();
                if(usuarioinserido.equals(usuario)){ //compara o login inserido com o definido
                    System.out.println("Digite a senha: "); //caso for o login certo, pede a senha
                    senhainserida = input.nextInt();
                    if(senhainserida== senha){ //caso a senha for correta, abre o sistema de admin
                        opc = 1;
                        while (opc != 0){ //Opc = Opção para entrar em algum menu
                        System.out.println();
                        System.out.println("|===================================|");
                        System.out.println("|         MENU ADMINISTRADOR        |");
                        System.out.println("|-----------------------------------|");
                        System.out.println("|   Menu de Cadastros      (1)      |");
                        System.out.println("|   Menu de Alterações     (2)      |");
                        System.out.println("|   Menu de Exclusões      (3)      |");
                        System.out.println("|   Voltar ao menu inicial (0)      |");
                        System.out.println("|===================================|");
                        System.out.print("Digite a opção: ");
                        opc = input.nextInt(); 
                        opc2 = 1;                       
                            if(opc==1){ // MENU DE CADASTROS
                                while(opc2 != 0){ //OPC2 = Opção para entrar em algum menu de cadastro
                                    System.out.println();
                                    System.out.println("|=====================================|");
                                    System.out.println("|          MENU DE CADASTROS          |");
                                    System.out.println("|-------------------------------------|");
                                    System.out.println("|    Cadastro de Passageiros (1)      |");
                                    System.out.println("|    Cadastro de Ônibus      (2)      |");
                                    System.out.println("|    Cadastro de Motorista   (3)      |");
                                    System.out.println("|    Cadastro de Rotas       (4)      |");
                                    System.out.println("|    Menu Administrador      (0)      |");
                                    System.out.println("|=====================================|");
                                    System.out.print("Digite a opção-> ");
                                    opc2 = input.nextInt();
                                    switch(opc2){ // OPÇÃO DO MENU DE CADASTROS
                                        case 1:{ //CADASTRO DE PASSAGEIROS
                                            String nome, rg, prof, rua, bairro, cidade;   
                                            int num;
                                            System.out.print("Digite o código do usuário (0 a 399):"); //código = posição do vetor 
                                            p = input.nextInt(); 
                                            if(pas[p]==null){ //verifica se já não existe um passageiro nesse indice
                                                input.nextLine();                                       
                                                System.out.print("Digite o nome do passageiro: ");
                                                nome = input.nextLine();
                                                System.out.print("Digite o RG do passageiro: ");                                  
                                                rg = input.nextLine();
                                                System.out.print("Digite a profissão do passageiro: ");
                                                prof = input.nextLine();
                                                System.out.println("Digite o endereço do passageiro.");
                                                System.out.print("Digite a rua: ");
                                                rua = input.nextLine();
                                                System.out.print("Digite o bairro: ");
                                                bairro = input.nextLine();
                                                System.out.print("Digite a cidade: ");
                                                cidade = input.nextLine();
                                                System.out.print("Digite o número da casa: ");
                                                num = input.nextInt();
                                                
                                                pas[p] = new Passageiro(nome,rg,prof,rua,bairro,cidade,num); //instancia o passageiro
                                                pas[p].dNasc(); //verifica e atribui o dia de nascimento
                                                pas[p].mNasc(); //   =     =    =    = mes de nascimento
                                                pas[p].aNasc();//    =     =    =    = ano de nascimento
                                                System.out.println("Passageiro cadastrado.");
                                            }
                                            else System.out.println("Já existe um usuário com esse código.");                                            
                                        } break;  
                                        case 2:{ //CADASTRO DE ONIBUS
                                            String modelo, marca;
                                            int anoFab, km;
                                            System.out.print("Digite o código do Ônibus (0 a 39): "); //codigo = indice do vetor
                                            o = input.nextInt();
                                            if(oni[o]==null){ //verifica se o onibus já não foi cadastrado
                                                input.nextLine();
                                                System.out.print("Digite o modelo do ônibus: ");
                                                modelo = input.nextLine();
                                                System.out.print("Digite a marca do ônibus: ");
                                                marca = input.nextLine();
                                                System.out.print("Digite o ano de fabricação: ");
                                                anoFab = input.nextInt();
                                                    int anoFabV = 0; //verifica se o ano inserido é válido
                                                    while(anoFabV == 0){                                                                                                 
                                                        if(anoFab < 1950 || anoFab > 2017){
                                                            System.out.println("Ano inválido, digite novamente.");
                                                            System.out.print("Digite o ano de fabricação: ");
                                                            anoFab = input.nextInt();                                                    
                                                        }
                                                        else anoFabV = 1;
                                                    }
                                                System.out.print("Digite a quilometragem: ");
                                                km = input.nextInt();
                                                oni[o] = new Onibus(modelo,marca,anoFab,km); //instancia onibus
                                                oni[o].criaOnibus(); //cria a matriz dos assentos 
                                                oni[o].setRota(false); //seta o atributo de rota como false
                                                System.out.println("Ônibus cadastrado.");
                                            }
                                            else System.out.println("Já existe um ônibus com esse código.");
                                        } break;
                                   
                                        case 3:{ //CADASTRO DE MOTORISTAS
                                            String cnh, nome;
                                            System.out.print("Digite o código do motorista (0 a 19): "); //codigo = indice do vetor
                                            m = input.nextInt();
                                            if(mot[m]==null ){ //verifica se o motorista ja foi cadastrado
                                                input.nextLine();
                                                System.out.print("Digite o nome do Motorista: ");
                                                nome = input.nextLine();
                                                System.out.print("CNH: ");
                                                cnh = input.nextLine();
                                                mot[m] = new Motorista (cnh,nome); //instancia o motorista
                                                mot[m].dAdm(); //verifica e atribui o dia de admissão
                                                mot[m].mAdm(); //  =      =   =     = mes de admissão
                                                mot[m].aAdm(); //  =      =   =     = ano de admissão
                                                mot[m].calculaTempo(); //calcula o tempo de admissão
                                            }
                                            else System.out.println("Já existe um motorista com esse código.");
                                        } break;
                                        
                                        case 4:{ //CADASTRO DE ROTAS
                                            String origem, destino, p1,p2,p3;
                                            double valor;
                                            int numparada;
                                            System.out.print("Digite o código da rota (0 a 19): "); //codigo = indice do vetor
                                            r = input.nextInt();
                                            if(rota[r]==null){ //verifica se já existe uma rota nessa posição
                                                input.nextLine();
                                                System.out.print("Digite a origem: ");
                                                origem = input.nextLine();                                        
                                                System.out.print("Digite o destino: ");
                                                destino = input.nextLine();
                                                System.out.print("Digite o valor da passagem: ");
                                                valor = input.nextDouble();
                                                System.out.print("Quantas paradas haverá(Min 0/Max 3): "); //numero de paradas
                                                numparada = input.nextInt();
                                                input.nextLine();
                                                if(numparada == 1){  //caso for 1 parada
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,"","",valor); //instancia passando a p2 e p3 como vazio                                         
                                                }
                                                else if(numparada == 2){ //caso for 2 paradas
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    System.out.print("Parada 2: ");
                                                    p2 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,p2,"",valor); //instancia passando p3 como vazio
                                                }
                                                else if(numparada == 3){ //caso for 3 paradas
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    System.out.print("Parada 2: ");
                                                    p2 = input.nextLine();
                                                    System.out.print("Parada 3: ");
                                                    p3 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,p2,p3,valor); //instancia passando p1,p2 e p3 como vazio
                                                }
                                                else if(numparada<0 || numparada>3){ //verifica se o número de paradas é válido
                                                    System.out.println("Número inválido de parada(s)."); 
                                                    rota[r] = new Rotas(origem,destino,"","","",valor); //como o número é inválido, instancia a rota com p1,p2 e p3 vazia
                                                }
                                                else{
                                                rota[r] = new Rotas(origem,destino,"","","",valor); 
                                                }
                                                rota[r].horaSaida(); //verifica e atribui a hora de saída
                                                rota[r].horaChegada(); //verifica e atribui a hora de chegada
                                                
                                                //Cadastrar Onibus na Rota
                                                int numoni = rota[r].cadastraOnibus(oni); //atribui o número de um onibus a rota
                                                if(numoni!=-1 && oni[numoni].getRota()!=true){ //verifica se o onibus é válido
                                                    oni[numoni].setRota(true);
                                                   System.out.println("Onibus cadastrado na rota.");
                                                   rota[r].setNumoni(numoni); //guarda o indice do onibus da rota, na rota.
                                               }
                                                else{
                                                   System.out.println("Ônibus não cadastrado.");
                                                    rota[r].setNumoni(-1); //guarda o valor -1 como indice do onibus da rota
                                                }
                                                
                                                //Cadastrar Motorista na Rota
                                                int nummoto = rota[r].cadastraMotorista(mot); //atribui o numero de um motorista a rota
                                                if(nummoto!=-1){ //verifica se o numero do motorista é valido
                                                   System.out.println("Motorista cadastrado na rota.");
                                                   rota[r].setNummoto(nummoto); //guarda o indice do motorista da rota, na rota.
                                               }
                                                else{
                                                    System.out.println("Motorista não cadastrado. "); 
                                                    rota[r].setNummoto(-1);//guarda o valor -1 como indice do motorista da rota
                                                }
                                            } else System.out.println("Já existe uma rota com esse código.");
                                        }
                                    }   
                                } //SAI DO MENU DE CADASTRO
                            }
                            else if(opc == 2){ // MENU DE ALTERAÇÕES
                                while(opc2 != 0){
                                    System.out.println();
                                    System.out.println("|================================|");
                                    System.out.println("|       MENU DE ALTERAÇÕES       |");
                                    System.out.println("|--------------------------------|");
                                    System.out.println("|   Alterar Passageiro (1)       |");
                                    System.out.println("|   Alterar Ônibus     (2)       |");
                                    System.out.println("|   Alterar Motoristas (3)       |");
                                    System.out.println("|   Alterar Rotas      (4)       |");
                                    System.out.println("|   Menu Administrador (0)       |");
                                    System.out.println("|================================|");
                                    System.out.print("Digite a opção-> ");
                                    opc2 = input.nextInt();
                                    switch(opc2){
                                        case 1:{ // ALTERAR PASSAGEIRO
                                            int nump;
                                            System.out.print("Digite o número do passageiro: ");
                                            nump = input.nextInt(); //indice do passageiro a ser alterado
                                            System.out.println();
                                            if(pas[nump]!=null){ //verifica se o número do passageiro é válido
                                                System.out.println("Passageiro Número "+nump+":"); 
                                                pas[nump].mostraPassageiro(); //mostra os dados do passageiro a ser alterado
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar: ");
                                                System.out.println("Nome                 (1)");
                                                System.out.println("RG                   (2)");
                                                System.out.println("Profissão            (3)");
                                                System.out.println("Endereço             (4)");
                                                System.out.println("Data de Nascimento   (5)");
                                                System.out.println("Voltar               (0)");
                                                System.out.print("Digite a opção-> ");
                                                int dado = input.nextInt();
                                                switch(dado){ //dado a ser alterado
                                                    case 1:{
                                                        pas[nump].setNome();
                                                    } break;
                                                    case 2:{
                                                        pas[nump].setRG();
                                                    }break;
                                                    case 3:{
                                                        pas[nump].setProf();
                                                    }break;
                                                    case 4:{
                                                        pas[nump].setEnd();
                                                    }break;
                                                    case 5:{
                                                        pas[nump].dNasc();
                                                        pas[nump].mNasc();
                                                        pas[nump].aNasc();
                                                    }break;
                                                }                                            
                                            } else System.out.println("Usuário foi removido ou não existe.");                    
                                        }break;
                                        case 2:{
                                            int numo; //numero do onibus
                                            System.out.print("Digite o número do Ônibus: ");
                                            numo = input.nextInt(); //numero do onibus a ser alterado
                                            if(oni[numo]!=null){ //verifica se o onibus é válido
                                                input.nextLine();
                                                System.out.println();
                                                System.out.println("Ônibus número "+numo+":");
                                                oni[numo].mostraOnibus();
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar:");
                                                System.out.println("Modelo            (1)");
                                                System.out.println("Marca             (2)");
                                                System.out.println("Ano de Fabricação (3)");
                                                System.out.println("Quilometragem     (4)");
                                                System.out.println("Zerar Assentos    (5)");
                                                System.out.println("Voltar            (0)");
                                                System.out.print("Digite a opção-> ");
                                                int dado = input.nextInt();
                                                switch(dado){
                                                    case 1:{
                                                        oni[numo].setModelo();
                                                    } break;
                                                    case 2:{
                                                        oni[numo].setMarca();
                                                    }break;
                                                    case 3:{
                                                        oni[numo].setAnoFab();
                                                    }break;
                                                    case 4:{
                                                        oni[numo].setKm();
                                                    }break;
                                                    case 5:{
                                                        oni[numo].criaOnibus();
                                                        System.out.println("Assentos Zerados.");
                                                    }
                                                }
                                            } else System.out.println("O ônibus foi removido ou não existe.");
                                        }break;
                                        case 3:{
                                            int numm; //numero do motorista
                                            System.out.print("Digite o número do Motorista: ");
                                            numm = input.nextInt();
                                            if(mot[numm]!=null){ //verifica se o motorista é válido
                                                System.out.println();
                                                System.out.println("Motorista número "+numm+":");
                                                mot[numm].mostraMotorista();
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar:");
                                                System.out.println("Nome             (1)");
                                                System.out.println("CNH              (2)");
                                                System.out.println("Data de Admissão (3)");
                                                System.out.println("Voltar           (0)");
                                                System.out.print("Digite a opção-> ");
                                                int dado = input.nextInt();
                                                switch(dado){
                                                    case 1:{
                                                        mot[numm].setNome();
                                                    }break;
                                                    case 2:{
                                                        mot[numm].setCnh();
                                                    }break;
                                                    case 3:{
                                                        mot[numm].dAdm();
                                                        mot[numm].mAdm();
                                                        mot[numm].aAdm();
                                                    }break;
                                                }
                                            } else System.out.println("O motorista foi removido ou não existe.");        
                                        }break;
                                        case 4:{
                                            int numr; //numero da rota
                                            System.out.print("Digite o número da Rota: ");
                                            numr = input.nextInt(); 
                                            if(rota[numr]!=null){ //verifica se a rota é válida
                                                System.out.println();
                                                System.out.println("Rota número "+numr+":");
                                                rota[numr].mostraRota();
                                                if(rota[numr].getOnibus() != -1 && oni[rota[numr].getOnibus()]!=null){ //verifica se existe um onibus na rota
                                                System.out.println("Ônibus da Rota: ");
                                                oni[rota[numr].getOnibus()].mostraOnibus();
                                                }
                                                if(rota[numr].getMotorista() != -1 && mot[rota[numr].getMotorista()]!=null){ //verifica se existe um motorista na rota
                                                System.out.println("Motorista da Rota: ");
                                                mot[rota[numr].getMotorista()].mostraMotorista();
                                                }
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar: ");
                                                System.out.println("Origem             (1)");
                                                System.out.println("Destino            (2)");
                                                System.out.println("Primeira Parada    (3)");
                                                System.out.println("Segunda Parada     (4)");
                                                System.out.println("Terceira Parada    (5)");
                                                System.out.println("Valor da Passagem  (6)");
                                                System.out.println("Horário de Saída   (7)");
                                                System.out.println("Horário de Chegada (8)");
                                                System.out.println("Motorista da Rota  (9)");
                                                System.out.println("Onibus da Rota     (10)");
                                                System.out.println("Voltar             (0)");
                                                System.out.print("Digite a opção-> ");
                                                
                                                int dado = input.nextInt();
                                                switch(dado){ //dado a ser alterado
                                                    case 1:{
                                                        rota[numr].setOrigem();
                                                    }break;
                                                    case 2:{
                                                        rota[numr].setDestino();
                                                    }break;
                                                    case 3:{
                                                        rota[numr].setP1();
                                                    }break;
                                                    case 4:{
                                                        rota[numr].setP2();
                                                    }break;
                                                    case 5:{
                                                        rota[numr].setP3();
                                                    }break;
                                                    case 6:{
                                                        rota[numr].setValor();
                                                    }break;
                                                    case 7:{
                                                        rota[numr].horaSaida();
                                                    }break;
                                                    case 8:{
                                                        rota[numr].horaChegada();
                                                    }break;
                                                    case 9:{
                                                        int novomoto = rota[numr].cadastraMotorista(mot); //atribui o novo numero do motorista
                                                        if(novomoto != -1) rota[numr].setNummoto(novomoto); //verifica se o motorista é valido
                                                        else System.out.println("Motorista não alterado.");
                                                    }break;
                                                    case 10:{
                                                        int velhooni = rota[numr].getOnibus(); //recebe o valor do antigo onibus
                                                        int novooni = rota[numr].cadastraOnibus(oni); //atribui o novo numero do onibus
                                                        if(novooni != -1 && oni[novooni].getRota()!=true){ //verifica se o novo onibus é valido
                                                            if(velhooni != -1) oni[velhooni].setRota(false); //caso o novo for válido, disponibiliza o onibus antigo para outras rotas
                                                            rota[numr].setNumoni(novooni); //atribui a rota o novo onibus
                                                            oni[novooni].setRota(true); //deixa o novo onibus indisponivel para outras rotas
                                                        }
                                                        else if(novooni ==-1) System.out.println("Ônibus não alterado."); 
                                                        else System.out.println("Ônibus já utilizado ou não existe.");
                                                    }break; 
                                                }
                                            } else System.out.println("A rota não existe ou foi removida.");
                                        }break;
                                    }
                                }
                            }
                            else if(opc == 3){
                                int opcao = 1;
                                while(opcao!=0){
                                System.out.println();
                                System.out.println("|================================|");
                                System.out.println("|        MENU DE EXCLUSÕES       |");
                                System.out.println("|--------------------------------|");
                                System.out.println("|   Excluir Passageiros (1)      |");
                                System.out.println("|   Excluir Ônibus      (2)      |");
                                System.out.println("|   Excluir Motorista   (3)      |");
                                System.out.println("|   Excluir Rotas       (4)      |");
                                System.out.println("|   Menu Administrador  (0)      |");
                                System.out.println("|================================|");
                                System.out.print("Digite a opção-> ");
                                opcao = input.nextInt(); 
                                    switch(opcao){
                                        case 1:{
                                            // excluir passageiros
                                            System.out.print("Digite o número do passageiro a ser excluído: ");
                                            int nump = input.nextInt(); //numero do passageiro a ser excluido
                                            if(pas[nump]!=null){ //verifica se o passageiro existe
                                                System.out.println();
                                                System.out.println("Passageiro "+nump+":");
                                                pas[nump].mostraPassageiro(); //mostra o passageiro
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
                                                if(input.next().charAt(0)=='S'){ //caso digitar S o passageiro é excluido
                                                    pas[nump] = null;// Comando para excluir
                                                    System.out.println("Passageiro excluído.");
                                                }
                                                else ;
                                            } else System.out.println("Passageiro "+nump+" não existe.");
                                        } break;
                                        case 2:{
                                            // excluir onibus
                                            System.out.print("Digite o número do Ônibus a ser excluído: ");
                                            int numo = input.nextInt(); //numero do onibus a ser excluido
                                            if(oni[numo]!=null){ //verifica se o onibus existe
                                                System.out.println();
                                                System.out.println("Ônibus "+numo+":");
                                                oni[numo].mostraOnibus(); //mostra o onibus
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
                                                if(input.next().charAt(0)=='S'){ //caso digitar S o onibus é excluido
                                                    oni[numo] = null; //Comando para excluir
                                                    System.out.println("Ônibus excluído.");
                                                    
                                                }
                                                else ;
                                            } else System.out.println("Ônibus "+numo+" não existe.");
                                        } break;
                                        case 3:{
                                            // excluir motorista
                                            System.out.print("Digite o número do motorista a ser excluído: ");
                                            int numm = input.nextInt(); //numero do motorista a ser excluido
                                            if(mot[numm]!=null){ // verifica se o motorista existe
                                                System.out.println();
                                                System.out.println("Motorista "+numm+":");
                                                mot[numm].mostraMotorista(); //mostra o motorista
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): "); //caso digitar S o motorista é excluido
                                                if(input.next().charAt(0)=='S'){
                                                    mot[numm] = null; //Comando para excluir
                                                    System.out.println("Motorista excluído.");
                                                }
                                                else ;
                                            } else System.out.println("Motorista "+numm+" não existe.\n");
                                        } break;
                                        case 4:{
                                            // excluir rotas
                                            System.out.print("Digite o número da rota a ser excluído: ");
                                            int numr = input.nextInt(); //numero da rota a ser excluida 
                                            if(rota[numr]!=null){ //verifica se a rota existe
                                                System.out.println(); 
                                                System.out.println("Rota "+numr+":");
                                                rota[numr].mostraRota(); //mostra a rota
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): "); //caso digitar S a rota é excluida
                                                if(input.next().charAt(0)=='S'){
                                                    oni[rota[numr].getOnibus()].setRota(false);
                                                    rota[numr] = null; //Comando para excluir
                                                    System.out.println("Rota excluída.");
                                                }   
                                               else ;
                                            } else System.out.println("Rota "+numr+" não existente.");
                                        } break;
                                    }
                                }
                        }
                        }
                    }
                    else if (senhainserida!= senha){ //caso a senha for invalida
                        System.out.println("Senha Inválida.");
                    }
                }
                else System.out.println("Usuário Inválido."); //caso o usuário for invalido
            }
            else if(admin == 2){ //entrar no menu de clientes
                System.out.println();
                opc = 1;
                int i, numrota;
                while(opc != 0){
                    System.out.println();
                    System.out.println("|================================|");
                    System.out.println("|         MENU CLIENTE           |");
                    System.out.println("|--------------------------------|");
                    System.out.println("|    Comprar Passagem       (1)  |");
                    System.out.println("|    Voltar ao menu inicial (0)  |");
                    System.out.println("|================================|");
                    System.out.print("Digite a opção-> ");
                    opc = input.nextInt();
                    System.out.println();
                    if(opc==1){
                        int controta = 0; //contador para verificar se existem rotas cadastradas
                        for(i=0;i<19;i++){ //repetição para mostrar rotas
                            if(rota[i]!=null){  //se a rota for != null ela existe
                                System.out.println("Rota número "+i+":");
                                rota[i].rotaCliente(); //mostra a rota 
                                System.out.println();
                            }
                            else controta++; //se é =null aumenta contador
                            if(controta == 19) System.out.println("Não existe rota cadastrada."); //se o contador = 19 significa que todas as rotas não existem
                        }
                        System.out.print("Digite o número da rota desejada ou voltar (-1): ");
                        numrota = input.nextInt(); //numero da rota a ser comprada
                        if(numrota == -1); 
                        else if(rota[numrota]!=null){ //verifica se a rota inserida existe
                            rota[numrota].mostraRota(); //mostra a rota desejada
                            if(rota[numrota].getMotorista() != -1){ //verifica se a rota tem motorista
                            System.out.println("Motorista da Rota: ");
                            mot[rota[numrota].getMotorista()].mostraMotorista();  //mostra o motorista
                            mot[rota[numrota].getMotorista()].calculaTempo();     //mostra o tempo de admissão
                            }
                            else System.out.println("Não existe motorista cadastrado nessa rota.");                
                           
                            int comprou = 0;
                            if(rota[numrota].getOnibus()!= -1 && oni[rota[numrota].getOnibus()]!=null){ //verifica se a rota possui onibus
                                System.out.println("Ônibus da Rota: ");
                                oni[rota[numrota].getOnibus()].mostraOnibus(); //mostra o onibus da rota
                                System.out.println("Assentos do Ônibus (J=Janela/C=Corredor): ");
                                System.out.println(" J\t  C\t  C\t  J");
                                oni[rota[numrota].getOnibus()].mostraAssentos(); //mostra os asssentos disponiveis 
                                if(oni[rota[numrota].getOnibus()].verificaLotado()==1){ //verifica se o onibus está lotado
                                    System.out.println("Ônibus lotado.");
                                    comprou = 1; //Se o onibus estive lotado ele atribui 1 a compra
                                }
                            }
                            else if(comprou==0){ //Caso estiver lotado não entra aqui
                                comprou = 1;    //Não entra na compra pois não existe onibus na rota
                                System.out.println("Não existe ônibus cadastrado nessa rota.");
                                }

                            //Efetuando a compra
                            while(comprou == 0){ //Caso estiver lotado ou não possuir onibus não entra na compra
                                System.out.print("Digite o assento que deseja comprar ou sair(0): ");
                                int assento = input.nextInt(); //numero do assento a ser comprado
                                if(assento !=0){ 
                                    if(oni[rota[numrota].getOnibus()].compraAssentos(assento)==1){ //Caso conseguir reservar assento
                                        comprou = 1;
                                        System.out.println("Compra efetuada com sucesso.");
                                    }
                                    else{  //Se não conseguir reservar, comprou = 0;
                                        System.out.println("Assento já reservado, insira novamente.");
                                        comprou = 0;
                                    }
                                }
                                else comprou = 1; //Caso digite 0, para voltar ao menu
                            System.out.println();
                            }                       
                        }
                    }
                }
            }
        }
    }   
}
