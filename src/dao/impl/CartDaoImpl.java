package dao.impl;

import dao.CartDao;
import entity.Cart;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.List;

public class CartDaoImpl implements CartDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
//根据用户id查询所有购物车数量
    @Override
    public int findCartCountByUserId(int user_id) {
        try {
            String sql = "select count(*) from tb_cart where user_id = ?";
            return template.queryForObject(sql,Integer.class,user_id);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return 0;
        }

    }
//根据id查询购物车清单
    @Override
    public List<Cart> findCartListByUserId(int i) {
        try {
            String sql = "select * from tb_cart where user_id = ?";
            List<Cart> c = template.query(sql, new BeanPropertyRowMapper<Cart>(Cart.class),i);
            return c;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//添加购物车
    @Override
    public void addCart(Cart c) {
        try {
            String sql = "insert into tb_cart(product_id,user_id,product_name,product_price,product_quantity,product_style,product_photo) values(?,?,?,?,?,?,?)";
            template.update(sql,c.getProduct_id(),c.getUser_id(),c.getProduct_name(),c.getProduct_price(),c.getProduct_quantity(),c.getProduct_style(),c.getProduct_photo());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
