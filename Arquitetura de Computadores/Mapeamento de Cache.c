/****************************************************************
|     Nome: Mateus Matinato                                     |
|     Data: 19/10/2017   Hora: 23:32                            |
|---------------------------------------------------------------|
|  ATENÇÃO: Caso for copiar esse código, tenha a decência de    |
|  adaptá-lo a fim de não me foder, se foder e nos foder...     |
|  ATENÇÃO 2: Esse código está uma gambiarra monstrissima, mas  |
|  parece estar funcionando, então não deve ser questionado, é  |
| um axioma computacional, aceite.                              |
|---------------------------------------------------------------|
| FUNCIONAMENTO: Esse programa recebe um valor em hexa e chama  |
| a função transforma que retorna uma string do valor converti- |
| do pra binário, após isso é chamada a função formata que mos- |
| tra na saída o endereço formatado de acordo com TAG/SET/WORD. |
****************************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>
#include <math.h>

char *transforma(char *end){
	char *endreal = (char*)malloc(32*sizeof(char)); 
	int i;
	for(i=0;i<32;i++) endreal[i] = '0';
	int j = 0;
	for(i=0;i<8;i++){
    //Aqui fica lindo <3 -> GO HORSE
		if(end[i] == '0'){
			endreal[j] =   '0';
			endreal[j+1] = '0';
			endreal[j+2] = '0';
			endreal[j+3] = '0';
		}
		else if(end[i] == '1'){
			endreal[j] =   '0';
			endreal[j+1] = '0';
			endreal[j+2] = '0';
			endreal[j+3] = '1';
		}
		else if(end[i] == '2'){
			endreal[j] =   '0';
			endreal[j+1] = '0';
			endreal[j+2] = '1';
			endreal[j+3] = '0';
		}
		else if(end[i] == '3'){
			endreal[j] =   '0';
			endreal[j+1] = '0';
			endreal[j+2] = '1';
			endreal[j+3] = '1';
		}
		else if(end[i] == '4'){
			endreal[j] =   '0';
			endreal[j+1] = '1';
			endreal[j+2] = '0';
			endreal[j+3] = '0';
		}
		else if(end[i] == '5'){
			endreal[j] =   '0';
			endreal[j+1] = '1';
			endreal[j+2] = '0';
			endreal[j+3] = '1';
		}
		else if(end[i] == '6'){
			endreal[j] =   '0';
			endreal[j+1] = '1';
			endreal[j+2] = '1';
			endreal[j+3] = '0';
		}
		else if(end[i] == '7'){
			endreal[j] =   '0';
			endreal[j+1] = '1';
			endreal[j+2] = '1';
			endreal[j+3] = '1';
		}
		else if(end[i] == '8'){
			endreal[j] =   '1';
			endreal[j+1] = '0';
			endreal[j+2] = '0';
			endreal[j+3] = '0';
		}
		else if(end[i] == '9'){
			endreal[j] =   '1';
			endreal[j+1] = '0';
			endreal[j+2] = '0';
			endreal[j+3] = '1';
		}
		else if(end[i] == 'A'){
			endreal[j] =   '1';
			endreal[j+1] = '0';
			endreal[j+2] = '1';
			endreal[j+3] = '0';
		}
		else if(end[i] == 'B'){
			endreal[j] =   '1';
			endreal[j+1] = '0';
			endreal[j+2] = '1';
			endreal[j+3] = '1';
		}
		else if(end[i] == 'C'){
			endreal[j] =   '1';
			endreal[j+1] = '1';
			endreal[j+2] = '0';
			endreal[j+3] = '0';
		}
		else if(end[i] == 'D'){
			endreal[j] =   '1';
			endreal[j+1] = '1';
			endreal[j+2] = '0';
			endreal[j+3] = '1';
		}
		else if(end[i] == 'E'){
			endreal[j] =   '1';
			endreal[j+1] = '1';
			endreal[j+2] = '1';
			endreal[j+3] = '0';
		}
		else if(end[i] == 'F'){
			endreal[j] =   '1';
			endreal[j+1] = '1';
			endreal[j+2] = '1';
			endreal[j+3] = '1';
		}
		j += 4;

	}
	return endreal;
}

void formata(char *endreal, int tag, int set, int word){
	int i;
	char *tag1 = (char*)malloc(tag*sizeof(char));
	char *set1 = (char*)malloc(set*sizeof(char));
	char *word1 = (char*)malloc(word*sizeof(char));
	int j;		
	j = 0;
  
  //Até a linha 151 só serve pra deixar TAG  SET   WORD   alinhados
	for(i=0;i<tag/2;i++)printf(" "); 
	printf("TAG");
	for(i=0;i<tag/2;i++)printf(" "); 
	printf("\t");
	for(i=0;i<set/2;i++)printf(" "); 
	printf("SET");
	for(i=0;i<set/2;i++)printf(" "); 
	printf("\t");
	for(i=0;i<word/2;i++)printf(" "); 
	printf("WORD");
	printf("\n");
	
	//Mostra a tag 
	for(i=0;i<tag;i++){
		tag1[j] = endreal[i];
		j++;
		printf("%c",endreal[i]);
	}
	printf("\t");
  
  //Mostra o set
	j = 0;
	for(i=tag;i<tag+set;i++){
		set1[j] = endreal[i];
		j++;
		printf("%c",endreal[i]);
	}
	printf("\t");
	
  //Mostra a word
	j = 0;
	for(i=tag+set;i<tag+set+word;i++){
		word1[j] = endreal[i];
		j++;
		printf("%c",endreal[i]);
	}
	
	//converte set pra decimal: 
	int valorSet = 0;
	for(i = set; i>0; i--){
  		valorSet += (set1[i-1] - '0') * pow(2, set - i);  
 	}
 	
 	//Converte word pra decimal
	int valorWord = 0;
	for(i = word; i>0; i--){
  		valorWord += (word1[i-1] - '0') * pow(2, word - i);  
 	}
 	printf("\nSet: %d\nWord: %d\n",valorSet,valorWord);
printf("\n\n");
}

void main(){
	setlocale(LC_ALL,"Portuguese");
	int tag,set,word;
	int opc = -1;
	while(opc != 0){
		printf("|===============================|\n");
		printf("|         Mapeamento Cache      |\n");
		printf("|-------------------------------|\n");
		printf("|     Mapeamento Direto     (1) |\n");
		printf("|     Assoc. por Conjunto   (2) |\n");
		printf("|     Sair                  (0) |\n");
		printf("|-------------------------------|\n");
		printf("Digite a opção -> ");
		scanf("%d",&opc);
		if(opc == 1){
			tag = 19;
			set = 11;
			word = 2;
		}
		else if(opc == 2){
			printf("Digite o número de conjuntos(potencias de 2): ");
			scanf("%d",&set);
			int cont = 0;
			while(set > 1){
				set = set/2;
				cont++;
			}		
			set = cont;
			word = 2;
			tag = 32 - set - 2;
		}
		if(opc != 0){
			fflush(stdin);
			char end[8];
			printf("Digite um endereço em hexadecimal de 32 bits: ");
			scanf("%[^\n]",end);
			char *endbin = transforma(end);
			printf("\nEndereço inserido:\n%s\n",end);
			printf("\nEndereço em Binário é:\n%s\n\n",endbin);
		formata(endbin,tag,set,word);
		}
	}
	if(opc == 0) printf("\n\n\n\t\t\tFim do Programa!\n\n\n");
}
