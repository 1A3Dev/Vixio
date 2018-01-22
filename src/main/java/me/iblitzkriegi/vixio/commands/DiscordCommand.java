/**
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Copyright 2011-2017 Peter Güttinger and contributors
 */
package me.iblitzkriegi.vixio.commands;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ch.njol.skript.command.Argument;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.command.Commands.CommandAliasHelpTopic;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Trigger;
import ch.njol.skript.lang.TriggerItem;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.localization.Language;
import ch.njol.skript.localization.Message;
import ch.njol.skript.log.LogEntry;
import ch.njol.skript.log.ParseLogHandler;
import ch.njol.skript.log.SkriptLogger;
import ch.njol.skript.log.Verbosity;
import ch.njol.skript.util.EmptyStacktraceException;
import ch.njol.skript.util.Utils;
import ch.njol.util.StringUtils;
import ch.njol.util.Validate;

public class DiscordCommand {

    final String name;
    private final String label;
    private final List<String> aliases;
    private List<String> activeAliases;
    private final String description;
    final String usage;

    final Trigger trigger;

    private final String pattern;
    private final List<Argument<?>> arguments;

    /**
     * Creates a new SkriptCommand.
     *
     * @param name /name
     * @param pattern
     * @param arguments the list of Arguments this command takes
     * @param description description to display in /help
     * @param usage message to display if the command was used incorrectly
     * @param aliases /alias1, /alias2, ...
     * @param permission permission or null if none
     * @param permissionMessage message to display if the player doesn't have the given permission
     * @param items trigger to execute
     */
    public DiscordCommand(final File script, final String name, final String pattern, final List<Argument<?>> arguments, final String description, final String usage, final ArrayList<String> aliases, final String permission, final String permissionMessage, final int executableBy, final List<TriggerItem> items) {
        Validate.notNull(name, pattern, arguments, description, usage, aliases, items);
        this.name = name;
        label = "" + name.toLowerCase();

        final Iterator<String> as = aliases.iterator();
        while (as.hasNext()) { // remove aliases that are the same as the command
            if (as.next().equalsIgnoreCase(label))
                as.remove();
        }
        this.aliases = aliases;
        activeAliases = new ArrayList<String>(aliases);

        this.description = Utils.replaceEnglishChatStyles(description);
        this.usage = Utils.replaceEnglishChatStyles(usage);


        this.pattern = pattern;
        this.arguments = arguments;

        trigger = new Trigger(script, "command /" + name, new SimpleEvent(), items);

    }

    public boolean execute(final String commandLabel, final String rest) {
        return true;
    }


    /**
     * Gets the arguments this command takes.
     *
     * @return The internal list of arguments. Do not modify it!
     */
    public List<Argument<?>> getArguments() {
        return arguments;
    }

    public String getPattern() {
        return pattern;
    }


    public void register() {

    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public List<String> getActiveAliases() {
        return activeAliases;
    }

    @Nullable
    public File getScript() {
        return trigger.getScript();
    }

}