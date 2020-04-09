package me.iblitzkriegi.vixio.events.message;

import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.iblitzkriegi.vixio.events.base.BaseEvent;
import me.iblitzkriegi.vixio.events.base.SimpleVixioEvent;
import me.iblitzkriegi.vixio.util.UpdatingMessage;
import me.iblitzkriegi.vixio.util.Util;
import me.iblitzkriegi.vixio.util.wrapper.Bot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;

public class EvtMessageEdited extends BaseEvent<MessageUpdateEvent> {

    static {
        BaseEvent.register("discord message edit", EvtMessageEdited.class, EditMessageEvent.class,
                "[discord] message (edit|update)")
                .setName("Edit Message")
                .setDesc("Fired when a message is edited.")
                .setExample(
                        "on discord message edit:\n",
                        "\tbroadcast \"%event-user% edited %content of old message% to say %content of new message%\""
                );

        EventValues.registerEventValue(EditMessageEvent.class, Bot.class, new Getter<Bot, EditMessageEvent>() {
            @Override
            public Bot get(EditMessageEvent event) {
                return Util.botFrom(event.getJDAEvent().getJDA());
            }
        }, 0);

        EventValues.registerEventValue(EditMessageEvent.class, Member.class, new Getter<Member, EditMessageEvent>() {
            @Override
            public Member get(EditMessageEvent event) {
                return event.getJDAEvent().getMember();
            }
        }, 0);

        EventValues.registerEventValue(EditMessageEvent.class, UpdatingMessage.class, new Getter<UpdatingMessage, EditMessageEvent>() {
            @Override
            public UpdatingMessage get(EditMessageEvent event) {
                return UpdatingMessage.from(event.getJDAEvent().getMessage());
            }
        }, 0);

        EventValues.registerEventValue(EditMessageEvent.class, Guild.class, new Getter<Guild, EditMessageEvent>() {
            @Override
            public Guild get(EditMessageEvent event) {
                return event.getJDAEvent().getGuild();
            }
        }, 0);

        EventValues.registerEventValue(EditMessageEvent.class, User.class, new Getter<User, EditMessageEvent>() {
            @Override
            public User get(EditMessageEvent event) {
                return event.getJDAEvent().getMember().getUser();
            }
        }, 0);


    }

    public class EditMessageEvent extends SimpleVixioEvent<MessageUpdateEvent> {
    }

}
