
package viacaoasteroide;
import java.util.Scanner;

public class Onibus {
    private String modelo,marca;
    private int anoFab;
    private int assentos[][]= new int [9][4];
    private int km;
    Scanner input = new Scanner(System.in);
    
    public Onibus(String modelo, String marca, int anoFab, int km){
        this.modelo = modelo;
        this.marca = marca;
        this.anoFab = anoFab;
        this.km = km;        
    }
    
    public void setModelo(){
        input.nextLine();
        System.out.print("Digite o modelo: ");
        this.modelo = input.nextLine();
    }
    
    public void setMarca(){
        input.nextLine();
        System.out.print("Digite a marca: ");
        this.marca = input.nextLine();
    }
    
    public void setAnoFab(){
        System.out.print("Digite o ano de fabricação: ");
        this.anoFab = input.nextInt();
    }
    
    public void setKm(){
        System.out.print("Digite a quilometragem: ");
        this.km = input.nextInt();
    }
    
    public void criaOnibus(){
        for(int i=0;i<assentos.length;i++){
            for(int j=0;j<assentos[0].length;j++){
                assentos[i][j] = 0;
            }
        }
    }
    
    public void mostraOnibus(){
        System.out.println("Modelo do Ônibus: "+this.modelo+". Marca: "+this.marca);
        System.out.println("Ano de Fabricação: "+this.anoFab+ " Quilometragem: "+this.km);
    }
    
    public void mostraAssentos(){
        int k = 1;
        char comprado;
        for(int i=0; i < assentos.length; i++){
            for(int j=0; j< assentos[0].length ; j++){
                if(assentos[i][j]==0) comprado = ' ';
                else comprado = 'x';
                System.out.print(k+"("+comprado+")\t ");
                k++;
            }
        System.out.println();
        }
        System.out.println();
    }
    
    public int compraAssentos(int assento){
        int k = 1;
        int v = 0;
        for(int i=0;i<assentos.length;i++){
            for(int j = 0; j<assentos[0].length && v == 0;j++){
                    if(assento == k && assentos[i][j]==0){
                        assentos[i][j] = 1;
                        v = 1;
                        return 1;
                    }
                    else k++;               
            }
        }
                    return 0;
    }
}
