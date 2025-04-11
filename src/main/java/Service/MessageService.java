package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

/**
 * This is a Service class that acts between the web/database layer
 * (controller) of the "message" table and the persistence layer (DAO) of the
 * "message" Java class.
 */
public class MessageService {
    public MessageDAO messageDAO;
    
    // CREATE OPERATIONS //
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

    /**
     * This registers a new message into the "message" database table.
     * 
     * @param message   The new message to be created
     * 
     * @return  Message if it was successfully persisted, or "null" if it
     *          wasn't successfully persisted
     */
    public Message createMessage(Message message) {
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
     * @return  Singular message with the specified ID
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
     *          wasn't successfully updated
     */
    public Message updateMessageText(int message_id, Message message) {
        this.messageDAO.updateMessageTextById(message_id, message);
        return this.messageDAO.getMessageById(message_id);
    }

    // DELETE OPERATIONS
    /**
     * ++++NEEDS WORK++++
     * Deletes message with the corresponding ID.
     * 
     * @param message_id    ID of the message that we want to delete
     */
    public void deleteMessageById(int message_id) {
        this.messageDAO.deleteMessageById(message_id);
    }
}
