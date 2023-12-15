package net.ent.etrs.shell.start;

import net.ent.etrs.shell.start.commons.utils.AffichageConsole;
import net.ent.etrs.shell.start.commons.utils.LectureConsole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lanceur {
    static Path path = Paths.get(System.getProperty("user.dir"));
    public static void main(String[] args) {
        String shell;


        try {
            do {
                shell = LectureConsole.lectureChaineCaracteres(">>> " + path + ">");

                String[] arguments = shell.split(" ");
                String nomDossierOuFichierAvecEspace = "";

                if (shell.equals("help")) {
                    affichageHelp();
                }

                if (shell.equals("ls")) {
                    afficherLS(path);
                }

                if (shell.equals("cd ..") || shell.equals("cd")) {
                    path = remonterUnNiveau(path);
                } else if (shell.startsWith("cd ")) {
                    if (arguments.length > 1) {
                        nomDossierOuFichierAvecEspace = recupererTousLesArguments(arguments);
                        path = seDeplacerDansLeDossier(path, nomDossierOuFichierAvecEspace);
                    } else {
                        AffichageConsole.afficherErreur("Il faut saisir un nom de dossier !");
                    }
                }

                if (shell.startsWith("mkdir ")) {
                    if (arguments.length > 1) {
                        nomDossierOuFichierAvecEspace = recupererTousLesArguments(arguments);
                        creerUnDossier(path, nomDossierOuFichierAvecEspace);
                    } else {
                        AffichageConsole.afficherErreur("Il faut saisir un nom de dossier !");
                    }
                }

                if (shell.startsWith("touch ")) {
                    if (arguments.length > 1) {
                        nomDossierOuFichierAvecEspace = recupererTousLesArguments(arguments);
                        creerUnFichier(path, nomDossierOuFichierAvecEspace);
                    } else {
                        AffichageConsole.afficherErreur("Il faut saisir un nom de fichier !");
                    }
                }

                if (shell.contains(">>>")) {
                    String[] separationTexteEtNomFichier = shell.split(">>>");
                    String contenu = separationTexteEtNomFichier[0];
                    String nomFichier = separationTexteEtNomFichier[1];
                    remplirFichier(nomFichier, contenu);
                }
            } while (!shell.contains("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void remplirFichier(String nomFichier, String contenu) {
        byte[] bytes = contenu.getBytes();
        Path fichier = Paths.get(path + "\\" + nomFichier);
        try {
            Files.write(fichier, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void creerUnFichier(Path path, String nomDossierOuFichierAvecEspace) {
        try {
            Path oui = Paths.get(path + "\\" + nomDossierOuFichierAvecEspace);
            if (Files.exists(oui)) {
                AffichageConsole.afficherErreur(String.format("Le fichier %s existe déjà !", oui.getFileName()));
            } else {
                Files.createFile(oui);
                AffichageConsole.afficherMessageAvecSautLigne(String.format("Le fichier %s a été créé avec succès !", oui.getFileName()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void creerUnDossier(Path path, String nomDossierAvecEspace) {
        try {
            Path oui = Paths.get(path + "\\" + nomDossierAvecEspace);
            if (Files.exists(oui)) {
                AffichageConsole.afficherErreur(String.format("Le dossier %s existe déjà !", oui.getFileName()));
            } else {
                Files.createDirectory(oui);
                AffichageConsole.afficherMessageAvecSautLigne(String.format("Le dossier %s a été créé avec succès !", oui.getFileName()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String recupererTousLesArguments(String[] arguments) {
        StringBuilder nomDossierAvecEspace = new StringBuilder(arguments[1]);
        if (arguments.length > 2) {
            nomDossierAvecEspace.append(" ");
            for (int i = 2; i < arguments.length; i++) {
                if (i != arguments.length - 1) {
                    nomDossierAvecEspace.append(arguments[i]).append(" ");
                } else {
                    nomDossierAvecEspace.append(arguments[i]);
                }
            }
        }
        return nomDossierAvecEspace.toString();
    }

    private static Path seDeplacerDansLeDossier(Path path, String argument) {
        File file = new File(path.toString() + "\\" + argument);
        if (!file.exists() || file.isFile()) {
            AffichageConsole.afficherErreur(file + " n'est pas un dossier valide !");
            return path;
        }
        return Paths.get(path + "\\" + argument);
    }

    private static Path remonterUnNiveau(Path path) {
        if (path.equals(path.getRoot())) {
            AffichageConsole.afficherErreur("Tu as déjà atteint la racine !");
            return path;
        }
        return path.getParent();
    }

    private static void afficherLS(Path path) throws IOException {
        File dir = new File(path.toString());

        String format1 = "%-40s | %-20s | %-10s";
        String directoryOrFile;

        AffichageConsole.afficherMessageAvecSautLigne(String.format(format1, "FILENAME", "SIZE", "TYPE"));
        AffichageConsole.afficherMessageAvecSautLigne("-".repeat(200));

        if (Objects.requireNonNull(dir.listFiles()).length == 0) {
            AffichageConsole.afficherMessageAvecSautLigne("Il n'y a aucun dossier ou fichier là dedans");
        } else {
            for (File item : Objects.requireNonNull(dir.listFiles())) {
                if (item.isDirectory()) {
                    directoryOrFile = "D";
                } else {
                    directoryOrFile = "F";
                }
                AffichageConsole.afficherMessageAvecSautLigne(String.format(format1, item.getName(), item.length(), directoryOrFile));
            }
        }
    }

    private static void affichageHelp() {
        String format = "%-20s %-40s";

        List<String> help = new ArrayList<>();
        help.add(String.format(format, "ls", ": liste les fichiers"));
        help.add(String.format(format, "cd $repertoire", ": se déplace vers $chemin"));
        help.add(String.format(format, "cd ..", ": remonte d'un niveau"));
        help.add(String.format(format, "mkdir $dirName", ": crée le dossier $dirName"));
        help.add(String.format(format, "rmdir $dirName", ": supprime le dossier $dirName"));
        help.add(String.format(format, "touch $fileName", ": crée le fichier $fileName"));
        help.add(String.format(format, "cat $fileName", ": affiche le fichier $fileName"));
        help.add(String.format(format, "rm $fileName", ": supprime le fichier $fileName"));
        help.add(String.format(format, "$txt>>>$fileName", ": écrit $txt à la suite dans $fileName"));
        help.add(String.format(format, "exit", ": quitte le programme"));

        AffichageConsole.afficherMessageAvecSautLigne("COMMANDS :");

        for (String s : help) {
            AffichageConsole.afficherMessageAvecSautLigne(String.format("%s", s.toLowerCase()));
        }
    }


}