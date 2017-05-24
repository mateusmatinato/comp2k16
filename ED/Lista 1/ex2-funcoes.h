//Implementação Lista Duplamente Encadeada
//TAD
struct NODP {
	//TAD Lista Duplamente
  int  info;
  struct NODP *prox;
  struct NODP *ant;
};
typedef struct NODP NODP;

//Cria Lista Dupla
NODP *criaListaDupla(){
	NODP *lista;
	lista = malloc(sizeof(NODP));
	lista = NULL;
	return lista;
}

//Imprime Lista Dupla
void imprimeDupla(NODP *lista){
	NODP *aux = lista;
	int i = 0;
	if(aux==NULL) printf("Lista vazia.\n");
	while(aux!=NULL){
		printf("Elemento [%d] = %d\n",i+1,aux->info);
		aux = aux->prox;
		i++;
	}
}
	
//Insere no Inicio da Lista Duplamente Encadeada
NODP *insereInicioDupla(NODP *lista, int valor){
	NODP *novo;
	novo = malloc(sizeof(NODP));
	novo->info = valor;
	if(lista == NULL){ //Caso a lista esteja vazia
		novo->ant = NULL;
		novo->prox = NULL;
		lista = novo;
		return lista;
	}
	lista->ant = novo;
	novo->ant = NULL;
	novo->prox = lista;
	lista = novo;
	return lista;
}

//Insere no Fim da Lista Duplamente Encadeada
NODP *insereFimDupla(NODP *lista, int valor){
	NODP *novo, *aux;
	aux = lista;
	novo = malloc(sizeof(NODP));
	novo->info = valor;
	if(aux==NULL){
		novo->ant = NULL;
		novo->prox = NULL;
		lista = novo;
		return lista;
	}
	while(aux->prox != NULL){
		aux = aux->prox;
	}
	aux->prox = novo;
	novo->ant = aux;
	novo->prox = NULL;
	return lista;
}

//Insere no Meio da Lista Duplamente Encadeada
NODP *insereMeioDupla(NODP *lista, int valor){
	NODP *aux = lista;
	NODP *novo = (NODP*)malloc(sizeof(NODP));
	novo->info = valor;
	
	if(aux==NULL){ //Lista com nenhum elemento
		novo->prox = NULL;
		novo->ant = NULL;
		lista = novo;
		return lista;
	}
	
	else if(aux->prox == NULL && aux->info < valor){ //Lista com 1 elemento
		novo->prox = NULL;
		aux->prox = novo;
		novo->ant = aux;
		return lista;
	}
	
	while(aux->prox != NULL && (aux->info) < valor) aux = aux->prox;
		if(aux==lista){ //Caso for o primeiro elemento
			novo->prox = aux;
			aux->ant = novo;
			novo->ant = NULL;
			lista = novo;
			return lista;
		}
		
		else if(aux->prox == NULL && aux->info < valor){ //Caso for inserir após o ultimo
			aux->prox = novo;
			novo->prox = NULL;
			novo->ant = aux;
			return lista;
		}
		//Caso geral 
	NODP *aux2 = aux->ant; 
	aux2->prox = novo;
	novo->ant = aux2;
	novo->prox = aux;
	aux->ant = novo;
	
	return lista;
}

//Remove um Elemento da Lista Duplamente Encadeada
NODP *removeDupla (NODP *lista, int valor){ 
	NODP *aux;
	aux = lista;
	if(lista==NULL){
		printf("Lista vazia.\n");
		return lista;
	}
	
	while(aux->prox != NULL && aux->info != valor){
		//Percorre a lista até encontrar o valor ou até o ultimo elemento
		aux = aux->prox;
	}
	if(aux->info != valor){ //Caso não remova nenhum
		return lista;
	}
	if(aux->ant == NULL && aux->prox != NULL){ //Caso remova o primeiro em lista com mais de 1 elemento
		aux = aux->prox;
		aux->ant = NULL;
		return aux;
	}
	if(aux->ant == NULL && aux->prox == NULL){ //Caso remova o primeiro em lista com 1 elemento
		lista = NULL;
		return lista;
	}
	if(aux->ant != NULL && aux->prox == NULL){ //Caso remova o ultimo
		aux = aux->ant;
		aux->prox = NULL;
		return lista;
	}
	 //Caso geral 
	NODP *aux2 = aux->ant;
	aux2->prox = aux->prox;
	aux = aux->prox;
	aux->ant = aux2;
	return lista;
}

