/*******************************
Exercício de Laboratório de ED
Nome: Mateus Matinato
Data: 24/05/2017
*******************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include "funcoesArv.h"

typedef struct arv Arv;

void main(){
	setlocale(LC_ALL,"Portuguese");
	Arv* n11 = cria_arv(11,cria_arv_vazia(), cria_arv_vazia());
	Arv* n2 = cria_arv(2,cria_arv_vazia(), cria_arv_vazia());
	Arv* n99 = cria_arv(99,n11,n2);
	
	Arv* n25 = cria_arv(25,cria_arv_vazia(), cria_arv_vazia());
	Arv* n3 = cria_arv(3,cria_arv_vazia(),n25);
	Arv* n14 = cria_arv(14,n99,n3);
	
	Arv* n37 = cria_arv(37,cria_arv_vazia(), cria_arv_vazia());
	Arv* n33 = cria_arv(33,n37, cria_arv_vazia());
	Arv* n9 = cria_arv(9,cria_arv_vazia(), cria_arv_vazia());
	Arv* n42 = cria_arv(42,n9,n33);
	
	Arv* n5 = cria_arv(5,n14,n42);
	
	printf("Impressão Simétrica: ");
	imprime_arvSimetrica(n5);
	printf("\n");
	
	printf("Impressão Pós Ordem: ");
	imprime_arvPosOrdem(n5);
	printf("\n");
	
	printf("Impressão Pré Ordem: ");
	imprime_arvPreOrdem(n5);
	printf("\n");
	
}






