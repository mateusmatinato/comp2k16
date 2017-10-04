

typedef struct pcb{
	//Tad do PCB
	int id;
	int estado;
	int contador; 
	float tempoCPU;
}PCB;

typedef struct processo{
	//Tad do Processo
	int id;
	char text[20];
	char data[20];
	int tam;
	PCB pcb;
}PROCESSO;

struct node{
	//Tad do nó da fila
	PROCESSO processo;
	struct NODE *prox;
};
typedef struct node NODE;

struct memorynode{
	//Tad do nó da memória
	PROCESSO *processo;
	int posicao;
	int tamanho;
	struct memorynode *prox;
};
typedef struct memorynode MEM;

typedef struct fila{
	//Tad da fila
	NODE *ini;
	NODE *fim;
}FILA;

MEM *criaMemoria(){
	//Função que cria memória
	MEM *novo = (MEM*)malloc(sizeof(MEM));
	novo->processo = NULL;
	novo->tamanho = 0;
	novo->posicao = NULL;
	novo->prox = NULL;
	return novo;
}

MEM *insereMemoria(MEM *memoria,PROCESSO *p1, int *inseriu){
	//Função que insere um processo na memoria e altera a variável "inseriu"

	if(memoria->processo == NULL && p1->tam <= memoria->tamanho){
		//Caso da primeira posição já ser a adequada
		memoria->processo = p1;
		int tamLivre = memoria->tamanho - p1->tam;
		memoria->tamanho = p1->tam;
		if(tamLivre > 0){
			//Irá criar um espaço vazio
			MEM *vazio = criaMemoria();
			vazio->tamanho = tamLivre;
			vazio->posicao = p1->tam;
			vazio->prox = memoria->prox;
			memoria->prox = vazio;
		}
		
		printf("O Processo %d (Tamanho-> %d) foi inserido na memória.\n",p1->id,p1->tam);
		*inseriu = 1;
		return memoria;
	}
	MEM *aux = memoria;
	MEM *ant = aux;
	int achou = 0;
	while(aux != NULL && achou == 0){
		//Percorre a memória até achar uma posição
		if(aux->processo == NULL && aux->tamanho >= p1->tam)
			achou = 1;
		ant = aux;
		aux = aux->prox;
	}
	aux = ant;
	if(achou == 1){
		//Achou a primeira posição que cabe
		int tamLivre = aux->tamanho - p1->tam;
		aux->processo = p1;
		aux->tamanho = p1->tam;
		if(tamLivre > 0){
			//Nesse caso é necessário avaliar se o anterior e o proximo são áreas livres ou se será necessário criar uma
			if(aux->prox == NULL){
				//Caso for inserir na ultima posição
				MEM *livre = criaMemoria();
				livre->posicao = aux->posicao + aux->tamanho;
				livre->tamanho = tamLivre;
				livre->prox = NULL;
				aux->prox = livre;
			}
			else if(aux->prox->processo == NULL){
				printf("xd");
				//O próximo é uma área livre
				aux->prox->posicao -= tamLivre;
				aux->prox->tamanho += tamLivre;
			}
			else{
				//O proximo é um processo, portanto é necessário criar uma área livre
				MEM *livre = criaMemoria();		//Cria o espaço livre
				MEM *proxProc = aux->prox;		//Guarda a posição do proximo processo
				livre->posicao = aux->posicao + aux->tamanho; //A posição da área livre
				livre->tamanho = tamLivre;		//Tamanho da Área livre
				aux->prox = livre;				//Liga o processo inserido na area livre
				livre->prox = proxProc;			//Liga a area livre no proximo processo
			}
		}
		printf("O Processo %d (Tamanho-> %d) foi inserido na memória.\n",p1->id,p1->tam);
		*inseriu = 1;
		return memoria;
	}
	else{
		printf("Impossível inserir o Processo %d na Memória.\n",p1->id);
		*inseriu = 0;
		return memoria;
	}
}

