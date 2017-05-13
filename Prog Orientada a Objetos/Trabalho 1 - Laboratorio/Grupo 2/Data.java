public class Data{
	private int dia, mes, ano;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public int valida(int dia, int mes) { // Usado para validar data de nascimento, data de admissão
        if(mes < 1 || mes > 12){
            System.out.println("Mês inválido");
            return 0;
        } else if(dia < 1 || dia > 31){
            System.out.println("Dia inválido");
            return 0;
        } else
            return 1;
    }  
	
}
