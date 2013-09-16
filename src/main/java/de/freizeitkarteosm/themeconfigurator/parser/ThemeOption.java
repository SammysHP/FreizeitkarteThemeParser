package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.Locale;

public interface ThemeOption {
    /**
     * Enables this option.
     */
    public void enable();

    /**
     * Disables this option.
     */
    public void disable();

    /**
     * Sets the status of this option.
     */
    public void setStatus(boolean status);

    /**
     * Return the status of this option.
     * 
     * @return true, if option is enabled
     */
    public boolean getStatus();

    /**
     * Get a reference to the group of this option.
     * 
     * @return The group of this option
     */
    public ThemeOptionGroup getGroup();

    /**
     * Get the name of this option.
     * 
     * This method uses the default locale provided by the JVM.
     * 
     * @return The name of this option or an empty string if it is not available
     */
    public String getName();

    /**
     * Get the name of this option with a custom locale.
     * 
     * @return The name of this option or an empty string if it is not available
     */
    public String getName(Locale locale);

    /**
     * Get the line where the section starts in the theme.
     * 
     * @return The first line of the corresponding section
     */
    public int getStartLine();

    /**
     * Get the line where the section ends in the theme.
     * 
     * @return The last line of the corresponding section
     */
    public int getEndLine();

    /**
     * Get the id of this option.
     * 
     * @return The id of this option
     */
    public String getId();
}
