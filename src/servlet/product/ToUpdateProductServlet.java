package servlet.product;

import entity.Category;
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

@WebServlet("/toUpdateProductServlet")
public class ToUpdateProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        //获取商品id的参数值
        String productId = request.getParameter("productId");
        System.out.println(productId);
        int pId = Integer.valueOf(productId);

        //创建商品服务层对象 查询该商品后传到前台
        //实例化service层中ProductService对象
        ProductService service = new ProductServiceImpl();
        //调用service层中findProductById方法
        Product product = service.findProductById(pId);
        request.setAttribute("p",product);

        //创建分类服务层对象 查询分类列表后传到前台
        CategoryService service1 = new CategoryServiceImpl();
        List<Category> flist = service1.findCategoryListByName("father");
        List<Category> clist = service1.findCategoryListByName("child");
        request.setAttribute("flist",flist);
        request.setAttribute("clist",clist);

        //请求重定向到admin_productupdate.jsp页面
        request.getRequestDispatcher("admin_productupdate.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
