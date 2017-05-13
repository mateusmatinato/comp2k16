package viacaoasteroide;
import java.util.Scanner;

public class Passageiro {
    private String rg;
    private String nome,prof;
    private String rua;
    private String bairro;
    private String cidade;
    private int num;
    Data data=new Data(); //data de nascimento
    Scanner input = new Scanner(System.in);
    
    public Passageiro(String nome, String rg, String prof, String rua, String bairro, String cidade, int num){ //construtor
        this.nome = nome;
        this.rg = rg;
        this.prof = prof;
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.num = num;    
    }
    
    public void setNome(){ //seta o nome do passageiro
        input.nextLine();
        System.out.print("Qual o nome: ");
        this.nome = input.nextLine();
    }
    
    public void setRG(){ //seta o rg do passageiro
        input.nextLine();
        System.out.print("Qual o RG: ");
        this.rg = input.nextLine();
    }
    
    public void setProf(){ //seta a profissão do passageiro
        input.nextLine();
        System.out.print("Qual a profissão: ");
        this.prof = input.nextLine();
    }
    
    public void setEnd(){ //seta o endereço do passageiro
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
    
    public void dNasc(){ //verifica e atribui o dia de nascimento
        Scanner input = new Scanner(System.in);
        int dia = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o dia de nascimento: ");
        dia = input.nextInt();
        if(data.validaDia(dia) == 1) v = 1; //verifica se o dia é valido
            else if (data.validaDia(dia) == 0){
                v = 0;
                System.out.println("Dia inválido, insira novamente.");
            }
        }
        data.setDia(dia);        
    }
    
    public void mNasc(){ //verifica e atribui o mes de nascimento
        Scanner input = new Scanner(System.in);
        int mes = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o mês de nascimento: ");
        mes = input.nextInt();
        if(data.validaMes(mes) == 1) v = 1; //verifica se o mes é valido
            else if (data.validaMes(mes) == 0){
                v = 0;
                System.out.println("Mês inválido, insira novamente.");
            }
        }
        data.setMes(mes);        
    }
    
    public void aNasc(){ //verifica e atribui o ano de nascimento
        Scanner input = new Scanner(System.in);
        int ano = 0;
        int v = 0;
        while(v==0){
        System.out.print("Digite o ano de nascimento: "); 
        ano = input.nextInt();
        if(data.validaAno(ano) == 1) v = 1;//verifica se o ano é valido
            else if (data.validaAno(ano) == 0){
                v = 0;
                System.out.println("Ano inválido, insira novamente.");
            }
        }
        data.setAno(ano);        
    }
    
    public void mostraPassageiro(){ //mostra os dados do passageiro
        System.out.println("Nome: "+this.nome);
        System.out.println("RG: "+this.rg);
        System.out.println("Profissão: "+this.prof);
        System.out.println("Rua: "+this.rua+" Bairro:"+this.bairro+" Número: "+this.num);
        System.out.println("Cidade: "+this.cidade);
        System.out.println("Data de Nascimento: "+this.data.getDia()+"/"+this.data.getMes()+"/"+this.data.getAno());
    }  
    
}
