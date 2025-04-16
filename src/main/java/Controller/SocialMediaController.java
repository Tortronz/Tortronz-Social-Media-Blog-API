package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }



    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/accounts", this::postAccountHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{account_id}", this::getAllMessagesByAccountHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.put("/messages/{message_id}", this::putMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        app.start(8080);

        return app;
    }

    /**
     * Handler to retrieve all messages.
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void getAllMessagesHandler(Context ctx) {
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }

    /**
     * Handler to retrieve all messages by a single account.
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void getAllMessagesByAccountHandler(Context ctx) {
        int account_id = Integer.parseInt(ctx.pathParam("account_id"));
        List<Message> messages = messageService.getAllMessagesByAccount(account_id);
        ctx.json(messages);
    }

    /**
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void getMessageByIdHandler(Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessage(message_id);
        ctx.json(message);
    }

    /**
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void postAccountHandler(Context ctx) throws JsonProcessingException {

    }

    /**
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void putMessageHandler(Context ctx) throws JsonProcessingException {

    }

    /**
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void deleteMessageHandler(Context ctx) {

    }
}