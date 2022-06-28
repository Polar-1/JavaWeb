package servlet.product;

import entity.Category;
import entity.PageBean;
import entity.Product;

import service.CategoryService;
import service.ProductService;

import service.impl.CategoryServiceImpl;
import service.impl.ProductServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//后端管理显示所有商品信息
@WebServlet("/productListServlet")
public class ProductListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获得分页参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }

        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中findProductByPage方法
        PageBean<Product> pb = service.findProductByPage(currentPage,rows);
        //存值
        request.setAttribute("pb",pb );

        //实例化service层中CategoryService对象
        CategoryService service2 = new CategoryServiceImpl();
        //调用service层中findCategoryListByname方法
        List<Category> f = service2.findCategoryListByName("father");
        List<Category> c = service2.findCategoryListByName("child");
        //存值
        request.setAttribute("flist", f);
        request.setAttribute("clist", c);

        //请求重定向到admin_product.jsp页面
        request.getRequestDispatcher("admin_product.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
