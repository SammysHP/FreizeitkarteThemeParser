package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.List;
import java.util.Locale;

public interface ThemeOptionGroup {
    /**
     * Get the name of this group.
     * 
     * This method uses the default locale provided by the JVM.
     * 
     * @return The name of this group or an empty string if it is not available.
     */
    public String getName();

    /**
     * Get the name of this group with a custom locale.
     * 
     * @return The name of this group or an empty string if it is not available.
     */
    public String getName(Locale locale);

    /**
     * Get a list of options in this group.
     * 
     * This list is unmodifiable.
     * 
     * @return List of options in this group.
     */
    public List<ThemeOption> getOptions();
}
