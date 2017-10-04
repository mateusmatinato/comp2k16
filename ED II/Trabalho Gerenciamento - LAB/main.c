/************************************************************************|
|  Simulação de Escalonamento de Processos com Gerenciamento de Memória  |
|   |---------------------------|                                        |
|   | Integrantes:		|    		 	                 |
|   | João Marcos Rosa  	|			                 |
|   | Jonatan Rodrigues	        |			                 |
|   | Mateus Matinato           |			                 |
|   | Vinicius de Freitas	|			          	 |
|   |---------------------------|					 |
|     Disciplina: laboratório de Estrutura de Dados II                   |
|     Professor: Leandro Neves			Data: 12/09/2017	 |
|************************************************************************/


#include <locale.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>		//Usada para gerar numeros aleatórios e tempos
#include <windows.h> 	//Usada para configurar tamanho da janela
#include <conio.h>		//Usada para kbhit
#include "funcoesGeren.h"

#define quantum 1	//Define o quantum do processador
#define velocidade 2.5 //Define a velocidade do programa (0 -> muito rapido / 5 -> muito devagar)


void main(){
	setlocale(LC_ALL,"Portuguese");
	system("mode con:cols=210 lines=70");
	int idDisponivel[1000];
	int i;
	for(i=0;i<1000;i++){
		idDisponivel[i] = 1;
	}
	
	MEM *memoria = criaMemoria();
	memoria->tamanho = 512;
	memoria->posicao = 0;
	
	srand(time(NULL));
	//Cria as filas necessárias
	FILA *jobs = criaFila();
	FILA *pronto = criaFila();
	FILA *dispositivo = criaFila();
	FILA *tabela = criaFila();
	
	
	int flag = 0;
	int contadorprocessos = 0;
	
	//Inicio do Programa
	printf("|============================================|\n");
	printf("|   Simulação de Escalonamento de Processos  |\n");
	printf("|--------------------------------------------|\n");
	printf("|       Simular Manualmente        (1)       |\n");
	printf("|       Simular Automaticamente    (2)       |\n");
	printf("|       Simular Pressionando Enter (3)       |\n");
	printf("|============================================|\n");
	printf("Digite sua opção -> ");
	int opc;
	scanf("%d",&opc);
	system("cls");
	
	//Loop que gerará aleatoriedades
	int aleatorio = -1;
	int fez;
	int tecla = 13;
	while(opc == 1 || (opc == 2 && !_kbhit()) || (opc == 3 && tecla == 13) ){
		aleatorio = randomInteger(1,110); 
		fez = 1;
		if(opc == 1){
			printf("\n|=================================================|\n");
			printf("|                     MENU MANUAL                 |\n");
			printf("|-------------------------------------------------|\n");
			printf("|    Gerar um Processo Aleatório              (1) |\n");
			printf("|    Processar com Round Robin (1 ciclo)      (2) |\n");
			printf("|    Liberar um Processo em Dispositivo       (3) |\n");
			printf("|    Informações Sobre Processo/PCB           (4) |\n");
			printf("|    Sair                                     (0) |\n");
			printf("|=================================================|\n");
			printf("Digite sua opção -> ");
			int opc2;
			scanf("%d",&opc2);
			switch(opc2){
				case 1: aleatorio = 20; break;
				case 2:{
					if(filaVazia(pronto)){
						printf("--> Não existem processos na fila de Pronto <--\n");
						tempo(velocidade);
					}
					aleatorio = 60;	
					break;
				}
				case 3:{
					if(filaVazia(dispositivo)){
						printf("--> Não existem processos na fila de Dispositivo <--\n");
						tempo(velocidade);
					}
					aleatorio = 100;
					break;
				}
				case 4:{
					printf("Digite o número do processo: ");
					int numprocesso;
					scanf("%d",&numprocesso);
					procuraMemoria(memoria,numprocesso);
					char continuar = 13;
					do{
						fflush(stdin);
						printf("\nAperte enter para continuar...\n");
						continuar = getch();
					}while(continuar != 13);
					
					aleatorio = 150;
					break;
				}
				case 0:{
					//Caso for 0 não entra em nenhum if e sai do loop
					aleatorio = 150;
					opc = 0;
					break;
				}
			}	
		}
		
		else if((opc == 2 || opc == 3) && flag == 0){
			barraProgresso(1);
			flag = 1;
			if(opc == 3){
				printf("Aperte ENTER para gerar aleatoriedades...");
			}
		}
		
		mostraFilas(memoria,pronto,dispositivo);
		
		
		//Começo dos casos de aleatoriedade
		int inseriu = 0;
		if(aleatorio <= 40){
			//Gera um processo na hora
			PROCESSO *p1 = criaProcesso(idDisponivel);
			if(p1 == NULL){
				printf("Número máximo de processos atingido.\n");
				tempo(2);
				aleatorio = 60;
				break;
			}
			clock_t inicio = clock();
			double valor = 0;
			memoria = insereMemoria(memoria,p1,&inseriu);
			tempo(velocidade);			
			
			if(inseriu){
				//Caso tiver sido inserido na memória -> passa pra fila de pronto
				p1->pcb.id = p1->id;
				p1->pcb.estado = 0;
				p1->pcb.contador = rand()%10+1;
				p1->pcb.tempoCPU = ((float)rand()/(float)RAND_MAX) * (rand()%1000+20); 
				
				insereFila(tabela,*p1); //Insere na tabela de PCB o processo
				insereFila(pronto,*p1); //Insere na fila de pronto o processo
				printf("\nO Processo %d (Quantum -> %d)foi inserido na fila pronto.\n",p1->id, p1->pcb.contador);
				tempo(velocidade);
				mostraFilas(memoria,pronto,dispositivo);
			}
			else{
				printf("\nO Processo %d não foi inserido na fila de pronto por falta de memória.\n",p1->id);
				tempo(velocidade);
			}
			
		}
			
		else if(aleatorio > 40 && aleatorio <= 80){
			//Faz o escalonamento e diminui os quantuns
			if(filaVazia(pronto)) fez = 0;
			int i;
			int tamanhoPronto = tamanhoFila(pronto);
			for(i = 0; i < tamanhoPronto; i++){
				PROCESSO executando = removeFila(pronto);
				removeFila(tabela);
				
				if(executando.pcb.contador > 0){				//Caso o quantum for positivo, subtrai o quantum do processador
					executando.pcb.estado = 1;  				//Em execução
					printf("\nProcesso %d em execução...\n",executando.id);
					int device = rand()%10+1;
					
					if(device == 1 || device == 2){
						//Caso o inteiro device for 1 ou 2-> necessita de E/S
						executando.pcb.estado = 3;
						insereFila(dispositivo,executando);
						printf("---> O Processo %d foi INTERROMPIDO e movido para a fila de dispositivos <---\n",executando.id);
						tempo(velocidade+1.0);
						mostraFilas(memoria,pronto,dispositivo);
					}
					
					else if(executando.pcb.contador < quantum){		//Isso serve para esperar x segundos de processamento
						tempo(executando.pcb.contador);
						executando.pcb.contador -= quantum;			//Diminui o quantum do processo 
					}
					
					else if(executando.pcb.contador >= quantum){	//isso serve para esperar "quantum" segundos de processamento
						tempo(quantum);
						executando.pcb.contador -= quantum;			//Diminui o quantum do processo 
					}
					
					//Depois de executar e esperar o tempo ele reinsere na fila
					if(executando.pcb.contador > 0 && executando.pcb.estado != 3){ 				//Caso ainda houver quantum
						printf("---> ID: %d foi processado e resta(m) %d segundo(s) de processamento <---\n",executando.id, executando.pcb.contador);
						executando.pcb.estado = 1;
						tempo(velocidade);
						insereFila(pronto,executando);			//Joga o processo pra tabela auxiliar
						mostraFilas(memoria,pronto,dispositivo);
						
					}
					if(executando.pcb.contador <=0 && executando.pcb.estado != 3){				//Caso o quantum for 0 ou negativo, muda o estado para 2 e remove das filas
						executando.pcb.estado = 2;					//Altera estado para finalizado
						printf("---> O Processo %d foi FINALIZADO <---\n",executando.id);
						removeMemoria(memoria,&executando);
						tempo(velocidade);
						mostraFilas(memoria,pronto,dispositivo);
						contadorprocessos++;
					}
				}		
			}
			
		}
		else if(aleatorio > 80 && aleatorio <=110){
			//Retirará um processo da fila de dispositivo
			if(!filaVazia(dispositivo)){
				PROCESSO disp = removeFila(dispositivo);
				insereFila(pronto,disp);
				printf("\n---> O Processo %d foi TRANSFERIDO de Dispositivo para Pronto(Posição %d) <---\n",disp.id,tamanhoFila(pronto));
				tempo(velocidade);	
				mostraFilas(memoria,pronto,dispositivo);	
			}
			else fez = 0;
		}
		
		if(opc == 3 && fez == 1){
			fflush(stdin);
			printf("\nAperte ENTER para continuar ou ESC para sair.\n");
			tecla = getch();
		}
	}
	system("cls");
	printf("\n\n\t\t--> SIMULAÇÃO FINALIZADA <--\n\t\t%d PROCESSOS FINALIZADOS\n",contadorprocessos);
}
 
