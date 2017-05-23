/**************************************
*	Nome: Mateus Gutierrez Matinato   *
* 	Data: 24/05/2017				  *
*   Laboratório de Estrutura de Dados *
*   Lista 1 - Exercício 2 - Lista Circ*
**************************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "ex2-funcoes.h"

typedef struct node NODE;

void main(){
	setlocale(LC_ALL,"Portuguese");
	int opcao = 1;
	int criou = 0;
	NODE *lista;
	while(opcao != 0){
		printf("|==============================|\n");
		printf("|         Lista Circular       |\n");
		printf("|------------------------------|\n");
		printf("|  Cria Lista Circular     (1) |\n");
		printf("|  Insere Inicio           (2) |\n");
		printf("|  Insere Final            (3) |\n");
		printf("|  Remove Elemento         (4) |\n");
		printf("|  Imprime Lista Circular  (5) |\n");
		printf("|  Sair                    (0) |\n");
		printf("|==============================|\n");
		printf("Digite a opção ->  ");
		scanf("%d",&opcao);
			switch(opcao){
				case 1:{
					system("cls");
					lista = criaListaCircular();
					criou = 1;
					printf("Lista Criada.\n\n");
					break;
				}
				case 2:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser inserido no inicio: ");
						scanf("%d",&elemento);
						lista = insereInicioCircular(lista,elemento);

					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 3:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser inserido no final: ");
						scanf("%d",&elemento);
						lista = insereFinalCircular(lista,elemento);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 4:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser removido: ");
						scanf("%d",&elemento);
						lista = removeCircular(lista,elemento);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 5:{
					system("cls");
					printf("Lista Circular\n");
					imprimeCircular(lista);
					printf("\n");
					break;
				}
			}
	}
}
