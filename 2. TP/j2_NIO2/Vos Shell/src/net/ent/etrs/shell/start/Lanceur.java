package net.ent.etrs.shell.start;

import net.ent.etrs.shell.start.commons.utils.AffichageConsole;
import net.ent.etrs.shell.start.commons.utils.LectureConsole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lanceur {
    public static void main(String[] args) throws IOException {
        String shell;
        String pathStr = System.getProperty("user.dir");
        Path path = Paths.get(pathStr);

        do {
            shell = LectureConsole.lectureChaineCaracteres(">>> " + pathStr + ">");

            String[] arguments = shell.split(" ");

            if (shell.equals("help")) {
                affichageHelp();
            } else if (shell.equals("ls")) {
                afficherLS(path);
            } else if (shell.equals("cd " + arguments[1])) {
                pathStr += "\\" + arguments[1];
                path = seDeplacerDansLeDossier(path, arguments[1]);
            }
        } while (!shell.contains("exit"));
    }

    private static Path seDeplacerDansLeDossier(Path path, String argument) {
        return Paths.get(path.toString() + "\\" + argument);
    }

    private static void remonterUnNiveau() {
    }

    private static void afficherLS(Path path) throws IOException {
        String format1 = "%-40s | %-20s | %-10s";

        AffichageConsole.afficherMessageAvecSautLigne(String.format(format1, "FILENAME", "SIZE", "TYPE"));
        AffichageConsole.afficherMessageAvecSautLigne("-".repeat(200));

        File dir = new File(path.toString());
        for (File item : Objects.requireNonNull(dir.listFiles())) {
            String directoryOrFile = "";
            if (item.isDirectory()) {
                directoryOrFile = "D";
            } else {
                directoryOrFile = "F";
            }
            AffichageConsole.afficherMessageAvecSautLigne(String.format(format1, item.getName(), item.length(), directoryOrFile));
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