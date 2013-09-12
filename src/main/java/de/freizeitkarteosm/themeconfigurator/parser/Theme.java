package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.ArrayList;
import java.util.List;

class Theme {
    private final String theme;

    private final List<MutableThemeOptionGroup> groups;

    public Theme(final String theme) {
	this.theme = theme;
	groups = new ArrayList<MutableThemeOptionGroup>();

	parse();
    }

    private void parse() {
	// TODO
    }

    public List<ThemeOptionGroup> getGroups() {
	return null; // TODO
    }
}
