package viacaoasteroide;
import java.util.Scanner;

public class Passageiro {
    private String rg;
    private String nome,prof;
    private String rua;
    private String bairro;
    private String cidade;
    private int num;
    Data data=new Data();
    Scanner input = new Scanner(System.in);
    
    public Passageiro(String nome, String rg, String prof, String rua, String bairro, String cidade, int num){
        this.nome = nome;
        this.rg = rg;
        this.prof = prof;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.num = num;    
    }
    
    public void setNome(){
        input.nextLine();
        System.out.print("Qual o nome: ");
        this.nome = input.nextLine();
    }
    
    public void setRG(){
        input.nextLine();
        System.out.print("Qual o RG: ");
        this.rg = input.nextLine();
    }
    
    public void setProf(){
        input.nextLine();
        System.out.print("Qual a profissão: ");
        this.prof = input.nextLine();
    }
    
    public void setEnd(){
        input.nextLine();
        System.out.print("Qual a rua: ");
        this.rua = input.nextLine();
        System.out.print("Qual o bairro: ");
        this.bairro = input.nextLine();
        System.out.print("Qual a cidade: ");
        this.cidade = input.nextLine();
        System.out.print("Qual o número da casa: ");
        this.num = input.nextInt();
        input.nextLine();
    }
    
    public void dNasc(){
        Scanner input = new Scanner(System.in);
        int dia = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o dia de nascimento: ");
        dia = input.nextInt();
        if(data.validaDia(dia) == 1) v = 1;
            else if (data.validaDia(dia) == 0){
                v = 0;
                System.out.println("Dia inválido, insira novamente.");
            }
        }
        data.setDia(dia);        
    }
    
    public void mNasc(){
        Scanner input = new Scanner(System.in);
        int mes = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o mês de nascimento: ");
        mes = input.nextInt();
        if(data.validaMes(mes) == 1) v = 1;
            else if (data.validaMes(mes) == 0){
                v = 0;
                System.out.println("Mês inválido, insira novamente.");
            }
        }
        data.setMes(mes);        
    }
    
    public void aNasc(){
        Scanner input = new Scanner(System.in);
        int ano = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o ano de nascimento: ");
        ano = input.nextInt();
        if(data.validaAno(ano) == 1) v = 1;
            else if (data.validaAno(ano) == 0){
                v = 0;
                System.out.println("Ano inválido, insira novamente.");
            }
        }
        data.setAno(ano);        
    }
    
    public void mostraPassageiro(){
        System.out.println("Nome: "+this.nome);
        System.out.println("RG: "+this.rg);
        System.out.println("Profissão: "+this.prof);
        System.out.println("Rua: "+this.rua+" Bairro:"+this.bairro+" Número: "+this.num);
        System.out.println("Cidade: "+this.cidade);
        System.out.println("Data de Nascimento: "+this.data.getDia()+"/"+this.data.getMes()+"/"+this.data.getAno());
    }
    
    public int verificaPassageiro(){
        if(((this.nome).equals("") || (this.nome).equals(" ") || (this.nome).equals("\n")) 
                && ((this.rg).equals("") || (this.rg).equals(" ") || (this.rg).equals("\n"))){
            return 0;
        }
        else return 1;
    }
    
    
}
