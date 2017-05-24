package labpoo2405;

public abstract class Motor {
    protected int qtdCilindros;
    protected int potencia;
    protected String tipoCombustivel;
    
    public Motor(int qtdCilindros, int potencia, String tipoCombustivel){
        this.qtdCilindros = qtdCilindros;
        this.potencia = potencia;
        this.tipoCombustivel = tipoCombustivel;
    }
    
    public double calculaRendimento(int qtdCilindros, int potencia, String tipoCombustivel){
        double rendimento=0;
        switch (tipoCombustivel) {
            case "Etanol":
                rendimento = (qtdCilindros * potencia)/45;
                break;
            case "Gasolina":
                rendimento = (qtdCilindros * potencia)/34;
                break;
            case "Flex":
                rendimento = (qtdCilindros * potencia)/39;
                break;
            case "Diesel":
                rendimento = (qtdCilindros * potencia)/30;
                break;
        }
        return rendimento;
    }
}
