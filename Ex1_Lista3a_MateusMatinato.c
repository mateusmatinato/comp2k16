/**********************
Nome: Mateus Matinato
Data: 16/11/2016
Lista 3a - Exercício 1
**********************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

void LeVetor(int n, int *x){
	int i;
	for(i=0;i<n;i++){
		printf("x[%d]= ", i);
		scanf("%d", &x[i]);
	}
}

void EscVetor(int n, int *x){
	int i;
	for(i=0;i<n;i++){
		printf("v[%d]= %d\n",i, x[i]);
	}
}

int Busca(int *x, int n, int y){
	int i, res=0;
	for(i=0;i<n;i++){
		if(x[i]==y)res = 1;
	}
	return res;
}


int main(){
	setlocale(LC_ALL, "Portuguese");
	int n,m,i,y, x[100], w[100], z[100], j=0;
	
	//Lendo e Escrevendo Matrizes
	printf("Digite o número de elementos do vetor x: ");
	scanf("%d", &n);
	LeVetor(n,x);
	printf("Digite o número de elementos do vetor w: ");
	scanf("%d", &m);
	LeVetor(m,w);
	system("cls");
	puts("O primeiro vetor inserido é: ");
	EscVetor(n,x);
	puts("O segundo vetor inserido é: ");
	EscVetor(m,w);
	
	//Calculando o vetor interseccção
	for(i=0;i<m;i++){
		y = w[i];
		if((Busca(x,n,y))==1){
			z[j] = w[i];
			j++;
		}
	}
	//Escrevendo o vetor intersecção
	printf("O vetor intersecção é\n");	
	for(i=0;i<j;i++){
		printf("z[%d]= %d\n", i,z[i]);
	}
	
system("pause");
return 0;
}
