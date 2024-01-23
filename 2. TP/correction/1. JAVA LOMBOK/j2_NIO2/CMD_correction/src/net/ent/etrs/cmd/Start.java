package net.ent.etrs.cmd;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Start {
    
    private static Path pathLoaded = Path.of(System.getProperty("user.home"));
    
    private static boolean exit = false;
    
    public static void main(String[] args) {

        try {
            while (!exit) {

                System.out.print(">>> " + pathLoaded.toAbsolutePath() + ">");
                Scanner sc = new Scanner(System.in);

                String enterCommand = sc.nextLine();
                
                if (enterCommand.equals("help")) {
                    System.out.println("COMMANDS :");
                    System.out.printf("%-20s : %s %n", "ls", "liste les fichiers");
                    System.out.printf("%-20s : %s %n", "cd $repertoire", "se deplace vers $repertoire");
                    System.out.printf("%-20s : %s %n", "cd ..", "remonte d'un niveau");
                    System.out.printf("%-20s : %s %n", "mkdir $dirName", "crée le dossier $dirName");
                    System.out.printf("%-20s : %s %n", "rmdir $dirName", "supprime le dossier $dirName");
                    System.out.printf("%-20s : %s %n", "touch $fileName", "crée le fichier $fileName");
                    System.out.printf("%-20s : %s %n", "cat $fileName", "affiche le fichier $fileName");
                    System.out.printf("%-20s : %s %n", "rm $fileName", "supprime le fichier $fileName");
                    System.out.printf("%-20s : %s %n", "$txt>>>$fileName", "écrie $txt à la suite dans $fileName");
                    System.out.printf("%-20s : %s %n", "switch" , "Changer de partition");
                    System.out.printf("%-20s : %s %n", "exit", "quitte le programme");
                }
                
                if (enterCommand.equals("ls")) {
                    showFileList();
                }
                
                if (enterCommand.startsWith("cd ")) {
                    
                    if (enterCommand.equals("cd ..")) {
                        Path newPath = pathLoaded.getParent();
                        if (Objects.nonNull(newPath)) {
                            pathLoaded = newPath;
                        } else {
                            System.out.println("Impossible de remonter");
                        }
                    } else {
                        
                        String newPath = enterCommand.split("\\s+")[1];
                        Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + newPath + File.separator);
                        if (p.toFile().isDirectory()) {
                            pathLoaded = p;
                        } else {
                            System.out.println(p.toAbsolutePath() + " is not a directory");
                        }
                    }
                }
                
                if (enterCommand.startsWith("mkdir ")) {
                    String newDir = enterCommand.split("\\s+")[1];
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + newDir + File.separator);
                    try {
                        Files.createDirectory(p);
                    } catch (FileAlreadyExistsException e) {
                        System.out.println("Directory already exist");
                    }
                }
                
                if (enterCommand.startsWith("rmdir ")) {
                    String dirToDelete = enterCommand.split("\\s+")[1];
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + dirToDelete + File.separator);
                    try {
                        if (p.toFile().isFile()) {
                            System.out.println("pour supprimer un ficher voir \"rm\"");
                        } else {
                            Files.delete(p);
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Dossier introuvable");
                    } catch (DirectoryNotEmptyException e) {
                        System.out.println(p.toAbsolutePath() + " is not empty");
                    }
                }
                
                if (enterCommand.startsWith("touch ")) {
                    String newFile = enterCommand.split("\\s+")[1];
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + newFile);
                    try {
                        Files.createFile(p);
                    } catch (FileAlreadyExistsException e) {
                        System.out.println("File already exist");
                    }
                }
                
                if (enterCommand.startsWith("rm " )) {
                    String fileToDelete = enterCommand.split("\\s+")[1];
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + fileToDelete);
                    try {
                        if (p.toFile().isDirectory()) {
                            System.out.println("pour supprimer un répertoire voir \"rmdir\"");
                        } else {
                            Files.delete(p);
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("Fichier introuvable");
                    }
                }
                
                if (enterCommand.contains(">>>")) {
                    String[] tab = enterCommand.split(">>>");
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + tab[1]);
                    try {
                        Files.write(p, tab[0].getBytes(), StandardOpenOption.APPEND);
                    } catch (NoSuchFileException e) {
                        System.out.println(p.toAbsolutePath() + " not exist");
                    }
                }
                
                if (enterCommand.startsWith("cat ")) {
                    String readFile = enterCommand.split("\\s+")[1];
                    Path p = Paths.get(pathLoaded.toAbsolutePath() + File.separator + readFile);
                    try {
                        Files.lines(p).forEach(System.out::println);
                    } catch (NoSuchFileException e) {
                        System.out.println(p.toAbsolutePath() + " not exist");
                    }
                }

                if (enterCommand.startsWith("switch")) {
                    File[] list = File.listRoots();
                    boolean ok = false;
                    do {
                        for (int i = 1; i <= list.length; i++) {
                            System.out.println(i + " : " + list[i-1]);
                        }
                        Scanner sc2 = new Scanner(System.in);
                        try {
                            int r = sc2.nextInt();
                            if (r < 1 || r > list.length) {
                                System.out.println("Choix incorrect");
                            } else {
                                Start.pathLoaded = Path.of(list[r - 1].toURI());
                                ok = true;
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("Choix incorrect");
                        }
                    } while (!ok);
                }
                
                
                if (enterCommand.equals("exit")) {
                    exit = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showFileList() throws IOException {
        DirectoryStream<Path> stream = Files.newDirectoryStream(pathLoaded);
        try {
            System.out.printf("%-40s | %15s | %5s %n", "FILENAME", "SIZE", "TYPE");
            System.out.println("-------------------------------------------------------------------------------------------");
            stream.forEach((p) -> System.out.printf("%-40s | %15d | %5s %n", p.toFile().getName(), p.toFile().length(), p.toFile().isDirectory() ? "D": "F"));
        } finally {
            stream.close();
        }
    }
    
    
}
