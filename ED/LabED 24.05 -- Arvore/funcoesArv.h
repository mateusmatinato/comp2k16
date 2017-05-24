struct arv{
	int info;
	struct arv* esq;
	struct arv* dir;
};
typedef struct arv Arv;

Arv* cria_arv_vazia(){
	return NULL;
}

int esta_vazia(Arv* a){
	return a==NULL;
}

Arv* cria_arv(char c, Arv* sae, Arv* sad){
	Arv* p = (Arv*)malloc(sizeof(Arv));
	p->info = c;
	p->esq = sae;
	p->dir = sad;
	
	return p;
}

void imprime_arvSimetrica(Arv *a){
	if(!esta_vazia(a)){
	    imprime_arvSimetrica(a->esq);
		printf("%d ",a->info);
		imprime_arvSimetrica(a->dir);
	}
}

void imprime_arvPreOrdem(Arv *a){
	if(!esta_vazia(a)){
		printf("%d ",a->info);
	    imprime_arvPreOrdem(a->esq);
		imprime_arvPreOrdem(a->dir);
	}
}

void imprime_arvPosOrdem(Arv *a){
	if(!esta_vazia(a)){
	    imprime_arvPosOrdem(a->esq);
		imprime_arvPosOrdem(a->dir);
		printf("%d ",a->info);
	}
}

Arv* libera_arv(Arv *a){
	if(!esta_vazia(a)){
		libera_arv(a->esq);
		libera_arv(a->dir);
		free(a);
	}
	return NULL;
}

int pertence(Arv* a, int c){
	if(esta_vazia(a)){
		return 0;
	}
	else{
		return a->info == c || pertence(a->esq,c) || pertence(a->dir,c);
	}
}
