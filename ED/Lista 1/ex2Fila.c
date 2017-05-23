/**************************************
*	Nome: Mateus Gutierrez Matinato   *
* 	Data: 24/05/2017				  *
*   Laboratório de Estrutura de Dados *
*   Lista 1 - Exercício 2 - Fila      *
**************************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "ex2-funcoes.h"

typedef struct fila FILA;

void main(){
	setlocale(LC_ALL,"Portuguese");
	int opcao = 1;
	int criou = 0;
	FILA *fila;
	while(opcao != 0){
		printf("|========================|\n");
		printf("|          Fila          |\n");
		printf("|------------------------|\n");
		printf("|  Cria Fila         (1) |\n");
		printf("|  Insere na Fila    (2) |\n");
		printf("|  Remove da Fila    (3) |\n");
		printf("|  Imprime a Fila    (4) |\n");
		printf("|  Cabeça da Fila    (5) |\n");
		printf("|  Sair              (0) |\n");
		printf("|========================|\n");
		printf("Digite a opção ->  ");
		scanf("%d",&opcao);
			switch(opcao){
				case 1:{
					system("cls");
					fila = criaFila();
					criou = 1;
					printf("Fila Criada.\n\n");
					break;
				}
				case 2:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser inserido na fila: ");
						scanf("%d",&elemento);
						insereFila(fila,elemento);
					}
					else printf("Fila não criada.\n");
					printf("\n");
					break;
				}
				case 3:{
					system("cls");
					if(criou == 1){
						printf("Removeu o elemento: %d\n",removeFila(fila));
					}
					else printf("Fila não criada.\n");
					printf("\n");
					break;
				}
				case 4:{
					system("cls");
					int elemento;
					if(criou ==1){
						printf("Fila\n");
						imprimeFila(fila);
					}
					else printf("Fila não criada.\n");
					printf("\n");
					break;
				}
				case 5:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Cabeça da fila: %d\n",cabecaFila(fila));
					}
					else printf("Fila não criada.\n");
					printf("\n");
					break;
				}
			}
		}
}
