package Util;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UtilClassTest {

    @Test
    void record_treatment() throws IOException {
        Path record = Paths.get("test/GameCommons/test_records");
        ArrayList<String> scores = new ArrayList<String>();
        String line;
        RandomAccessFile monFichier = new RandomAccessFile(String.valueOf(record), "r");
        for (int i=0; i<9; i++) {
            line = monFichier.readLine();
            scores.add(line);
            }
        monFichier.close();
        String string = "";
        // On verifie le type
        assertEquals(string.getClass(), UtilClass.record_treatment(record, 20000).getClass());
        // On verifie qu'un score compris entre le 9e et le 1er est ajoute
        float first = Float.parseFloat(scores.get(0).split(" ")[1].split("s")[0]);
        float last = Float.parseFloat(scores.get(8).split(" ")[1].split("s")[0]);
        float number = last - (last - first)/2;
        UtilClass.record_treatment(record, number);
        ArrayList<String> scores2 = new ArrayList<String>();
        RandomAccessFile monFichier2 = new RandomAccessFile(String.valueOf(record), "r");
        for (int i=0; i<9; i++) {
            line = monFichier2.readLine();
            scores2.add(line.split(" ")[1].split("s")[0]);
        }
        monFichier2.close();
        assertTrue(scores2.contains(Float.toString(number)));
    }

    @Test
    void endless_treatment() throws IOException {
        Path record = Paths.get("test/GameCommons/test_endless_records");
        ArrayList<String> scores = new ArrayList<String>();
        String line;
        RandomAccessFile monFichier = new RandomAccessFile(String.valueOf(record), "r");
        for (int i=0; i<9; i++) {
            line = monFichier.readLine();
            scores.add(line);
        }
        monFichier.close();
        String string = "";
        // On verifie le type
        assertEquals(string.getClass(), UtilClass.endless_treatment(record, -20).getClass());
        // On verifie qu'un score compris entre le 9e et le 1er est ajoute
        int first = Integer.parseInt(scores.get(0).split(" ")[1]);
        int last = Integer.parseInt(scores.get(8).split(" ")[1]);
        int number = first - (first - last)/2;
        UtilClass.endless_treatment(record, number);
        ArrayList<String> scores2 = new ArrayList<String>();
        RandomAccessFile monFichier2 = new RandomAccessFile(String.valueOf(record), "r");
        for (int i=0; i<9; i++) {
            line = monFichier2.readLine();
            scores2.add(line.split(" ")[1]);
        }
        monFichier2.close();
        assertTrue(scores2.contains(Integer.toString(number)));
    }

    @Test
    void random_btw() {
        int random = UtilClass.random_btw(-5, 5);
        // On verifie que le nombre respecte les bornes
        assertTrue(random > -5 && random < 5);
    }

    @Test
    void get_leaderboard_data() throws IOException {
        // On verifie que la fonction recupere bien les lignes des fichiers textes
        Path record1 = Paths.get("src/main/java/GameCommons/Record_easy");
        Path record2 = Paths.get("src/main/java/GameCommons/Record_hard");
        Path record3 = Paths.get("src/main/java/GameCommons/Record_endless");
        ArrayList<Path> paths = new ArrayList<Path>();
        paths.add(record1);
        paths.add(record2);
        paths.add(record3);
        for (Path path : paths) {
            ArrayList<String> scores = UtilClass.get_leaderboard_data(path);
            String line;
            RandomAccessFile monFichier = new RandomAccessFile(String.valueOf(path), "r");
            for (int i=0; i<9; i++) {
                line = monFichier.readLine();
                assertEquals(line, scores.get(i));
            }
        }
    }




}