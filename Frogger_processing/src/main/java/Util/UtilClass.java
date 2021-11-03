package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

/**
 * Repertoire de quelques methodes utiles pour le traitement de fichiers texte et la generation de nombres aleatoires
 */
public class UtilClass {

    /**
     * Prend en parametres un chemin vers un fichier texte de scores et un autre score t de type float. Trie t avec les valeurs de la liste de scores par ordre croissant, modifie le fichier texte selon le tri, puis renvoie une remarque selon la position de t
     * @param path Le chemin du fichier texte d'un leaderboard
     * @param t Un score a trier avec la liste des valeurs dans le fichier
     * @return Une remarque selon la position de t dans la liste des scores triee
     * @throws NumberFormatException si une chaîne lue par ParseFloat() ne correspond pas a un float
     * @throws StringIndexOutOfBoundsException si le nombre de lignes du fichier a lire est inferieur strictement a 9
     * @throws IOException lors d'une lecture de fichier defaillante
     */
    public static String record_treatment(Path path, float t)

    {
        String remark = null;
        File file = new File(path.toString());
        ArrayList<String> Snumbers = new ArrayList<String>();
        ArrayList<String> NSnumbers = new ArrayList<String>();
        try{
            float[] numbers = new float[10];
            for (String ligne : Files.readAllLines(path)) {
                Snumbers.add(ligne.substring(3, ligne.length()-1));
            }
            for (int i =0; i<9; i++){
                numbers[i]= Float.parseFloat(Snumbers.get(i));
            }
            numbers[9] =t;
            java.util.Arrays.sort(numbers);
            if (numbers[0]==t){
                remark = "You ranked first ! (°0°)";
            }
            else if (numbers[9]!=t){
                remark = "You are in the leaderboard ! (=^_^=)";
            }
            else {
                remark = "But you didn't rank in the leaderboard (>_<)";
            }
            System.out.println("LEADERBOARD : ");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            for (int i=0; i<9; i++){
                int k = i+1;
                NSnumbers.add(k +". " + numbers[i] + "s" + "\n" );
                System.out.print(NSnumbers.get(i));
                fw.write(NSnumbers.get(i));
            }
            fw.close();
        }
        catch (NumberFormatException | StringIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
        return remark;
    }

    /**
     * Prend en parametres un chemin vers un fichier texte de scores et un score t de type int. Trie t avec les valeurs de la liste de score par ordre decroissant, modifie le fichier texte selon le tri, puis renvoie une remarque selon la position de t
     * @param path Le chemin du fichier texte a exploiter
     * @param t Le score a trier avec ceux du fichier texte
     * @return Une remarque selon la position de t dans la liste des scores triee
     * @throws NumberFormatException si une chaîne lue par ParseInt() ne correspond pas a un float
     * @throws StringIndexOutOfBoundsException si le nombre de lignes du fichier a lire est inferieur strictement a 9
     * @throws IOException lors d'une lecture de fichier defaillante
     */
    public static String endless_treatment(Path path, int t)

    {
        String remark = null;
        File file = new File(path.toString());
        ArrayList<String> Snumbers = new ArrayList<String>();
        ArrayList<String> NSnumbers = new ArrayList<String>();
        try{
            int[] numbers = new int[10];
            for (String ligne : Files.readAllLines(path)) {
                Snumbers.add(ligne.substring(3, ligne.length()));
            }
            for (int i =0; i<9; i++){
                numbers[i]= Integer.parseInt(Snumbers.get(i));
            }
            numbers[9] =t;
            java.util.Arrays.sort(numbers);
            if (numbers[9]==t){
                remark = "You ranked first ! (°0°)";
            }
            else if (numbers[0]!=t){
                remark = "You are in the leaderboard ! (=^_^=)";
            }
            else {
                remark = "You didn't rank in the leaderboard (>_<)";
            }
            System.out.println("LEADERBOARD : ");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            for (int i=0; i<9; i++){
                int k = i+1;
                NSnumbers.add(k +". " + numbers[numbers.length-i-1] + "\n" );
                System.out.print(NSnumbers.get(i));
                fw.write(NSnumbers.get(i));
            }
            fw.close();
        }
        catch (NumberFormatException | StringIndexOutOfBoundsException | IOException e){
            e.printStackTrace();
        }
        return remark;
    }

/**
 * Genere un nombre aleatoire en deux bornes (comprises)
 * @param begin borne de debut
 * @param end borne de fin
 * @throws IllegalArgumentException si (end - begin) < 1
 */
    public static int random_btw(int begin, int end) {
        try {
        Random rand = new Random();
        return rand.nextInt(end - begin + 1) + begin;
    }
        catch(IllegalArgumentException e){
            e.printStackTrace();
            Random rand = new Random();
            return rand.nextInt(4) +1;
        }
    }

    /**
     * Recupere les donnees d'un leaderboard declare en Path (pour le bouton leaderboard)
     * @param path Le chemin du fichier a recuperer
     * @throws IOException lors d'une lecture de fichier defaillante
     */
    public static ArrayList<String> get_leaderboard_data(Path path) throws IOException {
        ArrayList<String> scores = new ArrayList<String>();
        String line;
        try{
            RandomAccessFile monFichier = new RandomAccessFile(String.valueOf(path), "r");
            for (int i=0; i<9; i++) {
                line = monFichier.readLine();
                scores.add(line);
            }
        }
        catch (IOException e) {
            throw new IOException("Chemin specifie introuvable");
        }

        return scores;
    }
}

