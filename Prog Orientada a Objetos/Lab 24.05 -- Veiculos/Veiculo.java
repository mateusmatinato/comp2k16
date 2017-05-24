
package labpoo2405;

public abstract class Veiculo extends Motor {
    protected double peso;
    protected double velMax;
    protected double preco;
    
    public Veiculo(int qtdCilindros, int potencia, String tipoCombustivel, double peso, double velMax, double preco){
        super(qtdCilindros,potencia,tipoCombustivel);
        this.peso = peso;
        this.velMax = velMax;
        this.preco = preco;
    }
}
