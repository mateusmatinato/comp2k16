
#include <stdlib.h>
#include "glut.h"
#include "glu.h"
#include "gl.h"
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <windows.h>

#define NUM_CANOS 3 //numero de canos que são criados previamente pelo sistema

	
//Declaração de Variáveis Globais
int projecao=1; //Variável Lógica para Definir o Tipo de Projeção (Perspectiva ou Ortogonal)
int trocaProjecao = 1; //variável de controle pra saber se vai iniciar em 2D OU PRIMEIRA PESSOA
int alturaCanos[NUM_CANOS]; //vetor que armazena a altura dos canos;
float distanciaCanos[NUM_CANOS]; //vetor que armazena a distancia entre os canos;
int flagCanos[NUM_CANOS]; //vetor que armazena flags para saber se o passaro passou do cano, para incrementar a pontuação
int distanciaMaxima = 0; //distancia que o cano tem que ser posicionado depois que ele sai da tela
FILE *recordFile; //arquivo que armazena o recorde
int record;		//variável que armazena o valor do record
int proxCano = 0; //variável que indica qual o próximo cano o passaro tem pela frente

float velocidadeJogo = 0.4;//velocidade do jogo (velocidade que os canos vem na direção do passaro)

float posicao,velocidade,aceleracao,tempo; //variáveis para fazer o movimento do passaro
int iniciou = 0;	//variável para saber se iniciou o jogo
int gameOver = 0; // variável para saber se deu gameover
int pontos = 0; //variável de pontos do jogador
char pontosString[10];

clock_t start, end;
float t, taxa=0;

//Protótipos das Funções
void Display();
void Mouse(int botao, int estado, int x, int y);
void keyboard (unsigned char key, int x, int y);

float max(float a, float b){
	if(a > b) return a;
	else return b;
}

float min(float a, float b){
	if(a < b) return a;
	else return b;
}

char colisaoCano(float pos, float altura){
	/* 
		Acha o ponto pertencente aos retangulos (os canos) mais proximo do circulo (o passaro) e se a distancia do ponto ao centro
		do circulo for menor que o raio do mesmo temos uma colisão.
	*/
	float DeltaX = 0 - max(pos - 32.0, min(0, pos + 32.0)); //verifica X
	float DeltaHY = posicao - max(altura + (170.0/2), min(posicao, altura + (170.0/2) + 500)); //verifica Y do cano de cima
	float DeltaLY = posicao - min(altura - (170.0/2), max(posicao, altura - (170.0/2) - 500)); //verifica Y do cano de baixo
	return ((DeltaX * DeltaX + DeltaHY * DeltaHY) < (25 * 25)) || ((DeltaX * DeltaX + DeltaLY * DeltaLY) < (25 * 25));
}

int colisaoChao(int posicaoPassaro){
	//função que verifica se o passaro colidiu com o chão
	if(posicaoPassaro <= -225){
		return 1;
	}
	else return 0;
}

int colisaoTeto(int posicaoPassaro){
	//função que verifica se o passaro colidiu com o teto
	if(posicaoPassaro >= 335){
		return 1;
	}
	else return 0;
}

void DesenhaTexto(void *font, char *string){  
	// Escreve texto na tela
	while(*string)
		glutStrokeCharacter(GLUT_STROKE_ROMAN,*string++); 
}

void lerRecord(){
	//lê o record que está gravado no arquivo record.txt
	recordFile = fopen("record.txt","r");
	if(recordFile == NULL){
		record = 0;
		fclose(recordFile);
	}
	else{
		fscanf(recordFile,"%d",&record);
		fclose(recordFile);
	}
}

void salvarRecord(int pontos){
	//salvar o record no arquivo record.txt
	recordFile = fopen("record.txt","w");
	fprintf(recordFile,"%d",pontos);
	fclose(recordFile);
}


