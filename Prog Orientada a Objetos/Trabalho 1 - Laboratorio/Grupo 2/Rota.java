public class Rota {
    private String ori, dest, city1, city2, city3;
    private double valor;
    private boolean existe;
   // private int contMot, contBus;
    Hora chegada = new Hora ();
    Hora saida = new Hora();
    Onibus codBus;
    Motorista codMot;

    public void setOri(String ori) {
        this.ori = ori;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public void setCity3(String city3) {
        this.city3 = city3;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCodBus(Onibus codBus) {
        this.codBus = codBus;
    }

    public void setCodMot(Motorista codMot) {
        this.codMot = codMot;
    }

    public String getOri() {
        return ori;
    }

    public String getDest() {
        return dest;
    }

    public String getCity1() {
        return city1;
    }

    public String getCity2() {
        return city2;
    }

    public String getCity3() {
        return city3;
    }

    public double getValor() {
        return valor;
    }

    public Hora getChegada() {
        return chegada;
    }

    public Hora getSaida() {
        return saida;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