void removeMemoria(MEM *memoria, PROCESSO *p1){
	//Função que remove um processo da memória

	int achou = 0;
	if(memoria->processo != NULL){
		if(memoria->processo->id == p1->id){
			//O primeiro já é o processo
			MEM *proximo = memoria->prox;
			if(proximo->processo == NULL){
				//O segundo é uma área livre
				memoria->tamanho = memoria->tamanho + proximo->tamanho;
				memoria->processo = NULL;
				memoria->prox = proximo->prox;
			}
			else{
				//O segundo é um processo
				memoria->processo = NULL;
			}
			achou = 1;
		}
	}
	
	MEM *aux = memoria;
	MEM *ant = aux;
	while(aux != NULL && achou == 0){
		if(aux->processo != NULL){
			//Verifica se existe um processo
			if(aux->processo->id == p1->id){
				MEM *proximo = aux->prox; //Proximo processo da sequencia
				achou = 1;
				if(proximo != NULL){
					//Caso o proximo não for o fim 
					if(ant->processo == NULL && proximo->processo == NULL){
						//Caso antes e depois sejam areas livres
						ant->tamanho = ant->tamanho + aux->tamanho + proximo->tamanho;
						ant->prox = proximo->prox;
					}
					else if(ant->processo == NULL){
						//Caso somente o anterior for uma area livre
						ant->tamanho = ant->tamanho + aux->tamanho;
						ant->prox = aux->prox;
					}
					else if(proximo->processo == NULL){
						//Caso somente o proximo for uma area livre
						proximo->tamanho = proximo->tamanho + aux->tamanho;
						proximo->posicao = aux->posicao;
						ant->prox = proximo;	
					}
					else{
						//Caso onde o anterior e o proximo são processos
						aux->processo = NULL;
					}
				}
				else{
					//Se o processo for o ultimo
					if(ant->processo == NULL){
						//Caso o penultimo for area livre
						ant->tamanho = ant->tamanho + aux->tamanho;
						ant->prox = NULL;
					}
					else{
						//Caso o penultimo for processo
						aux->processo = NULL;
					}
				}
			}
			else{
				achou = 0;
				ant = aux;
				aux = aux->prox;
			}
		}
		else{
			//Caso o processo for null -> vai pro proximo
			achou = 0;
			ant = aux;
			aux = aux->prox;
		}
	}
	
	if(achou == 1){
		printf("O Processo %d foi removido da memória.\n",p1->id);
	}
	else{
		printf("O Processo %d não foi encontrado na memória.\n",p1->id);
	}
}

int tamanhoMem(MEM *memoria){
	//Função que retorna o tamanho da memória
	
	MEM *aux = memoria;
	int cont = 0;
	while(aux != NULL){
		cont++;
		aux = aux->prox;
	}
	return cont;
}

void procuraMemoria(MEM *memoria, int numprocesso){
	//Função que procura um processo na memória e mostra seus atributos
	
	MEM *aux = memoria;
	int achou = 0;
	while(aux != NULL && achou == 0){
		PROCESSO *p = aux->processo;
		if(p != NULL){
			if(p->id == numprocesso){
				achou = 1;
				//Achou o processo na memória
				printf("\nPROCESSO NÚMERO %d\n",numprocesso);
				printf("Text: %s\n",p->text);
				printf("Data: %s\n",p->data);
				printf("Tamanho do Processo: %d\n\n",p->tam);
					printf("DADOS DA PCB\n");
					printf("Tempo de CPU: %f\nQuantum: %d\n\n",p->pcb.tempoCPU,p->pcb.contador);
			}	
		}
		if(achou == 0) aux = aux->prox;
	}
	if(aux == NULL){
		//Não está na memória
		printf("O processo não foi encontrado na memória.\n\n");
	}
}


