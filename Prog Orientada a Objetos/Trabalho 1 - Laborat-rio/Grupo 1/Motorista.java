
package viacaoasteroide;
import java.util.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class Motorista {
    private String cnh;
    Data data = new Data();
    private String nome;
Scanner input = new Scanner(System.in);
    
    public Motorista(String cnh, String nome){
        this.cnh = cnh;
        this.nome = nome;        
    }
    
    public void setCnh(){
        input.nextLine();
        System.out.print("Digite a CNH: ");
        this.cnh = input.nextLine();
    }
    
    public void setNome(){
        input.nextLine();
        System.out.print("Digite o nome: ");
        this.nome = input.nextLine();
    }
    
    public void dAdm(){
        int dia = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o dia de admissão: ");
        dia = input.nextInt();
        if(data.validaDia(dia) == 1) v = 1;
            else if (data.validaDia(dia) == 0){
                v = 0;
                System.out.println("Dia inválido, insira novamente.");
            }
        }
        data.setDia(dia);        
    }
    
    public void mAdm(){
        Scanner input = new Scanner(System.in);
        int mes = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o mês de admissão: ");
        mes = input.nextInt();
        if(data.validaMes(mes) == 1) v = 1;
            else if (data.validaMes(mes) == 0){
                v = 0;
                System.out.println("Mês inválido, insira novamente.");
            }
        }
        data.setMes(mes);        
    }
    
    public void aAdm(){
        Scanner input = new Scanner(System.in);
        int ano = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o ano de admissão: ");
        ano = input.nextInt();
        if(data.validaAno(ano) == 1) v = 1;
            else if (data.validaAno(ano) == 0){
                v = 0;
                System.out.println("Ano inválido, insira novamente.");
            }
        }
        data.setAno(ano);        
    }
    
    public void calculaTempo(){
        int dia, mes, ano, diaA, mesA, anoA, tempoA, tempoM;
        dia = data.getDia();
        mes = data.getMes();
        ano = data.getAno();
        
        /* Funções para obter a data atual e atribuir em variáveis
        diferentes e convertendo para inteiro
        */
        Date data = new Date(System.currentTimeMillis());  
        SimpleDateFormat diaAtual = new SimpleDateFormat("dd");  
        diaA = Integer.parseInt(diaAtual.format(data));                                    
        SimpleDateFormat mesAtual = new SimpleDateFormat("MM"); 
        mesA = Integer.parseInt(mesAtual.format(data));
        SimpleDateFormat anoAtual = new SimpleDateFormat("yyyy"); 
        anoA = Integer.parseInt(anoAtual.format(data));

        //Função para calcular o tempo de admissão
        tempoA = tempoM = 0;
         tempoA = anoA - ano;
         if(mesA < mes){ 
             tempoA--;
             tempoM = 12 - (mes-mesA);
         }
         else if (mesA > mes){
            tempoM = mesA - mes;
        }
        System.out.println("Tempo de Admissão: "+tempoA+" ano(s) e "+tempoM+" mes(es).");
         
    }
    
    public void mostraMotorista(){
        System.out.println("Nome do Motorista: "+this.nome);
        System.out.println("CNH: "+this.cnh);
        System.out.println("Data de admissão: "+this.data.getDia()+"/"+this.data.getMes()+"/"+this.data.getAno());
    }
    
    
}
