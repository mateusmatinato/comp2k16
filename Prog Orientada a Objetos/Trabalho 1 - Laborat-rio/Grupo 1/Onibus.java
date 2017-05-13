
package viacaoasteroide;
import java.util.Scanner;

public class Onibus {
    private String modelo,marca;
    private int anoFab;
    private int assentos[][]= new int [9][4]; //matriz com assentos do onibus
    private int km;
    Scanner input = new Scanner(System.in);
    private boolean rota; //verifica se já esta em alguma rota
    
    public Onibus(String modelo, String marca, int anoFab, int km){ //construtor
        this.modelo = modelo;
        this.marca = marca;
        this.anoFab = anoFab;
        this.km = km;        
    }
    
    public void setRota(boolean rota){ //atribui se está ou não em alguma rota
        this.rota = rota;
    }
    
    public boolean getRota(){
        return this.rota;
    }
    
    public void setModelo(){ //set do modelo do onibus
        input.nextLine();
        System.out.print("Digite o modelo: ");
        this.modelo = input.nextLine();
    }
    
    public void setMarca(){ //set da marca do onibus
        input.nextLine();
        System.out.print("Digite a marca: ");
        this.marca = input.nextLine();
    }
    
    public void setAnoFab(){ //set do ano de fabricação
        System.out.print("Digite o ano de fabricação: ");
        this.anoFab = input.nextInt();
    }
    
    public void setKm(){ //set da quilometragem
        System.out.print("Digite a quilometragem: ");
        this.km = input.nextInt();
    }
    
    public void criaOnibus(){ //cria assentos do onibus e atribui 0
        for(int i=0;i<assentos.length;i++){
            for(int j=0;j<assentos[0].length;j++){
                assentos[i][j] = 0;
            }
        }
    }
    
    public void mostraOnibus(){ //mostra dados do onibus
        System.out.println("Modelo do Ônibus: "+this.modelo+". Marca: "+this.marca);
        System.out.println("Ano de Fabricação: "+this.anoFab+ " Quilometragem: "+this.km);
    }
    
    public void mostraAssentos(){ //mostra os assentos do onibus
        int k = 1; //contador de assentos
        char comprado; //char para mostrar se o assento está ocupado
        for(int i=0; i < assentos.length; i++){ 
            for(int j=0; j< assentos[0].length ; j++){
                if(assentos[i][j]==0) comprado = ' '; //se o assento for 0, o char é um espaço
                else comprado = 'x'; //caso não for 0, o assento foi comprado, o char é um X
                System.out.print(k+"("+comprado+")\t "); //mostra o numero do assento e o char
                k++; //contador para mostrar o assento
            }
        System.out.println();
        }
        System.out.println();
    }
    
    public int compraAssentos(int assento){ //compra o assento
        int k = 1; //contador de assentos
        int v = 0; //controle
        for(int i=0;i<assentos.length;i++){
            for(int j = 0; j<assentos[0].length && v == 0;j++){
                    if(assento == k && assentos[i][j]==0){ //verifica se o numero inserido é igual o contador e se está desocupado
                        assentos[i][j] = 1; //se sim, atribui 1
                        v = 1; //sai do loop
                        return 1;
                    }
                    else k++;               
            }
        }
        return 0;
    }
    
    public int verificaLotado(){ //verifica se o onibus está lotado
        int cont=0,i,j;
            for(i=0;i<assentos.length;i++){
                for(j=0;j<assentos[0].length;j++){
                    if(assentos[i][j]==1) cont++; // se assentos estiver comprado, soma no contador
                }
            }
        if(cont==36) return 1; //se o contador for 36(numero maximo) está lotado
        else return 0;
    }
}
