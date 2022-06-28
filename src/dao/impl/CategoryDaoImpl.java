package dao.impl;

import dao.CategoryDao;
import entity.Category;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //商品分类
    @Override
    public List<Category> findCategoryParentList() {
        try {
            String sql = "select * from tb_category where category_parentid=0";
            List<Category> c = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
            return c;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//商品细分分类
    @Override
    public List<Category> findCategoryChildList() {
        try {
            String sql = "select * from tb_category where category_parentid!=0";
            List<Category> c = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
            return c;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
//查询所有分类
    @Override
    public List<Category> findAllCategory() {
        try {
            String sql = "select * from tb_category";
            List<Category> c = template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
            return c;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    //通过子类找父类，未实现
    @Override
    public Category findCategoryByCid(int productCid) {
        try {
            String sql = "select * from tb_category where category_id = ?";
            Category category = template.queryForObject(sql,new BeanPropertyRowMapper<Category>(Category.class),productCid);
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
//添加
    @Override
    public void addCategory(Category category) {
        try {
            String sql = "insert into tb_category(category_name,category_parentid) values(?,?)";
            template.update(sql,category.getCategory_name(),category.getCategory_parentid());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//删除子类
    @Override
    public void delCategoryById(int cid2) {
        try {
            String sql = "delete from tb_category where category_id = ?";
            template.update(sql,cid2);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
//修改商品分类名称
    @Override
    public void updateCategoryName(String categoryName, int categoryId) {
        try {
            String sql = "update tb_category set category_name = ? where category_id = ?";
            template.update(sql,categoryName,categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
