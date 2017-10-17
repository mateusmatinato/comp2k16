/****************************************************
* Trabalho de Laboratório de Estrutura de Dados II 	*
* Integrantes:										*
*			João Marcos Rosa						*
*			Vinicius Freitas						*
*			Jonatan Rodrigues						*
*			Mateus Matinato							*
* Professor: Leandro Neves							*
* Data: 10/10/2017									*
****************************************************/ 

#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <math.h>
#include <conio.h>
#include <windows.h>

int profglobal = 2; //Define a profundidade global como variavel global


typedef struct registro{
	int cpf;
	int linha;
}REG;

typedef struct bucket{
	int num;
	REG *registro[4]; 
	int proflocal;
}BUCKET;

typedef struct node{
	BUCKET *bucket;
	struct node *prox;
}NODE;

NODE *duplicaDiretorio(int n, NODE *diretorio1){
	//Função utilizada pra duplicar o diretório quando aumentar a profundidade global
	int i;
	NODE *aux;
	NODE *diretorio2;
	for(i=(int)pow(2,profglobal+1)-1;i>=n;i--){
		//Cria os buckets começando pelo ultimo
		BUCKET *bucket = (BUCKET*)malloc(sizeof(BUCKET));
		diretorio2 = (NODE*)malloc(sizeof(NODE));
		bucket->num = i;
		
		NODE *aux2 = diretorio1;
		int achou = 0;
		while(aux2 != NULL && achou == 0){
			//Atribuir buckets aos respectivos
			if(aux2->bucket->num == (i - (int)pow(2,profglobal))){
				bucket->proflocal = aux2->bucket->proflocal;
				bucket->registro[0] = aux2->bucket->registro[0];
				bucket->registro[1] = aux2->bucket->registro[1];
				bucket->registro[2] = aux2->bucket->registro[2];
				bucket->registro[3] = aux2->bucket->registro[3];
				achou = 1;
			}
			aux2 = aux2->prox;
		}
		
		diretorio2->bucket = bucket;
		//insere no inicio
		if(i == pow(2,profglobal+1)-1){
			diretorio2->prox = NULL;
			aux = diretorio2;
		}
		else{
			diretorio2->prox = aux;
			aux = diretorio2;
		}
	}
	
	aux = diretorio1;
	while(aux->prox != NULL) aux = aux->prox;
	aux->prox = diretorio2;
	//liga o diretorio antigo no novo
	return diretorio1;
}

NODE *iniciaDiretorio(){
	//Função que cria os primeiros 4 buckets e seus registros
	int i;
	NODE *diretorio1;
	for(i=3;i>=0;i--){	
		BUCKET *bucket1 = (BUCKET*)malloc(sizeof(BUCKET));
		diretorio1 = (NODE*)malloc(sizeof(NODE));
		NODE *aux;
		bucket1->num = i;
		bucket1->proflocal = 2;
		diretorio1->bucket = bucket1;
		int j;
		for(j=0;j<4;j++){
			REG *registro = (REG*)malloc(sizeof(REG));
			registro->cpf = -1;
			registro->linha = -1;
			diretorio1->bucket->registro[j] = registro;
		}
		if(i == 3){
			diretorio1->prox = NULL;
			aux = diretorio1;
		}
		else{
			diretorio1->prox = aux;
			aux = diretorio1;
		}
	}
	return diretorio1;
}

int tamanhoRegistro(NODE *diretorio){
	//Função que retorna o espaço disponivel no registro (0 a 4)
	int i;
	int cont = 0;
	for(i=0;i<4;i++){
		if(diretorio->bucket->registro[i]->cpf != -1) cont++;
	}
	return cont;
}

