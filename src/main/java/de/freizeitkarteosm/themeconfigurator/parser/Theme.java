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

    private final String theme;

    // We use a LinkedHashMap to preserve the order of the groups as they are in the theme file.
    private final LinkedHashMap<String, MutableThemeOptionGroup> groups;

    public Theme(final String theme) {
        this.theme = theme;
        groups = new LinkedHashMap<String, MutableThemeOptionGroup>();

        parse();
    }

    private void parse() {
        Matcher matcher;

        matcher = PATTERN_HEADER.matcher(theme);
        if (!matcher.find()) {
            throw new RuntimeException("No theme header found!");
        }
        final String header = matcher.group(1);

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
    }

    public List<ThemeOptionGroup> getGroups() {
        List<ThemeOptionGroup> list = new ArrayList<ThemeOptionGroup>();

        for (MutableThemeOptionGroup group : groups.values()) {
            list.add(group);
        }

        return Collections.unmodifiableList(list);
    }
}
