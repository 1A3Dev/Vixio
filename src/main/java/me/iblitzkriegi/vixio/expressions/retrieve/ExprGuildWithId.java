package me.iblitzkriegi.vixio.expressions.retrieve;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.Vixio;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import org.bukkit.event.Event;

import java.util.Set;

/**
 * Created by Blitz on 7/26/2017.
 */
public class ExprGuildWithId extends SimpleExpression<Guild> {
    static {
        Vixio.getInstance().registerExpression(ExprGuildWithId.class, Guild.class, ExpressionType.SIMPLE, "(server|guild) with id %string%")
            .setName("Guild with id")
            .setDesc("Get a server/guild via it's ID")
            .setExample("guild with id \"16165198461\"");
    }

    private Expression<String> id;

    @Override
    protected Guild[] get(Event event) {
        return new Guild[]{getGuild(event)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends Guild> getReturnType() {
        return Guild.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "guild with id " + id.toString(event, b);
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<String>) expressions[0];
        return true;
    }

    private Guild getGuild(Event e) {
        String id = this.id.getSingle(e);
        if (id == null || id.isEmpty()) {
            return null;
        }
        Set<JDA> jdaInstances = Vixio.getInstance().botHashMap.keySet();
        if (jdaInstances != null && !jdaInstances.isEmpty()) {
            for (JDA jda : jdaInstances){
                Guild guild = jda.getGuildById(id);
                if (guild != null) {
                    return guild;
                }
                return null;
            }
        }
        return null;
    }

}