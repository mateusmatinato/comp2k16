LIBRARY ieee;
USE ieee.std_logic_1164.all;

ENTITY teste IS
	PORT (iSW : IN STD_LOGIC_VECTOR(17 DOWNTO 0);
		oHEX0_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX1_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX2_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX3_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX4_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX5_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX6_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX7_D : OUT STD_LOGIC_VECTOR(6 DOWNTO 0);
		oHEX0_DP : OUT STD_LOGIC;
		oHEX1_DP : OUT STD_LOGIC;
		oHEX2_DP : OUT STD_LOGIC;
		oHEX3_DP : OUT STD_LOGIC;
		oHEX4_DP : OUT STD_LOGIC;
		oHEX5_DP : OUT STD_LOGIC;
		oHEX6_DP : OUT STD_LOGIC;
		oHEX7_DP : OUT STD_LOGIC;
		
		oLEDR : OUT STD_LOGIC_VECTOR(17 DOWNTO 0));
END teste;

ARCHITECTURE Arquitetura OF teste IS
BEGIN
	oHEX0_D(0) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or (not iSW(2)) or iSW(1) or iSW(0))  and (iSW(3) or iSW(2) or (not iSW(1)) or iSW(0)));
	oHEX0_D(1) <= not(((not iSW(3)) or iSW(2) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or (not iSW(2)) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0))); 
	oHEX0_D(2) <= not(((not iSW(3)) or (not iSW(2)) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0)));
	oHEX0_D(3) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1) or iSW(0))) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or (not iSW(2)) or iSW(1) or (not iSW(0))) and (iSW(3) or iSW(2) or iSW(1) or iSW(0)));
	oHEX0_D(4) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or iSW(0)) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or iSW(2) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or (not iSW(2)) or (not iSW(1)) or iSW(0)));
	oHEX0_D(5) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or (not iSW(0))) and ((not iSW(3)) or (not iSW(2)) or iSW(1) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or iSW(0)));
	oHEX0_D(6) <= not(((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or (not iSW(0))) and ((not iSW(3)) or (not iSW(2)) or (not iSW(1)) or iSW(0)) and ((not iSW(3)) or iSW(2) or iSW(1) or iSW(0)) and (iSW(3) or iSW(2) or (not iSW(1)) or (not iSW(0))));
	oHEX0_DP <= '1';

	oHEX1_D(0) <= not(((not iSW(7)) or (not iSW(6)) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or iSW(6) or (not iSW(5)) or (not iSW(4))) and (iSW(7) or (not iSW(6)) or iSW(5) or iSW(4))  and (iSW(7) or iSW(6) or (not iSW(5)) or iSW(4)));
	oHEX1_D(1) <= not(((not iSW(7)) or iSW(6) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or iSW(6) or iSW(5) or (not iSW(4))) and (iSW(7) or (not iSW(6)) or iSW(5) or iSW(4)) and (iSW(7) or iSW(6) or (not iSW(5)) or (not iSW(4))) and (iSW(7) or iSW(6) or iSW(5) or (not iSW(4))) and (iSW(7) or iSW(6) or iSW(5) or iSW(4))); 
	oHEX1_D(2) <= not(((not iSW(7)) or (not iSW(6)) or iSW(5) or (not iSW(4))) and (iSW(7) or iSW(6) or (not iSW(5)) or (not iSW(4))) and (iSW(7) or iSW(6) or iSW(5) or (not iSW(4))) and (iSW(7) or iSW(6) or iSW(5) or iSW(4)));
	oHEX1_D(3) <= not(((not iSW(7)) or (not iSW(6)) or (not iSW(5) or iSW(4))) and ((not iSW(7)) or iSW(6) or (not iSW(5)) or (not iSW(4))) and ((not iSW(7)) or iSW(6) or iSW(5) or iSW(4)) and (iSW(7) or (not iSW(6)) or iSW(5) or (not iSW(4))) and (iSW(7) or iSW(6) or iSW(5) or iSW(4)));
	oHEX1_D(4) <= not(((not iSW(7)) or (not iSW(6)) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or (not iSW(6)) or iSW(5) or iSW(4)) and ((not iSW(7)) or iSW(6) or (not iSW(5)) or (not iSW(4))) and ((not iSW(7)) or iSW(6) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or iSW(6) or iSW(5) or iSW(4)) and (iSW(7) or (not iSW(6)) or (not iSW(5)) or iSW(4)));
	oHEX1_D(5) <= not(((not iSW(7)) or (not iSW(6)) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or (not iSW(6)) or iSW(5) or (not iSW(4))) and ((not iSW(7)) or (not iSW(6)) or iSW(5) or iSW(4)) and ((not iSW(7)) or iSW(6) or iSW(5) or iSW(4)) and (iSW(7) or iSW(6) or (not iSW(5)) or iSW(4)));
	oHEX1_D(6) <= not(((not iSW(7)) or (not iSW(6)) or (not iSW(5)) or (not iSW(4))) and ((not iSW(7)) or (not iSW(6)) or (not iSW(5)) or iSW(4)) and ((not iSW(7)) or iSW(6) or iSW(5) or iSW(4)) and (iSW(7) or iSW(6) or (not iSW(5)) or (not iSW(4))));
	oHEX1_DP <= '1';

	oHEX2_D(0) <= not(((not iSW(11)) or (not iSW(10)) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or iSW(10) or (not iSW(9)) or (not iSW(8))) and (iSW(11) or (not iSW(10)) or iSW(9) or iSW(8))  and (iSW(11) or iSW(10) or (not iSW(9)) or iSW(8)));
	oHEX2_D(1) <= not(((not iSW(11)) or iSW(10) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or iSW(10) or iSW(9) or (not iSW(8))) and (iSW(11) or (not iSW(10)) or iSW(9) or iSW(8)) and (iSW(11) or iSW(10) or (not iSW(9)) or (not iSW(8))) and (iSW(11) or iSW(10) or iSW(9) or (not iSW(8))) and (iSW(11) or iSW(10) or iSW(9) or iSW(8))); 
	oHEX2_D(2) <= not(((not iSW(11)) or (not iSW(10)) or iSW(9) or (not iSW(8))) and (iSW(11) or iSW(10) or (not iSW(9)) or (not iSW(8))) and (iSW(11) or iSW(10) or iSW(9) or (not iSW(8))) and (iSW(11) or iSW(10) or iSW(9) or iSW(8)));
	oHEX2_D(3) <= not(((not iSW(11)) or (not iSW(10)) or (not iSW(9) or iSW(8))) and ((not iSW(11)) or iSW(10) or (not iSW(9)) or (not iSW(8))) and ((not iSW(11)) or iSW(10) or iSW(9) or iSW(8)) and (iSW(11) or (not iSW(10)) or iSW(9) or (not iSW(8))) and (iSW(11) or iSW(10) or iSW(9) or iSW(8)));
	oHEX2_D(4) <= not(((not iSW(11)) or (not iSW(10)) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or (not iSW(10)) or iSW(9) or iSW(8)) and ((not iSW(11)) or iSW(10) or (not iSW(9)) or (not iSW(8))) and ((not iSW(11)) or iSW(10) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or iSW(10) or iSW(9) or iSW(8)) and (iSW(11) or (not iSW(10)) or (not iSW(9)) or iSW(8)));
	oHEX2_D(5) <= not(((not iSW(11)) or (not iSW(10)) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or (not iSW(10)) or iSW(9) or (not iSW(8))) and ((not iSW(11)) or (not iSW(10)) or iSW(9) or iSW(8)) and ((not iSW(11)) or iSW(10) or iSW(9) or iSW(8)) and (iSW(11) or iSW(10) or (not iSW(9)) or iSW(8)));
	oHEX2_D(6) <= not(((not iSW(11)) or (not iSW(10)) or (not iSW(9)) or (not iSW(8))) and ((not iSW(11)) or (not iSW(10)) or (not iSW(9)) or iSW(8)) and ((not iSW(11)) or iSW(10) or iSW(9) or iSW(8)) and (iSW(11) or iSW(10) or (not iSW(9)) or (not iSW(8))));
	oHEX2_DP <= '1';

	oHEX3_D(0) <= not(((not iSW(15)) or (not iSW(14)) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or iSW(14) or (not iSW(13)) or (not iSW(12))) and (iSW(15) or (not iSW(14)) or iSW(13) or iSW(12))  and (iSW(15) or iSW(14) or (not iSW(13)) or iSW(12)));
	oHEX3_D(1) <= not(((not iSW(15)) or iSW(14) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or iSW(14) or iSW(13) or (not iSW(12))) and (iSW(15) or (not iSW(14)) or iSW(13) or iSW(12)) and (iSW(15) or iSW(14) or (not iSW(13)) or (not iSW(12))) and (iSW(15) or iSW(14) or iSW(13) or (not iSW(12))) and (iSW(15) or iSW(14) or iSW(13) or iSW(12))); 
	oHEX3_D(2) <= not(((not iSW(15)) or (not iSW(14)) or iSW(13) or (not iSW(12))) and (iSW(15) or iSW(14) or (not iSW(13)) or (not iSW(12))) and (iSW(15) or iSW(14) or iSW(13) or (not iSW(12))) and (iSW(15) or iSW(14) or iSW(13) or iSW(12)));
	oHEX3_D(3) <= not(((not iSW(15)) or (not iSW(14)) or (not iSW(13) or iSW(12))) and ((not iSW(15)) or iSW(14) or (not iSW(13)) or (not iSW(12))) and ((not iSW(15)) or iSW(14) or iSW(13) or iSW(12)) and (iSW(15) or (not iSW(14)) or iSW(13) or (not iSW(12))) and (iSW(15) or iSW(14) or iSW(13) or iSW(12)));
	oHEX3_D(4) <= not(((not iSW(15)) or (not iSW(14)) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or (not iSW(14)) or iSW(13) or iSW(12)) and ((not iSW(15)) or iSW(14) or (not iSW(13)) or (not iSW(12))) and ((not iSW(15)) or iSW(14) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or iSW(14) or iSW(13) or iSW(12)) and (iSW(15) or (not iSW(14)) or (not iSW(13)) or iSW(12)));
	oHEX3_D(5) <= not(((not iSW(15)) or (not iSW(14)) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or (not iSW(14)) or iSW(13) or (not iSW(12))) and ((not iSW(15)) or (not iSW(14)) or iSW(13) or iSW(12)) and ((not iSW(15)) or iSW(14) or iSW(13) or iSW(12)) and (iSW(15) or iSW(14) or (not iSW(13)) or iSW(12)));
	oHEX3_D(6) <= not(((not iSW(15)) or (not iSW(14)) or (not iSW(13)) or (not iSW(12))) and ((not iSW(15)) or (not iSW(14)) or (not iSW(13)) or iSW(12)) and ((not iSW(15)) or iSW(14) or iSW(13) or iSW(12)) and (iSW(15) or iSW(14) or (not iSW(13)) or (not iSW(12))));
	oHEX3_DP <= '1';
	
	oHEX4_D(3) <= '1';
	oHEX5_D(0) <= '1';
	oHEX5_D(1) <= '1';
	oHEX5_D(2) <= '1';
	oHEX5_D(6) <= '1';
	oHEX6_D(0) <= '1';
	oHEX6_D(6) <= '1';
	oHEX5_D(0) <= '1';
	oHEX5_D(1) <= '1';
	oHEX5_D(2) <= '1';
	oHEX5_D(6) <= '1';
	oHEX7_D(0) <= '1';
	oHEX7_D(1) <= '1';
	oHEX7_D(2) <= '1';
	oHEX7_D(6) <= '1';
	
	oHEX4_DP <= '1';
	oHEX5_DP <= '1';
	oHEX6_DP <= '1';
	oHEX7_DP <= '1';
	
		
	oLEDR <= not iSW;

END Arquitetura;
