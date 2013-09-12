package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

class MutableThemeOptionGroup implements ThemeOptionGroup {
    private Map<String, String> name;

    private List<ThemeOption> options;

    /**
     * Constructs an empty group.
     */
    public MutableThemeOptionGroup() {
        name = new HashMap<String, String>();
        options = new ArrayList<ThemeOption>();
    }

    /**
     * Constructs a group with given translated names.
     * 
     * @param name
     *            Translations of the name of this group
     */
    public MutableThemeOptionGroup(final Map<Locale, String> name) {
        this.name = new HashMap<String, String>();
        for (Locale l : name.keySet()) {
            this.name.put(l.getISO3Language(), name.get(l));
        }

        options = new ArrayList<ThemeOption>();
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

        return ""; // TODO: Throw exception or return empty string?
    }

    /**
     * Adds a translation for the name of this group.
     * 
     * @param locale
     *            The locale of the language of the translation
     * @param name
     *            The translation of the name
     * @return This group
     */
    public MutableThemeOptionGroup setName(final Locale locale,
            final String name) {
        this.name.put(locale.getISO3Language(), name);
        return this;
    }

    @Override
    public List<ThemeOption> getOptions() {
        return Collections.unmodifiableList(options);
    }

    /**
     * Adds an option to this group.
     * 
     * @param option
     *            The option to add to this group
     * @return This group
     */
    public MutableThemeOptionGroup addOption(final ThemeOption option) {
        this.options.add(option);
        return this;
    }

    @Override
    public String toString() {
        return getName();
    }
}
