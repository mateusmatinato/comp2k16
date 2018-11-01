/************************************************
*  	Trabalho de Organização de Computadores		*
*************************************************
*   Professor: Guilherme Freitas                *
*************************************************
*	* Integrantes:		 *||* Data de Entrega:	*
*	*	Mateus Matinato	 *||*	 04/12/2017     *
*	*	João Marcos Rosa *||* 					*
*	*	Jonatan Rodrigues*||* Data de Edição:   *
*	*	Vinicius Freitas *||* 27/11 - 16:45  	*
*************************************************
* Opções do Programa:
* 	1) Escrever Programa: O usuário pode entrar *
* 	com uma sequência de comandos que serão sal-*
*	vos na memória, os comandos devem respeitar *
*	o formato exigido:							*
*		(3 bits -> opcode | 7 bits -> endereço)	*
*												*
*	2) Executar Programa: Executa todas as ins- *
*	truções salvas na memória. Devem ser execu-	*
*	tadas na ordem correta e realizando as ope- *
*	rações de acordo com o indicado				*
*												*
*	3) Exibir Endereços de Memória: Exibir as   *
*	palavras salvas na memória entre um endereço*
*	A e B. 										*
*												*
*	4) Exibir Processador: Exibe na tela o con- *
*	teúdo atual do PC, IR, MAR e MBR			*
************************************************/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <locale.h>

typedef struct word{
	char *word[10];
}WORD;

void formata(char *instrucao, int *opcode1, int *address1, int flag){
	int i;
	if(flag == 0){
		//Se a flag for 0 é uma instrução
		char *opcode = (char*)malloc(3*sizeof(char));
		char *address = (char*)malloc(7*sizeof(char));
		int j;		
		j = 0;
		
		
		//Separa a instrução em OPCODE 
		for(i=0;i<3;i++){
			opcode[j] = instrucao[i];
			j++;
		}
	  
	  //Separa a instrução em ADDRESS
		j = 0;
		for(i=3;i<10;i++){
			address[j] = instrucao[i];
			j++;
		}
			
		//converte opcode pra decimal: 
		int valorOpcode = 0;
		for(i = 3; i>0; i--){
	  		valorOpcode += (opcode[i-1] - '0') * pow(2, 3 - i);  
	 	}
	 	
	 	//Converte word pra decimal
		int valorAddress = 0;
		for(i = 7; i>0; i--){
	  		valorAddress += (address[i-1] - '0') * pow(2, 7 - i);  
	 	}
	 	
	 	*opcode1 = valorOpcode;
	 	*address1 = valorAddress;
	}
	else if(flag == 1){
		//É um dado, portanto separa em sinal e valor
		
		char *sinal = (char*)malloc(1*sizeof(char));
		char *valor = (char*)malloc(9*sizeof(char));
		int j;		
		j = 0;
		
		//Separa a instrução em SINAL
		for(i=0;i<1;i++){
			sinal[j] = instrucao[i];
			j++;
		}
	  
	  //Separa a instrução em VALOR
		j = 0;
		for(i=1;i<10;i++){
			valor[j] = instrucao[i];
			j++;
		}
		
		//converte opcode pra decimal: 
		int valorSinal = 0;
		for(i = 1; i>0; i--){
	  		valorSinal += (sinal[i-1] - '0') * pow(2, 1 - i);  
	 	}
	 	
	 	//Converte word pra decimal
		int valor1 = 0;
		for(i = 9; i>0; i--){
	  		valor1 += (valor[i-1] - '0') * pow(2, 9 - i);  
	 	}
	 	
	 	*opcode1 = valorSinal;
	 	*address1 = valor1;	
		
	}
		
}

