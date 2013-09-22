package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Theme {
    private static final Pattern PATTERN_HEADER = Pattern.compile("<!--BEGINN_THEME_CONFIGURATOR\\s*(.+?)\\s*ENDE_THEME_CONFIGURATOR-->", Pattern.DOTALL);
    private static final Pattern PATTERN_HEADER_GROUP = Pattern.compile("^\\s*@@@(.+?)\\|\\|\\|(.+)", Pattern.MULTILINE);
    private static final Pattern PATTERN_HEADER_OPTION = Pattern.compile("^\\s*###(.+?)@@@(.+?)\\|\\|\\|(.+)", Pattern.MULTILINE);

    private static final String PATTERN_OPTION_START_PRE = "[BEGINN_KONFIG_";
    private static final String PATTERN_OPTION_START_POST = "]";

    private static final String PATTERN_OPTION_END_PRE = "[ENDE_KONFIG_";
    private static final String PATTERN_OPTION_END_POST = "]";

    private static final String PATTERN_COMMENT_START = "<!--";
    private static final String PATTERN_COMMENT_END = "-->";

    private final String theme;
    private final String[] lineTheme;

    // We use a LinkedHashMap to preserve the order of the groups as they are in the theme file.
    private final LinkedHashMap<String, MutableThemeOptionGroup> groups;

    /**
     * Parses a theme from a given string.
     * 
     * @param theme
     *            The theme xml
     */
    public Theme(final String theme) {
        this.theme = theme;
        lineTheme = theme.split("\n");
        groups = new LinkedHashMap<String, MutableThemeOptionGroup>();

        parse();
    }

    private void parse() {
        Matcher matcher;

        // extract header
        matcher = PATTERN_HEADER.matcher(theme);
        if (!matcher.find()) {
            throw new RuntimeException("No theme header found!");
        }
        final String header = matcher.group(1);

        // extract groups
        matcher = PATTERN_HEADER_GROUP.matcher(header);
        while (matcher.find()) {
            final String id = matcher.group(1);
            final String[] translations = matcher.group(2).split("\\|\\|\\|");

            final Map<Locale, String> names = new HashMap<Locale, String>();
            for (String t : translations) {
                final String[] tt = t.split("===");
                names.put(Locale.forLanguageTag(tt[0]), tt[1]);
            }

            groups.put(id, new MutableThemeOptionGroup(names));
        }

        // extract options
        matcher = PATTERN_HEADER_OPTION.matcher(header);
        while (matcher.find()) {
            final String optionId = matcher.group(1);
            final String groupId = matcher.group(2);
            final String[] translations = matcher.group(3).split("\\|\\|\\|");

            if (!groups.containsKey(groupId)) {
                System.out.println("Group \"" + groupId + "\" for option \"" + optionId + "\" not found, skipping.");
                continue;
            }

            final Map<Locale, String> names = new HashMap<Locale, String>();
            for (String t : translations) {
                final String[] tt = t.split("===");
                names.put(new Locale(tt[0]), tt[1]);
            }

            final List<Integer> startLines = new ArrayList<Integer>();
            final List<Integer> endLines = new ArrayList<Integer>();
            boolean status = false;

            final String patternStart = PATTERN_OPTION_START_PRE + optionId + PATTERN_OPTION_START_POST;
            final String patternEnd = PATTERN_OPTION_END_PRE + optionId + PATTERN_OPTION_END_POST;

            for (int i = 0; i < lineTheme.length; i++) {
                if (lineTheme[i].contains(patternStart)) {
                    startLines.add(i);
                    status = lineTheme[i].endsWith(PATTERN_COMMENT_END);
                }

                if (lineTheme[i].contains(patternEnd)) {
                    endLines.add(i);
                }
            }

            if (startLines.size() == 0 || endLines.size() == 0) {
                System.out.println("Start or end lines for option \"" + optionId + "\" not found, skipping.");
                continue;
            }

            final MutableThemeOptionGroup group = groups.get(groupId);
            group.addOption(new MutableThemeOption(group, names, optionId, status, startLines, endLines));
        }
    }

    /**
     * Returns a list of all groups.
     * 
     * @return List of all groups
     */
    public List<ThemeOptionGroup> getGroups() {
        List<ThemeOptionGroup> list = new ArrayList<ThemeOptionGroup>();

        for (MutableThemeOptionGroup group : groups.values()) {
            list.add(group);
        }

        return Collections.unmodifiableList(list);
    }

    /**
     * Returns a list of all available options.
     * 
     * @return List of all available options
     */
    public List<ThemeOption> getOptions() {
        List<ThemeOption> options = new ArrayList<ThemeOption>();

        for (ThemeOptionGroup group : getGroups()) {
            options.addAll(group.getOptions());
        }

        return Collections.unmodifiableList(options);
    }

    /**
     * Returns a theme with all options applied.
     * 
     * @return Theme with all options applied
     */
    public String compile() {
        for (ThemeOption option : getOptions()) {
            for (int i : option.getStartLines()) {
                lineTheme[i] = PATTERN_COMMENT_START + ' ' + PATTERN_OPTION_START_PRE + option.getId() + PATTERN_OPTION_START_POST + (option.getStatus() ? ' ' + PATTERN_COMMENT_END : "");
            }

            for (int i : option.getEndLines()) {
                lineTheme[i] = (option.getStatus() ? PATTERN_COMMENT_START + ' ' : "") + PATTERN_OPTION_END_PRE + option.getId() + PATTERN_OPTION_END_POST + ' ' + PATTERN_COMMENT_END;
            }
        }

        return Util.combine("\n", lineTheme);
    }
}
