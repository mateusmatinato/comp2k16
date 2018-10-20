/*********************************
Trabalho de Estrutura de Dados I
Sistema de Adivinha��o com ABB
Integrantes:	Mateus Matinato
				Jo�o Marcos Rosa
				Jonatan Rodrigues
Data: 12/07/2017
Professor: Matheus Gon�alves
**********************************/

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <string.h>


typedef struct arv{
	char info[100];
	struct arv *esq;
	struct arv *dir;
}Arv;

Arv *cria_abb_vazia(){
	return NULL;
}

Arv *readArvore(FILE *arq){
	//Essa fun��o l� a arvore do arquivo txt
  char info[100];
  int isFolha = 0;
  
  fscanf(arq, "%[^;];%i;\n", info, &isFolha); //L� a string at� o ; e o indice
  Arv* raiz = (Arv*)malloc(sizeof(Arv)); //Aloca o novo n� que ser� inserido
  strcpy(raiz->info, info); //Copia a string no n�
  
	if(isFolha == 0) raiz->esq = readArvore(arq); //Caso ela n�o for folha, chama a fun��o para a arvore esquerda
	else raiz->esq = NULL; //Se n�o atribui NULL
	
	if(isFolha == 0) raiz->dir = readArvore(arq); //Caso ela n�o for folha, chama a fun��o para a arvore direita
	else raiz->dir = NULL; //Se n�o atribui NULL;

  return raiz;
}


void imprime_abb(Arv *a){
	if(a != NULL){
		imprime_abb(a->esq);
		printf("%s ",a->info);
		imprime_abb(a->dir);
	}
}

void writeArvore (Arv *a, FILE *arq){
	//Essa fun��o escreve no arquivo os n�s da arvore
	int i = 0;
	if(isFolha(a)) i = 1; //Caso o n� for folha atribui i = 1
	fprintf(arq,"%s;%i;\n",a->info,i); //Escreve no arquivo no formato: STRING;INDICE;
		if(a->esq)writeArvore(a->esq,arq); 
		if(a->dir)writeArvore(a->dir,arq);
}


Arv *insere_abb(Arv *a, char info[]){
	//Fun��o que insere na arvore
	if(a == NULL){
		a = (Arv*)malloc(sizeof(Arv));
		strcpy(a->info,info);
		a->esq = a->dir = NULL;
	}
}


int isFolha(Arv *a){
	//Essa fun��o retorna 1 caso o n� for folha e 0 caso n�o for
	if(a->dir == NULL && a->esq == NULL){
		return 1;
	}
	else return 0;
}

void main(){
	system("mode con:cols=110 lines=30"); //Configura o tamanho da tela
	setlocale(LC_ALL,"Portuguese");
	char resposta[10];
	char pensou[40];
	char pergunta[100];
	int acabou = 0;
	int opcao;
	
	
	//Pega dados do arquivo
	FILE *arq = fopen("arvore.txt","r");
	Arv *a = readArvore(arq);
	fclose(arq);


	printf("\t|==============================|\n");
	printf("\t| AKINATOR -> Times de Futebol |\n");
	printf("\t|------------------------------|\n");
	printf("\t|   Iniciar Jogo      (1)      |\n");
	printf("\t|   Finalizar         (0)      |\n");
	printf("\t|==============================|\n");
	printf("\tDigite sua op��o -> ");
	scanf("%d",&opcao);
	system("cls");
	
//	Arv *a = cria_abb_vazia();
//	a = insere_abb(a,"Esse time � brasileiro? ");
//	a->esq = insere_abb(a->esq,"Palmeiras");
//	a->dir = insere_abb(a->dir,"Barcelona");

	while(opcao == 1){
		acabou = 0;
		Arv *noatual = a;
		printf("\nPense em um time...\n");
		while(acabou == 0){
			//Enquanto n�o acertar
			printf("\n%s (SIM/NAO): ",noatual->info);
			scanf("%s",resposta);
			if(stricmp(resposta,"Sim")==0){
				//Caso a resposta for sim
				noatual = noatual->esq;
			}
			else noatual = noatual->dir; //Caso for n�o
		
			if(isFolha(noatual)){
				//Caso o n� atual seja uma folha
				printf("\nVoc� pensou no %s! Acertei (SIM/NAO)? ",noatual->info);
				scanf("%s",resposta);
				fflush(stdin);
				if(stricmp(resposta,"Sim")==0){
					//Caso seja um n� folha e a resposta for sim -> fim
					printf("EU GANHEI!!!!\n\n");
					acabou = 1;
				}
				else{
					//Caso seja um n� folha e a resposta for n�o -> complementa a arvore
					printf("\nAhhh, eu perdi...\n\nNo que voc� pensou: ");
					gets(pensou);
					fflush(stdin);
					printf("Me ajude a melhorar...\n");
					printf("Fa�a uma pergunta que � v�lido para o %s e n�o � v�lido para o %s:\n",pensou,noatual->info);
					gets(pergunta);
					fflush(stdin);
					char respostaErrada[40];
					strcpy(respostaErrada,noatual->info);
					strcpy(noatual->info,pergunta);
					noatual->esq = insere_abb(noatual->esq,pensou);
					noatual->dir = insere_abb(noatual->dir,respostaErrada);
					printf("\nDa proxima vez eu ganho...\n");
					acabou = 1;
				}
			}	
			else{
				//O n� atual � outra pergunta
				acabou = 0;
			}
		}
		printf("\t|==============================|\n");
		printf("\t| AKINATOR -> Times de Futebol |\n");
		printf("\t|------------------------------|\n");
		printf("\t|   Jogar Novamente   (1)      |\n");
		printf("\t|   Finalizar         (0)      |\n");
		printf("\t|==============================|\n");	
		printf("\tDigite sua op��o -> ");
		scanf("%d",&opcao);
		system("cls");
	}
	system("cls");
	printf("Programa Finalizado...\n\n");
	
	//Salva no arquivo
	arq = fopen("arvore.txt","w");
	writeArvore(a,arq);
	fclose(arq);

system("pause");	
}