void mostraMemoria(MEM *memoria){
	//Função que formata a memória e mostra em blocos
	
	MEM *aux = memoria;
	int i;
	int tam = tamanhoMem(memoria);
	for(i=0;i<tam;i++){
		printf(" |------------|   "); //1a linha da box
	}
	printf("\n"); //pula
	
	aux = memoria;
	while(aux != NULL){
		if(aux->processo != NULL){
			PROCESSO *processo = aux->processo;
			if(processo->id < 100 && processo->id >= 10){
				printf(" |PROCESSO %d |   ",processo->id); //segunda linha
			}
			else if(processo->id < 10){
				printf(" |PROCESSO %d  |   ",processo->id); //segunda linha
			}
			else printf(" |PROCESSO %d|   ",processo->id); //segunda linha		
		}
		else{
			printf(" | AREA LIVRE |   "); //terceira linha
		}
	aux = aux->prox;
	}
	printf("\n"); //pula
	
	aux = memoria;
	while(aux != NULL){
			if(aux->posicao < 100 && aux->posicao >= 10){
				printf(" |     %d     | ->",aux->posicao); //terceira linha
			}
			else if(aux->posicao < 10){
				printf(" |     %d      | ->",aux->posicao); //terceira linha
			}
			else printf(" |    %d     | ->",aux->posicao); //terceira linha
			
		if(aux->prox == NULL) printf(" NULL");
			aux = aux->prox;
	}
	
	printf("\n"); //pula
	
	aux = memoria;
	while(aux != NULL){
			if(aux->tamanho < 100 && aux->tamanho >= 10){
				printf(" | Tamanho %d |   ",aux->tamanho); //quarta linha
			}
			else if(aux->tamanho < 10){
				printf(" | Tamanho %d  |   ",aux->tamanho); //quarta linha
			}
			else printf(" | Tamanho %d|   ",aux->tamanho); //quarta	linha
			aux = aux->prox;
	}
	
	printf("\n"); //pula

	
	for(i=0;i<tam;i++){
		printf(" |------------|   "); //quinta linha da box
	}
}

FILA *criaFila(){
	//Função que cria a fila
	FILA *fila = (FILA*)malloc(sizeof(FILA));
		fila->ini = NULL;
		fila->fim = NULL;
	return fila;
}

int filaVazia(FILA *fila){
	//Função que verifica se uma fila é vazia
	if(fila->ini == NULL) return 1;
	else return 0;
}

void insereFila(FILA *fila, PROCESSO processo){
	//Função que insere um processo no fim da fila
	NODE *novo = (NODE*)malloc(sizeof(NODE));
	novo->processo = processo;
	novo->prox = NULL;
	if(filaVazia(fila)){
		fila->fim = novo;
		fila->ini = novo;
	}
	else{
		fila->fim->prox = novo;
		fila->fim = novo;
	}
}


int tamanhoFila(FILA *fila){
    //Fila vazia
    if(fila->ini == NULL && fila->fim == NULL)
        return 0;
    int i;
    NODE *no = fila->ini;
    for (i = 1; no->prox != NULL; i++)
        no = no->prox;
    return i;
}

PROCESSO removeFila(FILA *fila){
	//Função que remove o primeiro elemento da fila
		NODE *aux = fila->ini;
		fila->ini = aux->prox;
		PROCESSO proc = aux->processo;
		free(aux);
		if(fila->ini == NULL) fila->fim = NULL;
		return proc;
}

void mostraFila(FILA *fila, int flag){
	//Função que mostra uma fila em forma de blocos
	
	NODE *node = fila->ini;
	int tam = tamanhoFila(fila);
	int i;
	for(i=0;i<tam;i++){
		printf(" |------------|   "); //1a linha da box
	}
	printf("\n"); //pula
	
	
	for(i=0;i<tam;i++){
		printf(" |            |   "); //2a linha da box
	}
	printf("\n"); //pula
	
	
	while(node != NULL){
	PROCESSO aux = node->processo;
		if(aux.id < 100 && aux.id >= 10){
			printf(" |Processo %d | ->",aux.id); //terceira linha
		}
		else if(aux.id < 10){
			printf(" |Processo %d  | ->",aux.id); //teceira linha
		}
		else printf(" |Processo %d| ->",aux.id); //terceira linha		
	node = node->prox;
		if(node == NULL) printf(" NULL");
	}
	
	printf("\n"); //pula
	
	if(flag == 2){
		NODE *node = fila->ini;
		while(node != NULL){
			PROCESSO aux = node->processo;
			if(aux.pcb.contador < 10) printf(" | Quantum %d  |   ",aux.pcb.contador);
			else printf(" | Quantum %d |   ",aux.pcb.contador);
			node = node->prox;
		}
	}
	
	else if(flag == 1 || flag == 3){
		for(i=0;i<tam;i++){
			printf(" |            |   "); //quarta linha
		}
	}
	printf("\n"); //pula

	
	for(i=0;i<tam;i++){
		printf(" |------------|   ");
	}
}

