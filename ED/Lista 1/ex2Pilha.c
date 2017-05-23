/**************************************
*	Nome: Mateus Gutierrez Matinato   *
* 	Data: 24/05/2017				  *
*   Laboratório de Estrutura de Dados *
*   Lista 1 - Exercício 2 - Pilha     *
**************************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "ex2-funcoes.h"

typedef struct pilha PILHA;

void main(){
	setlocale(LC_ALL,"Portuguese");
	int opcao = 1;
	int criou = 0;
	PILHA *pilha;
	while(opcao != 0){
		printf("|========================|\n");
		printf("|          Pilha         |\n");
		printf("|------------------------|\n");
		printf("|  Cria Pilha        (1) |\n");
		printf("|  Insere na Pilha   (2) |\n");
		printf("|  Remove da Pilha   (3) |\n");
		printf("|  Remover Elemento  (4) |\n");
		printf("|  Imprime a Pilha   (5) |\n");
		printf("|  Cabeça da Pilha   (6) |\n");
		printf("|  Destruir Pilha    (7) |\n");
		printf("|  Sair              (0) |\n");
		printf("|========================|\n");
		printf("Digite a opção ->  ");
		scanf("%d",&opcao);
			switch(opcao){
				case 1:{
					system("cls");
					pilha = criaPilha();
					criou = 1;
					printf("Pilha Criada.\n\n");
					break;
				}
				case 2:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser inserido na pilha: ");
						scanf("%d",&elemento);
						push(pilha,elemento);
					}
					else printf("Pilha não criada.\n");
					printf("\n");
					break;
				}
				case 3:{
					system("cls");
					if(criou == 1){
						printf("Removeu o elemento: %d\n",pop(pilha));
					}
					else printf("Pilha não criada.\n");
					printf("\n");
					break;
				}
				case 4:{
					system("cls");
					int elemento;
					if(criou ==1){
						printf("Digite o elemento a ser removido: ");
						scanf("%d",&elemento);
						removeElementoPilha(pilha,elemento);
					}
					else printf("Pilha não criada.\n");
					printf("\n");
					break;
				}
				case 5:{
					system("cls");
					int elemento;
					if(criou ==1){
						imprimePilha(pilha);
					}
					else printf("Pilha não criada.\n");
					printf("\n");
					break;
				}
				case 6:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Cabeça da pilha: %d\n",top(pilha));
					}
					else printf("Pilha não criada.\n");
					printf("\n");
					break;
				}
				case 7:{
					system("cls");
					destroiPilha(pilha);
					criou = 0;
					printf("Pilha destruída.\n\n");
					break;
				}
			}
		}
}
