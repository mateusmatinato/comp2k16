LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Aula1910 IS
	PORT ( 
		Clock: IN STD_LOGIC;
		Display0 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display1 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display2 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display3 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display4 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display5 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display6 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display7 : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		Display0P : OUT STD_LOGIC;
		Display1P : OUT STD_LOGIC;
		Display2P : OUT STD_LOGIC;
		Display3P : OUT STD_LOGIC;
		Display4P : OUT STD_LOGIC;
		Display5P	 : OUT STD_LOGIC;
		Display6P	 : OUT STD_LOGIC;
		Display7P	 : OUT STD_LOGIC;
		iSWA : IN STD_LOGIC_VECTOR (15 DOWNTO 8);
		rw : in STD_LOGIC;
		iSWD : IN STD_LOGIC_VECTOR (7 DOWNTO 0));
END Aula1910;

ARCHITECTURE Behavior OF Aula1910 IS
	SIGNAL S: STD_LOGIC_VECTOR (7 DOWNTO 0);
	SIGNAL Q0: STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL Q1: STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL E0: STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL E1: STD_LOGIC_VECTOR (3 DOWNTO 0);
	COMPONENT ram IS
		port(
			address		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
			clock		: IN STD_LOGIC ;
			data		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
			wren		: IN STD_LOGIC ;
			q		: OUT STD_LOGIC_VECTOR (7 DOWNTO 0)
		);
	END COMPONENT;
	
	COMPONENT Disp IS
		PORT (	iSW : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
				oHEX0_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
				oHEX0_DP : OUT STD_LOGIC
			);
	END COMPONENT;
	
	BEGIN
		Memory : ram port map ( iSWA, Clock, iSWD, rw, S);
		Q0 <=  S(3 DOWNTO 0);
		Q1 <=  S(7 DOWNTO 4);
		E0 <=  iSWA(11 DOWNTO 8);
		E1 <=  iSWA(15 DOWNTO 12);
		Disp0 : Disp port map (not Q0, Display0, Display0P);
		Disp1 : Disp port map (not Q1, Display1, Display1P);
		Disp4 : Disp port map (not E0, Display4, Display4P);
		Disp5 : Disp port map (not E1, Display5, Display5P);
		
		Display2(0) <='1';
		Display2(1) <='1';
		Display2(2) <='1';
		Display2(3) <='1';
		Display2(4) <='1';
		Display2(5) <='1';
		Display2(6) <='1';
		Display2P <='1';
		
		Display3(0) <='1';
		Display3(1) <='1';
		Display3(2) <='1';
		Display3(3) <='1';
		Display3(4) <='1';
		Display3(5) <='1';
		Display3(6) <='1';
		Display3P <='1';
		
		Display6(0) <='1';
		Display6(1) <='1';
		Display6(2) <='1';
		Display6(3) <='1';
		Display6(4) <='1';
		Display6(5) <='1';
		Display6(6) <='1';
		Display6P <='1';
		
		Display7(0) <='1';
		Display7(1) <='1';
		Display7(2) <='1';
		Display7(3) <='1';
		Display7(4) <='1';
		Display7(5) <='1';
		Display7(6) <='1';
		Display7P <= not rw;
 
	END;
		
--------------------------------------------------



LIBRARY ieee;
USE ieee.std_logic_1164.all;

LIBRARY altera_mf;
USE altera_mf.all;

ENTITY ram IS
	PORT
	(
		address		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
		clock		: IN STD_LOGIC ;
		data		: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
		wren		: IN STD_LOGIC ;
		q		: OUT STD_LOGIC_VECTOR (7 DOWNTO 0)
	);
END ram;


ARCHITECTURE SYN OF ram IS

	SIGNAL sub_wire0	: STD_LOGIC_VECTOR (7 DOWNTO 0);



	COMPONENT altsyncram
	GENERIC (
		clock_enable_input_a		: STRING;
		clock_enable_output_a		: STRING;
		intended_device_family		: STRING;
		lpm_hint		: STRING;
		lpm_type		: STRING;
		numwords_a		: NATURAL;
		operation_mode		: STRING;
		outdata_aclr_a		: STRING;
		outdata_reg_a		: STRING;
		power_up_uninitialized		: STRING;
		widthad_a		: NATURAL;
		width_a		: NATURAL;
		width_byteena_a		: NATURAL
	);
	PORT (
			wren_a	: IN STD_LOGIC ;
			clock0	: IN STD_LOGIC ;
			address_a	: IN STD_LOGIC_VECTOR (7 DOWNTO 0);
			q_a	: OUT STD_LOGIC_VECTOR (7 DOWNTO 0);
			data_a	: IN STD_LOGIC_VECTOR (7 DOWNTO 0)
	);
	END COMPONENT;

BEGIN
	q    <= sub_wire0(7 DOWNTO 0);

	altsyncram_component : altsyncram
	GENERIC MAP (
		clock_enable_input_a => "BYPASS",
		clock_enable_output_a => "BYPASS",
		intended_device_family => "Cyclone II",
		lpm_hint => "ENABLE_RUNTIME_MOD=NO",
		lpm_type => "altsyncram",
		numwords_a => 256,
		operation_mode => "SINGLE_PORT",
		outdata_aclr_a => "NONE",
		outdata_reg_a => "UNREGISTERED",
		power_up_uninitialized => "FALSE",
		widthad_a => 8,
		width_a => 8,
		width_byteena_a => 1
	)
	PORT MAP (
		wren_a => wren,
		clock0 => clock,
		address_a => address,
		data_a => data,
		q_a => sub_wire0
	);



END SYN;

-----------------------------------------------------------
LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY Disp IS
	PORT (iSW : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
		oHEX0_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX0_DP : OUT STD_LOGIC
		);
END Disp;

ARCHITECTURE Arquitetura OF Disp IS
BEGIN
	oHEX0_D(0) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or (not iSW(2)) or iSW(1) or iSW(0))  and (iSW(3) or iSW(2) or (not iSW(1)) or iSW(0)));
	oHEX0_D(1) <= not(((not iSW(3)) or iSW(2) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or (not iSW(2)) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0))); 
	oHEX0_D(2) <= not(((not iSW(3)) or (not iSW(2)) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0)));
	oHEX0_D(3) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1) or iSW(0))) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or (not iSW(2)) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0)));
	oHEX0_D(4) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or iSW(0)) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or (not iSW(2)) or (not iSW(1)) or iSW(0)));
	oHEX0_D(5) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or (not iSW(0))) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or iSW(0)));
	oHEX0_D(6) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))));
	oHEX0_DP <= '1';
END Arquitetura;
