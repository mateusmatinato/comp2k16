package labpoo2405;

public class CarroPasseio extends Veiculo {
    protected cor c1;
    protected marca m1;
    
    public CarroPasseio(int qtdCilindros, int potencia, String tipoCombustivel, double peso, double velMax, double preco, cor c1, marca m1){
        super(qtdCilindros, potencia, tipoCombustivel, peso, velMax, preco);
        this.c1 = c1;
        this.m1 = m1;
    }
    
    public void mostraCarro(){
        System.out.println("Marca: "+m1+"\tCor: "+c1);
        System.out.println("Potencia: "+potencia+"\tCilindros: "+qtdCilindros+"\tVel. Máx: "+velMax);
        System.out.println("Combustivel: "+tipoCombustivel+"\tRendimento: "+this.calculaRendimento(qtdCilindros, potencia, tipoCombustivel)+"km/l\tPeso: "+peso);
        System.out.println("Preço: "+preco);
    }
}
