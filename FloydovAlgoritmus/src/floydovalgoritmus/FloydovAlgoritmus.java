package floydovalgoritmus;

import floydovalgoritmus.vynimky.CestaNeexistujeException;
import floydovalgoritmus.vynimky.NacitavanaMaticaException;
import floydovalgoritmus.vynimky.NespravnaVelkostMaticException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FloydovAlgoritmus {
    
    private static int[][] maticaVzdialenosti;
    private static int[][] maticaSmernikov;
    
    public static void main(String[] args) {
        
        try {
            maticaVzdialenosti = nacitajMaticu("MaticaVzdialenosti2.txt");
            maticaSmernikov = nacitajMaticu("MaticaSmernikov2.txt");
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (NacitavanaMaticaException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        
        try {
            kontrolaVstupu(maticaVzdialenosti, maticaSmernikov);
            
            zmenMaxZaNajvacsiuHodnotu(maticaVzdialenosti);
            
            System.out.println("Matica vzdialenosti");
            vypisMaticu(maticaVzdialenosti);
            System.out.println("Matica smernikov");
            vypisMaticu(maticaSmernikov);
            System.out.println("\n");
            
            vykonajFloydovAlgoritmus(maticaVzdialenosti, maticaSmernikov);
            
            System.out.println("Vysledna matica vzdialenosti");
            vypisMaticu(maticaVzdialenosti);
            System.out.println("Vysledna matica smernikov");
            vypisMaticu(maticaSmernikov);
            System.out.println("");
            
            vypisPostupnost(najdiNajrychlejsiuCestu(1, 4));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private static void vykonajFloydovAlgoritmus(int[][] vzdialenosti, int[][] smernikov) {
        for (int i = 0; i < vzdialenosti.length; i++) {
            for (int j = 0; j < vzdialenosti.length; j++) {
                for (int k = 0; k < vzdialenosti.length; k++) {
                    if (j == i || k == i) {
                        continue;
                    }
                    if (vzdialenosti[j][i] + vzdialenosti[i][k] < vzdialenosti[j][k]) {
                        vzdialenosti[j][k] = vzdialenosti[j][i] + vzdialenosti[i][k];
                        smernikov[j][k] = smernikov[i][k];
                    }
                }
            }
        }
    }
    
    private static void vypisMaticu(int[][] matica) {
        for (int i = 0; i < matica.length; i++) {
            for (int j = 0; j < matica[i].length; j++) {
                System.out.format("%4d", matica[i][j]);
            }
            System.out.println("");
        }
    }
    
    private static void zmenMaxZaNajvacsiuHodnotu(int[][] matica) {
        int sucetPrvkov = 0;
        for (int[] is : matica) {
            for (int i : is) {
                if (i != Integer.MAX_VALUE) {
                    sucetPrvkov += i;
                }
            }
        }
        
        for (int i = 0; i < matica.length; i++) {
            for (int j = 0; j < matica[i].length; j++) {
                if (matica[i][j] > sucetPrvkov) {
                    matica[i][j] = sucetPrvkov + 1;
                }
            }
        }
    }
    
    private static ArrayList<Integer> najdiNajrychlejsiuCestu(int zaciatok, int koniec) throws CestaNeexistujeException {
        ArrayList<Integer> ret = new ArrayList<>();
        ret.add(koniec);
        int z = zaciatok - 1;
        
        int dalsi = koniec;
        try {
            do {
                dalsi = maticaSmernikov[z][dalsi - 1];
                ret.add(dalsi);
            } while (dalsi != zaciatok);
        } catch (Exception e) {
            throw new CestaNeexistujeException(String.format("Cesta z %d do %d neexistuje", zaciatok, koniec));
        }
        
        return ret;
    }
    
    private static void vypisPostupnost(ArrayList<Integer> postupnost) {
        System.out.format("Najrychlejsia cesta z %d do %d:%n", postupnost.get(postupnost.size() - 1), postupnost.get(0));
        System.out.format("%4d", postupnost.get(postupnost.size() - 1));
        for (int i = postupnost.size() - 2; i >= 0; i--) {
            System.out.format(" - %d", postupnost.get(i));
        }
        System.out.println();
        System.out.format("Dlzka cesty: %d%n", maticaVzdialenosti[postupnost.get(postupnost.size() - 1) - 1][postupnost.get(0) - 1]);
        
    }
    
    private static void kontrolaVstupu(int[][] vzdialenosti, int[][] smernikov) {
        if (vzdialenosti.length != smernikov.length) {
            throw new NespravnaVelkostMaticException("Nesuhlasi velkost matic");
        }
    }
    
    private static int[][] nacitajMaticu(String nazovSuboru) throws FileNotFoundException, NacitavanaMaticaException {
        File subor = new File(nazovSuboru);
        if (!subor.exists()) {
            throw new NacitavanaMaticaException(String.format("Subor %s sa nenasiel", nazovSuboru));
        }
        
        Scanner sc = new Scanner(subor);
        Scanner scStr = null;
        
        int r = 0;
        int[][] ret = null;
        ArrayList<Integer> cisla = new ArrayList<>();
        
        while (sc.hasNextLine()) {
            String riadok = sc.nextLine();
            scStr = new Scanner(riadok);
            while (scStr.hasNextInt()) {
                int prvok = scStr.nextInt();
                if (prvok < 0) {
                    prvok = Integer.MAX_VALUE;
                }
                cisla.add(prvok);
            }
            if (ret == null) {
                ret = new int[cisla.size()][cisla.size()];
            }
            try {
                for (int i = 0; i < cisla.size(); i++) {
                    ret[r][i] = cisla.get(i);
                }
            } catch (Exception e) {
                throw new NacitavanaMaticaException(String.format("Necitavana matica zo suboru %s nie je stvorcova", nazovSuboru));
            }
            cisla.clear();
            r++;
        }
        if (scStr != null) {
            scStr.close();
        } else {
            throw new NacitavanaMaticaException(String.format("Subor %s je prazdny", nazovSuboru));
        }
        sc.close();
        return ret;
    }
    
}