void insereArquivo(NODE *diretorio, int cpf, int campo, char *nome, float limite, float saldo){
	FILE *arq;
	//Percorre o arquivo procurando a proxima linha livre
	arq = fopen("bd.txt","r");
	char caracter;
	int contlinhas = 0;
	if(arq != NULL){
		while (!feof(arq)){
			//Conta quantas linhas estão utilizadas
			caracter = fgetc(arq);
			if(caracter=='\n') contlinhas++;
		}
		fclose(arq);
	}
	
	NODE *aux = diretorio;
	while(aux->bucket->num != campo) aux = aux->prox;
	int i;
	for(i=0;i<4;i++){
		if(aux->bucket->registro[i]->cpf == cpf){
			//Muda a linha na hash
			aux->bucket->registro[i]->linha = contlinhas;
		}
	}
	
	//Insere no fim do arquivo 
	arq = fopen("bd.txt","a");
	fprintf(arq, "%d;%d;%s;%f;%f\n",contlinhas,cpf,nome,limite,saldo);
	fclose(arq);
}

void atualizaBuckets(NODE *primeiro, NODE *segundo){
	int i;
	int j;
	for(i=0;i<4;i++){
		if((primeiro->bucket->registro[i]->cpf%((int)pow(2,profglobal))) != primeiro->bucket->num){
			//Se entrar aqui, o elemento deve mudar de bucket
			for(j=0;j<3;j++){
				//Percorre o segundo procurando um espaço vazio
				if(segundo->bucket->registro[j]->cpf == -1){
					//Achou o espaço vazio
					segundo->bucket->registro[j]->cpf = primeiro->bucket->registro[i]->cpf;
					segundo->bucket->registro[j]->linha = primeiro->bucket->registro[i]->linha;
					primeiro->bucket->registro[i]->cpf = -1; //Libera o valor do primeiro
					primeiro->bucket->registro[i]->linha = -1;
				}
			}	
		}
	}
}

void insereDiretorio(NODE *diretorio, int cpf, int campo, char *nome, float limite, float saldo,int salvar,int numlinha){
	//O parametro salvar -> indica se será necessário escrever no arquivo o CPF que está sendo inserido
	//O parametro numlinha -> caso esteja recuperando do arquivo é necessário atribuir o numero da linha na tabela
	
	NODE *aux = diretorio;
	while(aux->bucket->num != campo){
	//Percorre o diretorio procurando o bucket adequado para inserir (hashing)
		aux = aux->prox;
	}
	if(tamanhoRegistro(aux)<4){
		int inseriu = 0;
		//Não está cheio -> pode inserir
		int i;
		for(i=0;i<4 && inseriu == 0;i++){
			//Percorre o bucket para inserir
			if(aux->bucket->registro[i]->cpf == -1){
				//Está vazio pode ser usado
				aux->bucket->registro[i]->cpf = cpf;
				inseriu = 1;
			}
		}
		//Caso salvar == 1, é necessário inserir no arquivp
		if(salvar == 1) insereArquivo(diretorio,cpf,cpf%(int)pow(2,profglobal),nome,limite,saldo);
		//Se não for salvar, significa que está sendo recuperado, portanto precisa atribuir a linha
		else aux->bucket->registro[i-1]->linha = numlinha;
	}
	else if(aux->bucket->proflocal == profglobal){
		//Está cheio, precisa dividir e mudar profundidades
		if(profglobal < 4){
			//Aumenta a profundidade local
			aux->bucket->proflocal++;
			NODE *aux3 = diretorio;
			while(aux3->bucket->num != campo) aux3 = aux3->prox; //Armazena a posição do campo a ser inserido
			
			
			//É necessário dobrar o tamanho do diretório
			diretorio = duplicaDiretorio(pow(2,profglobal),diretorio);
			NODE *aux2 = diretorio;
			int achou = 0;
			
			//Percorre o novo diretorio buscando o bucket que será criado
			while(aux2 != NULL && achou == 0){
				if(aux2->bucket->num == (campo + pow(2,profglobal))){
					//Achou o bucket a ser criado
					BUCKET *bucket1 = (BUCKET*)malloc(sizeof(BUCKET));
					bucket1->num = aux2->bucket->num;
					bucket1->proflocal = aux2->bucket->proflocal;
					int j;
					for(j=0;j<4;j++){
						REG *registro = (REG*)malloc(sizeof(REG));
						registro->cpf = -1;
						registro->linha = -1;
						bucket1->registro[j] = registro;
					}
					aux2->bucket = bucket1;
					achou = 1;
					
					profglobal++;					
					//Após criar o novo bucket, é necessário reorganizar ele e o bucket que encheu
					atualizaBuckets(aux3,aux2);
				}
				aux2 = aux2->prox;
			}
		//Após isso, chama a função novamente para inserir o valor desejado	
		insereDiretorio(diretorio,cpf,cpf%(int)pow(2,profglobal),nome,limite,saldo,salvar,numlinha);
		}
		
		else{
			//Caso a prof global seja 4 é impossível duplicar
			printf("Tamanho máximo do SGBD atingido. Foi impossível inserir na tabela.\n");
		}
		
	}
	else{
		//Está cheio, precisa só criar o novo bucket
		if(aux->bucket->num > pow(2,aux->bucket->proflocal)){
			//Esse if verifica se você não está tentando inserir no campo 5 quando deveria estar inserindo no campo 1 por exemplo...
			campo = aux->bucket->num - pow(2,aux->bucket->proflocal); //Muda o campo a ser inserido -> 5 para 1
			aux = diretorio;
			while(aux->bucket->num != campo) aux=aux->prox; //Posiciona o aux no campo a inserir
		}
		//Aumenta profundidade local
		aux->bucket->proflocal++;
		
		NODE *aux3 = diretorio;
		while(aux3->bucket->num != campo) aux3 = aux3->prox; //Armazena a posição do campo a ser inserido
		
		NODE *aux2 = diretorio;
		int achou = 0;
		
		//Percorre diretorio buscando o bucket que será criado
		while(aux2 != NULL && achou == 0){
			if(aux2->bucket->num == (campo + pow(2,profglobal-1))){
				//Achou o bucket a ser criado
				BUCKET *bucket1 = (BUCKET*)malloc(sizeof(BUCKET));
				bucket1->num = aux2->bucket->num;
				bucket1->proflocal = aux2->bucket->proflocal + 1;
				int j;
				for(j=0;j<4;j++){
					REG *registro = (REG*)malloc(sizeof(REG));
					registro->cpf = -1;
					registro->linha = -1;
					bucket1->registro[j] = registro;
				}
				aux2->bucket = bucket1;
				achou = 1;			
				//Após criar o novo bucket é necessário atualizar ele e o bucket que encheu		
				atualizaBuckets(aux3,aux2);
			}
			aux2 = aux2->prox;
		}
		//Chama novamente a função para o valor a ser inserido com os buckets rearranjados
		insereDiretorio(diretorio,cpf,cpf%(int)pow(2,profglobal),nome,limite,saldo,salvar,numlinha);		
	}

}

