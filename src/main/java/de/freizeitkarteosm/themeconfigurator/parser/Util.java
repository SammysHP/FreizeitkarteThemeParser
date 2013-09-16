package de.freizeitkarteosm.themeconfigurator.parser;

import java.util.Locale;
import java.util.Map;

class Util {
    final static private String ENGLISH = Locale.ENGLISH.getISO3Language();

    public static String getTranslation(final Map<String, String> translations) {
        return getTranslation(translations, Locale.getDefault());
    }

    public static String getTranslation(final Map<String, String> translations, final Locale locale) {
        return getTranslation(translations, locale, true);
    }

    public static String getTranslation(final Map<String, String> translations, final Locale locale, final boolean allowDefault) {
        final String language = locale.getISO3Language();

        // try requested language first
        if (translations.containsKey(language)) {
            return translations.get(language);
        }

        // requested language not available, try English
        if (allowDefault && translations.containsKey(ENGLISH)) {
            return translations.get(ENGLISH);
        }

        // neither requested language nor English available
        return "";
    }

    public static String combine(final String glue, final String... in) {
        final int length = in.length;

        if (length == 0) {
            return null;
        }

        final StringBuilder out = new StringBuilder();
        out.append(in[0]);

        for (int i = 1; i < length; i++) {
            out.append(glue).append(in[i]);
        }

        return out.toString();
    }
}
