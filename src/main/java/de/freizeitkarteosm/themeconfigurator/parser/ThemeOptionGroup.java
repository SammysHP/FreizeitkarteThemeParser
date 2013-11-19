package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.List;
import java.util.Locale;

public interface ThemeOptionGroup {
    /**
     * Status of a group.
     * 
     * The status gives an overview of the options in the group.
     */
    public enum GroupStatus {
        /**
         * No option of the group is enabled.
         */
        NONE,

        /**
         * Some options of the group are enabled.
         */
        SOME,

        /**
         * All options of the group are enabled or the group is empty.
         */
        ALL
    }

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

    /**
     * Enables this group.
     */
    public void enable();

    /**
     * Disables this group.
     */
    public void disable();

    /**
     * Return the status of this group.
     * 
     * If the group is empty, the status will be {@link GroupStatus#ALL}
     * 
     * @return Status of this group
     */
    public GroupStatus getStatus();
}
