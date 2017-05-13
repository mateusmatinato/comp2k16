
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
    
    public Rotas (String origem, String destino, String p1, String p2, String p3, double valor){
        this.origem = origem;
        this.destino = destino;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.valor = valor;
    }
    
    public void setNummoto(int nummoto){
        this.nummoto = nummoto;
    }
    
    public void setNumoni(int numoni){
        this.numoni = numoni;
    }
    
    public void setOrigem(){
        input.nextLine();
        System.out.print("Digite a origem: ");
        this.origem = input.nextLine();
    }
    
    public void setDestino(){
        input.nextLine();
        System.out.print("Digite o destino: ");
        this.destino = input.nextLine();
    }
    
    public void setP1(){
        input.nextLine();
        System.out.print("Digite a parada 1: ");
        this.p1 = input.nextLine();
    }
    
    public void setP2(){
        input.nextLine();
        System.out.print("Digite a parada 2: ");
        this.p2 = input.nextLine();
    }
    
    public void setP3(){
        input.nextLine();
        System.out.print("Digite a parada 3: ");
        this.origem = input.nextLine();
    }
    
    public void setValor(){
        System.out.print("Digite o valor da passagem: ");
        this.valor = input.nextDouble();
    }

    public int getMotorista(){
        return this.nummoto;
    }
    
    public int getOnibus(){
        return this.numoni;
    }
    
    public void horaSaida(){
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
    
    public void horaChegada(){
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
    
    public void mostraRota(){
        System.out.println("Local de Origem: "+this.origem);
        System.out.println("Local de Destino: "+this.destino);
        if((this.p1).equals("") || (this.p1).equals(" ") || (this.p1).equals("\n"))
            System.out.println("Não possui paradas.");
        else System.out.println("Parada 1: "+this.p1);       
        if((this.p2).equals("") || (this.p2).equals(" ") || (this.p2).equals("\n"));
        else System.out.println("Parada 2: "+this.p2);
        if((this.p3).equals("") || (this.p3).equals(" ") || (this.p3).equals("\n"));
        else System.out.println("Parada 3: "+this.p3);
        System.out.println("Valor da Passagem: "+this.valor);
        System.out.println("Horário de Saída: "+this.hSaida.getHora()+":"+this.hSaida.getMin());
        System.out.println("Horário de Chegada: "+this.hChegada.getHora()+":"+this.hChegada.getMin());
    }
    
    public void rotaCliente(){
        System.out.println("Local de Origem: "+this.origem+"\nLocal de Destino: "+this.destino);
    }
    
    public int cadastraMotorista(Motorista mot[]){
        //Cadastrar Motorista na Rota
        int nummoto, motovale = 0, sair=0;
        while(motovale == 0 && sair == 0){
            System.out.print("Digite o número do Motorista dessa rota ou sair(-1): ");
            int opcao = input.nextInt();
            if(opcao==-1) sair = -1;
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
            if(opcao==-1){
                sair = -1;
            }
            else {
                numoni = opcao;
                if(oni[numoni]!=null){
                    onivale = 1;
                    return numoni;
                }
                else{
                    System.out.println("Número de onibus inválido. ");
                    onivale = 0;
                }
            }
        }
        return -1;
    }
}
