package servlet.cart;

import entity.Cart;
import entity.Category;
import entity.User;
import service.CartService;
import service.CategoryService;
import service.impl.CartServiceImpl;
import service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/toCartServlet")
public class ToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");

        if (user != null) {

            //查询该商品的二级和一级分类
            CategoryService service1 = new CategoryServiceImpl();
            List<Category> flist = service1.findCategoryListByName("father");
            List<Category> clist = service1.findCategoryListByName("child");
            request.setAttribute("flist",flist);
            request.setAttribute("clist",clist);

            //创建购物车服务层对象 查询该购物车后传到前台
            //实例化service层中CartService对象
            //调用findCartListByUserId方法
            CartService service = new CartServiceImpl();
            List<Cart> cartList= service.findCartListByUserId(user.getUser_id());
            request.setAttribute("cartList",cartList);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
