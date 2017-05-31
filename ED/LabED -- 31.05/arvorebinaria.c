/*********************
Nome: Mateus Matinato
laboratório de ED I
Data: 31/05/2017
/********************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

struct arv{
	int info;
	struct arv* esq;
	struct arv* dir;
};
typedef struct arv Arv;


Arv* cria_vazia(){
	return NULL;
}

void imprime_abb(Arv *arvore){
	if(arvore != NULL){
		imprime_abb(arvore->esq);
		printf("%d ",arvore->info);
		imprime_abb(arvore->dir);
	}
}

Arv* insere_abb(Arv *arvore, int v){
	if(arvore == NULL){
		arvore = (Arv *)malloc(sizeof(Arv));
		arvore->info = v;
		arvore->esq = arvore->dir = NULL;
	}
	else if(v < arvore->info)
		arvore->esq = insere_abb(arvore->esq,v);
	else
		arvore->dir = insere_abb(arvore->dir,v);
		
		return arvore;
}

Arv* busca_abb(Arv* arvore, int v){
	if(arvore == NULL) return NULL;
	else if(arvore->info > v) return busca_abb(arvore->esq,v);
	else if(arvore->info < v) return busca_abb(arvore->dir,v);
	else return arvore;
}

Arv* retira_no_abb(Arv *arvore, int v){
	if(arvore == NULL) return NULL;
	else if(arvore->info > v) arvore->esq = retira_no_abb(arvore->esq,v);
	else if(arvore->info < v) arvore->dir = retira_no_abb(arvore->dir,v);
	else{
		//Elemento sem filhos
		if(arvore->esq == NULL && arvore->dir == NULL){
			free(arvore);
			arvore = NULL;
		}
		else if(arvore->esq == NULL){
			Arv *aux = arvore;
			arvore = arvore->dir;
			free(aux);
		}
		else if(arvore->dir == NULL){
			Arv *aux = arvore;
			arvore = arvore->esq;
			free(aux);
		}
		else{
			Arv *f = arvore->esq;
			while(f->dir != NULL){
				f  = f->dir;
			}
			arvore->info = f->info;
			f->info = v;
			arvore->esq = retira_no_abb(arvore->esq,v);
		}
		
	}
	return arvore;
}



void main(){
	setlocale(LC_ALL,"Portuguese");
	int opcao;
	int elemento;
	Arv* arvore = NULL;
	do{
	printf("Inserir elementos (1)\nImprimir Arvore Ordenada (2)\nSair(0)\n");
	printf("Digite a opção: ");
	scanf("%d",&opcao);
		switch(opcao){
			case 1:{
				printf("Digite o elemento a ser inserido na árvore: ");
				scanf("%d",&elemento);
				arvore = insere_abb(arvore,elemento);
				system("cls");
				
			break;
			}
			case 2:{
				system("cls");
				printf("Árvore binária ordenada:\n");
				imprime_abb(arvore);
				printf("\n");
				break;
			}
			case 0:{
				printf("Fim.\n");
				break;
			}
		}//Fim switch
		
	}while(opcao !=0);
}