void iniciaCanos(){
	//Cria valores iniciais para os canos
	int i,j;
	int distanciaAnterior = 0;
	for(i = 0; i < NUM_CANOS; i++){
		alturaCanos[i] = ((rand()%230)+1);
		if(i == 0){
			distanciaCanos[i] = 200;
		}
		else{
			//gera as distâncias entre um cano e outro
			distanciaCanos[i] = ((rand()%50)+250)+distanciaAnterior;
		}
		distanciaAnterior = distanciaCanos[i];
		
		distanciaMaxima = distanciaAnterior; //a distancia maxima é a distancia do ultimo cano, usada para resetar os canos quando saem da tela
		
		flagCanos[i] = 0; //seta as flags como 0
		
	}
}

void desenhaPassaro(){
	
		//Desenha o passaro
	
	 glPushMatrix();
        glTranslatef(-10, 0, 0);
        glColor3ub(212,192,46);
        glutSolidCube(30);
    glPopMatrix();
    glPushMatrix();
    glTranslatef(0, 0, 0);
    
        glColor3ub(252, 56, 0);
        glutSolidCube(30);
    glPopMatrix();
}

int gerarAlturaCano(){
	//gera uma altura para o cano
	return ((rand()%230)+1);
}

void telaInicial(){
	//Desenha a tela inicial...
	glPushMatrix();
	glColor3f(1,1,1);
	glTranslatef(-45,70,100);
	glScalef(0.4, 0.4, 0.4);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Flappy Bird");
	glPopMatrix();
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(-49,68,100);
	glScalef(0.41, 0.41, 0.41);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Flappy Bird");
	glPopMatrix();
	
	
	glPushMatrix();
	glColor3f(1,1,1);
	glTranslatef(-95,320,100);
	glScalef(0.13, 0.13, 0.13);
	glLineWidth(2.0); 
	if(trocaProjecao == 1){
		//camera 2D
		DesenhaTexto(GLUT_STROKE_ROMAN,"Camera: 2D");
	}
	else{
		DesenhaTexto(GLUT_STROKE_ROMAN,"Camera: Perspectiva");
	}
	
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(-10,40,100);
	glScalef(0.12, 0.12, 0.12);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Para iniciar aperte ESPACO");
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(-30,20,100);
	glScalef(0.12, 0.12, 0.12);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Para alterar a camera aperte C");
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(5,0,100);
	glScalef(0.12, 0.12, 0.12);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Para sair aperte ESC");
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(1,1,1);
	glTranslatef(35,-230,100);
	glScalef(0.18, 0.18, 0.18);
	glLineWidth(2); 
	lerRecord();
	char string[100];
	sprintf(string,"Record: %d",record);
	DesenhaTexto(GLUT_STROKE_ROMAN,string);
	glPopMatrix();
}

void fim(){
	// deve dar gameover, mostrar na tela que acabou e parar o jogo
	if(projecao == 0){
		//quando dá game over muda pra projecao 2D para ver o menu de game over.
		projecao = 1;
	}
	
	//seta as variáveis de controle
	gameOver = 1;
	iniciou = 0;
	
	//escreve as mensagens na tela
	glPushMatrix();
	glColor3f(1,1,1);
	glTranslatef(-75,70,100);
	glScalef(0.4, 0.4, 0.4);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Fim de Jogo");
	glPopMatrix();
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(-80,68,100);
	glScalef(0.41, 0.41, 0.41);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Fim de Jogo");
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(2,40,100);
	glScalef(0.12, 0.12, 0.12);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Para reiniciar aperte R");
	glPopMatrix();
	
	glPushMatrix();
	glColor3f(0,0,0);
	glTranslatef(5,20,100);
	glScalef(0.12, 0.12, 0.12);
	glLineWidth(2); 
	DesenhaTexto(GLUT_STROKE_ROMAN,"Para sair aperte ESC");
	glPopMatrix();
	
	int num = 0;
	//deve verificar se o numero de pontos é maior que o record
	if(pontos >= record){
		//salva o record no arquivo e escreve que bateu o record
		salvarRecord(pontos);
		
		glPushMatrix();
		glTranslatef(15,-200,100);
		glScalef(0.18, 0.18, 0.18);
		glLineWidth(2);
		
		//fica variando a cor em amarelo e branco
		num = rand()%2;
		if(num == 0){
			glColor3f(1,1,1);
		}
		else{
			glColor3f(1,1,0);
		}
		DesenhaTexto(GLUT_STROKE_ROMAN,"Novo record!");
		glPopMatrix();
	}
	// aqui deve mostrar o record embaixo
		glPushMatrix();
		//também varia a cor em amarelo e branco
		if(num == 0){
			glColor3f(1,1,1);
		}
		else{
			glColor3f(1,1,0);
		}
		glTranslatef(35,-230,100);
		glScalef(0.18, 0.18, 0.18);
		glLineWidth(2); 
		lerRecord();
		char string[100];
		sprintf(string,"Record: %d",record);
		DesenhaTexto(GLUT_STROKE_ROMAN,string);
		glPopMatrix();
}

