package carthage.nihil.honkbot.test;

import java.util.concurrent.ExecutionException;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter
{
	public static void main(String[] args)
	{
		try
		{
			JDA jdaHonkBot = new JDABuilder(AccountType.BOT)
				.setToken("TOKEN")
				.addEventListener(new MessageListener())
				.buildBlocking();
		}
		catch (LoginException e)
		{
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (RateLimitedException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	/*
	 * HONKS at a user
	 */
	public void onMessageReceived(MessageReceivedEvent event)
	{
		try
		{
			//gets the user, and from there opens a DM box, then starts the message procedure
			event.getAuthor().openPrivateChannel().submit().get().sendMessage(
					//use MessageBuilder for rich message creation
					new MessageBuilder()
					//add a mention to the user
					.append(event.getAuthor().getAsMention())
					//tell the user "HONK"
					.append(" HONK").build()
					//always use .submit() to send the message
					).submit();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		catch (ExecutionException e)
		{
			e.printStackTrace();
		}
	}

}