void mostraDiretorio(NODE *diretorio){
//	system("cls");
	//Função que mostra o diretorio e os buckets 
	NODE *aux = diretorio;
	printf("\nPROFUNDIDADE GLOBAL: %d\n\n",profglobal);
	while(aux != NULL){
		char bin[10];
		itoa(aux->bucket->num,bin,2);
		printf("\nBucket %d\nProfundidade Local %d\t>>> ",aux->bucket->num,aux->bucket->proflocal);
		int i;
		int cont = 0;
		if(aux->bucket->num < (int)pow(2,aux->bucket->proflocal)){
			for(i=0;i<4;i++){
				if(aux->bucket->registro[i]->cpf != -1){
					printf("CPF: %d Linha %d  \t",aux->bucket->registro[i]->cpf,aux->bucket->registro[i]->linha);
					cont++;
					if(cont < tamanhoRegistro(aux)) printf("->  \t");
				}
			}
			printf("\n");
		}
		printf("\n");
		printf("--------------------------\n");
	aux = aux->prox;
	}
	
}

void buscarCliente(NODE *diretorio, int cpf){
	NODE *aux = diretorio;
	//Percorre o diretorio procurando o bucket a ser consultado
	while(aux->bucket->num != (cpf%(int)pow(2,profglobal))) aux = aux->prox;
	int i;
	int achou = 0;
	for(i=0;i<4;i++){
		if(aux->bucket->registro[i]->cpf == cpf){
			//Achou o cpf no registro
			int linha = aux->bucket->registro[i]->linha;
			//Agora precisa buscar no arquivo
			FILE *arq;
			//Percorre o arquivo procurando a linha indicada
			arq = fopen("bd.txt","r");
			int cpf,numlinha;
			char nome[100];
			float limite;
			float saldo;

			while (!feof(arq) && achou == 0){
				fscanf(arq,"%d;%d;%[^;];%f;%f\n",&numlinha,&cpf,nome,&limite,&saldo);
				if(numlinha == linha){
					//Quando achar a linha
					achou = 1;
					printf("\nCPF: %d\nNome: %s\nLimite: %.2f\tSaldo: %.2f\n",cpf,nome,limite,saldo);
				}
			}
			fclose(arq);
		}
	}
	if(achou == 0)printf("\nCliente não encontrado.\n");
}