void desenhaCanos(){
	//função que cria os canos e decrementa a posição
   int i;
   for(i = 0; i < NUM_CANOS; i++){
   	//cria NUM_CANOS por vez...
   		glPushMatrix();
   		glColor3ub(115,190,46);
   		
   		//se iniciou for 1 significa que o jogo começou e deve decrementar da distanciaCanos[i] a velocidade do jogo
		if(iniciou == 1) distanciaCanos[i] -= velocidadeJogo; 
		
		if(distanciaCanos[i] < -150){
			//cano saiu da tela, deve ir pro final da tela
			distanciaCanos[i] = distanciaMaxima-100; //coloca o cano depois do ultimo (decrementa 100 para ficar a uma distânia não mt grande)
			//deve gerar uma nova altura pro cano
			alturaCanos[i] = gerarAlturaCano();
			//deve setar a flag do cano como 0 de novo, para poder pontuar
			flagCanos[i] = 0;
		}
		
		//aqui desenha os canos
		glTranslatef(distanciaCanos[i],0,0);
		
			//cano de cima (ponta cabeça)
	   		glPushMatrix();
	            glTranslatef(0, alturaCanos[i]+85, 0);
	            glRotatef(90.0, -1, 0, 0);
	            //cilindro menor
	            gluCylinder(gluNewQuadric(), 32.0f, 32.0f, 10.0, 20, 20);
	            //cilindro maior
	           	gluCylinder(gluNewQuadric(), 26.0f, 26.0f, 500.0, 20, 20);
	    	glPopMatrix();
	
			//cano de baixo
	   		glPushMatrix();
	            glTranslatef(0, alturaCanos[i]-85, 0);
	            glRotatef(90.0, 1, 0, 0);
	            //cilindro menor
	            gluCylinder(gluNewQuadric(), 32.0f, 32.0f, 10.0, 20, 20);
	            //cilindro maior
	           	gluCylinder(gluNewQuadric(), 26.0f, 26.0f, 500.0, 20, 20);
	    	glPopMatrix();

        glPopMatrix();
        


   }
}

void atualizaPontos(){
	int i;
	for(i = 0 ; i < NUM_CANOS ; i++){
		if(distanciaCanos[i] < 0 && flagCanos[i] == 0){
			//quando a distancia for menor que zero, significa que passou do passaro, logo incrementa um ponto e seta a flag como 1
        	pontos++;
        	flagCanos[i] = 1;
		}
	}
			
}

void proximoCano(){
	//Função que atualiza qual é o proximo cano
		if(distanciaCanos[proxCano] < 0){
		//significa que já passou do cano i, então aumenta a variável proximoCano
		if(proxCano == (NUM_CANOS - 1)) proxCano = 0; //se o cano que passou for o último, o próximo volta para 0
		else proxCano+=1;	//incrementa
	}
	
}

