package servlet.product;

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
import java.util.List;

@WebServlet("/searchProductServlet")
public class SearchProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文乱码问题
        request.setCharacterEncoding("utf-8");
        //获得关键字参数
        String key = request.getParameter("key");
        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中findProductListBykey方法
        List<Product> list = service.findProductListByKey(key);
        //存入productList属性并赋值list
        request.setAttribute("productList",list);


        //查询购物车数量
        HttpSession session = request.getSession();
        //获取用户姓名
        User user = (User) session.getAttribute("name");
        //初始时，购物车为空
        String cartCount = "0";
        //实例化service层中CartService对象
        CartService service1 = new CartServiceImpl();
        if (user != null) {
            //调用service层中的findCartCountByUserId方法获取指定userid的购物车数量
            cartCount = String.valueOf(service1.findCartCountByUserId(user.getUser_id()));
        }else {
            cartCount = "?";
        }
        //存值
        request.setAttribute("cartCount",cartCount);

        //实例化service层中CategoryService对象
        CategoryService service2 = new CategoryServiceImpl();
        //调用service层中findCategoryListByname方法
        List<Category> flist =  service2.findCategoryListByName("father");
        //存值
        request.setAttribute("flist", flist);
        List<Category> clist =  service2.findCategoryListByName("child");
        request.setAttribute("clist", clist);

        //*父类图片
        request.setAttribute("link", "img/banner1.jpg");

        //请求重定向跳转为searchproductlist.jsp
        request.getRequestDispatcher("searchproductlist.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
