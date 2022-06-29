package servlet.home;

import entity.Category;
import entity.Product;
import entity.User;
import service.CartService;
import service.CategoryService;
import service.ProductService;
import service.impl.CartServiceImpl;
import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询该商品的二级和一级分类
        CategoryService service = new CategoryServiceImpl();
        List<Category> flist =  service.findCategoryListByName("father");
        request.setAttribute("flist", flist);
        List<Category> clist =  service.findCategoryListByName("child");
        request.setAttribute("clist", clist);

        //创建产品服务层对象 查询该产品后传到前台
        ProductService service1 = new ProductServiceImpl();
        //实例化service层中findProductTimeSort对象
        List<Product> newProductList = service1.findProductTimeSort();
        //实例化service层中findProductSalesSort对象
        List<Product> newProductList2 = service1.findProductSalesSort();

        request.setAttribute("newProductList", newProductList);
        request.setAttribute("newProductList2", newProductList2);

        //查询购物车数量
        HttpSession session = request.getSession();
        //获取用户姓名
        User user = (User) session.getAttribute("name");

        //初始时，购物车为空
        String cartCount = "0";
        //实例化service层中CartService对象
        CartService service2 = new CartServiceImpl();
        if (user != null) {
            //调用service层中的findCartCountByUserId方法获取指定userid的购物车数量
            cartCount = String.valueOf(service2.findCartCountByUserId(user.getUser_id()));
        }else {
            cartCount = "?";
        }
        //存值
        request.setAttribute("cartCount",cartCount);

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