PROCESSO *criaProcesso(int *idDisponivel){
	//Função que gera um processo
	
	PROCESSO *novo = (PROCESSO*)malloc(sizeof(PROCESSO));
	int disponivel = 0;
	
	//Verifica se pode criar processo com o id gerado
	int cont = 0;
	do{
		novo->id = randomInteger(0,999);
		if(idDisponivel[novo->id] == 1){ 
			disponivel = 1;
			idDisponivel[novo->id] = 0;
		}
		else{
		disponivel = 0;
		cont++;
		}
	}while(disponivel == 0 && cont < 1000);
	
	if(cont == 1000) return NULL; //Caso for 1000 significa que não existe mais id disponivel
	
	//Gera processo e tamanho
	printf("\nPROCESSO %d GERADO\n",novo->id);
	novo->tam = rand()%120+8;
	int i;
	
	//Gera o text
	printf("Text do Processo:\n");
	for(i = 0 ; i < 20 ; i++){
		char letra = randomInteger(33,122);
		novo->text[i] = letra;
		printf("%c",novo->text[i]);
	}

	//Gera os dados
	printf("\nDados do Processo:\n");
	for(i = 0 ; i < 20 ; i++){
		char dado = randomInteger(33,64);
		novo->data[i] = dado;
		printf("%c",novo->data[i]);
	}
	printf("\n");
	return novo;
}

void tempo(double tempo){
	//Função que dá um "intervalo" de x segundos no programa (facilitar a leitura e entendimento)
	
	clock_t inicio = clock();
	double valor = 0;
	while(valor < tempo){
		clock_t fim = clock() - inicio;
		valor = (double)fim/(double)CLOCKS_PER_SEC;
	}
}

void barraProgresso(double tempo2){
	//Função que cria uma "barra de progresso" de x segundos (visual)
	
	int i;
	int cont = 0;
	for(i = 0; i< 30 ;i++){
	double valor = 0;
	int fim = 0;
	clock_t inicio = clock();
		while(valor < (double)tempo2/(double)30){
			clock_t fim = clock() - inicio;
			valor = (double)fim/(double)CLOCKS_PER_SEC;	
		}
		system("cls");
		if(cont%4 == 0)printf("Carregando");
		if(cont%4 == 1)printf("Carregando.");
		if(cont%4 == 2)printf("Carregando..");
		if(cont%4 == 3)printf("Carregando...");
		cont++;
	}
	system("cls");
	printf("--> Carregado com Sucesso <--\n");
	printf("A simulação irá começar. Para finalizar aperte ESC.\n");
	tempo(3);
}

void mostraFilas(MEM *memoria, FILA *pronto, FILA *dispositivo){
	//Função que mostra as filas e a memória 
	
	system("cls");
		//Mostra as Filas
		printf("|============================================= Memória ==============================================|\n");
		mostraMemoria(memoria);
		printf("\n\n");
		printf("|========================================== Fila de Pronto ==========================================|\n");
		mostraFila(pronto,2);
		printf("\n\n");
		printf("|======================================== Fila de Dispositivo =======================================|\n");
		mostraFila(dispositivo,3);
		printf("\n\n");
}


int randomInteger (int low, int high){ 
	//Função que gera numeros aleatórios em um intervalo
    int k;
    double d;
    d = (double) rand () / ((double) RAND_MAX + 1);
    k = d * (high - low + 1);
    return low + k;
}
