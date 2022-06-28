package servlet.cart;

import entity.Cart;
import entity.Product;
import entity.User;
import service.CartService;
import service.ProductService;
import service.impl.CartServiceImpl;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/addCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("name");
        if (user != null) {

            //获取商品id
            //获取数量
            String pid = request.getParameter("pid");
            int pid2 = Integer.valueOf(pid);
            String count = request.getParameter("count");
            int count2 = Integer.valueOf(count);

            //实例化对象 调用findProductById方法
            ProductService service = new ProductServiceImpl();
            Product p = service.findProductById(pid2);

            //获取当前用户id，商品名称，商品价格，数量，商品风格，商品图片
            Cart cart = new Cart(pid2,user.getUser_id(),p.getProduct_name(),p.getProduct_price(),count2,"0",p.getProduct_photo());

            //创建购物车服务层对象 查询该购物车后传到前台
            //实例化service层中CartService对象
            CartService service2 = new CartServiceImpl();
            service2.addCart(cart);


            request.getRequestDispatcher("toCartServlet").forward(request, response);
        }else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
