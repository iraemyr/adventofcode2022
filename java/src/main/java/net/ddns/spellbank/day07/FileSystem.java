package net.ddns.spellbank.day07;

import java.util.ArrayList;
import java.util.List;

public class FileSystem {
    String name;
    private Long size;
    boolean isFile;
    List<FileSystem> children;
    FileSystem parent;

    public FileSystem(String name, Long size, boolean file, FileSystem parent) {
        this.name = name;
        this.size = size;
        this.isFile = file;
        this.children = new ArrayList<FileSystem>();
        this.parent = parent;
    }

    public void addChild(FileSystem child) {
        this.children.add(child);
    }

    public FileSystem getDir(String name) {
        for (var child : children) {
            if (child.name.equals(name))
                return child;
        }
        return null;
    }

    public long getSize() {
        if (size == null) {
            size = 0L;
            for (var child : children)
                size += child.getSize();
        }
        return size;
    }

    public static FileSystem parseFileSystem(String[] lines) {
        FileSystem root = new FileSystem("/", null, false, null);
        int index = 1;
        FileSystem current = root;
        while (index < lines.length) {
            var fields = lines[index++].split(" ");
            switch (fields[1]) {
                case "cd":
                    if (fields[2].equals("/")) {
                        current = root;
                    } else if (fields[2].equals("..")) {
                        current = current.parent;
                    } else {
                        var dir = current.getDir(fields[2]);
                        if (dir != null) {
                            current = dir;
                        } else
                            System.out.println("Invalid directory: " + fields[2]);
                    }
                    break;
                case "ls":
                    while (index < lines.length && !lines[index].startsWith("$")) {
                        var attributes = lines[index++].split(" ");
                        if (attributes[0].equals("dir")) {
                            current.addChild(new FileSystem(attributes[1], null, false, current));
                        } else {
                            current.addChild(
                                    new FileSystem(attributes[1], Long.parseLong(attributes[0]), true, current));
                        }
                    }
                    break;
                default:
                    index++;
            }
        }
        return root;
    }
}
