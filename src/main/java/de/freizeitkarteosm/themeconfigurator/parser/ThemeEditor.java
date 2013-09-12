package de.freizeitkarteosm.themeconfigurator.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ThemeEditor {
    private final File file;

    private final Theme theme;

    public ThemeEditor(final File file) {
	this.file = file;
	theme = new Theme(""); // TODO: Content from file
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
