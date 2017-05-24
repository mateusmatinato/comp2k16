
package labpoo2405;

public class Caminhao extends Veiculo{
    protected double cargaMax;
    protected double altura;
    protected String nomeEmpresa;
    protected double comprimento;
    protected tipodecarga t1;
    
    public Caminhao(int qtdCilindros, int potencia, String tipoCombustivel, double peso, double velMax, double preco, double cargaMax, double altura, String nomeEmpresa, double comprimento, tipodecarga t1){
        super(qtdCilindros,potencia,tipoCombustivel,peso,velMax,preco);
        this.cargaMax = cargaMax;
        this.comprimento = comprimento;
        this.altura = altura;
        this.nomeEmpresa = nomeEmpresa;
        this.t1 = t1;
    }
    
    public void mostraCaminhao(){
        System.out.println("Nome da Empresa: "+nomeEmpresa+"\tTipo de Carga: "+t1);
        System.out.println("Carga Máxima: "+cargaMax+"\tAltura: "+altura+"\tComprimento: "+comprimento);
        System.out.println("Potencia: "+potencia+"\tCilindros: "+qtdCilindros+"\tVel. Máx: "+velMax);
        System.out.println("Combustivel: "+tipoCombustivel+"\tRendimento: "+this.calculaRendimento(qtdCilindros, potencia, tipoCombustivel)+"\tPeso: "+peso);
        System.out.println("Preço: "+preco);
    }
}
