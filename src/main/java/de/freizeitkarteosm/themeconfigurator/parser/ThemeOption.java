package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.Locale;

public interface ThemeOption {
    public void enable();

    public void disable();

    public boolean getStatus();

    public ThemeOptionGroup getGroup();

    public String getName();

    public String getName(Locale locale);

    public int getStartLine();

    public int getEndLine();
}
