/**************************************
*	Nome: Mateus Gutierrez Matinato   *
* 	Data: 24/05/2017				  *
*   Laboratório de Estrutura de Dados *
*   Lista 1 - Exercício 2 - Lista DP  *
**************************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "ex2-funcoes.h"

typedef struct NODP NODP;

void main(){
	setlocale(LC_ALL,"Portuguese");
	int opcao = 1;
	int criou = 0;
	NODP *lista;
	while(opcao != 0){
		printf("|===========================================|\n");
		printf("|         Lista Duplamente Encadeada        |\n");
		printf("|-------------------------------------------|\n");
		printf("|  Cria Lista Duplamente Encadeada      (1) |\n");
		printf("|  Insere Inicio Lista Dupla            (2) |\n");
		printf("|  Insere Final Lista Dupla             (3) |\n");
		printf("|  Insere Ordenado Lista Dupla          (4) |\n");
		printf("|  Remove Elemento da Lista             (5) |\n");
		printf("|  Imprime Lista Dupla                  (6) |\n");
		printf("|  Sair                                 (0) |\n");
		printf("|===========================================|\n");
		printf("Digite a opção ->  ");
		scanf("%d",&opcao);
			switch(opcao){
				case 1:{
					system("cls");
					lista = criaListaDupla();
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
						lista = insereInicioDupla(lista,elemento);

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
						lista = insereFimDupla(lista,elemento);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 4:{
					system("cls");
					int elemento;
					if(criou ==1){
						printf("Digite o elemento para ser inserido ordenado na lista: ");
						scanf("%d",&elemento);
						lista = insereMeioDupla(lista,elemento);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 5:{
					system("cls");
					int elemento;
					if(criou == 1){
						printf("Digite o elemento a ser removido: ");
						scanf("%d",&elemento);
						lista = removeDupla(lista,elemento);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
				case 6:{
					system("cls");
					if(criou == 1){
						printf("Lista Duplamente Encadeada\n");
						imprimeDupla(lista);
					}
					else printf("Lista não criada.\n");
					printf("\n");
					break;
				}
			}
	}
}
