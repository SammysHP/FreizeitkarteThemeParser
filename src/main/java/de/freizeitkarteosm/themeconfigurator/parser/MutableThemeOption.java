package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class MutableThemeOption implements ThemeOption {
    private ThemeOptionGroup group;

    private Map<String, String> name;

    private boolean status;

    private List<Integer> startLines;

    private List<Integer> endLines;

    private String id;

    /**
     * Constructs an empty option.
     */
    public MutableThemeOption() {
        group = null;
        name = new HashMap<String, String>();
        status = false;
        startLines = new ArrayList<Integer>();
        endLines = new ArrayList<Integer>();
    }

    /**
     * Cunstructs an non empty option.
     * 
     * @param group
     *            Reference to the group of this option
     * @param name
     *            Translations of the name of this option
     * @param id
     *            The id of this option used to mark the section
     * @param status
     *            If this option is enabled or not
     * @param startLine
     *            The first line of the corresponding section
     * @param endLine
     *            The last line of the corresponding section
     */
    public MutableThemeOption(final ThemeOptionGroup group,
            final Map<Locale, String> name, final String id,
            final boolean status, final List<Integer> startLines,
            final List<Integer> endLines) {
        this.group = group;

        this.name = new HashMap<String, String>();
        for (Locale l : name.keySet()) {
            this.name.put(l.getLanguage(), name.get(l));
        }

        this.id = id;
        this.status = status;
        this.startLines = new ArrayList<Integer>(startLines);
        this.endLines = new ArrayList<Integer>(endLines);
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
        return Util.getTranslation(name);
    }

    @Override
    public String getName(Locale locale) {
        return Util.getTranslation(name, locale);
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
        this.name.put(locale.getLanguage(), name);
        return this;
    }

    @Override
    public List<Integer> getStartLines() {
        return Collections.unmodifiableList(startLines);
    }

    /**
     * Sets the line where the section starts in the theme.
     * 
     * @param line
     *            The first line of the corresponding section
     * @return This option
     */
    public MutableThemeOption setStartLines(final List<Integer> lines) {
        startLines = new ArrayList<Integer>(lines);
        return this;
    }

    @Override
    public List<Integer> getEndLines() {
        return Collections.unmodifiableList(endLines);
    }

    /**
     * Sets the line where the section ends in the theme.
     * 
     * @param line
     *            The last line of the corresponding section
     * @return This option
     */
    public MutableThemeOption setEndLines(final List<Integer> lines) {
        endLines = new ArrayList<Integer>(lines);
        return this;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Sets the id of this option.
     * 
     * @param id
     *            The id of this option
     * @return This option
     */
    public MutableThemeOption setId(final String id) {
        this.id = id;
        return this;
    }

    @Override
    public void setStatus(boolean status) {
        this.status = status;
    }
}
