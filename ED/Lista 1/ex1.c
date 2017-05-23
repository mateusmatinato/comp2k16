	/*******************************
	*  Nome: Mateus Matinato       *
	*  Data: 24/05/17              *
	*  Lista de Exercícios 1       *
	*  Estrutura de Dados I        *
	*******************************/
	
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

struct NODE {
  int  info;
  struct NODE *prox;
};

typedef struct NODE NODE;

void imprime(NODE *lista){
	NODE *aux = lista;
	int i = 0;
	while(aux!=NULL){
		printf("Elemento [%d] = %d\n",i+1,aux->info);
		aux = aux->prox;
		i++;
	}
}

int compararLista(NODE *lista1, NODE *lista2){ //Compara 2 listas - working
	NODE *aux1, *aux2;
	aux1 = lista1;
	aux2 = lista2;
	while(aux1!=NULL && aux2!=NULL){
		if(aux1->info == aux2->info){
			aux1 = aux1->prox;
			aux2 = aux2->prox;
		}
		else return 0;
	}
	if(aux1 == NULL && aux2 == NULL) return 1;
	else return 0;
}

void concatenarLista(NODE *lista1, NODE *lista2){ //Concatena L2 no fim de L1
	NODE *aux1;
	aux1 = lista1;
	while(aux1->prox != NULL){
		aux1 = aux1->prox;
	}
	aux1->prox = lista2;
}

NODE *insereFinal(NODE *lista, int valor){
	NODE *aux = lista;
	NODE *novo;
	novo = malloc(sizeof(NODE));
	novo->info = valor;
	
	//Caso a lista estiver vazia
	if(lista == NULL){
		novo->prox = NULL;
		novo->info = valor;
		lista = novo;
		return lista;
	}
	
	//Demais casos
	while(aux->prox != NULL){
		aux = aux->prox;
	}
	novo->prox = NULL;
	aux->prox = novo;
	return lista;
}


void main(){
	setlocale(LC_ALL,"Portuguese");
	printf("\n");
	printf("\t|===========================|\n");
	printf("\t|          Lista 1          |\n");
	printf("\t|---------------------------|\n");
	printf("\t Quantos elementos terá-> ");
	NODE *lista1;
	lista1 = malloc(sizeof(NODE));
	lista1 = NULL;
	int i, qtd1, elemento;
	scanf("%d",&qtd1);
	for(i = 0; i < qtd1 ; i++){
		printf("\tElemento %d -> ",i+1);
		scanf("%d", &elemento);
		lista1 = insereFinal(lista1,elemento);
	}
	printf("\n");
	printf("\t|===========================|\n");
	printf("\t|          Lista 2          |\n");
	printf("\t|---------------------------|\n");
	printf("\t Quantos elementos terá-> ");
	NODE *lista2;
	lista2 = malloc(sizeof(NODE));
	lista2 = NULL;
	int qtd2;
	scanf("%d",&qtd2);
	for(i = 0; i < qtd2 ; i++){
		printf("\tElemento %d -> ",i+1);
		scanf("%d", &elemento);
		lista2 = insereFinal(lista2,elemento);
	}
	system("cls");
	int opcao = 1;
		int concatenada = 0;
	while(opcao != 0){
		printf("\n");
		printf("\t|=============================|\n");
		printf("\t|   Imprimir Listas   (1)     |\n");
		printf("\t|   Comparar Listas   (2)     |\n");
		printf("\t|   Concatenar Listas (3)     |\n");
		printf("\t|   Sair              (0)     |\n");
		printf("\t|=============================|\n");
		printf("\tDigite a opção-> ");
		scanf("%d", &opcao);
		switch(opcao){
			case 1:{
				if(concatenada != 1){	
					system("cls");
					printf("Lista 1: \n");
					imprime(lista1);
					printf("\nLista 2:\n");
					imprime(lista2);
				}
				else{
					system("cls");
						printf("Lista 1 (Concatenada): \n");
						imprime(lista1);
						printf("\nLista 2: \n");
						imprime(lista2);
				}
				break;
			}
			case 2:{
				system("cls");
				if(concatenada == 1) printf("As listas já foram concatenadas. \n");
				else{
					if(compararLista(lista1,lista2)==1) printf("As listas 1 e 2 são iguais.\n");
					else printf("As listas 1 e 2 são diferentes.\n");
				}
				break;
			}
			case 3:{
				system("cls");
				if(concatenada == 0){
					NODE *lista3;
					concatenarLista(lista1,lista2);
					printf("As listas foram concatenadas.\n");
					printf("Lista Nova (Concatenada): \n");
					imprime(lista1);
					concatenada = 1;
				}
				else printf("As listas já foram concatenadas uma vez.\n");
				break;
			}
			case 0:{
				printf("FIM DA EXECUÇÃO.\n");
				break;
			}
		}
	}
}
