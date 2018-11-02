LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Aula3 IS
	PORT (iSW : IN STD_LOGIC_VECTOR(17 DOWNTO 0);		
		oLEDR : OUT STD_LOGIC_VECTOR(17 DOWNTO 0));
END Aula3;

ARCHITECTURE Arquitetura OF Aula3 IS
SIGNAL T : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL U : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL M : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL N : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL P : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL Q : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL R : STD_LOGIC_VECTOR(17 DOWNTO 0);
SIGNAL S : STD_LOGIC_VECTOR(17 DOWNTO 0);
BEGIN
	PROCESS(iSW,T,U)
	BEGIN
		FOR i in 0 to 7 loop
		
			T(i) <= (iSW(i) nand (iSW(17) and iSW(15) and iSW(16))) nand U(i); 
			U(i) <= ((not iSW(i)) nand (iSW(17) and iSW(15) and iSW(16))) nand T(i);

		END LOOP;
	END PROCESS;
	
	
	PROCESS(iSW,M,N)
	BEGIN
		FOR i in 0 to 7 loop
			M(i) <= (iSW(i) nand (iSW(17) and not (iSW(15) and iSW(16)))) nand N(i); 
			N(i) <= ((not iSW(i)) nand (iSW(17) and not iSW(15) and iSW(16))) nand M(i);

		END LOOP;
	END PROCESS;
	
	PROCESS(iSW,P,Q)
	BEGIN
		FOR i in 0 to 7 loop
		
			P(i) <= (iSW(i) nand (iSW(17) and iSW(15) and not iSW(16))) nand Q(i); 
			Q(i) <= ((not iSW(i)) nand (iSW(17) and iSW(15) and not iSW(16))) nand P(i);
			
		END LOOP;
	END PROCESS;
	
	PROCESS(iSW,R,S)
	BEGIN
		FOR i in 0 to 7 loop
		
			R(i) <= (iSW(i) nand (iSW(17) and not iSW(15) and not iSW(16))) nand S(i); 
			S(i) <= ((not iSW(i)) nand (iSW(17) and not iSW(15) and not iSW(16))) nand R(i);

		END LOOP;
	END PROCESS;
	
	oLEDR(0) <= (((T(0) and iSW(15)) or (M(0) and (not iSW(15)))) and iSW(16)) or (((P(0) and iSW(15)) or (R(0) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(1) <= (((T(1) and iSW(15)) or (M(1) and (not iSW(15)))) and iSW(16)) or (((P(1) and iSW(15)) or (R(1) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(2) <= (((T(2) and iSW(15)) or (M(2) and (not iSW(15)))) and iSW(16)) or (((P(2) and iSW(15)) or (R(2) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(3) <= (((T(3) and iSW(15)) or (M(3) and (not iSW(15)))) and iSW(16)) or (((P(3) and iSW(15)) or (R(3) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(4) <= (((T(4) and iSW(15)) or (M(4) and (not iSW(15)))) and iSW(16)) or (((P(4) and iSW(15)) or (R(4) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(5) <= (((T(5) and iSW(15)) or (M(5) and (not iSW(15)))) and iSW(16)) or (((P(5) and iSW(15)) or (R(5) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(6) <= (((T(6) and iSW(15)) or (M(6) and (not iSW(15)))) and iSW(16)) or (((P(6) and iSW(15)) or (R(6) and (not iSW(15)))) and not(iSW(16)));	
	oLEDR(7) <= (((T(7) and iSW(15)) or (M(7) and (not iSW(15)))) and iSW(16)) or (((P(7) and iSW(15)) or (R(7) and (not iSW(15)))) and not(iSW(16)));	

END Arquitetura;