void removerArquivo(NODE *diretorio,int linha){
	//Função que remove um cliente do arquivo
	FILE *arq, *arq2;
	//Percorre o arquivo procurando a proxima linha livre
	arq = fopen("bd.txt","r");
	arq2 = fopen("bd2.txt","w");
	int cpf,numlinha;
	char nome[100];
	float limite;
	float saldo;
	int achou = 0;
	while (!feof(arq)){
		//Enquanto não chegar no fim do arquivo scaneia as linhas
		fscanf(arq,"%d;%d;%[^;];%f;%f\n",&numlinha,&cpf,nome,&limite,&saldo);
		if(numlinha == linha){
			fprintf(arq2,"%d;%d;%s;%f;%f\n",numlinha,-1," ",0,0);
		}
		else{
			fprintf(arq2,"%d;%d;%s;%f;%f\n",numlinha,cpf,nome,limite,saldo);
		}
	}
	fclose(arq);
	fclose(arq2);
	unlink("bd.txt");
	rename("bd2.txt","bd.txt");
	
}

void removerCliente(NODE *diretorio,int cpf){
	//Pega o campo do cpf a ser removido
	int dirty = rand()%10+1;
	int pinCount = rand()%10+1;
	//Caso entrar aqui, deve ser removido   (só entra se dirty <= 7 e pincount <= 7) 
	int campo = cpf%(int)pow(2,profglobal);
	int campo2;
	NODE *aux = diretorio;
	while(aux->bucket->num != campo) aux = aux->prox;
	int i,j;
	int achou = 0;
	for(i=0;i<4 && achou == 0;i++){
		if(aux->bucket->registro[i]->cpf == cpf){
			if(dirty <=7 && pinCount <=7){
			//Ao achar o cpf, remove do arquivo e apaga da hash se dirty e pin forem os necessários
				removerArquivo(diretorio,aux->bucket->registro[i]->linha);
				aux->bucket->registro[i]->cpf = -1;
				aux->bucket->registro[i]->linha = -1;
				achou = 1;
			}
			else{
				printf("Não foi possível remover pois o registro está sendo acessado ou modificado.\n");
			}
		}
	}
	if(achou == 1 && profglobal > 2){
		//Caso achou e a profundidade global ja foi modificada	
		//Verifica qual é o bucket relacionado ao que será removido, por exemplo, com profundidade global 3 -> (0,4),(1,5),(2,6),(3,7)
		if(campo < (int)pow(2,profglobal - 1)){
			campo2 = campo + (int)pow(2,profglobal-1);
		}
		else{
			int auxiliar = campo;
			campo = campo - (int)pow(2,profglobal-1);
			campo2 = auxiliar;
		}
		//Tem os 2 campos, agora é necessário verificar se podem se juntar
		aux = diretorio;
		while(aux->bucket->num != campo) aux = aux->prox;
		
		NODE *aux2 = diretorio;
		while(aux2->bucket->num != campo2) aux2 = aux2->prox;
		
		if((tamanhoRegistro(aux) + tamanhoRegistro(aux2) <= 4) && (aux->bucket->proflocal > 2)){
		//Caso a soma dos tamanhos for igual a 4 podem se juntar (e a profundidade for maior que 2, se não só existe 1 bucket)
			for(i=0;i<4;i++){
				if(aux2->bucket->registro[i]->cpf != -1){
					//Esse if junta o segundo com o primeiro
					for(j=0;j<4;j++){
						if(aux->bucket->registro[j]->cpf == -1){
							aux->bucket->registro[j]->cpf = aux2->bucket->registro[i]->cpf;
							aux2->bucket->registro[i]->cpf = -1;
							aux->bucket->registro[j]->linha = aux2->bucket->registro[i]->linha;
							aux2->bucket->registro[i]->linha = -1;
						}
					}	
				}
			}
			//Diminui as prof locais
			aux->bucket->proflocal--;
			aux2->bucket->proflocal--;
		}
		
		aux = diretorio;
		int cont = 0;
		while(aux != NULL){
			//Verifica se é necessário mudar a prof global
			if(aux->bucket->proflocal == profglobal) cont++;
			aux = aux->prox;
		}
		if(cont == 0){
			//Se for 0 significa que é necessário mudar
			profglobal--;
			aux = diretorio;
			NODE *ant = aux;
			while(aux->bucket->num < (int)pow(2,profglobal)){
				ant = aux;
				aux = aux->prox;
			}
			ant->prox = NULL;
		}
		printf("Cliente removido com sucesso.\n");	
	}
	else if(achou == 0 && dirty <=7 && pinCount <=7){
		printf("\nCliente não encontrado.\n");
	}
}

