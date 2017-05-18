
package labpoo.ex2;
	public class Estudante extends Passageiro{
		private String instituicao;
		private int ra;
		private int anoInicio;
		private int anoTermino;
		
		public Estudante(int rg, String nome, int dNasc, int mNasc, int aNasc, String end, String instituicao, int ra, int anoInicio, int anoTermino){
			super(rg,nome,dNasc,mNasc,aNasc,end);
                        this.ra = ra;
                        this.instituicao = instituicao;
                        this.anoInicio = anoInicio;
                        this.anoTermino = anoTermino;
		}
		
                public String mostrarEstudante(){
                    String resultado;
                    resultado = super.mostrarPassageiro();
                    resultado = resultado + ("Instituição: "+instituicao+"\tRA: "+ra+"\n");
                    resultado = resultado + ("Ano de Início: "+anoInicio+"\tAno Término: "+anoTermino+"\n");
                    return resultado;
                }
	}
	
