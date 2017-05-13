public class Passageiro {
    private int codPassageiro, rg, numEnd;
    private String nome, rua, bairro, cidade, profissao, cep;
    private boolean existe;

    public void setCodPassageiro(int codPassageiro) {
        this.codPassageiro = codPassageiro;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setNumEnd(int numEnd) {
        this.numEnd = numEnd;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getCodPassageiro() {
        return codPassageiro;
    }

    public String getNome() {
        return nome;
    }
    
    Data dataNasc = new Data ();

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
}
