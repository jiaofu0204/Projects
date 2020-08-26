package edu.northeastern.cs5200.repository;

import edu.northeastern.cs5200.models.Message;
import edu.northeastern.cs5200.models.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

//@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into message (content, sender_id, receiver_id) values (:content, :senderid, :receiverid) ", nativeQuery = true)
    public void sendMessage(@Param("content") String content, @Param("senderid") int senderid, @Param("receiverid") int receiverid);

    @Query(value = "select * from message where message.sender_id = :senderid", nativeQuery = true)
    public List<Message> findMessageBySender(@Param("senderid") int senderid);

    //@Query(value = "select * from message where message.sender_id = :senderid", nativeQuery = true)
    //public Message findMessageBySender(@Param("senderid") Person senderid);

    @Query(value = "select * from message where message.receiver_id = :receiverid", nativeQuery = true)
    public List<Message> findMessageByReceiver(@Param("receiverid") int receiverid);

    //@Query(value = "select * from message where message.receiver_id = :receiverid", nativeQuery = true)
    //public Message findMessageByReceiver(@Param("receiverid") int receiverid);
}