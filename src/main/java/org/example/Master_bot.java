package org.example;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import com.theokanning.openai.service.OpenAiService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Master_bot extends TelegramLongPollingBot {

    //TODO add intro message with instructions and creator message
    //TODO customise bot
    //TODO deploy bot on AWS


    //@Override
    public void SendAnim(Long who, String file) {
        SendAnimation sa = SendAnimation.builder()
                .chatId(who.toString())
                .animation(new InputFile(file)).build();

        try {
            execute(sa);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }

    //private static final String OPENAI_TOKEN;
    @Override
    public String getBotUsername() {return "chatgpt_bot";}
    @Override
    public String getBotToken() {
        return "5425935361:AAHo1PZosMVoLpog1OVUEvLVKKb8g54sx4s";
    }

    /*public  void SendAnimation(Long who, String file)  {
        Properties properties = new Properties();
        //Update update = new Update();
        //Long chat_Id = update.getMessage().getChatId();
        //properties.load(new FileInputStream("local.properties"));
        SendAnimation sa = SendAnimation.builder()
                .chatId(who.toString())
                .animation(new InputFile(file)).build();

        try {
            execute(sa);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }*/

    @Override
    public void onUpdateReceived(Update update){

        //send Dice
        SendDice sd = SendDice.builder()
                .chatId(update.getMessage().getChatId().toString()).build();

        var text = update.getMessage().getText();

        //AI code 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

        System.out.println(update.getMessage().getAnimation());

        OpenAiService service = new OpenAiService("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");
        //var answer = service.createCompletion(completionRequest).getChoices();
        System.out.println("\nCreating completion...");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(text)
                .echo(true)
                .n(1)
                .maxTokens(500)
                .bestOf(1)
                .build();
        service.createCompletion(completionRequest).getChoices().forEach(System.out::println);

        var answer = service.createCompletion(completionRequest).getChoices();

        var file = new InputFile("CgACAgQAAxkBAAM4ZGvkE0WvkEO3jtkycPiWOqpPIVUAAh0DAALnagVTslhv0Tu6PvwvBA");
        //ends here

        if (update.hasMessage() && update.getMessage().hasText()){
            //System.out.println(update.getMessage().getChatId());
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Answer: " + answer);

           /* SendAnimation sm = SendAnimation.builder()
                    .chatId(update.getMessage().getChatId().toString())
                    .animation(file).build();
*/
            try {
                //execute();
                //execute(sm);
                //SendAnim(5822826384L, "CgACAgIAAxkBAAIBDGR05gWWGTPupT8JUWm6kw5t8dTQAAI-EgACLDlBSNytvXBQPGoJLwQ");
                execute(sd);
                execute(message); // Call method to send the message

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }else if(update.getMessage().getText().equals("/roll")){
            //create poll to provide feedback
        }

    }

//no main manifest attribute, in BhejaniTech-1.0-SNAPSHOT.jar


    /*public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Master_bot bot = new Master_bot();
            botsApi.registerBot(bot);
            bot.SendAnim(5822826384L, "CgACAgIAAxkBAAIBDGR05gWWGTPupT8JUWm6kw5t8dTQAAI-EgACLDlBSNytvXBQPGoJLwQ");
            //bot.SendAnimation(5822826384L, "CgACAgIAAxkBAAIBDGR05gWWGTPupT8JUWm6kw5t8dTQAAI-EgACLDlBSNytvXBQPGoJLwQ");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }*/

}



