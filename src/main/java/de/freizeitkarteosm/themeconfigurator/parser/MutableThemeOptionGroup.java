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

    public MutableThemeOptionGroup() {
	name = new HashMap<String, String>();
	options = new ArrayList<ThemeOption>();
    }

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

    public MutableThemeOptionGroup setName(final Locale locale,
	    final String name) {
	this.name.put(locale.getISO3Language(), name);
	return this;
    }

    @Override
    public List<ThemeOption> getOptions() {
	return Collections.unmodifiableList(options);
    }

    public MutableThemeOptionGroup addOption(final ThemeOption option) {
	this.options.add(option);
	return this;
    }

}
