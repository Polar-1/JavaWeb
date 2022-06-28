package servlet.product;

import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delProductServlet")
public class DelProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //获取商品编号参数
        int productId = Integer.valueOf(request.getParameter("productId"));

        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中delProductById方法
        service.delProductById(productId);
        //请求跳转到相对路径到productListServlet
        response.sendRedirect(request.getContextPath()+"/productListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
