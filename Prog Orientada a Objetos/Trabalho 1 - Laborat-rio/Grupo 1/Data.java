
package viacaoasteroide;

public class Data {
    private int dia,mes,ano;
    
    public int validaDia(int dia){
        if(dia<1 || dia>31){
            return 0;
        }    
        else{
            this.dia = dia;
            return 1;
        }
    }
    public int validaMes(int mes){
        if(mes<1 || mes>12){
            return 0;
        }
        else{
            this.mes = mes;
            return 1;
        }        
    }
    public int validaAno(int ano){
        if(ano<1900 || ano>2017){
            return 0;
        }
        else{
            this.ano = ano;
            return 1;
        }
    }
    
    public void setDia(int dia){
        this.dia = dia;
    }
    
    public void setMes(int mes){
        this.mes = mes;
        }
    
    public void setAno(int ano){
        this.ano = ano;
    }
    
    public int getDia(){
        return dia;
    }
    
    public int getMes(){
        return mes;
    }
    
    public int getAno(){
        return ano;
    }
    
}
