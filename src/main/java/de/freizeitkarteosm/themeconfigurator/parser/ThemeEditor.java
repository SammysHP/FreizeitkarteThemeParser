package de.freizeitkarteosm.themeconfigurator.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThemeEditor {
    private File file;

    private final Theme theme;

    /**
     * Loads a theme from a file.
     * 
     * @param file
     *            The file of the theme
     * @throws FileNotFoundException
     * @throws IOException
     */
    public ThemeEditor(final File file) throws FileNotFoundException, IOException {
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

    /**
     * Loads a theme from a string.
     * 
     * {@link #save()} will throw an exception if the filename was not set manually with {@link #setFile(File)}. Use
     * {@link #save(File)} instead.
     * 
     * @param theme
     *            A string containing the theme
     */
    public ThemeEditor(final String theme) {
        file = null;
        this.theme = new Theme(theme);
    }

    /**
     * Saves the theme.
     */
    public void save() {
        save(file);
    }

    /**
     * Saves the theme in a given file.
     * 
     * @param file
     *            The filename where the theme should be stored
     */
    public void save(File file) {
        if (file == null) {
            throw new RuntimeException("No filename set!");
        }

        // TODO
    }

    /**
     * Returns a list of all available options.
     * 
     * @return List of all available options
     */
    public List<ThemeOption> getOptions() {
        List<ThemeOption> options = new ArrayList<ThemeOption>();

        List<ThemeOptionGroup> groups = theme.getGroups();
        for (ThemeOptionGroup group : groups) {
            options.addAll(group.getOptions());
        }

        return options;
    }

    /**
     * Returns a list of all groups.
     * 
     * @return List of all groups
     */
    public List<ThemeOptionGroup> getGroups() {
        return theme.getGroups();
    }

    /**
     * Returns the file where this theme should be stored.
     * 
     * @return The file where this theme should be stored
     */
    public File getFile() {
        return file;
    }

    /**
     * Sets the file where this theme should be stored.
     * 
     * @param file
     *            The file where this theme should be stored
     */
    public void setFile(File file) {
        this.file = file;
    }
}
