package dao.impl;

import dao.OrderDao;
import entity.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
//添加商品订单
    @Override
    public void addOrder(Order o) {
        try {
            String sql = "insert into tb_order(order_id,user_id,order_price,order_time,is_pay,is_ship,is_receipt,product_id,product_quantity,product_name,product_photo,product_price) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            template.update(sql,o.getOrder_id(),o.getUser_id(),o.getOrder_price(),o.getOrder_time(),o.getIs_pay(),o.getIs_ship(),o.getIs_receipt(),o.getProduct_id(),o.getProduct_quantity(),o.getProduct_name(),o.getProduct_photo(),o.getProduct_price());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//根据订单编号查询商品订单
    @Override
    public Order findOrderById(String oid) {
        try {
            String sql = "select * from tb_order where order_id = ?";
            Order o = template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class),oid);
            return o;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//更新地址
    @Override
    public void updateOrderAddress(String oid, int addressid) {
        try {
            String sql = "update tb_order set address_id = ? where order_id = ?";
            template.update(sql,addressid,oid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//更新支付状态
    @Override
    public void updateOrderIsPay(String oid, String s) {
        try {
            String sql = "update tb_order set is_pay = ? where order_id = ?";
            template.update(sql,s,oid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//查询用户id
    @Override
    public List<Order> findOrderByUserId(int user_id) {
        try {
            String sql = "select * from tb_order where user_id = ? order by order_time Desc";
            List<Order> orders = template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),user_id);
            return orders;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//查询所有订单
    @Override
    public List<Order> findAllOrder() {
        try {
            String sql = "select * from tb_order";
            List<Order> o = template.query(sql, new BeanPropertyRowMapper<Order>(Order.class));
            return o;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//订单总数
    @Override
    public int findTotalCount() {
        try {
            String sql = "select count(*) from tb_order";
            return template.queryForObject(sql, Integer.class);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }
//页面显示
    @Override
    public List<Order> findByPage(int start, int rows) {
        try {
            String sql = "select * from tb_order limit ? , ?";
            return template.query(sql, new BeanPropertyRowMapper<Order>(Order.class),start,rows);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//关键字查询
    @Override
    public List<Order> findOrderListByKey(String keywords) {
        try {
            String sql = "select * from tb_order where order_id like '%' ? '%'";
            List<Order> orders = template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),keywords);
            return orders;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//查询用户收货状态和用户id查询订单
    @Override
    public List<Order> findOrderByUserIdAndIsReceipt(int user_id) {
        try {
            String sql = "select * from tb_order where user_id = ? and is_receipt = '1'";
            List<Order> orders = template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),user_id);
            return orders;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//更新商品发货状态
    @Override
    public void updateOrderIsShip(String oid) {
        try {
            String sql = "update tb_order set is_ship = '1' where order_id = ?";
            template.update(sql,oid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//更新用户收货状态
    @Override
    public void updateOrderIsReceipt(String oid) {
        try {
            String sql = "update tb_order set is_receipt = '1' where order_id = ?";
            template.update(sql,oid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//删除订单
    @Override
    public void delOrderById(String oid) {
        try {
            String sql = "delete from tb_order where order_id = ?";
            template.update(sql,oid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

}