void recuperaArquivo(NODE *diretorio){
	FILE *arq;
	//Percorre o arquivo procurando a proxima linha livre
	arq = fopen("bd.txt","r");
	if(arq == NULL){
		printf("Não foi possível recuperar as informações.\n");
	}
	else{
		int cpf,numlinha;
		char nome[100];
		float limite;
		float saldo;
		while (!feof(arq)){
			//Enquanto não chegar no fim do arquivo scaneia as linhas e atribui os valores na hash
			fscanf(arq,"%d;%d;%[^;];%f;%f\n",&numlinha,&cpf,nome,&limite,&saldo);
				//A função insere deve ser chamada com parametro salvar = 0 para não salvar novamente no arquivo
				if(cpf != -1)
				insereDiretorio(diretorio,cpf,cpf%(int)pow(2,profglobal),nome,limite,saldo,0,numlinha);
		}
		fclose(arq);
	}
}

void main(){
	
	setlocale(LC_ALL,"Portuguese");
	NODE *diretorio = iniciaDiretorio(); //Função que cria o diretorio e seus respectivos buckets já esvaziados e com profundidades corretas
	recuperaArquivo(diretorio); //Função que recupera os dados de um arquivo txt
	int opc = -1;
	while(opc != 0){
		printf("\n|================================|\n");
		printf("|         SGBD - Indexação       |\n");
		printf("|--------------------------------|\n");
		printf("|      Cadastrar Cliente  (1)    |\n");
		printf("|      Remover Cliente    (2)    |\n");
		printf("|      Consultar Cliente  (3)    |\n");
		printf("|      Visualizar Tabela  (4)    |\n");
		printf("|      Sair               (0)    |\n");
		printf("|================================|\n");
		printf("Digite a opção -> ");
		scanf("%d",&opc);
		system("cls");
		switch(opc){
			case 1:{
				int cpf;
				char nome[100];
				float limite,saldo;
				
				printf("Digite o número do CPF: ");
				scanf("%d",&cpf);
				fflush(stdin);
				printf("Digite o nome: ");
				scanf("%[^\n]",nome);
				printf("Digite o limite de crédito: ");
				scanf("%f",&limite);
				printf("Digite o saldo da conta: ");
				scanf("%f",&saldo);
				int campo = cpf%((int)pow(2,profglobal));
				insereDiretorio(diretorio,cpf,campo,nome,limite,saldo,1,-1);
				break;
			}
			case 2:{
				printf("Digite o CPF a ser removido: ");
				int cpf;
				scanf("%d",&cpf);
				removerCliente(diretorio,cpf);
				break;
			}
			case 3:{
				printf("Digite o CPF a ser consultado: ");
				int cpf;
				scanf("%d",&cpf);
				buscarCliente(diretorio,cpf);
				break;
			}
			case 4:{
				mostraDiretorio(diretorio);
				break;
			}
			case 0:{
				system("cls");
				printf("\n\n\n\t\t\tFim do Programa!\n\n\n");
				break;
			}
		}
	}
}
