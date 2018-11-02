LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY LB IS
	PORT ( D,Clock: IN STD_LOGIC;
	oLEDG : OUT STD_LOGIC_VECTOR (3 DOWNTO 0);
	Display0 : OUT STD_LOGIC_VECTOR (6 DOWNTO 0);
	Display0P : OUT STD_LOGIC;
	Display1 : OUT STD_LOGIC_VECTOR (6 DOWNTO 0);
	Display1P : OUT STD_LOGIC;
	Display4 : OUT STD_LOGIC_VECTOR (6 DOWNTO 0);
	Display4P : OUT STD_LOGIC
	);
END LB;

ARCHITECTURE Behavior OF LB IS
	SIGNAL S: STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL P: STD_LOGIC_VECTOR (3 DOWNTO 0);
	SIGNAL M: STD_LOGIC_VECTOR (3 DOWNTO 0);
	COMPONENT ClockN IS
		generic (
			n : natural := 4
		);
		port(
			clock : in STD_LOGIC;
			reset_n : in STD_LOGIC;
			Q : out STD_LOGIC_VECTOR(n-1 downto 0);
			Q1 : out STD_LOGIC_VECTOR(n-1 downto 0);
			Q2 : out STD_LOGIC_VECTOR(n-1 downto 0)
		);
	END COMPONENT;
	
	COMPONENT Disp IS
		PORT (iSW : IN STD_LOGIC_VECTOR(3 DOWNTO 0);
				oHEX0_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
				oHEX0_DP : OUT STD_LOGIC
			);
	END COMPONENT;
	BEGIN
		LT_1 : ClockN generic map ( n => 4) port map (Clock, D, S, P, M);
		DSP0 : Disp port map ( not S, Display0, Display0P );
		DSP1 : Disp port map ( not P, Display1, Display1P );
		DSP4 : Disp port map ( not M, Display4, Display4P );
		oLEDG <= S;
	END Behavior;
		


------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_signed.all;
entity ClockN is
	generic (
	n : natural := 4
	);
	port(
		clock : in STD_LOGIC;
		reset_n : in STD_LOGIC;
		Q : out STD_LOGIC_VECTOR(n-1 downto 0);
		Q1 : out STD_LOGIC_VECTOR(n-1 downto 0);
		Q2 : out STD_LOGIC_VECTOR(n-1 downto 0)
	);
end entity;

architecture rtl of ClockN is 
	signal v : std_logic_vector(n-1 downto 0); 
	signal p : std_logic_vector(n-1 downto 0);
	signal m : std_logic_vector(n-1 downto 0);
	signal s : integer := 0;
begin
	PROCESS(clock,reset_n)
	begin
		if(reset_n = '0') then
			v <= (OTHERS => '0');
			p <= (OTHERS => '0');
			m <= (OTHERS => '0');
		elsif ((clock'event) and (clock ='1')) then
				s <= s + 1;
				if(s = 3000000) then
					s <= 0;
					v <= v + 1;
					if(v = 9) then
						v <= (OTHERS => '0');
						p <= p + 1;
						if(p = 5 and v = 9) then
							p <= (OTHERS => '0');
							m <= m + 1;
						end if;
					end if;
				end if;
		end if;
	end process;
	Q <= v;
	Q1 <= p;
	Q2 <= m;
end rtl;

-----------------------------

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