//Implementação de Pilha
//TAD Nós
struct node{
	//TAD nós
	int info;
	struct node *prox;
};
typedef struct node NODE;

//TAD Pilha
struct pilha{
	//TAD pilha
	NODE *topo;
};
typedef struct pilha PILHA;

//Cria Pilha
PILHA *criaPilha(){
	//Função para criar pilhas
	PILHA* p = (PILHA*)malloc(sizeof(PILHA));
	p->topo = NULL;	
	return p;
}

//Pilha Vazia
int vaziaPilha(PILHA *pilha){
	//Função que retorna 1 se está vazia 0 caso não
	if(pilha->topo == NULL) return 1;
	else return 0;
}

//Insere na Pilha
void push(PILHA *pilha, int valor){
	//Função que insere na pilha
	NODE *novo = (NODE*)malloc(sizeof(NODE));
	novo->info = valor;
	novo->prox = pilha->topo;
	pilha->topo = novo;
}

//Remove da Pilha
int pop(PILHA *pilha){
	//Função que remove o topo da pilha e retorna o valor removido
	NODE *aux = pilha->topo;
	int valor;
	pilha->topo = aux->prox;     //Muda o valor do topo para o proximo
	valor = aux->info;
	free(aux);
	return valor;
}

//Verifica o topo da pilha
int top(PILHA *pilha){
	//Função que retorna o valor do topo
	return pilha->topo->info;
}

//Destrói a pilha
void destroiPilha(PILHA *pilha){
	//Função para destruir a pilha
	while(vaziaPilha(pilha)!=1){
		pop(pilha);
	}
	free(pilha);
}

//Remove um elemento da pilha
PILHA *removeElementoPilha(PILHA *pilha, int valor){
	//Função para remover um valor da pilha
	int achou = 0; 
	PILHA *aux = criaPilha();
	while(vaziaPilha(pilha)!=1 && achou ==0){		//Enquanto a pilha 1 não for vazia
		if(top(pilha)!=valor){  //Se o valor do topo não for o desejado
			push(aux,pop(pilha)); //Insere na pilha auxiliar o valor do topo e remove da pilha
		}
		else{				//Se o valor do topo for o desejado
			pop(pilha); 	//Remove da pilha
			achou = 1;
		}
	}
	while(vaziaPilha(aux)!=1){	//Enquanto a pilha auxiliar não for vazia
		push(pilha,pop(aux));	//Devolve os valores da pilha auxiliar pra pilha
	}
	return pilha;
}

//Imprime a pilha
void imprimePilha(PILHA *pilha){
	NODE *aux;
	aux = pilha->topo;
	if(!vaziaPilha(pilha)){
		printf("Pilha: \n");
		int i=0;
		while(aux!=NULL){
			printf("%dº elemento: %d\n",i+1,aux->info);
			aux = aux->prox;
			i++;
		}
	}
	else printf("Pilha vazia.\n");
}

//Implementação da Fila
//TAD Fila
struct fila{
	NODE *ini;
	NODE *fim;
};
typedef struct fila FILA;

//Cria Fila
FILA *criaFila(){
	FILA *fila = (FILA*)malloc(sizeof(FILA));
	fila->ini = NULL;
	fila->fim = NULL;
	return fila;
}

//Fila Vazia
int filaVazia(FILA *fila){
	if(fila->ini == NULL) return 1;
	else return 0;
}

