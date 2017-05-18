
package labpoo.ex2;
	public class Passageiro{
		private int rg;
		private String nome;
		private int dNasc;
                private int mNasc;
                private int aNasc;
			
					
		private String end;
		
		public Passageiro(int rg, String nome, int dNasc, int mNasc, int aNasc, String end){
			this.rg = rg;
			this.nome = nome;
			this.dNasc = dNasc;
			this.mNasc = mNasc;
			this.aNasc = aNasc;
			this.end = end;
		}
                
                public String mostrarPassageiro(){
                    String resultado;
                    resultado = ("Nome: "+nome+"\tRG: "+rg+"\n");
                    resultado = resultado + ("Data de Nascimento: "+dNasc+"/"+mNasc+"/"+aNasc+"\n");
                    resultado = resultado + ("Endereço: "+end+"\n");
                    return resultado;
                }
		
	}
	
