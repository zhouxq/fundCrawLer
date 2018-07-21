package com.jxnu.finance.store.mapper;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public abstract class BaseStore<T> {
    private final static Logger logger = LoggerFactory.getLogger(BaseStore.class);
    @Autowired
    protected SqlSessionTemplate template;
    public String storeName = "";


    /**
     * 插入多个数据库实体
     *
     * @param lists
     */
    public void insert(List<T> lists) {
        logger.info("{} insert list:{}", storeName, lists);
        template.insert(storeName + ".insert", lists);
    }

    /**
     * 查询单个数据库实体
     *
     * @param map
     * @return
     */
    public T selectOne(Map map) {
        logger.info("{} selectOne query map:{}", storeName, map);
        T t = template.selectOne(storeName + ".selectOne", map);
        logger.info("{}selectOne query result:{}", storeName, t);
        return t;
    }

    /**
     * 查询多个数据库实体
     *
     * @param map
     * @return
     */
    public List<T> selectMulti(Map map) {
        logger.info("{} selectMulti query map:{}", storeName, map);
        List<T> lists = template.selectList(storeName + ".selectMulti", map);
        logger.info("{} selectMulti query result:{}", storeName, lists);
        return lists;
    }

    /**
     * 删除数据库实体
     *
     * @param map
     */
    public void delete(Map map) {
        logger.info("{} delete map:{}", storeName, map);
        Integer num = template.delete(storeName + ".delete", map);
        logger.info("{} delete result num:{}", storeName, num);
    }

    /**
     * 更新数据库实体
     *
     * @param map
     */
    public void update(Map map) {
        logger.info("{} update map:{}", storeName, map);
        Integer num = template.update(storeName + ".update", map);
        logger.info("{} update result num:{}", storeName, num);
    }
}
