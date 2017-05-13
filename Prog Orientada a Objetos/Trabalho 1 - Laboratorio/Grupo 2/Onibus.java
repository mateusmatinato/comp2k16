public class Onibus {

    private int km;
    private int assento[] = new int[42];
    private String modelo, marca;
    private boolean disp;
    //Passageiro pas;

    public void setKm(int km) {
        this.km = km;
    }

    public void setAssento(int[] assento) {
        this.assento = assento;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int[] getAssento() {
        return assento;
    }

    public boolean getDisp() {
        return disp;
    }

    public void setDisp(boolean disp) {
        this.disp = disp;
    }

    public void colocarAssentos() {
        for (int i = 0; i < 42; i++) {
            assento[i] = 1;
        }
    }

    public void assentosDisponiveis() {
        System.out.println("Assentos:");
        for (int i = 0; i < 42; i++) {
            if (assento[i] == 1) {
                System.out.print(i+" ");
            }
        }
    }
    
    public int compraAssento(int lugar){
        if(assento[lugar]==1){
            assento[lugar]=0;
            System.out.println("Compra realizada com sucesso!");
            return 1;
        } else {
            System.out.println("Assento indisponível! Por favor, realize o processo de compra novamente!");
            return 0;
        }
    }

    Data anoFab = new Data();
}
