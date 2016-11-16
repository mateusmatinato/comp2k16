/**********************
Nome: Mateus Matinato
Data: 16/11/2016
Lista 3a - Exercício 2
**********************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
void LeMatriz(int n, int m, int x[][100]){
	int i,j;
	for(i=0;i<n;i++){
		for(j=0;j<m;j++){
			printf("x[%d][%d]= ",i,j);
			scanf("%d", &x[i][j]);
		}
	}
}

void EscMatriz(int n, int m, int x[][100]){
	int i,j;
		for(i=0;i<n;i++){
			for(j=0;j<m;j++)
			printf("%d    ",x[i][j]);
			printf("\n");
	}
}

void Produto(int n, int m, int l, int a[][100], int b[][100], int p[][100]){
	int i,j,x;

	for(i=0;i<n;i++){
		for(j=0;j<l;j++){
			p[i][j]= 0; 
		}
	}
	
	for(i=0;i<n;i++){
		for(j=0;j<l;j++){
			for(x=0;x<m;x++){
			p[i][j] += a[i][x]*b[x][j]; 
			}
		}
	}
}


int main(){
	int n,m,z,l, a[100][100], b[100][100], p[100][100];
	setlocale(LC_ALL, "Portuguese");
	
	//Lendo e Escrevendo as Duas Matrizes
	printf("Digite o número de linhas e colunas da primeira matriz: ");
	scanf("%d %d", &n, &m);
	LeMatriz(n,m,a);
	printf("Digite o número de linhas e colunas da segunda matriz: ");
	scanf("%d %d",&z,&l);
	if(z != m){
		printf("Não é possivel multiplicar essas duas matrizes.");
		return 0;
	}
	LeMatriz(m,l,b);
	system("cls");
	printf("A primeira matriz(A) é\n");
	EscMatriz(n,m,a);
	printf("A segunda matriz(B) é\n");
	EscMatriz(m,l,b);

	//Calculando Produto e Escrevendo
	Produto(n,m,l,a,b,p);
	printf("O produto das matrizes A e B é\n");
	EscMatriz(n,l,p);	
	
	system("pause");
	return 0;
}
