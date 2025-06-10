package LLDProjects.FileDirectoryLLD;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ---------- File Class ----------
class File {
    String fileName;
    String fileExtension;
    String fileContent;

    public File(String fileName, String fileExtension, String fileContent) {
        this.fileName = fileName;
        this.fileExtension = fileExtension;
        this.fileContent = fileContent;
    }
}

// ---------- Directory Class ----------
class Directory {
    String name;
    Directory parent;
    List<Directory> subDirs = new ArrayList<>();
    List<File> files = new ArrayList<>();

    public Directory(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
    }

    public void addFile(File file) {
        files.add(file);
    }

    public void addDirectory(Directory dir) {
        subDirs.add(dir);
    }

    public Directory getSubDirectory(String name) {
        for (Directory d : subDirs) {
            if (d.name.equals(name)) return d;
        }
        return null;
    }

    public Directory getParent() {
        return parent;
    }

    public void ls() {
        for (Directory d : subDirs) System.out.println(d.name + "/");
        for (File f : files) System.out.println(f.fileName + "." + f.fileExtension);
    }

    public void pwd() {
        List<String> path = new ArrayList<>();
        Directory temp = this;
        while (temp != null) {
            path.add(0, temp.name);
            temp = temp.parent;
        }
        System.out.println("/" + String.join("/", path));
    }
}

// ---------- FileSystem Class ----------
public class FileSystem {
    private Directory root;
    private Directory current;

    public FileSystem() {
        root = new Directory("root", null);
        current = root;
    }

    public void ls() {
        current.ls();
    }

    public void cd(String name) {
        if (name.equals("..")) {
            cdUp();
            return;
        }

        Directory next = current.getSubDirectory(name);
        if (next != null) current = next;
        else System.out.println("Directory not found: " + name);
    }

    public void cdUp() {
        if (current.getParent() != null) current = current.getParent();
        else System.out.println("Already at root.");
    }

    public void pwd() {
        current.pwd();
    }

    public void mkdir(String name) {
        current.addDirectory(new Directory(name, current));
    }

    public void touch(String name, String ext, String content) {
        current.addFile(new File(name, ext, content));
    }


    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Scanner sc = new Scanner(System.in);

        System.out.println("Simple Shell. Commands: ls, pwd, cd <dir>, mkdir <name>, touch <name.ext>, exit");
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine().trim();
            String[] parts = input.split("\\s+");

            if (parts.length == 0) continue;

            String command = parts[0];

            switch (command) {
                case "ls":
                    fs.ls();
                    break;
                case "pwd":
                    fs.pwd();
                    break;
                case "cd":
                    if (parts.length < 2) {
                        System.out.println("Usage: cd <directory>");
                    } else {
                        fs.cd(parts[1]);
                    }
                    break;
                case "mkdir":
                    if (parts.length < 2) {
                        System.out.println("Usage: mkdir <name>");
                    } else {
                        fs.mkdir(parts[1]);
                    }
                    break;
                case "touch":
                    if (parts.length < 2 || !parts[1].contains(".")) {
                        System.out.println("Usage: touch <filename.ext>");
                    } else {
                        String[] nameParts = parts[1].split("\\.");
                        fs.touch(nameParts[0], nameParts[1], "sample content");
                    }
                    break;
                case "exit":
                    System.out.println("Bye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}