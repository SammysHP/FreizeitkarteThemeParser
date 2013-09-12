package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class MutableThemeOption implements ThemeOption {
    private ThemeOptionGroup group;

    private Map<String, String> name;

    private boolean status;

    private int startLine;

    private int endLine;

    public MutableThemeOption() {
        group = null;
        name = new HashMap<String, String>();
        status = false;
        startLine = -1;
        endLine = -1;
    }

    public MutableThemeOption(final ThemeOptionGroup group,
            final Map<Locale, String> name, final boolean status,
            final int startLine, final int endLine) {
        this.group = group;

        this.name = new HashMap<String, String>();
        for (Locale l : name.keySet()) {
            this.name.put(l.getISO3Language(), name.get(l));
        }

        this.status = status;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    @Override
    public void enable() {
        status = true;
    }

    @Override
    public void disable() {
        status = false;
    }

    @Override
    public boolean getStatus() {
        return status;
    }

    @Override
    public ThemeOptionGroup getGroup() {
        return group;
    }

    public MutableThemeOption setGroup(final ThemeOptionGroup group) {
        this.group = group;
        return this;
    }

    @Override
    public String getName() {
        return getName(Locale.getDefault());
    }

    @Override
    public String getName(Locale locale) {
        final String language = locale.getISO3Language();

        if (name.containsKey(language)) {
            return name.get(language);
        }

        return ""; // TODO: Throw exception or return emty string?
    }

    public MutableThemeOption setName(final Locale locale, final String name) {
        this.name.put(locale.getISO3Language(), name);
        return this;
    }

    @Override
    public int getStartLine() {
        return startLine;
    }

    public MutableThemeOption setStartLine(final int line) {
        startLine = line;
        return this;
    }

    @Override
    public int getEndLine() {
        return endLine;
    }

    public MutableThemeOption setEndLine(final int line) {
        endLine = line;
        return this;
    }
}
