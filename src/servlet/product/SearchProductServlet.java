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
        request.setAttribute("productList",list);


        //查询购物车数量
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");

        String cartCount = "0";
        CartService service1 = new CartServiceImpl();
        if (user != null) {
            cartCount = String.valueOf(service1.findCartCountByUserId(user.getUser_id()));
        }else {
            cartCount = "?";
        }
        request.setAttribute("cartCount",cartCount);

        CategoryService service2 = new CategoryServiceImpl();
        List<Category> flist =  service2.findCategoryListByName("father");
        request.setAttribute("flist", flist);
        List<Category> clist =  service2.findCategoryListByName("child");
        request.setAttribute("clist", clist);


        //没实现分类添加图片
        request.setAttribute("link", "img/banner1.jpg");


        request.getRequestDispatcher("searchproductlist.jsp").forward(request, response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