//Insere um elemento na fila
void insereFila(FILA *fila, int valor){
	NODE *novo = (NODE*)malloc(sizeof(NODE));
	novo->info = valor;
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

//Remove um elemento da fila
int removeFila(FILA *fila){
	NODE *aux = fila->ini;
	fila->ini = aux->prox;
	int valor = aux->info;
	free(aux);
	if(fila->ini == NULL) fila->fim = NULL;
	return valor;
}

//Imprime a fila
void imprimeFila(FILA *fila){
	NODE *aux = fila->ini;
	int i=0;
	while(aux != NULL){
		printf("%dº elemento: %d\n",i+1,aux->info);
		aux = aux->prox;
		i++;
	}
}

//Verifica a cabeça da fila
int cabecaFila(FILA *fila){
	return fila->ini->info;
}

//Implementação de Lista Circular
//Cria Lista Circular
NODE *criaListaCircular(){
	NODE *lista;
	lista = malloc(sizeof(NODE));
	lista = NULL;
	return lista;
}

//Imprime a Lista Circular
void imprimeCircular(NODE *lista){
	NODE *aux;
	int v = 0;
	int i =0;
	if(lista==NULL){
	 printf("Lista vazia.\n");
	 v = 1;
	}
	else if(v==0){
		printf("[%d]-> %d\n",i+1,lista->info);
		i++;
		aux = lista->prox;
		while(aux != lista){
			if(aux!=NULL){
				printf("[%d]-> %d\n",i+1,aux->info);
				aux = aux->prox;
				i++;
			}
		}		
	}
}

//Remove um elemento da lista circular
NODE *removeCircular(NODE *lista, int valor){ 
	NODE *aux, *ant;
	if(lista == NULL){  //Verifica se a lista está vazia
		printf("Lista vazia.\n");
		return NULL;
	}
	ant = lista;
	aux = lista->prox;
	if(lista->prox == lista){ //Caso tenha 1 elemento
		if(lista->info == valor){ 
		 	return NULL;
		 }	
		else return lista;
	}
	
	if(ant->info == valor){ //Caso o primeiro elemento for o desejado pra remoção
		while(aux->prox != ant) aux = aux->prox;  //Percorre o aux até o ultimo(antes de ligar no inicio)
		aux->prox = ant->prox;
		free(ant);
		aux = aux->prox;
		return aux;
	}
	while(aux->info != valor && aux->prox!=lista){ //Percorre a lista
		aux = aux->prox;
		ant = ant->prox;
	}
	//Caso geral
	ant->prox = aux->prox; 
	free(aux);
	return lista;
}

//Insere no inicio da lista circular
NODE *insereInicioCircular (NODE *lista, int valor){
	NODE *aux, *novo;
	novo = malloc(sizeof(NODE));
	novo->info = valor;
	aux = lista;
	if(lista == NULL){
		//Se a lista for vazia
		novo->prox = novo;
		lista = novo;
		return lista;
	}
	else{
		//Se não for vazia
		novo->prox = lista; //Liga o novo na lista
		while(aux->prox != lista) aux = aux->prox; //Procura o ultimo elemento (o que liga na ponta)
		aux->prox = novo;
		lista = novo;
		return lista;
	}
}

//Insere no final da lista circular
NODE  *insereFinalCircular(NODE *lista, int valor){
	NODE *aux, *novo;
	novo = malloc(sizeof(NODE));
	novo->info = valor;
	aux = lista;
	if(lista == NULL){
		novo->prox = novo;
		lista = novo;
		return lista;
	}
	while(aux->prox!=lista){
		aux = aux->prox;
	}
	
	aux->prox = novo;
	novo->prox = lista;
	return lista;
}

void destroiListaCircular(NODE *lista){
	NODE *aux;
	while(lista != NULL){
		aux = lista;
		lista = lista->prox;
		free(aux);
	}
}