void desenhaChao(){
	 //Desenha o chão
    glPushMatrix();
    glColor3ub(155, 235, 85);
        glTranslatef(0, -240, 0);
        glScalef(260.0f, 0.02f, 1.0f);
        glutSolidCube(200);
    glPopMatrix();

    glPushMatrix();
    glColor3ub(220, 218, 147);
        glTranslatef(0, -340, 0);
        glScalef(260.0f, 1.0f, 1.0f);
        glutSolidCube(200);
    glPopMatrix();
}

void escrevePontos(char *texto, int projecao){
		//Escreve pontos na tela, como os pontos ficam em cima da tela nas duas projeções, eles devem ser criados de maneira diferente para cada uma delas
		if(projecao == 1){
			//projecao em 2d
			glPushMatrix();
				glColor3f(1,1,1);
				glTranslatef(70,280,100);
				glScalef(0.4, 0.4, 0.4);
				glLineWidth(2); 
				DesenhaTexto(GLUT_STROKE_ROMAN,texto);
			glPopMatrix();
			glPushMatrix();
				glColor3f(0,0,0);
				glTranslatef(68,278,100);
				glScalef(0.43, 0.43, 0.43);
				glLineWidth(2); 
				DesenhaTexto(GLUT_STROKE_ROMAN,texto);
			glPopMatrix();
		}
		else{
			//projecao primeira pessoa
			glPushMatrix();
				glColor3f(1,1,1);
				glTranslatef(0,posicao+100,100);
				glScalef(0.2, 0.2, 0.2);
				glLineWidth(2); 
				DesenhaTexto(GLUT_STROKE_ROMAN,texto);
			glPopMatrix();
			glPushMatrix();
				glColor3f(0,0,0);
				glTranslatef(-2,posicao+98,100);
				glScalef(0.22, 0.22, 0.22);
				glLineWidth(2); 
				DesenhaTexto(GLUT_STROKE_ROMAN,texto);
			glPopMatrix();
		}

}

