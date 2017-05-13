
package viacaoasteroide;

import java.util.Scanner;

public class Rotas {
    private String origem,destino,p1,p2,p3;
    private double valor;
    Hora hSaida=new Hora();
    Hora hChegada=new Hora();
    Scanner input = new Scanner(System.in);
    private int nummoto;
    private int numoni;
    
    public Rotas (String origem, String destino, String p1, String p2, String p3, double valor){ //Construtor
        this.origem = origem;
        this.destino = destino;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.valor = valor;
    }
    
    public void setNummoto(int nummoto){ //Set Numero do Motorista da Rota
        this.nummoto = nummoto;
    }
    
    public void setNumoni(int numoni){ //Set Numero do Onibus da Rota
        this.numoni = numoni;
    }
    
    public void setOrigem(){ //Set Origem da Rota
        input.nextLine();
        System.out.print("Digite a origem: ");
        this.origem = input.nextLine();
    }
    
    public void setDestino(){ //Set Destino da Rota
        input.nextLine();
        System.out.print("Digite o destino: ");
        this.destino = input.nextLine();
    }
    
    public void setP1(){ //Set Primeira Parada
        input.nextLine(); 
        System.out.print("Digite a parada 1: ");
        this.p1 = input.nextLine();
    }
    
    public void setP2(){ //Set Segunda Parada
        input.nextLine();
        System.out.print("Digite a parada 2: ");
        this.p2 = input.nextLine();
    }
    
    public void setP3(){ //Set Terceira Parad
        input.nextLine();
        System.out.print("Digite a parada 3: ");
        this.origem = input.nextLine();
    }
    
    public void setValor(){ //Set Valor da Passagem
        System.out.print("Digite o valor da passagem: ");
        this.valor = input.nextDouble();
    }

    public int getMotorista(){ //Get numero do motorista
        return this.nummoto;
    }
    
    public int getOnibus(){ //Get numero do onibus
        return this.numoni;
    }
    
    public void horaSaida(){ //Verifica e atribui hora saida
        Scanner input = new Scanner(System.in);
        int hora = 0;
        int min = 0;
        int v = 0;
        while(v==0){
            System.out.print("Digite a hora de saída no formato (HH MM): ");
            hora = input.nextInt();
            min = input.nextInt();
            if((hSaida.validaHora(hora) == 1) && (hSaida.validaMinuto(min)==1)) v = 1;
            else{
                System.out.println("Hora inválida, digite novamente.");
            }
        }
        hSaida.setHora(hora);
        hSaida.setMin(min);
    }
    
    public void horaChegada(){ //Verifica e atribui hora chegada
        Scanner input = new Scanner(System.in);
        int hora = 0;
        int min = 0;
        int v = 0;
        while(v==0){
            System.out.print("Digite a hora de chegada no formato (HH MM): ");
            hora = input.nextInt();
            min = input.nextInt();
            if((hChegada.validaHora(hora) == 1) && (hChegada.validaMinuto(min)==1)) v = 1;
            else{
                System.out.println("Hora inválida, digite novamente.");
            }
        }
        hChegada.setHora(hora);
        hChegada.setMin(min);
    }
    
    public void mostraRota(){ //Mostra a rota 
        System.out.println("Local de Origem: "+this.origem);
        System.out.println("Local de Destino: "+this.destino);
        if((this.p1).equals("") || (this.p1).equals(" ") || (this.p1).equals("\n"))
            System.out.println("Não possui paradas.");
        else System.out.println("Parada 1: "+this.p1);       
        if((this.p2).equals("") || (this.p2).equals(" ") || (this.p2).equals("\n"));
        else System.out.println("Parada 2: "+this.p2);
        if((this.p3).equals("") || (this.p3).equals(" ") || (this.p3).equals("\n"));
        else System.out.println("Parada 3: "+this.p3);
        System.out.println("Valor da Passagem: R$"+this.valor);
        System.out.println("Horário de Saída: "+this.hSaida.getHora()+":"+this.hSaida.getMin());
        System.out.println("Horário de Chegada: "+this.hChegada.getHora()+":"+this.hChegada.getMin());
    }
    
    public void rotaCliente(){ //Mostra a rota reduzida para escolha do cliente
        System.out.println("Local de Origem: "+this.origem+"\nLocal de Destino: "+this.destino+"\nHora de Saída: "+this.hSaida.getHora()+":"+this.hSaida.getMin());
    }
    
    public int cadastraMotorista(Motorista mot[]){ 
        //Cadastrar Motorista na Rota
        int nummoto, motovale = 0, sair=0;
        while(motovale == 0 && sair == 0){
            System.out.print("Digite o número do Motorista dessa rota ou sair(-1): ");
            int opcao = input.nextInt();
            if(opcao==-1) sair = -1; //sai do cadastro de motorista
            else {
                nummoto = opcao; 
                if(mot[nummoto]!=null){ 
                    motovale = 1;
                    return nummoto;
                }
                else{
                    System.out.println("Número de motorista inválido. ");
                    motovale = 0;
                }
            }
        }
        return -1;
    }
    
    public int cadastraOnibus(Onibus oni[]){
        //Cadastrar Onibus na Rota
        int numoni, onivale = 0, sair=0;
        while(onivale == 0 && sair == 0){
            System.out.print("Digite o número do Onibus dessa rota ou sair(-1): ");
            int opcao = input.nextInt();
            if(opcao==-1){ //sai do cadastro
                sair = -1;
            }
            else {
                numoni = opcao;
                if(oni[numoni]!=null && oni[numoni].getRota()!=true){ //verifica se o onibus existe e se ja esta em alguma rota
                    onivale = 1;
                    return numoni;
                }
                else{
                    System.out.println("Número de onibus inválido ou já utilizado. ");
                    onivale = 0;
                }
            }
        }
        return -1;
    }
}
