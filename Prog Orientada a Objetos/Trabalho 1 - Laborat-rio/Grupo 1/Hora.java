
package viacaoasteroide;

public class Hora {
    private int hora,min;


    public int validaHora(int hora){
        if(hora > 23 || hora<0) return 0;
        else{
            this.hora = hora;
            return 1;
        }
    }
    
    public int validaMinuto(int min){
        if(min < 0 || min>59){
            return 0;
        }
        else{
            this.min = min;
            return 1;
        }
    }
    
    public void setHora(int hora){
        this.hora = hora;
    }
    
    public void setMin(int min){
        this.min = min;
    }
    
    public int getHora(){
        return this.hora; 
    }
    
    public int getMin(){
        return this.min;
    }
}
