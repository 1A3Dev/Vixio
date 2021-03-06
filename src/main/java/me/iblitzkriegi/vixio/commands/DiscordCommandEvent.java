package me.iblitzkriegi.vixio.commands;

import me.iblitzkriegi.vixio.events.base.SimpleBukkitEvent;
import me.iblitzkriegi.vixio.util.wrapper.Bot;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.event.Cancellable;

public class DiscordCommandEvent extends SimpleBukkitEvent implements Cancellable {

    private DiscordCommand command;
    private Guild guild;
    private Message message;
    private User user;
    private Member member;
    private MessageChannel messagechannel;
    private String prefix;
    private String usedAlias;
    private boolean cancelled;
    private GuildChannel channel;
    private Bot bot;
	private String arguments;

	public DiscordCommandEvent(String prefix, String usedAlias, DiscordCommand command, String arguments, Guild guild,
                               MessageChannel messagechannel, GuildChannel channel, Message message, User user,
                               Member member, Bot bot) {
		this.arguments = arguments == null ? "" : arguments;
        this.command = command;
        this.guild = guild;
        this.user = user;
        this.usedAlias = usedAlias;
        this.message = message;
        this.member = member;
        this.channel = channel;
        this.messagechannel = messagechannel;
        this.prefix = prefix;
        this.bot = bot;
    }

    public DiscordCommandEvent(DiscordCommandEvent original) {
        this(original.getPrefix(),
                original.getUsedAlias(),
                original.getCommand(),
				original.getArguments(),
                original.getGuild(),
                original.getMessageChannel(),
                original.getChannel(),
                original.getMessage(),
                original.getUser(),
                original.getMember(),
                original.getBot());
    }

    public DiscordCommand getCommand() {
        return command;
    }

    public Guild getGuild() {
        return guild;
    }

    public Message getMessage() {
        return message;
    }

    public Member getMember() {
        return member;
    }

    public User getUser() {
        return user;
    }

    public GuildChannel getChannel() {
        return channel;
    }

    public MessageChannel getMessageChannel() {
        return messagechannel;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getUsedAlias() {
        return usedAlias;
    }

    public Bot getBot() {
        return bot;
    }

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments == null ? "" : arguments;
	}

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
