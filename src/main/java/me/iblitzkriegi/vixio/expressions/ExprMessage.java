package me.iblitzkriegi.vixio.expressions;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.iblitzkriegi.vixio.Vixio;
import me.iblitzkriegi.vixio.events.message.EvtMessageEdited;
import me.iblitzkriegi.vixio.util.UpdatingMessage;
import net.dv8tion.jda.api.entities.Message;
import org.bukkit.event.Event;

public class ExprMessage extends SimpleExpression<UpdatingMessage> {
    static {
        Vixio.getInstance().registerExpression(ExprMessage.class, UpdatingMessage.class, ExpressionType.SIMPLE,
                "[the] (prev[ious]|old) [discord] message", "[the] new [discord] message")
                .setName("New and Previous Message")
                .setDesc("Get the new channel or previous message for the message edit.")
                .setExample(
                        "on message edit:\n",
                        "\tbroadcast \"%event-user% changed %content of old message% to %content of new message%\""
                );
    }

    private boolean old;

    @Override
    protected UpdatingMessage[] get(Event e) {
        if (old) {
            return new UpdatingMessage[]{UpdatingMessage.from(((EvtMessageEdited.EditMessageEvent) e).getJDAEvent().getMessage())};
        }
        Message msg = ((EvtMessageEdited.EditMessageEvent) e).getJDAEvent().getMessage();
        UpdatingMessage.put(msg.getId(), msg);
        return new UpdatingMessage[]{UpdatingMessage.from(msg)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends UpdatingMessage> getReturnType() {
        return UpdatingMessage.class;
    }

    @Override
    public String toString(Event e, boolean debug) {
        return "the " + (old ? "old" : "new") + " message";
    }

    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (ScriptLoader.isCurrentEvent(EvtMessageEdited.EditMessageEvent.class)) {
            old = matchedPattern == 0;
            return true;
        }
        Skript.error("This expression may only be used inside the message edit event.");
        return false;
    }
}
