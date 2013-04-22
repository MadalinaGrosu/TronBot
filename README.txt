README
Proiect Proiectarea Algoritmilor 2013
Echipa Nabucodonosor: GROSU Madalina - Andreea
					  BOBOC Radu - Gabriel
					  MANASIA Ion
					  POENARU Radu - Constantin
Limbaj de programare: Java
Mediu de programare: Eclipse
Solutie pentru managementul surselor: Github

Etapa 1
	Solutia trimisa pentru aceasta etapa -
	https://www.hackerrank.com/contests/bucharest-tron/submissions/game/618075
	Meciul cu botul explorer - TO BE ADDED*
	Meciul cu botul RandomBot - TO BE ADDED*
	https://www.hackerrank.com/showgame/1814699
	https://www.hackerrank.com/showgame/1814700
* Din cauza unor defectiuni tehnice din partea celor de pe HackerRank nu am reusit sa obtinem linkurile utile. Le vom adauga de indata ce se remediaza problema.
	In aceasta etapa am realizat un schelet de cod pentru player-ul nostru. Am ales sa folosim un algoritm negamax pentru determinarea urmatoarei mutari. Euristica folosita pentru aceasta versiune este urmatoarea: botul va muta in casuta care este adiacenta cu
cat mai multi pereti si in felul acesta lasa spatiu pentru urmatoarele mutari. Adancimea pentru acesta este 15. Harta este reprezentata intern ca o matrice de int astfel: player-ul are valoarea 0, adversarul 2, zidurile -2, iar spatiile libere 1.
Fisierul sursa contine doua clase: 
-> clasa Position cu metoda mutariPosibile care intoarce mutarile posibile din pozitia curenta
-> clasa Solution cu metodele: 
		-> citire : citeste de la system.in datele de intrare
		-> evaluate : functia de evaluare pentru negamax; intoarce suma vecinilor pozitiei curente
		-> negamax : implementeaza algoritmul neagamax; intoarce cea mai buna mutare pentru pasul curent
		-> move : realizeaza cea mai buna mutare
	Referinte: https://project.dke.maastrichtuniversity.nl/games/files/bsc/Kang_Bsc-paper.pdf
