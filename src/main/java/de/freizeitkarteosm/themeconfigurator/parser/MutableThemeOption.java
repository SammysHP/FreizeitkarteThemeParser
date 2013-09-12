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

    /**
     * Constructs an empty option.
     */
    public MutableThemeOption() {
        group = null;
        name = new HashMap<String, String>();
        status = false;
        startLine = -1;
        endLine = -1;
    }

    /**
     * Cunstructs an non empty option.
     * 
     * @param group
     *            Reference to the group of this option
     * @param name
     *            Translations of the name of this option
     * @param status
     *            If this option is enabled or not
     * @param startLine
     *            The first line of the corresponding section
     * @param endLine
     *            The last line of the corresponding section
     */
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

    /**
     * Sets a reference to the group of this option.
     * 
     * @param group
     *            The group of this option
     * @return This option
     */
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

    /**
     * Adds a translation for the name of this option.
     * 
     * @param locale
     *            The locale of the language of the translation
     * @param name
     *            The translation of the name
     * @return This option
     */
    public MutableThemeOption setName(final Locale locale, final String name) {
        this.name.put(locale.getISO3Language(), name);
        return this;
    }

    @Override
    public int getStartLine() {
        return startLine;
    }

    /**
     * Sets the line where the section starts in the theme.
     * 
     * @param line
     *            The first line of the corresponding section
     * @return This option
     */
    public MutableThemeOption setStartLine(final int line) {
        startLine = line;
        return this;
    }

    @Override
    public int getEndLine() {
        return endLine;
    }

    /**
     * Sets the line where the section ends in the theme.
     * 
     * @param line
     *            The last line of the corresponding section
     * @return This option
     */
    public MutableThemeOption setEndLine(final int line) {
        endLine = line;
        return this;
    }

    @Override
    public String toString() {
        return getName();
    }
}
