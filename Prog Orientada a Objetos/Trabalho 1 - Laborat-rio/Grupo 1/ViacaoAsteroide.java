/*********************************************
*                                            * 
*Trabalho de Programação Orientada a Objetos *
*Integrantes: João Marcos Rosa               *
*             Jonatan Rodrigues              *
*             Mateus Matinato                *
*Data de Entrega: 10/05/2017                 *
*Tema: Viação Asteróide                      *
*                                            *
*********************************************/


package viacaoasteroide;
import java.util.*;

public class ViacaoAsteroide {

    public static void main(String[] args) {
        int opc = 1;
        int opc2 = 1;
        int p,o,m,r;
        int senha = 1234;
        String usuario = "asteroide";
        Passageiro pas[] = new Passageiro[400];
        Onibus oni[] = new Onibus[10];
        Motorista mot[] = new Motorista[20];
        Rotas rota[] = new Rotas[20];
        
        p = o = m = r = 0;
        String usuarioinserido;
        int senhainserida, admin = 1;
        Scanner input = new Scanner(System.in);
        while(admin != 0){
            System.out.println();
            System.out.println("|==================================|");
            System.out.println("|    Bem vindo a Viação Asteróide  |");
            System.out.println("|----------------------------------|");
            System.out.println("|    Menu Admin      (1)           |");
            System.out.println("|    Menu Cliente    (2)           |");
            System.out.println("|    Sair do Sistema (0)           |");
            System.out.println("|==================================|");
            System.out.print("Digite a opção -> ");
            admin = input.nextInt();     
            input.nextLine();
            if(admin == 1){
                System.out.println("Digite o usuário: ");
                usuarioinserido = input.next();
                if(usuarioinserido.equals(usuario)){
                    System.out.println("Digite a senha: ");
                    senhainserida = input.nextInt();
                    if(senhainserida== senha){
                        opc = 1;
                        while (opc != 0){ //Opc = Opção para entrar em algum menu
                        System.out.println();
                        System.out.println("|===============================| ");
                        System.out.println("|      MENU ADMINISTRADOR       |");
                        System.out.println("|-------------------------------|");
                        System.out.println("|    Menu de Cadastros      (1) |");
                        System.out.println("|    Menu de Alterações     (2) |");
                        System.out.println("|    Menu de Exclusões      (3) |");
                        System.out.println("|    Voltar ao menu inicial (0) |");
                        System.out.println("|===============================|");
                        System.out.print("Digite a opção-> ");
                        opc = input.nextInt();
                        opc2 = 1;
                       
                            if(opc==1){ // MENU DE CADASTROS
                                while(opc2 != 0){ //OPC2 = Opção para entrar em algum menu de cadastro
                                    System.out.println();
                                    System.out.println("|===============================|");
                                    System.out.println("|        MENU DE CADASTROS      |");
                                    System.out.println("|-------------------------------|");
                                    System.out.println("|  Cadastro de Passageiros (1)  |");
                                    System.out.println("|  Cadastro de Ônibus      (2)  |");
                                    System.out.println("|  Cadastro de Motorista   (3)  |");
                                    System.out.println("|  Cadastro de Rotas       (4)  |");
                                    System.out.println("|  Menu Administrador      (0)  |");
                                    System.out.println("|===============================|");
                                    System.out.print("Digite a opção-> ");
                                    opc2 = input.nextInt();
                                    switch(opc2){ // OPÇÃO DO MENU DE CADASTROS
                                        case 1:{ //CADASTRO DE PASSAGEIROS
                                            String nome, rg, prof, rua, bairro, cidade;   
                                            int num;
                                            System.out.print("Digite o código do usuário (0 a 399):");
                                            p = input.nextInt();
                                            if(pas[p]==null){
                                                input.nextLine();                                       
                                                System.out.printf("Digite o nome do passageiro: ");
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
                                                
                                                pas[p] = new Passageiro(nome,rg,prof,rua,bairro,cidade,num);
                                                pas[p].dNasc();
                                                pas[p].mNasc();
                                                pas[p].aNasc();
                                                System.out.println("Passageiro cadastrado.");
                                            }
                                            else System.out.println("Já existe um usuário com esse código.");                                       
                                        } break;  
                                        case 2:{ //CADASTRO DE ONIBUS
                                            String modelo, marca;
                                            int anoFab, km;
                                            System.out.print("Digite o código do Ônibus (0 a 9): ");
                                            o = input.nextInt();
                                            if(oni[o]==null){
                                                input.nextLine();
                                                System.out.print("Digite o modelo do ônibus: ");
                                                modelo = input.nextLine();
                                                System.out.print("Digite a marca do ônibus: ");
                                                marca = input.nextLine();
                                                System.out.print("Digite o ano de fabricação: ");
                                                anoFab = input.nextInt();                                        
                                                System.out.print("Digite a quilometragem: ");
                                                km = input.nextInt();
                                                oni[o] = new Onibus(modelo,marca,anoFab,km);
                                                oni[o].criaOnibus();
                                                System.out.println("Ônibus cadastrado.");
                                            }
                                            else System.out.println("Já existe um ônibus com esse código.");
                                        } break;
                                        
                                        case 3:{ //CADASTRO DE MOTORISTAS
                                            String cnh, nome;
                                            System.out.print("Digite o código do motorista (0 a 19): ");
                                            m = input.nextInt();
                                            if(mot[m]==null ){
                                                input.nextLine();
                                                System.out.print("Digite o nome do Motorista: ");
                                                nome = input.nextLine();
                                                System.out.print("CNH: ");
                                                cnh = input.nextLine();
                                                mot[m] = new Motorista (cnh,nome);
                                                mot[m].dAdm();
                                                mot[m].mAdm();
                                                mot[m].aAdm();
                                                mot[m].calculaTempo();
                                            }
                                            else System.out.println("Já existe um motorista com esse código.");
                                        } break;
                                        
                                        case 4:{ //CADASTRO DE ROTAS
                                            String origem, destino, p1,p2,p3;
                                            double valor;
                                            int numparada;
                                            System.out.print("Digite o código da rota (0 a 19): ");
                                            r = input.nextInt();
                                            if(rota[r]==null){
                                                input.nextLine();
                                                System.out.print("Digite a origem: ");
                                                origem = input.nextLine();                                        
                                                System.out.print("Digite o destino: ");
                                                destino = input.nextLine();
                                                System.out.print("Digite o valor da passagem: ");
                                                valor = input.nextDouble();
                                                System.out.print("Quantas paradas haverá(Min 0/Max 3): ");
                                                numparada = input.nextInt();
                                                input.nextLine();
                                                if(numparada == 1){
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,"","",valor);                                            
                                                }
                                                else if(numparada == 2){
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    System.out.print("Parada 2: ");
                                                    p2 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,p2,"",valor);
                                                }
                                                else if(numparada == 3){
                                                    System.out.print("Parada 1: ");
                                                    p1 = input.nextLine();
                                                    System.out.print("Parada 2: ");
                                                    p2 = input.nextLine();
                                                    System.out.print("Parada 3: ");
                                                    p3 = input.nextLine();
                                                    rota[r] = new Rotas(origem,destino,p1,p2,p3,valor);
                                                }
                                                else{
                                                rota[r] = new Rotas(origem,destino,"","","",valor);
                                                }
                                                rota[r].horaSaida();
                                                rota[r].horaChegada();
                                                
                                                //Cadastrar Onibus na Rota
                                                int numoni = rota[r].cadastraOnibus(oni);
                                                if(numoni!=-1){
                                                   System.out.println("Onibus cadastrado na rota.");
                                                   rota[r].setNummoto(numoni);
                                               }
                                                else{
                                                    System.out.println("Onibus não cadastrado. ");
                                                    rota[r].setNumoni(-1);
                                                }
                                                
                                                //Cadastrar Motorista na Rota
                                                int nummoto = rota[r].cadastraMotorista(mot);
                                                if(nummoto!=-1){
                                                   System.out.println("Motorista cadastrado na rota.");
                                                   rota[r].setNumoni(numoni);
                                               }
                                                else{
                                                    System.out.println("Motorista não cadastrado. ");
                                                    rota[r].setNummoto(-1);
                                                }
                                            } else System.out.println("Já existe uma rota com esse código.");
                                        }
                                    }   
                                } //SAI DO MENU DE CADASTRO
                            }
                            else if(opc == 2){ // MENU DE ALTERAÇÕES
                                while(opc2 != 0){
                                    System.out.println();
                                    System.out.println("|===============================|");
                                    System.out.println("|       MENU DE ALTERAÇÕES      |");
                                    System.out.println("|-------------------------------|");
                                    System.out.println("|   Alterar Passageiro (1)      |");
                                    System.out.println("|   Alterar Ônibus     (2)      |");
                                    System.out.println("|   Alterar Motoristas (3)      |");
                                    System.out.println("|   Alterar Rotas      (4)      |");
                                    System.out.println("|   Menu Administrador (0)      |");
                                    System.out.println("|===============================|");
                                    System.out.print("Digite a opção->");
                                    opc2 = input.nextInt();
                                    switch(opc2){
                                        case 1:{ // ALTERAR PASSAGEIRO
                                            int nump;
                                            System.out.print("Digite o número do passageiro.");
                                            nump = input.nextInt();
                                            System.out.println();
                                            if(pas[nump]!=null){
                                                System.out.println("Passageiro Número "+nump+":");
                                                pas[nump].mostraPassageiro();
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar: ");
                                                System.out.println("Nome (1)\nRG (2)\nProfissão (3)\nEndereço (4)\nData de Nascimento (5)\nVoltar (0)");
                                                System.out.print("Digite a opção: ");
                                                int dado = input.nextInt();
                                                switch(dado){
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
                                            numo = input.nextInt();
                                            if(oni[numo]!=null){
                                                System.out.println();
                                                System.out.println("Ônibus número "+numo+":");
                                                oni[numo].mostraOnibus();
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar:");
                                                System.out.println("Modelo (1)\nMarca (2)\nAno de Fabricação (3)\nQuilometragem (4)\nZerar Assentos(5)\nVoltar (0)");
                                                System.out.print("Digite a opção: ");
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
                                            if(mot[numm]!=null){
                                                System.out.println();
                                                System.out.println("Motorista número "+numm+":");
                                                mot[numm].mostraMotorista();
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar:");
                                                System.out.println("Nome (1)\nCNH (2)\nData de Admissão(3)\nVoltar (0)");
                                                System.out.print("Digite a opção: ");
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
                                            if(rota[numr]!=null){
                                                System.out.println();
                                                System.out.println("Rota número "+numr+":");
                                                rota[numr].mostraRota();
                                                if(rota[numr].getOnibus() != -1){
                                                    System.out.println("Ônibus da Rota: ");
                                                    oni[rota[numr].getOnibus()].mostraOnibus();
                                                }
                                                if(rota[numr].getMotorista() != -1){
                                                    System.out.println("Motorista da Rota: ");
                                                    mot[rota[numr].getMotorista()].mostraMotorista();
                                                }
                                                System.out.println();
                                                System.out.println("Qual dado deseja alterar: ");
                                                System.out.println("Origem (1)\nDestino (2)\nParada 1(3)\nParada 2(4)\nParada 3(5)");
                                                System.out.println("Valor da Passagem(6)\nHorário de Saída(7)\nHorário de Chegada(8)\nMotorista da Rota(9)\nOnibus da Rota(10)\nVoltar (0)");
                                                System.out.print("Digite a opção: ");
                                                int dado = input.nextInt();
                                                switch(dado){
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
                                                    int novomoto = rota[numr].cadastraMotorista(mot);
                                                    if(novomoto != -1) rota[numr].setNummoto(novomoto);
                                                    else System.out.println("Motorista não alterado.");
                                                }break;
                                                case 10:{
                                                    int novooni = rota[numr].cadastraOnibus(oni);
                                                    if(novooni != -1) rota[numr].setNumoni(novooni);
                                                    else System.out.println("Ônibus não alterado.");
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
                                System.out.print("Digite a opção: ");
                                opcao = input.nextInt();
                                    switch(opcao){
                                        case 1:{
                                            // excluir passageiros
                                            System.out.print("Digite o número do passageiro a ser excluído: ");
                                            int nump = input.nextInt();
                                            if(pas[nump]!=null){
                                                System.out.println();
                                                System.out.println("Passageiro "+nump+":");
                                                pas[nump].mostraPassageiro();
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
                                                if(input.next().charAt(0)=='S'){
                                                    pas[nump] = null;// Comando para excluir
                                                    System.out.println("Passageiro excluído.");
                                                }
                                                else ;
                                            } else System.out.println("Passageiro "+nump+" não existe.");
                                        } break;
                                        case 2:{
                                            // excluir onibus
                                            System.out.print("Digite o número do Ônibus a ser excluído: ");
                                            int numo = input.nextInt();
                                            if(oni[numo]!=null){
                                                System.out.println();
                                                System.out.println("Ônibus "+numo+":");
                                                oni[numo].mostraOnibus();
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
                                                if(input.next().charAt(0)=='S'){
                                                    oni[numo] = null; //Comando para excluir
                                                    System.out.println("Ônibus excluído.");
                                                
                                                }
                                                else ;
                                            } else System.out.println("Ônibus "+numo+" não existe.");
                                        } break;
                                        case 3:{
                                            // excluir motorista
                                            System.out.print("Digite o número do motorista a ser excluído: ");
                                            int numm = input.nextInt();
                                            if(mot[numm]!=null){
                                                System.out.println();
                                                System.out.println("Motorista "+numm+":");
                                                mot[numm].mostraMotorista();
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
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
                                            int numr = input.nextInt();
                                            if(rota[numr]!=null){
                                                System.out.println();
                                                System.out.println("Rota "+numr+":");
                                                rota[numr].mostraRota();
                                                input.nextLine();
                                                System.out.print("Deseja realmente excluir (S/N): ");
                                                if(input.next().charAt(0)=='S'){
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
                    else if (senhainserida!= senha){
                        System.out.println("Senha Inválida.");
                    }
                }
                else System.out.println("Usuário Inválido.");
            }
            else if(admin == 2){
                System.out.println();
                opc = 1;
                int i, numrota;
                while(opc != 0){
                    System.out.println();
                    System.out.println("|===============================|");
                    System.out.println("|          Menu Cliente         |");
                    System.out.println("|-------------------------------|");
                    System.out.println("|  Comprar Passagem       (1)   |");
                    System.out.println("|  Voltar ao menu inicial (0)   |");
                    System.out.println("|===============================|");
                    System.out.print("Digite a opção-> ");
                    opc = input.nextInt();
                    System.out.println();
                        if(opc==1){
                            for(i=0;i<19;i++){
                                if(rota[i]!=null){
                                    System.out.println("Rota número "+i+":");
                                    rota[i].rotaCliente();
                                System.out.println();
                                }
                            }
                            System.out.print("Digite o número da rota desejada ou voltar (-1): ");
                            numrota = input.nextInt();
                            if(numrota == -1);
                            else if(rota[numrota]!=null){
                                rota[numrota].mostraRota();
                                if(rota[numrota].getOnibus()!= -1){
                                    System.out.println("Ônibus da Rota: ");
                                oni[rota[numrota].getOnibus()].mostraOnibus();
                                }
                                else System.out.println("Não existe ônibus cadastrado nessa rota.");
                                if(rota[numrota].getMotorista() != -1){
                                    System.out.println("Motorista da Rota: ");
                                    mot[rota[numrota].getMotorista()].mostraMotorista();  
                                }
                                else System.out.println("Não existe motorista cadastrado nessa rota.");
                                System.out.println();
                                System.out.println("Assentos do Ônibus: ");
                                oni[rota[numrota].getOnibus()].mostraAssentos();                        
                                //Efetuando a compra
                                int comprou = 0;
                                while(comprou == 0){
                                System.out.print("Digite o assento que deseja comprar ou sair(0): ");
                                int assento = input.nextInt();
                                if(assento !=0){
                                    if(oni[rota[numrota].getOnibus()].compraAssentos(assento)==1){
                                        comprou = 1;
                                        System.out.println("Compra efetuada com sucesso.");
                                    }
                                    else{
                                        System.out.println("Assento já reservado, insira novamente.");
                                        comprou = 0;
                                    }
                                }
                                else comprou = 1;
                            System.out.println();
                            }                       
                        }
                    }
                }
            }
        }
    }    
}

//ARRUMAR BUG QUANDO NÃO CADASTRA ONIBUS NA ROTA E MOSTRA ASSENTO