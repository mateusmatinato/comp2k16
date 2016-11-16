/**********************
Nome: Mateus Matinato
Data: 16/11/2016
Lista 3a - Exerc�cio 3
**********************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

void divisores(int *n, int *soma, int *quant){
	int i, x;
	for(i=1;i<=*n;i++){
		if((*n%i)== 0){ 		//Para um n� ser divisor � necess�rio o resto ser zero
			*quant = *quant + 1;  //Contador
			*soma = *soma + i;	  //Soma dos divisores
		}
	}
}


int main(){
	setlocale(LC_ALL, "Portuguese");
	int i,n,m, div, soma=0, quant=0;
	
	//Atribuindo o n� de elementos
	printf("Quantos elementos ser�o analisados: ");
	scanf("%d", &m);
	
	//Calcular divisores de M elementos
	for(i=0;i<m;i++){
		printf("Digite o %d� n�mero: ", i+1);
		scanf("%d", &n);
		divisores(&n,&soma,&quant);
		printf("O n�mero %d possui %d divisores e a soma deles � %d.\n\n",n,quant,soma);
		soma = quant = 0; 			//� necess�rio zerar a soma e o contador para n�o acumular
	}
system("pause");
return 0;
}

