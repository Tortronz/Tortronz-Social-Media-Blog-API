package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        // Account Handlers
        app.post("/register", this::postRegisterAccountHandler);

        // Message Handlers
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/accounts/{account_id}/messages", this::getAllMessagesByAccountHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.post("/messages", this::postCreateMessageHandler);
        app.put("/messages/{message_id}", this::putUpdateMessageHandler);
        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        return app;
    }



    // ACCOUNT HANDLERS //
    /**
     * Handler for registering a new account.
     * 
     * The new account's username must not be be blank, nor have a password
     * less than 4 characters long. Failing to meet these requirements will
     * cancel the POST and the API will return a 400 message (client error).
     * 
     * If AccountService returns a null account (meaning posting an Account was
     * unsuccessful), the API will return a 400 message (client error).
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     * @throws JsonProcessingException  if there's an issue converting JSON
     *                                  into an object
     */
    private void postRegisterAccountHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);

        // Check username isn't blank
        if(account.getUsername() == "") {
            ctx.status(400);
            return;
        }
        // Check password is more than 4 characters
        if(account.getPassword().length() < 4) {
            ctx.status(400);
            return;
        }

        Account addedAccount = accountService.registerAccount(account);

        if(addedAccount != null){
            ctx.json(mapper.writeValueAsString(addedAccount));
        }else{
            ctx.status(400);
        }
    }



    // MESSAGE HANDLERS //
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
     * Handler to recieve a specific message by its ID.
     * 
     * Even if the message with specified ID doesn't exist (and thus can't be
     * retrieved), the API will return a 200 message (OK).
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void getMessageByIdHandler(Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.getMessage(message_id);

        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(200);
        }
    }

    /**
     * Handler for posting a new message.
     * 
     * The text content of the message can't be blank. Failing to meet this
     * requirement will cancel the POST and the API will return a 400 message
     * (client error).
     * 
     * If MessageService returns a null message (meaning posting an Message was
     * unsuccessful), the API will return a 400 message (client error).
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     * @throws JsonProcessingException  if there's an issue converting JSON
     *                                  into an object
     */
    private void postCreateMessageHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);

        // Check message text isn't blank
        if(message.getMessage_text() == "") {
            ctx.status(400);
            return;
        }

        Message addedMessage = messageService.createMessage(message);

        if(addedMessage != null){
            ctx.json(mapper.writeValueAsString(addedMessage));
        }else{
            ctx.status(400);
        }
    }

    /**
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void putUpdateMessageHandler(Context ctx) throws JsonProcessingException {

    }

    /**
     * Handler for deleting a message.
     * 
     * Even if the message with specified ID doesn't exist (and thus can't be
     * deleted), the API will return a 200 message (OK).
     * 
     * @param ctx   data handler for HTTP requests and responses, provided the
     *              Javalin app
     */
    private void deleteMessageHandler(Context ctx) {
        int message_id = Integer.parseInt(ctx.pathParam("message_id"));
        Message message = messageService.deleteMessageById(message_id);

        if(message != null) {
            ctx.json(message);
        } else {
            ctx.status(200);
        }
    }
}