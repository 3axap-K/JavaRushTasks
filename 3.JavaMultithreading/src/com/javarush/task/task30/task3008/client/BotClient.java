package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.SimpleFormatter;

public class BotClient extends Client{


    public static void main(String[] args){
        BotClient botClient = new BotClient();
        botClient.run();
    }


    @Override
    protected String getUserName() {

        return "date_bot_" + (int) (Math.random() * 100);
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }


    public class BotSocketThread extends SocketThread{
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if(message != null && message.contains(": ")) {

                String name = message.split(": ")[0];
                String text = message.split(": ")[1];
                String info = "Информация для " + name + ": ";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.MM.YYYY");
                Calendar calendar = new GregorianCalendar();

                switch (text) {
                    case "дата":
                        sendTextMessage(info + new SimpleDateFormat("d.MM.YYYY").format(new GregorianCalendar().getTime()));
                        break;
                    case "день":
                        sendTextMessage(info + new SimpleDateFormat("d").format(new GregorianCalendar().getTime()));
                        break;
                    case "месяц":
                        sendTextMessage(info + new SimpleDateFormat("MMMM").format(new GregorianCalendar().getTime()));
                        break;
                    case "год":
                        sendTextMessage(info + new SimpleDateFormat("YYYY").format(new GregorianCalendar().getTime()));
                        break;
                    case "время":
                        sendTextMessage(info + new SimpleDateFormat("H:mm:ss").format(new GregorianCalendar().getTime()));
                        break;
                    case "час":
                        sendTextMessage(info + new SimpleDateFormat("H").format(new GregorianCalendar().getTime()));
                        break;
                    case "минуты":
                        sendTextMessage(info + new SimpleDateFormat("m").format(new GregorianCalendar().getTime()));
                        break;
                    case "секунды":
                        sendTextMessage(info + new SimpleDateFormat("s").format(new GregorianCalendar().getTime()));
                        break;
                }
            }
        }
    }


}
