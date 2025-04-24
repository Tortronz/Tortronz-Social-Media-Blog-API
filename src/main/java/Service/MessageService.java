package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

/**
 * This is a Service class that acts between the endpoints (controller) and the
 * database (DAO) of the "Message" Java class, validating input.
 */
public class MessageService {
    public MessageDAO messageDAO;
    
    // CONSTRUCTORS //
    /**
     * No-args constructor for MessageService which creates an MessageDAO.
     */
    public MessageService(){
        messageDAO = new MessageDAO();
    }

    /**
     * Constructor for a MessageService when a MessageDAO is provided.
     * Used for mocking MessageDAO in the test cases.
     * 
     * @param messageDAO
     */
    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    // CREATE OPERATIONS //
    /**
     * This registers a new message into the "message" database table.
     * 
     * @param message   The new message to be created
     * 
     * @return  Message if it was successfully persisted, or "null" if it
     *          wasn't successfully persisted  or if new message text is blank
     */
    public Message createMessage(Message message) {
        // Check message text isn't blank
        if(message.getMessage_text() == "") {
            return null;
        }

        return this.messageDAO.insertMessage(message);
    }

    // READ OPERATIONS //
    /**
     * Gets all messages in chronological post order.
     * 
     * @return  All messages
     */
    public List<Message> getAllMessages() {
        return this.messageDAO.getAllMessages();
    }

    /**
     * Gets all messages in chronological post order by one account.
     * 
     * @param account_id    ID of the account that we want all messages from
     * @return  All messages by one account
     */
    public List<Message> getAllMessagesByAccount(int account_id) {
        return this.messageDAO.getAllMessagesByAccountId(account_id);
    }

    /**
     * Gets message with the corresponding ID.
     * 
     * @param message_id    ID of the message that we want
     * @return  Singular message with the specified ID, or "null" if it wasn't
     *          successfully retrieved
     */
    public Message getMessage(int message_id) {
        return this.messageDAO.getMessageById(message_id);
    }

    // UPDATE OPERATIONS //
    /**
     * Updates the text of a message.
     * 
     * @param message_id    ID of the message we want to update
     * @param message       Message object with new text to replace the old
     *                      message text with
     * 
     * @return  Message if it was successfully updated, or "null" if it
     *          wasn't successfully updated or if updated messaged text is
     *          blank or too long
     */
    public Message updateMessageText(int message_id, Message message) {
        // Check new message text isn't blank
        if(message.getMessage_text() == "") {
            return null;
        }
        // Check new message text isn't more than 253 characters
        if(message.getMessage_text().length() > 253) {
            return null;
        }

        return this.messageDAO.updateMessageTextById(message_id, message);
    }

    // DELETE OPERATIONS
    /**
     * Deletes message with the corresponding ID.
     * 
     * @param message_id    ID of the message that we want to delete
     */
    public Message deleteMessageById(int message_id) {
        return this.messageDAO.deleteMessageById(message_id);
    }
}
