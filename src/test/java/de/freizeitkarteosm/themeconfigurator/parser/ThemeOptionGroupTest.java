package de.freizeitkarteosm.themeconfigurator.parser;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

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

}
