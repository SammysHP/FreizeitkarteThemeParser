package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.List;
import java.util.Locale;

public interface ThemeOptionGroup {
    public String getName();

    public String getName(Locale locale);

    public List<ThemeOption> getOptions();
}
