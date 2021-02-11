package ihmistenLuonti20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class ohjelma {
	public static void main(String[] args) {
		
		Scanner syote = new Scanner(System.in);
		boolean kaynnissa = true;
		ArrayList<String> miesLista = lueMiestenNimet();
		ArrayList<String> naisLista = lueNaistenNimet();
		ArrayList<String> sukuLista = lueSukuNimet();
		ArrayList<String> tieLista = lueTienNimet();
		ArrayList<String> postiLista = luePostiNrot();
		ArrayList<String> tarkisteLista = lueTarkiste();
		
		String etuNimi = "";
		String sukuNimi = "";
		String tienNimi = "";
		String postiNro = "";
		String tarkiste = "";
		String tienNumero = "";
		String puhNro = "";
		String hetu = "";
		String syntyAika = "";
		int paiva = 0;
		int kuukausi = 0;
		int vuosi = 0;
		int loppuOsa = 0;
		int sukuPuoli = 0;
		int alkuOsa = 0;
		String valiMerkki = "";
		
		while (kaynnissa) {
			int montako = 0;
			ArrayList<String> ihmisLista = new ArrayList<String>();
			ArrayList<String> ihmisListaCSV = new ArrayList<String>();
			boolean montakoOk = false;
			while (montakoOk == false) {
				try {
					System.out.println("Montako nimeä haluat luoda? ");
					montako = Integer.parseInt(syote.nextLine());
					montakoOk = true;
					break;
				} catch (Exception e) {
					System.out.println("Anna kokonaisluku!");
				}
			}

			luoNimia(montako);
			Instant start = Instant.now();
			String henkiloTiedot = "";
			String henkiloTiedotCSV = "";
			Random satunnainen = new Random();
			for (int i = 0; i < montako; i++) {
				sukuPuoli = satunnainen.nextInt(2);
				if (sukuPuoli == 0) {
					etuNimi = arvoEtunimi(miesLista);
				} else {
					etuNimi = arvoEtunimi(naisLista);
				}
				
				sukuNimi = arvoSukuNimi(sukuLista);
				tienNimi = arvoTienNimi(tieLista);
				postiNro = arvoPostiNro(postiLista);
				tienNumero = arvoTienNumero();
				puhNro = arvoPuhelin();
				paiva = arvoPaiva();
				kuukausi = arvoKuukausi();
				vuosi = arvoVuosi();
				if (vuosi >= 2000) {
					valiMerkki = "A";
				} else {
					valiMerkki = "-";
				}
				alkuOsa = muodostaAlkuOsa(paiva,kuukausi,vuosi);
				loppuOsa = arvoLoppuOsa(sukuPuoli);
				tarkiste = tarkisteLista.get(muodostaTarkiste(alkuOsa,loppuOsa));
				syntyAika = muodostaSyntymaAika(paiva,kuukausi,vuosi);
				String loppuOsaString = String.format("%03d",loppuOsa);
				hetu = String.format("%06d",alkuOsa) + valiMerkki + loppuOsaString + tarkiste;
				henkiloTiedot = etuNimi + " " + sukuNimi + " " + hetu + " " + syntyAika + " " + tienNimi + " " + tienNumero + " " + postiNro + " " + puhNro;
				henkiloTiedotCSV = etuNimi + "," + sukuNimi + "," + hetu + "," + syntyAika + "," + tienNimi + " " + tienNumero + "," + postiNro + "," + puhNro;
				System.out.println(String.format("%08d",(i + 1 )) + ". " + henkiloTiedot);
				ihmisLista.add(henkiloTiedot);
				ihmisListaCSV.add(henkiloTiedotCSV);
			}
			Instant end = Instant.now();
			Duration kesto = Duration.between(start, end);
			System.out.println("Aikaa kului " + kesto.toMillis() + " millisekuntia eli " + kesto.toString());
			boolean valintaOk = false;
			String valinta = "";
			while (valintaOk == false) {

				System.out.println("Haluatko tallentaa tiedoston? k/e : ");
				valinta = syote.nextLine();
				if ((!valinta.equalsIgnoreCase("k")) && (!valinta.equalsIgnoreCase("e"))) {
					System.out.println("Valitse k tai e!");
				} else {
					valintaOk = true;
					break;
				}
				
			}
				
			if (valinta.equalsIgnoreCase("k")) {
				System.out.println("Anna tiedostolle nimi: ");
				String tiedostoNimi = syote.nextLine();
				boolean muotoOk = false;
				int muotoValinta = 0;
				while (muotoOk == false) {
					System.out.println("Haluatko tallentaa tiedoston");
					System.out.println("1. tekstitiedostona (.txt)");
					System.out.println("2. CSV-tiedostona (.csv)?");
					System.out.println("Valintasi 1/2 :");
					muotoValinta = Integer.parseInt(syote.nextLine());
					if ((muotoValinta != 1) && (muotoValinta != 2)) {
						System.out.println("Valitse 1 tai 2!");
					} else {
						muotoOk = true;
						break;
					}
				}
				if (muotoValinta == 1) {
					try {
						FileWriter kirjoittaja = new FileWriter(tiedostoNimi + ".txt");
						for (int i = 0; i < ihmisLista.size(); i++) {
							kirjoittaja.write(ihmisLista.get(i) + "\n");
						}
						kirjoittaja.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
					
				}
				
				if (muotoValinta == 2) {
					try {
						FileWriter kirjoittaja = new FileWriter(tiedostoNimi + ".csv");
						for (int i = 0; i < ihmisLista.size(); i++) {
							kirjoittaja.write(ihmisListaCSV.get(i) + "\n");
						}
						kirjoittaja.close();
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
					
				}
			}
			if (valinta.equalsIgnoreCase("e")) {
				;
			}
			String vielako = "";
			boolean vielakoOk = false;
			while (vielakoOk == false) {
				System.out.println("Haluatko luoda lisää nimiä? k/e : ");
				vielako = syote.nextLine();
				if ((!vielako.equalsIgnoreCase("k")) && (!vielako.equalsIgnoreCase("e"))) {
					System.out.println("Valitse k tai e!");
				} else {
					vielakoOk = true;
					break;
				}
			}

			if (vielako.equalsIgnoreCase("k")) {
				;
			}
			if (vielako.equalsIgnoreCase("e")) {
				break;
			}
		}
		syote.close();	
	} // main's end
	
	private static int muodostaTarkiste(int alkuOsa, int loppuOsa) {
		String luku = String.valueOf(alkuOsa) + String.valueOf(loppuOsa);
		int jakoJaannos = Integer.parseInt(luku) % 31;
		return jakoJaannos;
	}

	// muodostetaan p�iv�st�, kuukaudesta ja vuodesta hetun alkuosa ja palautetaan numerona
	private static int muodostaAlkuOsa(int paiva, int kuukausi, int vuosi) {
		String paivaString = String.format("%02d", paiva);
		String kuukausiString = String.format("%02d", kuukausi);
		String vuosiString = String.format("%02d",vuosi).substring(2,4);
		String alkuString = paivaString + kuukausiString + vuosiString;
		int alkuOsa = Integer.parseInt(alkuString);
		return alkuOsa;
	}

	// arvotaan hetun loppuosan alkuluku, miehille pariton ja naisille parillinen
	private static int arvoLoppuOsa(int sukuPuoli) {
		Random satunnainen = new Random();
		int loppuOsa = 0;
		if (sukuPuoli == 0) {
			loppuOsa = satunnainen.nextInt(448) * 2 + 3;
		} else {
			loppuOsa = (satunnainen.nextInt(448) + 1) * 2;
		}
		return loppuOsa;
	}

	private static String muodostaSyntymaAika(int paiva, int kuukausi, int vuosi) {
		String syntyAika = String.valueOf(paiva) + "-" + String.valueOf(kuukausi) + "-" + String.valueOf(vuosi);
		return syntyAika;
	}

	// arvotaan syntym�ajan vuosi
	private static int arvoVuosi() {
		Random satunnainen = new Random();
		int vuosi = satunnainen.nextInt(90) + 1930;
		return vuosi;
	}
	
	// arvotaan syntym�ajan kuukausi
	private static int arvoKuukausi() {
		Random satunnainen = new Random();
		int kuukausi = satunnainen.nextInt(12) + 1;
		return kuukausi;
	}
	
	// arvotaan syntym�ajan p�iv�
	private static int arvoPaiva() {
		Random satunnainen = new Random();
		int paiva = satunnainen.nextInt(28) + 1;
		return paiva;
	}

	// arvotaan suuntanumero ja puhelinnumeron loppuosa
	private static String arvoPuhelin() {
		String[] suuntaNrot = {"040", "050", "045", "041", "044"};
		String puhNro = "";
		Random satunnainen = new Random();
		String suunta = suuntaNrot[satunnainen.nextInt(suuntaNrot.length)];
		String numero = String.valueOf(satunnainen.nextInt(8999999) + 1000000);
		puhNro = suunta + numero;
		return puhNro;
	}


	private static String arvoTienNumero() {
		String[] rapunKirjain = {"A", "B", "C", "D", "E", "F", "G", "H"};
		
		String tieOsoite = "";
		Random satunnainen = new Random();
		int tienNumero = satunnainen.nextInt(45) + 1;
		int asunnonNumero = 0;
		String rappu = "";
		boolean asuntoOlemassa = false;
		boolean rappuOlemassa = false;
		int rappuko = satunnainen.nextInt(3);
		if (rappuko >= 1) {
			rappuOlemassa = true;
			rappu = rapunKirjain[satunnainen.nextInt(rapunKirjain.length)];
			int asuntoko = satunnainen.nextInt(3);
			if (asuntoko > 1) {
				asuntoOlemassa = true;
				asunnonNumero = satunnainen.nextInt(25) + 1;
			}
		}
		if ((rappuOlemassa == true) && (asuntoOlemassa == true)){
			tieOsoite = String.valueOf(tienNumero) + " " + rappu + " " + String.valueOf(asunnonNumero);
		}
		if ((rappuOlemassa == true) && (asuntoOlemassa == false)) {
			tieOsoite = String.valueOf(tienNumero) + " " + rappu;
		}
		if (rappuOlemassa == false) {
			tieOsoite = String.valueOf(tienNumero);
		}
		return tieOsoite;
	}


	// arvotaan postinumero listalta
	private static String arvoPostiNro(ArrayList<String> postiLista) {
		String postiNro = "";
		Random satunnainen = new Random();
		postiNro = postiLista.get(satunnainen.nextInt(postiLista.size()));
		return postiNro;
	}

	// arvotaan tien nimi listalta
	private static String arvoTienNimi(ArrayList<String> tieLista) {
		String tienNimi = "";
		Random satunnainen = new Random();
		tienNimi = tieLista.get(satunnainen.nextInt(tieLista.size()));
		return tienNimi;
	}

	// arvotaan sukunimi listalta
	private static String arvoSukuNimi(ArrayList<String> sukuLista) {
		String sukuNimi = "";
		Random satunnainen = new Random();
		sukuNimi = sukuLista.get(satunnainen.nextInt(sukuLista.size()));
		return sukuNimi;
	}


	// arvotaan mies vaiko nainen ja arvotulta listalta etunimi
	private static String arvoEtunimi(ArrayList<String> nimiLista) {
		int kumpi = 0;
		String etuNimi = "";
		Random satunnainen = new Random();
		kumpi = satunnainen.nextInt(2);
		etuNimi = nimiLista.get(satunnainen.nextInt(nimiLista.size()));
		return etuNimi;
	}



	private static void luoNimia(int montako) {
		// TODO Auto-generated method stub
		
	}
	
	// luetaan miesten etunimet listaan
	private static ArrayList<String> lueMiestenNimet() {
		String line = "";
		ArrayList<String> miesLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/etunimilista_miehet.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				miesLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return miesLista;
	}
	
	// luetaan naisten etunimet listaan
	private static ArrayList<String> lueNaistenNimet() {
		String line = "";
		ArrayList<String> naisLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/etunimilista_naiset.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				naisLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return naisLista;
	}
	
	// luetaan sukunimet listaan
	private static ArrayList<String> lueSukuNimet() {
		String line = "";
		ArrayList<String> sukuLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/sukunimilista.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				sukuLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return sukuLista;
	}
	
	// luetaan tiennimet listaan
	private static ArrayList<String> lueTienNimet() {
		String line = "";
		ArrayList<String> tieLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/tiet.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				tieLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return tieLista;
	}
	
	// luetaan postinumerot ja -toimipaikat listaan
	private static ArrayList<String> luePostiNrot() {
		String line = "";
		ArrayList<String> postiLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/postinrotsuodatettu.csv", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				postiLista.add(line.replace(",", " "));
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return postiLista;
	}
	
	// luetaan jakoj��nn�s listaan
	private static ArrayList<String> lueJaannos() {
		String line = "";
		ArrayList<String> jaannosLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/tarkiste_eka.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				jaannosLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return jaannosLista;
	}
	
	//luetaan tarkistemerkit listaan
	private static ArrayList<String> lueTarkiste() {
		String line = "";
		ArrayList<String> tarkisteLista = new ArrayList<String>();
		try {
			FileReader lukija = new FileReader("./data/tarkiste_toka.txt", StandardCharsets.UTF_8);
			BufferedReader puskuri = new BufferedReader(lukija);
			while ((line = puskuri.readLine()) != null) {
				tarkisteLista.add(line);
			}
			puskuri.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return tarkisteLista;
	}

}
