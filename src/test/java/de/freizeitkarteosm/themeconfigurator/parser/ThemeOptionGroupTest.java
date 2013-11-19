package de.freizeitkarteosm.themeconfigurator.parser;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import de.freizeitkarteosm.themeconfigurator.parser.ThemeOptionGroup.GroupStatus;

public class ThemeOptionGroupTest {

    @Test
    public void addNamesViaConstructor() {
        final Locale locale = Locale.GERMAN;

        final Map<Locale, String> translations = new HashMap<Locale, String>();
        translations.put(locale, "Hallo Welt");
        final MutableThemeOptionGroup tog = new MutableThemeOptionGroup(
                translations);

        assertEquals("Hallo Welt", tog.getName(locale));
    }

    @Test
    public void addNamesViaMethod() {
        final Locale locale = Locale.GERMAN;
        final MutableThemeOptionGroup tog = new MutableThemeOptionGroup();

        assertEquals("", tog.getName(locale));
        tog.setName(locale, "Hallo Welt");
        assertEquals("Hallo Welt", tog.getName(locale));
    }

    @Test
    public void groupStatus() {
        MutableThemeOptionGroup tog = new MutableThemeOptionGroup();
        ThemeOption option;

        assertEquals(GroupStatus.ALL, tog.getStatus());

        option = new MutableThemeOption();
        option.disable();
        tog.addOption(option);

        option = new MutableThemeOption();
        option.disable();
        tog.addOption(option);

        assertEquals(GroupStatus.NONE, tog.getStatus());

        option = new MutableThemeOption();
        option.enable();
        tog.addOption(option);

        assertEquals(GroupStatus.SOME, tog.getStatus());

        tog = new MutableThemeOptionGroup();

        assertEquals(GroupStatus.ALL, tog.getStatus());

        option = new MutableThemeOption();
        option.enable();
        tog.addOption(option);

        option = new MutableThemeOption();
        option.enable();
        tog.addOption(option);

        assertEquals(GroupStatus.ALL, tog.getStatus());
    }
}
