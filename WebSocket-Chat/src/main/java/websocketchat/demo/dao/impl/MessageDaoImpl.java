package websocketchat.demo.dao.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import websocketchat.demo.dao.MessageDao;
import websocketchat.demo.pojo.Message;

import java.util.Date;
import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/14 19:30
 * @Description:
 */
@Component
public class MessageDaoImpl implements MessageDao{

    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public List<Message> findListByFromAndTo(Long fromId, Long toId, Integer page, Integer rows) {




        // 用户A发送给用户B的条件
        Criteria criteriaFrom = new Criteria().andOperator(
                Criteria.where("from.id").is(fromId),
                Criteria.where("to.id").is(toId)
        );

        // 用户B发送给用户A的条件
        Criteria criteriaTo = new Criteria().andOperator(
                Criteria.where("from.id").is(toId),
                Criteria.where("to.id").is(fromId)
        );

        Criteria criteria = new Criteria().orOperator(criteriaFrom, criteriaTo);

        PageRequest pageRequest = PageRequest.of(page - 1, rows, Sort.by(Sort.Direction.ASC, "sendDate"));
        System.out.println(pageRequest.getPageNumber());
        System.out.println(pageRequest.getPageSize());
        System.out.println(pageRequest.getOffset());
        // 设置查询条件，分页

        Query query = Query.query(criteria).with(pageRequest);

        System.out.println(query);

        //Page<Message> messagePage=(Page<Message>)mongoTemplate.find(query, Message.class);
        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public Message findMessageById(String id) {
        return this.mongoTemplate.findById(new ObjectId(id), Message.class);
    }

    @Override
    public UpdateResult updateMessageState(ObjectId id, Integer status) {
        Query query = Query.query(Criteria.where("id").is(id));
        Update update = Update.update("status", status);
        if (status.intValue() == 1) {
            update.set("send_date", new Date());
        } else if (status.intValue() == 2) {
            update.set("read_date", new Date());
        }
        return this.mongoTemplate.updateFirst(query, update, Message.class);
    }

    @Override
    public Message saveMessage(Message message) {
        // 写入发送时间
        message.setSendDate(new Date());
        message.setStatus(1);
        message.setId(ObjectId.get());
        return this.mongoTemplate.save(message);
    }

    @Override
    public DeleteResult deleteMessage(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query, Message.class);
    }
}
