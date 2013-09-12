package de.freizeitkarteosm.themeconfigurator.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThemeEditor {
    private final File file;

    private final Theme theme;

    public ThemeEditor(final File file) throws FileNotFoundException,
            IOException {
        this.file = file;

        final StringBuilder content = new StringBuilder();

        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line);
            content.append("\n");
        }
        bufferedReader.close();

        this.theme = new Theme(content.toString());
    }

    public ThemeEditor(final String theme) {
        file = null;
        this.theme = new Theme(theme);
    }

    public void save() {
        save(file);
    }

    public void save(File file) {
        if (file == null) {
            throw new RuntimeException("No filename set!");
        }

        // TODO
    }

    public List<ThemeOption> getOptions() {
        List<ThemeOption> options = new ArrayList<ThemeOption>();

        List<ThemeOptionGroup> groups = theme.getGroups();
        for (ThemeOptionGroup group : groups) {
            options.addAll(group.getOptions());
        }

        return options;
    }

    public List<ThemeOptionGroup> getGroups() {
        return theme.getGroups();
    }
}
