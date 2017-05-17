package aula.labpoo;
import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
    Scanner input = new Scanner(System.in);
    private int isbn;
    private String titulo;
    private ArrayList <String> autores = new ArrayList<>();
    private String editora;
    private int anoPublicacao;
    private int numPags;
    
    public Livro(int isbn, String titulo, String editora, int anoPublicacao, int numPags){
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numPags = numPags;
    }
    
    public void setAutores(int num) {
        for(int i = 1; i<=num ; i++){
            System.out.print("Digite o autor "+i+": ");
            String autor = input.nextLine();
            autores.add(autor);
        }
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getNumPags() {
        return numPags;
    }
    
    public void MostrarLivro(){
        System.out.println();
        System.out.println("ISBN: "+this.isbn);
        System.out.println("Nome do Livro: "+this.titulo);
        System.out.println("Editora: "+this.editora);
        System.out.println("Ano de Publicação: "+this.anoPublicacao+" Número de Páginas: "+this.numPags);
        int i;
        System.out.println("Tamanho do autores: "+autores.size());
        for(i = 0; i<autores.size(); i++){
            String autor = autores.get(i);
            System.out.println("Autor "+(i+1)+": "+autor);
        }
    }
    
    public void MenuAlterar(){
        int opcao = 1;
        while(opcao != 0){
            System.out.println();
            System.out.println("->>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<-");
            System.out.println("->          ALTERAÇÕES           <-");
            System.out.println("->>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<-");
            System.out.println("-> Alterar Nome              (1) <-");
            System.out.println("-> Alterar Editora           (2) <-");
            System.out.println("-> Alterar Ano de Publicação (3) <-");
            System.out.println("-> Alterar Num de Páginas    (4) <-");
            System.out.println("-> Sair                      (0) <-");
            System.out.println("->>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<-");
            System.out.print("Digite a opção -> ");
            opcao = input.nextInt();
            input.nextLine();
            switch(opcao){
                case 1:{
                    
                    System.out.print("Digite o novo título: ");
                    this.titulo = input.nextLine();
                    break;
                }
                case 2:{
                    System.out.print("Digite a nova editora: ");
                    this.editora = input.nextLine();
                    break;
                }
                case 3:{
                    System.out.print("Digite o novo ano de publicação: ");
                    this.anoPublicacao = input.nextInt();
                    break;
                }   
                case 4:{
                    System.out.print("Digite o novo número de páginas: ");
                    this.numPags = input.nextInt();
                    break;
                }
            }
        }
    } 
}
