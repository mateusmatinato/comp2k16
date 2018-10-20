/*********************************
Trabalho de Estrutura de Dados I
Sistema de Adivinhação com ABB
Integrantes:	Mateus Matinato
				João Marcos Rosa
				Jonatan Rodrigues
Data: 12/07/2017
Professor: Matheus Gonçalves
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
	//Essa função lê a arvore do arquivo txt
  char info[100];
  int isFolha = 0;
  
  fscanf(arq, "%[^;];%i;\n", info, &isFolha); //Lê a string até o ; e o indice
  Arv* raiz = (Arv*)malloc(sizeof(Arv)); //Aloca o novo nó que será inserido
  strcpy(raiz->info, info); //Copia a string no nó
  
	if(isFolha == 0) raiz->esq = readArvore(arq); //Caso ela não for folha, chama a função para a arvore esquerda
	else raiz->esq = NULL; //Se não atribui NULL
	
	if(isFolha == 0) raiz->dir = readArvore(arq); //Caso ela não for folha, chama a função para a arvore direita
	else raiz->dir = NULL; //Se não atribui NULL;

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
	//Essa função escreve no arquivo os nós da arvore
	int i = 0;
	if(isFolha(a)) i = 1; //Caso o nó for folha atribui i = 1
	fprintf(arq,"%s;%i;\n",a->info,i); //Escreve no arquivo no formato: STRING;INDICE;
		if(a->esq)writeArvore(a->esq,arq); 
		if(a->dir)writeArvore(a->dir,arq);
}


Arv *insere_abb(Arv *a, char info[]){
	//Função que insere na arvore
	if(a == NULL){
		a = (Arv*)malloc(sizeof(Arv));
		strcpy(a->info,info);
		a->esq = a->dir = NULL;
	}
}


int isFolha(Arv *a){
	//Essa função retorna 1 caso o nó for folha e 0 caso não for
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
	printf("\tDigite sua opção -> ");
	scanf("%d",&opcao);
	system("cls");
	
//	Arv *a = cria_abb_vazia();
//	a = insere_abb(a,"Esse time é brasileiro? ");
//	a->esq = insere_abb(a->esq,"Palmeiras");
//	a->dir = insere_abb(a->dir,"Barcelona");

	while(opcao == 1){
		acabou = 0;
		Arv *noatual = a;
		printf("\nPense em um time...\n");
		while(acabou == 0){
			//Enquanto não acertar
			printf("\n%s (SIM/NAO): ",noatual->info);
			scanf("%s",resposta);
			if(stricmp(resposta,"Sim")==0){
				//Caso a resposta for sim
				noatual = noatual->esq;
			}
			else noatual = noatual->dir; //Caso for não
		
			if(isFolha(noatual)){
				//Caso o nó atual seja uma folha
				printf("\nVocê pensou no %s! Acertei (SIM/NAO)? ",noatual->info);
				scanf("%s",resposta);
				fflush(stdin);
				if(stricmp(resposta,"Sim")==0){
					//Caso seja um nó folha e a resposta for sim -> fim
					printf("EU GANHEI!!!!\n\n");
					acabou = 1;
				}
				else{
					//Caso seja um nó folha e a resposta for não -> complementa a arvore
					printf("\nAhhh, eu perdi...\n\nNo que você pensou: ");
					gets(pensou);
					fflush(stdin);
					printf("Me ajude a melhorar...\n");
					printf("Faça uma pergunta que é válido para o %s e não é válido para o %s:\n",pensou,noatual->info);
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
				//O nó atual é outra pergunta
				acabou = 0;
			}
		}
		printf("\t|==============================|\n");
		printf("\t| AKINATOR -> Times de Futebol |\n");
		printf("\t|------------------------------|\n");
		printf("\t|   Jogar Novamente   (1)      |\n");
		printf("\t|   Finalizar         (0)      |\n");
		printf("\t|==============================|\n");	
		printf("\tDigite sua opção -> ");
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