void main(){
	setlocale(LC_ALL,"Portuguese");
	system("mode con:cols=100 lines=80");
	int mar = -1;
	int mbr = 0;
	WORD *memoria[128]; 		//Memória tem 128 endereços -> 2^7 sendo que os primeiros 64 são para instruções e os outros 64 são para dados
	char ir[10];
	strcpy(ir,"XXXXXXXXXX");
	
	int i;
	for(i=0;i<128;i++){
		memoria[i] = (WORD*)malloc(sizeof(WORD));
		strcpy(memoria[i]->word,"XXXXXXXXXX");
	}
		
	int opc = -1;
	int posicaoatual = 0;
	while(opc != 0){
		printf("|============================|\n");
		printf("|   Trabalho de Organização  |\n");
		printf("|----------------------------|\n");
		printf("|   Escrever Programa    (1) |\n");
		printf("|   Executar Programa    (2) |\n");
		printf("|   Exibir Endereços     (3) |\n");
		printf("|   Exibir Processador   (4) |\n");
		printf("|   Sair                 (0) |\n");
		printf("|============================|\n");
		printf("-> ");
		scanf("%d",&opc);
		switch(opc){
			case 1:{
				system("cls");
				//Escrever programa
				posicaoatual = 0;
				int *opcode , *address;
				int cont = 0;
				printf("\n");
				printf("----------------------------------------------------\n");
				printf("------>   INSIRA AS INSTRUÇÕES DO PROGRAMA   <------\n");
				printf("------> 3 bits: opcode | 7 bits -> endereço  <------\n");
				printf("----------------------------------------------------\n");
				int operacao;
				do{
					char instrucao[10];
					printf("------> [%d]: ",cont);
					scanf("%s",instrucao);
					formata(instrucao,&opcode,&address,0);		//Essa função serve pra verificar se a instrução inserida é valida
					operacao = opcode;
					if(operacao <=7 && operacao >0){
						strcpy(memoria[posicaoatual]->word,instrucao);
						posicaoatual++;
						cont++;
					}
					else printf("------> Operação inválida. Insira outra instrução.\n");
				}while(operacao<7 && posicaoatual < 64);
				printf("----------------------------------------------------\n");
				printf("Você escreveu um programa com %d instruções.\n",cont);
				printf("\n\n");
				break;
			}
			case 2:{
				system("cls");
				//Executar programa
				posicaoatual = 0; //Começa a ler o programa da posição zero
				int operacao;
				int cont = 0;
				do{
					int *opcode, *address;
					int endereco;
					//Começa a executar
					char *instrucao = memoria[posicaoatual]->word;
					formata(instrucao,&opcode,&address,0);		//Chama a função que modifica o opcode e address por referencia
					strcpy(ir,memoria[posicaoatual]->word); 	//IR recebe a palavra que foi lida
					operacao = opcode;
					endereco = address;
					if(operacao != 7 ) mar = endereco; 			//MAR recebe o endereço que está sendo lido caso não for a ultima instrução
					printf("%d\n",mar);
					switch(operacao){
						//Todos os opcodes possiveis
						case 1:{
							//Lê o valor do endereco e salva no mbr
							int *sinal;
							int *valor;
							formata(memoria[endereco]->word,&sinal,&valor,1);		//Passando a flag 1 ele formata como dado
							int aux = valor;
							if(sinal==0) mbr = aux;		//Caso sinal for 1 ele é negativo
							else mbr = (aux)*(-1);		//Caso for 0 ele é positivo
							break;
						}
						case 2:{
							//Salva o valor contido no MBR no endereço
							char *bin = (char*)malloc(9*sizeof(char));
							itoa(abs(mbr),bin,2);
							int tam = strlen(bin);
							char *zero;
							for(i = tam;i<10;i++){
								zero = (char*)malloc(10*sizeof(char));
								zero[0] = '0';
								strcat(zero,bin);
								strcpy(bin,zero);
							}
							if(mbr < 0) bin[0] = '1';
							else bin[0] = '0';
							
							strcpy(memoria[endereco]->word,bin); //Copia pro endereço o numero do MBR convertido
							break;
						}
						case 3:{
							//Lê o valor contido no endereço X e soma ao valor contido no mbr. A soma é armazenada no mbr
							int *sinal;
							int *valor;
							formata(memoria[endereco]->word,&sinal,&valor,1);		//Passando a flag 1 ele formata como dado
							int aux = valor;
							if(sinal == 1) aux = (aux)*(-1);
							printf("%d\n",aux);
							mbr += aux;
							break;
						}
						case 4:{
							//Lê o valor contido no endereço X e subtrai do valor contido no MBR (MBR - X). A subtração é armazenada no mbr
							int *sinal;
							int *valor;
							formata(memoria[endereco]->word,&sinal,&valor,1);		//Passando a flag 1 ele formata como dado
							int aux = valor;
							if(sinal == 1) aux = (aux)*(-1);
							mbr -= aux;
							break;
						}
						case 5:{
							//Move o contador de programa para a linha de endereço X
							posicaoatual = endereco;
							break;
						}
						case 6:{
							//Escreve o valor numérico Y no MBR
							mbr = endereco;
							break;
						}
					}
					if(operacao != 5) posicaoatual++; //Incrementa o contador de programa caso não for um JMP
					cont++;
				}while(operacao < 7);
				if(operacao == 7)printf("\nPrograma lido com sucesso.\nForam lidas %d instruções!\n\n",cont);
				break;
			}
			case 3:{
				system("cls");
				//Exibir Endereços
				int i;
				printf("\n");
				printf("|===============================================================================================|\n");
				printf("|                   ENDEREÇOS: 0 A 63 -> INTRUÇÕES    |     64 A 127 -> DADOS                   |\n");
				printf("|===============================================================================================|\n");
				printf("|                             ENDEREÇOS RESERVADOS PARA INSTRUÇÕES                              |\n");
				printf("|===============================================================================================|\n");
				for(i=0;i<128;i+=4){
					if(i < 10){
						if(i < 8) printf("| [0%d]->  %s\t| [0%d]->  %s\t| [0%d]->  %s\t| [0%d]->  %s\t|\n",i,memoria[i]->word,i+1,memoria[i+1]->word,i+2,memoria[i+2]->word,i+3,memoria[i+3]->word);
						else printf("| [0%d]->  %s\t| [0%d]->  %s\t| [%d]->  %s\t| [%d]->  %s\t|\n",i,memoria[i]->word,i+1,memoria[i+1]->word,i+2,memoria[i+2]->word,i+3,memoria[i+3]->word);
					}
					else if(i<100)printf("| [%d]->  %s\t| [%d]->  %s\t| [%d]->  %s\t| [%d]->  %s\t|\n",i,memoria[i]->word,i+1,memoria[i+1]->word,i+2,memoria[i+2]->word,i+3,memoria[i+3]->word);
					else printf("| [%d]-> %s\t| [%d]-> %s\t| [%d]-> %s\t| [%d]-> %s\t|\n",i,memoria[i]->word,i+1,memoria[i+1]->word,i+2,memoria[i+2]->word,i+3,memoria[i+3]->word);
				
					if(i == 60){
						printf("|===============================================================================================|\n");		
						printf("|                               ENDEREÇOS RESERVADOS PARA DADOS                                 |\n");
						printf("|===============================================================================================|\n");
					}
				}
				printf("|===============================================================================================|\n\n");
				break;
			}
			case 4:{
				//Exibir Processador
				system("cls");
				printf("\n");
				printf(" \t PROCESSADOR   \n");
				printf(" \tPC: %d\n",posicaoatual);
				printf(" \tIR: %s\n",ir);
				printf(" \tMAR: %d\n",mar);
				printf(" \tMBR: %d\n",mbr);
				printf("\n\n");
				break;
			}
		}
	}
	system("cls");
	printf("\n\n\n\t\t\tFim do Programa!\t\t\t\n\n\n");
	
}