void Display(){
	glEnable(GL_DEPTH_TEST);
    glEnable(GL_LINE_SMOOTH);
    glEnable(GL_POLYGON_SMOOTH);
    glEnable(GL_SMOOTH);
    glEnable(GL_BLEND);
    glEnable(GL_TEXTURE_2D);
   
   // Inicializa parâmetros de rendering
    //Fundo RGB: 112,197,205. Para converter para GLfLoat faz 1/255*NUM
   glClearColor(0.43921568627f, 0.7725490196f, 0.80392156862f, 1.0f);
   
   
   glMatrixMode(GL_PROJECTION);
   glLoadIdentity();//"Limpa" ou "transforma" a matriz em identidade, reduzindo possíveis erros.

   if (projecao==1){
   	// Projeção ortográfica
   		glOrtho(-200, 200, -350, 350, -80.0f, 200);
		glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(100, 0, 30, 100, 0, 0, 0, 1, 0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
   }
   else if(projecao == 0 && iniciou == 1){
   	//Projeção perspectiva só é feita quando inicia o jogo (para não afetar os menus)
		gluPerspective(45,1,50,5000);
		glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(-100, posicao, 400, 30, posicao, 0, 0, 1, 0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glBlendFunc(GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
   }
   
   
	desenhaChao(); //desenha o chão do cenário	
   
   
   //física do passaro, atualiza a posição e velocidade
   	end = clock();
    t = (end - start)*100.0/CLOCKS_PER_SEC;
    start = clock();
   	taxa+=t;
   
   if(iniciou == 1 && taxa > 0.01){
   	posicao = posicao + velocidade*tempo + aceleracao*tempo*tempo/2.0; //atualiza a posição do passaro s = s0 + v*t + (a*t²)/2;
 	velocidade = velocidade + aceleracao*tempo; //atualiza a velocidade v = v0 + a*t;
 	
 	taxa = 0;
 	
   }
   
   
   if(gameOver == 0 && iniciou == 1){
	   	//começou o jogo, então deve criar o passaro, criar os canos e atualizar os pontos
	   	
	   	projecao = trocaProjecao; //seta a projeção como a variável trocaProjecao
	   	
	   	glPushMatrix();
	        glTranslatef(0, posicao, 0); //translada o passaro pela equação do movimento
	        glPushMatrix();
	            // Rotação
	            glRotatef(velocidade*0.50, 0, 0, 1); //rotaciona pela velocidade 
	
				desenhaPassaro();
	   		glPopMatrix();
	        glTranslatef(0, -posicao, 0);
	    glPopMatrix();
	
		//função que fica verificando qual é o proximo cano
		proximoCano();
		//função que desenha os canos (e atualiza a posição deles)
		desenhaCanos();
		// função que atualiza os pontos do jogo
	   	atualizaPontos();
	   	
		//converte os pontos em uma string
		itoa(pontos, pontosString, 10);
		//escreve a string na tela
		escrevePontos(pontosString,projecao);
   }
   else if(gameOver == 0 && iniciou == 0){
	   	//se nao começou e nao perdeu ainda é a tela inicial
	   	telaInicial();
   }
  
       
    if(colisaoTeto(posicao) || colisaoChao(posicao) || colisaoCano(distanciaCanos[proxCano],alturaCanos[proxCano])){
    	//verifica se colidiu no teto ou no chao ou no cano
    	escrevePontos(pontosString,projecao); //deixa os  pontos escrito em cima para ver quanto fez
    	fim(); //mostra tela final
	}
	
   glutSwapBuffers(); //Executa a Cena. SwapBuffers dá suporte para mais de um buffer, permitindo execução de animações sem cintilações. 
   glutPostRedisplay();
}


void keyboard (unsigned char key, int x, int y){
//Key - recebe o código ASCII da tecla
 //x, y - recebem as posições do mouse na tela (permite tratar os dois dispositivos)
      if (key=='c'){
      	//troca a projecao de perspectiva pra ortogonal
         if(trocaProjecao == 0) trocaProjecao = 1;
         else trocaProjecao = 0;
      }
      else if(key == 32){
      	// pressionou ESPAÇO
      	if(gameOver == 0){
      		//só vai entrar aqui se não estiver na tela de gameover, aumenta a velocidade
      		velocidade = 120;
			//seta iniciou como 1 para começar a atualizar a posição do passaro
      		iniciou = 1;
		  }
      		
	  }
	  else if(key == 'r'){
	  	//reiniciar jogo
	  		iniciaCanos(); //cria novos canos
      		gameOver = 0;
      		posicao = -85;
      		velocidade = 120;
      		iniciou = 0;
      		pontos = 0;
      		proxCano = 0;	// volta o proximo cano para zero
	  }
      else if (key==27){
	  //esc sai         
         exit(0);
      }
      else if(key == '1'){
      	//aumenta vleocidade
      	velocidadeJogo += 0.1;
	  }
	  else if(key == '2'){
	  	//diminui
	  	velocidadeJogo -= 0.1;
	  }
}



int main(int argc,char **argv){
	
	//inicialização de variáveis
	posicao = -85;
	velocidade = 0;
	tempo = 0.01;
	aceleracao = -60;
	
	
   srand(time(NULL));
	
	//Primeiro precisa criar as distâncias dos canos e as alturas
	iniciaCanos(); //cria as distâncias iniciais do cano
	
	
   glutInit(&argc, argv); // Initializes glut
    
   
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH); /*Define as características do espaço vetorial. 
                                                                           //  Nesse caso, permite animações (sem cintilações), cores compostas por Verm. Verde e Azul,
                                                                           //  Buffer que permite trablhar com profundidade e elimina faces escondidas.*/           
  
   glutInitWindowSize(400, 700);
   glutInitWindowPosition(100, 100);
   glutCreateWindow("Flappy Bird");
   glutDisplayFunc(Display);
   glutKeyboardFunc(keyboard);
   glutMainLoop();
   return 0; 
}
