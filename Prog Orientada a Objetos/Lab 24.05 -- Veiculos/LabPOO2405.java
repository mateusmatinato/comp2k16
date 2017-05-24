package labpoo2405;
import java.util.*;

public class LabPOO2405{
    
    public static void main(String[] args) {        
    Caminhao c1 = new Caminhao(4,350,"Flex",4500,120,98000,1500,3.5,"Scania",3.8,tipodecarga.INFLAMÁVEL);
    CarroPasseio cp1 = new CarroPasseio(4,125,"Diesel",850,210,52500,cor.BRANCO,marca.CHEVROLET);
    c1.mostraCaminhao();
    System.out.println();
    cp1.mostraCarro();
    }
    
}
