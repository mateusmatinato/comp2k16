public class Hora{
	private int hora, min;

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
    
    public int valida(int hora, int min){
        if (hora < 0 || hora > 23){
            System.out.println("Hora inválida");
            return 0;
        } else if (min < 0 || min > 59){
            System.out.println("Minuto inválido");
            return 0;
        } else{
            return 1;
        }
    }
    
}
